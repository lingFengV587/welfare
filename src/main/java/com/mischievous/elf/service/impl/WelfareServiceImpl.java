package com.mischievous.elf.service.impl;

import com.mischievous.elf.constant.FileConstant;
import com.mischievous.elf.constant.WelfareConstant;
import com.mischievous.elf.dto.LotteryRecordDto;
import com.mischievous.elf.service.WelfareService;
import com.mischievous.elf.utils.DateUtils;
import com.mischievous.elf.utils.FileUtils;
import com.mischievous.elf.utils.GloubFunc;
import com.mischievous.elf.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lifang
 */
@Service("welfareService")
public class WelfareServiceImpl implements WelfareService {
    private static final Logger logger = LoggerFactory.getLogger(WelfareServiceImpl.class);

    private static final int batchSize = 20;

    @Override
    public int insertHistoryRecord() throws Exception {
        int count, totalCount = 0;
        try {
            String localFilePath = this.convertLocalFilePath(FileConstant.FILE_PATH);
            String fileUrl = localFilePath + WelfareConstant.NEWEST_RECORD_TXT;
            this.convertLocalFilePath(fileUrl);
            String newestRecord = FileUtils.readFileOneLine(fileUrl, 0);
            if (GloubFunc.isEmpty(newestRecord)) {
                return totalCount;
            }
            int newestRecordNum = Integer.parseInt(newestRecord.split(",")[0]);
            logger.warn("==insertHistoryRecord==最后记录期号：{}", newestRecordNum);

            do {
                count = this.insertHistoryRecord(newestRecordNum + 1, newestRecordNum + batchSize);
                totalCount += count;
                newestRecordNum += count;
            } while (batchSize <= count);
        } catch (Exception e) {
            logger.error("==insertHistoryRecord异常==", e);
            throw e;
        }
        return totalCount;
    }

    @Override
    public int insertHistoryRecord(int fromNum, int toNum) throws Exception {
        List<LotteryRecordDto> lotteryList = null;
        try {
            String httpUrl = WelfareConstant.SSQ_SEARCH_URL + "?start=" + fromNum + "&end=" + toNum;
            String xmlContent = HttpClientUtils.doGet(httpUrl);
            if (GloubFunc.isEmpty(xmlContent)) {
                return 0;
            }
            lotteryList = this.analysisXMLBySelf(xmlContent);
            if (CollectionUtils.isEmpty(lotteryList)) {
                return 0;
            }

            StringBuffer fileContent = new StringBuffer();
            List<String> contentList = new ArrayList<>();
            for (int j = lotteryList.size()-1; j>=0; j--) {
                LotteryRecordDto l = lotteryList.get(j);
                fileContent.append(l.toString()).append("\n");
                contentList.add(l.toString());
            }

            String localFilePath = this.convertLocalFilePath(FileConstant.FILE_PATH);
            FileUtils.writeFileContent(localFilePath + WelfareConstant.HISTORY_RECORD_TXT
                    , fileContent.toString(), true);
            FileUtils.writeFileContent(localFilePath + WelfareConstant.NEWEST_RECORD_TXT
                    , lotteryList.get(0).toString(), false);
            FileUtils.writeExcelContent(localFilePath + WelfareConstant.STATISTICS_EXCEL
                    , FileConstant.STATISTICS_EXCEL_SHEET, contentList);
        } catch (Exception e) {
            logger.error("==insertHistoryRecord异常==", e);
            throw e;
        }
        return lotteryList.size();
    }

    @Override
    public LotteryRecordDto queryNewestRecord() throws Exception {
        LotteryRecordDto lottery = null;
        try {
            String localFilePath = this.convertLocalFilePath(FileConstant.FILE_PATH);
            String fileUrl = localFilePath + WelfareConstant.NEWEST_RECORD_TXT;
            String newestRecord = FileUtils.readFileOneLine(fileUrl, 0);
            if (GloubFunc.notEmpty(newestRecord)) {
                lottery = this.convertStringToRecord(newestRecord);
            }
        } catch (Exception e) {
            logger.error("==queryNewestRecord异常==", e);
            throw e;
        }
        return lottery;
    }

    /**
     * 解析页面数据
     *
     * @param xmlContent
     * @return
     * @throws Exception
     */
    private List<LotteryRecordDto> analysisXMLBySelf(String xmlContent) throws Exception {
        List<LotteryRecordDto> lotteryList = new ArrayList<LotteryRecordDto>();
        xmlContent = xmlContent.split("<tbody id=\"tdata\">")[1]
                .split("</tbody>")[0]
                .replace("&nbsp;","");
        String[] trList = xmlContent.split("</tr>");
        int trListLength = trList.length;
        if (trListLength == 0) {
            return lotteryList;
        }

        for (int i = 0; i < trListLength; i++) {
            String tr = trList[i].trim();
            if (!tr.contains("</td>")) {
                continue;
            }
            String[] tdList = tr.split("</td>");
            LotteryRecordDto lottery = new LotteryRecordDto();

            String[] textContent1 = tdList[1].split(">");
            lottery.setSeries(Integer.parseInt(textContent1[textContent1.length - 1]));

            String[] textContent2 = tdList[2].split(">");
            lottery.setFirstBall(textContent2[textContent2.length - 1]);

            String[] textContent3 = tdList[3].split(">");
            lottery.setSecondBall(textContent3[textContent3.length - 1]);

            String[] textContent4 = tdList[4].split(">");
            lottery.setThirdBall(textContent4[textContent4.length - 1]);

            String[] textContent5 = tdList[5].split(">");
            lottery.setFourthBall(textContent5[textContent5.length - 1]);

            String[] textContent6 = tdList[6].split(">");
            lottery.setFifthBall(textContent6[textContent6.length - 1]);

            String[] textContent7 = tdList[7].split(">");
            lottery.setSixthBall(textContent7[textContent7.length - 1]);

            String[] textContent8 = tdList[8].split(">");
            lottery.setBlueBall(textContent8[textContent8.length - 1]);

            String[] textContent16 = tdList[16].split(">");
            lottery.setLotteryDate(textContent16[textContent16.length - 1]);

            lotteryList.add(lottery);
        }
        return lotteryList;
    }

    /**
     * 转换开奖记录类型
     *
     * @param record
     * @return
     * @throws Exception
     */
    private LotteryRecordDto convertStringToRecord(String record) throws Exception {
        LotteryRecordDto lottery = new LotteryRecordDto();
        if (GloubFunc.isEmpty(record)) {
            return lottery;
        }
        String[] recordList = record.split(",");
        lottery.setSeries(Integer.parseInt(recordList[0]));
        lottery.setFirstBall(recordList[2]);
        lottery.setSecondBall(recordList[3]);
        lottery.setThirdBall(recordList[4]);
        lottery.setFourthBall(recordList[5]);
        lottery.setFifthBall(recordList[6]);
        lottery.setSixthBall(recordList[7]);
        lottery.setBlueBall(recordList[8]);
        lottery.setLotteryDate(recordList[1]);
        lottery.setCreateTime(DateUtils.getDate(recordList[1], DateUtils.YYYY_MM_DD));

        return lottery;
    }

    private String convertLocalFilePath(String filePath) throws Exception {
        String localPath = getClass()
                .getClassLoader()
                .getResource("/")
                .toString();
        localPath = URLDecoder.decode(localPath, "utf-8");

        int index = localPath.indexOf("WEB-INF");
        if(index == -1){
            index = localPath.indexOf("classes");
        }
        if(index == -1){
            index = localPath.indexOf("bin");
        }
        localPath = localPath.substring(0, index);

        if(localPath.startsWith("zip")){
            //当class文件在war中时，此时返回zip:D:/...这样的路径
            localPath = localPath.substring(4);
        }else if(localPath.startsWith("file")){
            //当class文件在class文件中时，此时返回file:/D:/...这样的路径
            localPath = localPath.substring(6);
        }else if(localPath.startsWith("jar")){
            //当class文件在jar文件里面时，此时返回jar:file:/D:/...这样的路径
            localPath = localPath.substring(10);
        }

        return localPath + filePath;
    }
}
