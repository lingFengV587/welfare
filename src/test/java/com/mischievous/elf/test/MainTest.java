package com.mischievous.elf.test;


import com.mischievous.elf.dto.LotteryRecordDto;
import com.mischievous.elf.service.WelfareService;
import com.mischievous.elf.service.impl.WelfareServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class MainTest {
//    @Autowired
//    WelfareService welfareService;

    public static void main(String args[]) {
        ApplicationContext applicationContext = new XmlWebApplicationContext();
    }

    @Test
    public void testBatchInput() {
        try {
            WelfareService welfareService = new WelfareServiceImpl();
            int count = welfareService.insertHistoryRecord();
            System.out.println(count);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testQueryNewestRecord() {
        try {
            WelfareService welfareService = new WelfareServiceImpl();
            LotteryRecordDto recordDto = welfareService.queryNewestRecord();
            System.out.println(recordDto.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
