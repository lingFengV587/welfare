package com.mischievous.elf.controller;

import com.alibaba.fastjson.JSON;
import com.mischievous.elf.constant.FileConstant;
import com.mischievous.elf.constant.WelfareConstant;
import com.mischievous.elf.dto.LotteryRecordDto;
import com.mischievous.elf.service.WelfareService;
import com.mischievous.elf.utils.FileUtils;
import com.mischievous.elf.utils.GloubFunc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            String newestRecord = null == recordDto ? "": recordDto.toString();


            logger.warn("==queryNewestRecord==record:{}", newestRecord);

            //??????response???????????????????????????response????????????????????????????????????
            response.setCharacterEncoding("UTF-8");
            //???????????????????????????????????????????????????????????????
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().write(newestRecord);
        } catch (Exception e) {
            logger.error("==queryNewestRecord??????==", e);
        }
    }


    @RequestMapping(value = "/insertHistoryRecord")
    public void insertHistoryRecord(HttpServletResponse response) {
        try {
            int count = welfareService.insertHistoryRecord();
            logger.warn("==insertHistoryRecord??????==count???{}", count);

            //??????response???????????????????????????response????????????????????????????????????
            response.setCharacterEncoding("UTF-8");
            //???????????????????????????????????????????????????????????????
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().write(count);
        } catch (Exception e) {
            logger.error("==insertHistoryRecord??????==", e);
        }
    }
}
