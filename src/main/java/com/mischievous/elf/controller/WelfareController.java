package com.mischievous.elf.controller;

import com.mischievous.elf.dto.LotteryRecordDto;
import com.mischievous.elf.service.WelfareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lifang
 */
@Controller
@RequestMapping(value = "/welfare")
public class WelfareController {
    private static final Logger logger = LoggerFactory.getLogger(WelfareController.class);

    @Autowired
    WelfareService welfareService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        logger.warn("===sayHello=");
        return "hello";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String toMainPage() {
        return "welfareMain";
    }

    @RequestMapping(value = "/queryNewestRecord")
    public void queryNewestRecord(HttpServletRequest request, HttpServletResponse response) {
        try {
//            String realPath = request.getSession()
//                    .getServletContext()
//                    .getRealPath("/");
//            String localFilePath = realPath + "WEB-INF/static/doc/newestRecord.txt";
//            String newestRecord = FileUtils.readFileOneLine(localFilePath, 0);
            LotteryRecordDto recordDto = welfareService.queryNewestRecord();
            String newestRecord = null == recordDto ? "" : recordDto.toString();


            logger.warn("==queryNewestRecord==record:{}", newestRecord);

            //设置response使用的码表，以控制response以什么码表向浏览器写数据
            response.setCharacterEncoding("UTF-8");
            //指定浏览器以什么码表去读取传输过来的数据，
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().write(newestRecord);
        } catch (Exception e) {
            logger.error("==queryNewestRecord异常==", e);
        }
    }


    @RequestMapping(value = "/insertHistoryRecord")
    public void insertHistoryRecord(HttpServletResponse response) {
        try {
            int count = welfareService.insertHistoryRecord();
            logger.warn("==insertHistoryRecord结束==count：{}", count);

            //设置response使用的码表，以控制response以什么码表向浏览器写数据
            response.setCharacterEncoding("UTF-8");
            //指定浏览器以什么码表去读取传输过来的数据，
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().write(count);
        } catch (Exception e) {
            logger.error("==insertHistoryRecord异常==", e);
        }
    }
}
