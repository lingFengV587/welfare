package com.mischievous.elf.service;

import com.mischievous.elf.dto.LotteryRecordDto;

/**
 * @author lifang
 * @version 1.0
 * @CreateDate 2020/1/10
 */
public interface WelfareService {

    /**
     * 批量插入历史开奖号码
     *
     * @return
     * @throws Exception
     */
    int insertHistoryRecord() throws Exception;


    /**
     * 批量导入历史记录
     * 由于网站一次性返回数据大小限制，toNum-fromNum<30
     *
     * @param fromNum 起始期号
     * @param toNum   结束期号
     * @return
     */

    int insertHistoryRecord(int fromNum, int toNum) throws Exception;

    /**
     * 查询最新一期开奖记录
     *
     * @return
     * @throws Exception
     */
    LotteryRecordDto queryNewestRecord() throws Exception;
}
