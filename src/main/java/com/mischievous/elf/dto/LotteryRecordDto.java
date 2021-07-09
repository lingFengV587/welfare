package com.mischievous.elf.dto;

import java.util.Date;

/**
 * @author lifang
 * @version 1.0
 * @CreateDate 2020/1/10
 */
public class LotteryRecordDto {
    private int series;
    private String lotteryDate;

    private String firstBall;
    private String secondBall;
    private String thirdBall;
    private String fourthBall;
    private String fifthBall;
    private String sixthBall;

    private String blueBall;
    private String happySunday;

    private Date createTime;

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(String lotteryDate) {
        this.lotteryDate = lotteryDate;
    }

    public String getFirstBall() {
        return firstBall;
    }

    public void setFirstBall(String firstBall) {
        this.firstBall = firstBall;
    }

    public String getSecondBall() {
        return secondBall;
    }

    public void setSecondBall(String secondBall) {
        this.secondBall = secondBall;
    }

    public String getThirdBall() {
        return thirdBall;
    }

    public void setThirdBall(String thirdBall) {
        this.thirdBall = thirdBall;
    }

    public String getFourthBall() {
        return fourthBall;
    }

    public void setFourthBall(String fourthBall) {
        this.fourthBall = fourthBall;
    }

    public String getFifthBall() {
        return fifthBall;
    }

    public void setFifthBall(String fifthBall) {
        this.fifthBall = fifthBall;
    }

    public String getSixthBall() {
        return sixthBall;
    }

    public void setSixthBall(String sixthBall) {
        this.sixthBall = sixthBall;
    }

    public String getBlueBall() {
        return blueBall;
    }

    public void setBlueBall(String blueBall) {
        this.blueBall = blueBall;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHappySunday() {
        return happySunday;
    }

    public void setHappySunday(String happySunday) {
        this.happySunday = happySunday;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(series))
                .append(",").append(lotteryDate)
                .append(",").append(firstBall)
                .append(",").append(secondBall)
                .append(",").append(thirdBall)
                .append(",").append(fourthBall)
                .append(",").append(fifthBall)
                .append(",").append(sixthBall)
                .append(",").append(blueBall);
        return sb.toString();
    }
}
