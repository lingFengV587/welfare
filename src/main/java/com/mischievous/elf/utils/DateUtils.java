package com.mischievous.elf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DateUtils
{

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_IN_CHINESE = "YYYY年MM月";

    public static final String YYYY_MM_DD_IN_CHINESE = "yyyy年MM月dd日";

    public static final String YYYY_MM_DD_HH_MM_SS_IN_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_PLUS = "yyyy-MM-dd+HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss SSS";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS_DOT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String YYYY_MM_DD_HH_MM_SS_SLASH = "yyyy/MM/dd HH:mm:ss";

    public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";

    public static final String YYYY_MM_DD_DOT = "yyyy.MM.dd";

    public static final String YYYYMMDD_HH_MM_SS = "yyyyMMdd HH:mm:ss";

    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String HH_MM_SS = "HH:mm:ss";

    /**
     * 存储SimpleDateFormat对应格式发的String类型
     */
    private static Map<String, SimpleDateFormat> map = new HashMap<String, SimpleDateFormat>();

    /**
     * 间隔天数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 相差天数
     */
    public static int betweenDays(Date beginDate, Date endDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);

        try
        {
            beginDate = sdf.parse(sdf.format(beginDate));
            endDate = sdf.parse(sdf.format(endDate));
        }
        catch (ParseException e)
        {
            log.error("", e);
            return 0;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        long minTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long maxTime = cal.getTimeInMillis();
        long betweenDays = (maxTime - minTime) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 日期加N天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDayas(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        Date newDate = cal.getTime();
        return newDate;
    }


    /**
     * 日期加N天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDayasEnd(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        Date newDate = cal.getTime();
        return newDate;
    }

    /**
     * 日期加N月
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(Date date, int months)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        Date newDate = cal.getTime();
        return newDate;
    }

    /**
     * 日期加N月后取第一天0点
     *
     * @param date
     * @param months
     * @return
     */
    public static String addMonthBegin(Date date, int months) {
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date newDate = cal.getTime();
        String datestr = df.format(newDate);
        return datestr;
    }

    /**
     * 获取n分钟后的时间
     *
     * @param n 可以是负数
     *          ，负数的话表示n小时前
     * @return
     * @author liangyi
     */
    public static Timestamp getNMinutes(int n)
    {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MINUTE, n);
        Timestamp nMinutesLater = new Timestamp(calender.getTimeInMillis());

        return nMinutesLater;
    }

    /**
     * 获取n秒后的时间
     * @param n
     * @return
     */
    public static Timestamp getNSecond(int n)
    {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.SECOND, n);
        Timestamp nMinutesLater = new Timestamp(calender.getTimeInMillis());

        return nMinutesLater;
    }


    /**
     * 将指定格式的日期字符串转成Timestamp
     *
     * @param time
     * @param pattern
     * @return
     * @author mayt
     */
    public static Timestamp getTimestamp(String time, String pattern)
            throws Exception
    {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        Timestamp ts = new Timestamp(format.parse(time).getTime());
        ;
        return ts;
    }

    /**
     * 将指定格式的日期字符串转成Date
     *
     * @param dateStr
     * @param pattern
     * @return
     * @throws Exception
     * @author liangyi
     */
    public static Date getDate(String dateStr, String pattern)
            throws Exception
    {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        Date date = new Date(format.parse(dateStr).getTime());
        return date;
    }

    /**
     * 获取系统当前时间
     *
     * @return
     * @author liangyi
     */
    public static Timestamp getSysDate()
    {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time;
    }

    /**
     * 根据指定的格式输入时间字符串
     *
     * @param pattern
     * @return
     * @author liangyi
     */
    public static String getDateString(String pattern)
    {
        Timestamp time = getSysDate();
        DateFormat dfmt = new SimpleDateFormat(pattern);
        Date date = time;
        return dfmt.format(date);
    }

    /**
     * 根据指定的格式输入时间字符串
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String getDateString(Timestamp time, String pattern)
    {
        DateFormat dfmt = new SimpleDateFormat(pattern);
        Date date = time;
        return dfmt.format(date);
    }

    /**
     * 根据指定的格式输入时间字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateString(Date date, String pattern)
    {
        DateFormat dfmt = new SimpleDateFormat(pattern);
        return dfmt.format(date);
    }

    /**
     * 获取n天后的时间
     *
     * @param n 可以是负数，负数的话表示n天前
     * @return
     * @author liangyi
     */
    public static Timestamp getNDays(Date date, int n)
    {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        calender.add(Calendar.DATE, n);
        Timestamp nMinutesLater = new Timestamp(calender.getTimeInMillis());

        return nMinutesLater;
    }

    /**
     * 获取日期date的n天后的时间
     *
     * @param n 可以是负数，负数的话表示n天前
     * @return
     * @author liangyi
     */
    public static Timestamp getNDays(int n)
    {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, n);
        Timestamp nMinutesLater = new Timestamp(calender.getTimeInMillis());

        return nMinutesLater;
    }


    /**
     * 获取n 月后的日期
     * @param n
     * @return
     */
    public static Timestamp getNMonths(int n)
    {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MONTH, n);
        Timestamp nMinutesLater = new Timestamp(calender.getTimeInMillis());

        return nMinutesLater;
    }

    public String getDate(int month){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH,month);//对月份进行计算,减去12个月
        Date date=cal.getTime();
        return df.format(date);

    }

    /**
     * 获取从startDate 到 endDate之间的日期列表
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     * @author liangyi
     */
    public static List<String> getDates(Timestamp startDate, Timestamp endDate, String pattern)
            throws ParseException
    {
        List<String> dates = new LinkedList<String>();

        Calendar start = Calendar.getInstance();

        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        if (pattern == null)
        {
            pattern = YYYYMMDD;
        }
        DateFormat dfmt = new SimpleDateFormat(pattern);
        while (!start.after(end))
        {
            dates.add(dfmt.format(start.getTime()));
            start.add(Calendar.DAY_OF_YEAR, 1);
        }

        return dates;

    }

    /**
     * 转为时间hhmmss格式
     *
     * @param seconds  秒数
     * @param separate 分隔符，在hh、mm、ss之间的分隔符，如:或空等
     * @return
     * @author liangyi
     */
    public static String convertSecond2HHmmss(int seconds, String separate)
    {

        int rest = seconds;

        int hour = 0;//小时数
        int min = 0;//分钟数
        int sec = 0;//秒数

        hour = rest / 3600;
        rest = rest % 3600;

        min = rest / 60;
        rest = rest % 60;

        sec = rest;

        String result = (hour < 10 ? "0" + hour : hour) + separate + (min < 10 ? "0" + min : min) + separate +
                (sec < 10 ? "0" + sec : sec);

        return result;

    }

    /**
     * 获取srcDate以后（以前）的N个月
     *
     * @param srcDate
     * @param n       可以是负数
     * @return
     * @author liangyi
     */
    public static List<Date> getNMonthFrom(Date srcDate, int n)
    {
        if (srcDate == null)
        {
            return null;
        }
        List<Date> list = new ArrayList<>(Math.abs(n) + 1);

        if (n == 0)
        {
            list.add(srcDate);
        }
        else if (n > 0)
        {
            //后N个月
            list.add(srcDate);

            Calendar calender = Calendar.getInstance();
            for (int i = 1; i <= n; i++)
            {
                calender.setTime(srcDate);
                calender.add(Calendar.MONTH, i);
                Date _1MonthLater = new Date(calender.getTimeInMillis());
                list.add(_1MonthLater);
            }
        }
        else
        {
            //前N个月
            Calendar calender = Calendar.getInstance();
            for (int i = n; i < 0; i++)
            {
                calender.setTime(srcDate);
                calender.add(Calendar.MONTH, i);
                Date _1MonthEarlier = new Date(calender.getTimeInMillis());
                list.add(_1MonthEarlier);
            }

            list.add(srcDate);
        }

        return list;
    }

    /**
     * 获取系统时间
     *
     * @param format 14位:yyyyMMddHHmmss,19位：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getSysdate(String format)
    {
        Date now = new Date();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(now);
    }

    /**
     * 通过时间格式化字符串获取SimpleDateFormat
     *
     * @param parsePatterns 时间格式化
     * @return {@link SimpleDateFormat}
     */
    public static SimpleDateFormat getSimpleDateFormat(final String parsePatterns)
    {
        SimpleDateFormat dateFormat = null;
        if (map.containsKey(parsePatterns))
        {
            dateFormat = map.get(parsePatterns);
        }
        else
        {
            dateFormat = new SimpleDateFormat(parsePatterns);
            map.put(parsePatterns, dateFormat);
        }
        return dateFormat;
    }

    /**
     * 格式化时间，返回格式化后的时间字符串
     * <p>
     * <pre>
     * 例子,如有一个Date = 2012-08-09:
     * DateUtils.format(date,"yyyy-MM-dd") = "2012-08-09"
     * DateUtils.format(date,"yyyy年MM月dd日") = "2012年08月09日"
     * DateUtils.format(date,"") = null
     * DateUtils.format(date,null) = null
     * </pre>
     *
     * @param date          时间
     * @param parsePatterns 格式化字符串
     * @return String
     */
    public static String format(final Date date, final String parsePatterns)
    {
        if (StringUtils.isEmpty(parsePatterns) || date == null)
        {
            return null;
        }
        return getSimpleDateFormat(parsePatterns).format(date);
    }

    /**
     * 简单转换日期类型到默认字符串格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @param date 日期
     * @return String 格式化好的日期字符串 "yyyy-MM-dd HH:mm:ss"
     */
    public static String format(final Date date)
    {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 根据周期获取两个时间段内的天数
     *
     * @param date
     * @param py
     * @param cycle
     * @return
     */
    public static int getDaysByRule(Date date, int py, int cycle)
    {

        Date newDate = new Date();
        //周
        if (cycle == 2)
        {

            newDate = addDayas(date, 7*py);
        }

        //月
        else if (cycle == 3)
        {

            newDate = addMonth(date, py);
        }

        else
        {
            return py;
        }

        return betweenDays(date, newDate);
    }




    /**
     * 判断某个时间time1是否在另一个时间time2之前
     * @param time1 第一个时间
     * @param time2 第二个时间
     * @return 时间time1是否在另一个时间time2之前
     */
    public static boolean beforeTime(final Date time1, final Date time2) {
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);

        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);

        return calendar1.before(calendar2);
    }

    /**
     * 返回下个月最后一秒的时间
     * @return
     * @throws Exception
     */
    public  static Date getNextMonthLastTime(int end) throws Exception{

        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, end+1);
        calendar.set(Calendar.DATE, 0);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1,YYYY_MM_DD);
        yyyymmdd += " 23:59:59";

        date1 = getDate(yyyymmdd, YYYY_MM_DD_HH_MM_SS);

        return  date1;

    }

    /**
     * 返回上n个月最后一秒的时间
     * @return
     * @throws Exception
     */
    public  static Date getLastMonthLastTime(int end) throws Exception{

        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 2-end);
        calendar.set(Calendar.DATE, -1);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1,YYYY_MM_DD);
        yyyymmdd += " 23:59:59";

        date1 = getDate(yyyymmdd, YYYY_MM_DD_HH_MM_SS);

        return  date1;

    }

    /**
     *
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }



    public static String[] getSeasonDate(Date date) {
        String[] season = new String[2];
        int nSeason = getSeason(date);
        if (nSeason == 1) {
            // 第一季度
            season[0] = GloubFunc.now("yyyy")+"01";
            season[1] = GloubFunc.now("yyyy")+"03";
        } else if (nSeason == 2) {
            // 第二季度
            season[0] = GloubFunc.now("yyyy")+"04";
            season[1] = GloubFunc.now("yyyy")+"06";
        } else if (nSeason == 3) {
            // 第三季度
            season[0] = GloubFunc.now("yyyy")+"07";
            season[1] = GloubFunc.now("yyyy")+"09";
        } else if (nSeason == 4) {
            // 第四季度
            season[0] = GloubFunc.now("yyyy")+"10";
            season[1] = GloubFunc.now("yyyy")+"12";
        }
        return season;
    }

    public static int getDayOfMonth()
    {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取上一个月月份
     * @return
     */
    public static String getBeforeMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
        String time = format.format(calendar.getTime());
        return time;
    }

    /**
     * 获取指定日期的上一个月份
     * @return
     */
    public static String getBeforeMonthWithTragetDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
        String time = format.format(calendar.getTime());
        return time;
    }

    /**
     * 获得当月月底最后一秒钟的时间（格式：yyyy-MM-dd HH:mm:ss）
     *
     * @return
     * @throws Exception
     */
    public static Date getLastSecondOfCurrentMonth()
            throws Exception
    {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH,now.getActualMaximum(Calendar.DAY_OF_MONTH));
        //设置每天的最大小时
        now.set(Calendar.HOUR_OF_DAY, now.getActualMaximum(Calendar.HOUR_OF_DAY));
        //设置每小时最大分钟
        now.set(Calendar.MINUTE, now.getActualMaximum(Calendar.MINUTE));
        //设置每分钟最大秒
        now.set(Calendar.SECOND, now.getActualMaximum(Calendar.SECOND));
        return now.getTime();
    }

    public static int getAge(String idCard) {
        int idxSexStart = 16;
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if(idCard.length() == 15) {
            idxSexStart = 14;
            birthYearSpan = 2;
        }

        //出生日期
        String year = (birthYearSpan == 2 ? "19" : "") + idCard.substring(6, 6 + birthYearSpan);
        String month = idCard.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = idCard.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
        String birthday = year + '-' + month + '-' + day;

        //年龄
        Calendar certificateCal = Calendar.getInstance();
        Calendar currentTimeCal = Calendar.getInstance();
        certificateCal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
        int yearAge = (currentTimeCal.get(currentTimeCal.YEAR)) - (certificateCal.get(certificateCal.YEAR));
        certificateCal.set(currentTimeCal.get(Calendar.YEAR), Integer.parseInt(month)-1, Integer.parseInt(day));
        int monthFloor = (currentTimeCal.before(certificateCal) ? 1 : 0);

        int age = yearAge - monthFloor;
        return age;
    }

    public static String getSex(String idCard) {
        if(GloubFunc.isEmpty(idCard)
                || idCard.length() < 15) {
            // 默认给1
            return "1";
        }

        int idxSexStart = 16;
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if(idCard.length() == 15) {
            idxSexStart = 14;
            birthYearSpan = 2;
        }

        //性别
        String idxSexStr = idCard.substring(idxSexStart, idxSexStart + 1);
        int idxSex = Integer.parseInt(idxSexStr) % 2;
        String sex = (idxSex == 1) ? "0" : "1";

        return sex;
    }

    public static String getBirthDay(String idCard) {
        if(GloubFunc.isEmpty(idCard)
                || idCard.length() < 15) {
            // 默认给19900101
            return "19900101";
        }

        int idxSexStart = 16;
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if(idCard.length() == 15) {
            idxSexStart = 14;
            birthYearSpan = 2;
        }

        //出生日期
        String year = (birthYearSpan == 2 ? "19" : "") + idCard.substring(6, 6 + birthYearSpan);
        String month = idCard.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = idCard.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
        String birthday = year + month + day;

        return birthday;
    }


    public  static  int  dayForWeek() throws  Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int  dayForWeek = 0 ;
        if (c.get(Calendar.DAY_OF_WEEK) == 1 ){
            dayForWeek = 7 ;
        }else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1 ;
        }
        return  dayForWeek;
    }

    /**
     * 获取时间（去除时分秒）
     * @param date
     * @return
     */
    public static Date getDateOfBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date result = new Date(calendar.getTimeInMillis());
        return result;
    }

    /**
     * 获取时间（加上23:59:59）
     * @param date
     * @return
     */
    public static Date getDateOfEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        Date result = new Date(calendar.getTimeInMillis());
        return result;
    }




    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }



    public  static Date getNextMonthStartTime(int end) throws Exception{

        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, end);
        calendar.set(Calendar.DATE, 1);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1,YYYY_MM_DD);
        yyyymmdd += " 00:00:01";

        date1 = getDate(yyyymmdd, YYYY_MM_DD_HH_MM_SS);

        return  date1;

    }


    public  static Date getNextMonthZDay(int end,int days) throws Exception{

        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, end);
        calendar.set(Calendar.DATE, 1);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1,YYYY_MM_DD);
        yyyymmdd += " 00:00:01";

        date1 = getDate(yyyymmdd, YYYY_MM_DD_HH_MM_SS);


        Date date2 = addDayas(date1,days-1);

        return  date2;

    }

    public static Date getNextMonthBegin() throws Exception{
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);

        Date date1 = calendar.getTime();

        String yyyymmdd = format(date1,YYYY_MM_DD);
        yyyymmdd += " 00:00:00";

        date1 = getDate(yyyymmdd, YYYY_MM_DD_HH_MM_SS);
        return date1;
    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据当前时间获取该时间所在周一以及周日的时间
     * @param
     */
    public static String[] getMondayAndSunday(){
        String[] week = new String[2];

        //获取今天是周几（周日开始）
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek =  calendar.get(Calendar.DAY_OF_WEEK);

        //周一
        Date monday = null;
        //周日
        Date sunday = null;

        if(dayOfWeek==1){
            monday = DateUtils.addDayas(new Date(),-6);
            sunday = DateUtils.addDayas(new Date(),0);
        }else {
            monday = DateUtils.addDayas(new Date(),2-dayOfWeek);
            sunday = DateUtils.addDayas(new Date(),8-dayOfWeek);
        }

        week[0] = format(monday,YYYYMMDD);

        week[1] = format(sunday,YYYYMMDD);

        return week;
    }


    /*
     * 比较两个时间点相差多少年。
     */
    public static long compareTime(Date startDate,Date endDate){

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        try {

            start.setTime(startDate);
            end.setTime(endDate);

            if(end.get(Calendar.YEAR)>start.get(Calendar.YEAR)){
                int year=end.get(Calendar.YEAR)-start.get(Calendar.YEAR);

                if(year>1)
                {
                    return year;
                }

                if(end.get(Calendar.MONTH)+1>start.get(Calendar.MONTH)+1){
                    return year;

                }
                else if(end.get(Calendar.MONTH)+1==start.get(Calendar.MONTH)+1)
                {
                    if(end.get(Calendar.DATE)>=start.get(Calendar.DATE)){
                        return year;
                    }else{
                        return year-1;
                    }
                }

                else{
                    return year-1;
                }

            }else{
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

    /**
     * 获取两个时间段内的月数
     * @param end
     * @param begin
     * @return
     */
    public static  int  getMonths(Date end,Date begin)
    {


        Calendar c1 = Calendar.getInstance();

        Calendar c2 = Calendar.getInstance();

        c1.setTime(begin);

        c2.setTime(end);

        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 <= day2) {
            monthInterval--;
        }
        monthInterval %= 12;


        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);


        return monthsDiff-1;

    }




    public static  boolean  judgeNextMonth(Date end,Date begin)
    {


        Calendar c1 = Calendar.getInstance();

        Calendar c2 = Calendar.getInstance();

        c1.setTime(begin);

        c2.setTime(end);

        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        if (month1 < month2 ) {
            return false;
        }

        return true;

    }


    public static int judgeOperDateAndNow(String startTime)
    {

        Date start = GloubFunc.strToDateByWxDone(startTime);


        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");

        String t1 = df.format(start);


        String t2 = df.format(new Date());


        return (GloubFunc.initInt(t2,0)-GloubFunc.initInt(t1,0));

    }




    public static boolean judegeTimeRange(String startTime,String endTime)
    {
        Date start = GloubFunc.strToDateByWxDone(startTime);

        Date end = GloubFunc.strToDateByWxDone(endTime);


        return  isEffectiveDate(new Date(),start ,end);
    }



    public static boolean judgeTimeByStartDay(long s1,String s2)
    {
        Date start = GloubFunc.strToDateByWxDone(s2);

        String sDay = GloubFunc.formatDate(start,"yyyyMMdd");

        long day = GloubFunc.initLong(sDay,0);

        if(day>=s1)
        {
            return true;
        }
        else {
            return false;
        }
    }



    public static boolean judgeTimeByEndDay(long s1,String s2)
    {
        Date start = GloubFunc.strToDateByWxDone(s2);

        String sDay = GloubFunc.formatDate(start,"yyyyMMdd");

        long day = GloubFunc.initLong(sDay,0);

        if(day<=s1)
        {
            return true;
        }
        else {
            return false;
        }
    }



    public  static String  testit() throws  Exception{


        List<String> li = null;


        System.out.println(li.size());


        return "1";
    }



    public static void main(String[] args)
    {



        try
        {

            testit();

            System.out.println(2);

        }
        catch (Exception e)
        {
            throw  new RuntimeException("xxxx") ;
        }




    }



    private static void computerSelection(int[] redBall,int[] userRedBall){
        Random r = new Random();
        int index = -1;
        for(int i=0;i<userRedBall.length;i++){
            index = r.nextInt(redBall.length-i);
            userRedBall[i] = redBall[index];
            int temp = redBall[index];
            redBall[index] = redBall[redBall.length-i-1];
            redBall[redBall.length-i-1] = temp;
        }
    }


    /**
     * 两个时间相差的毫秒数
     * @param begin
     * @param end
     * @param patten
     * @return
     */
    public static long subTractTime(String begin, String end, String patten) throws Exception{
        Date b = getDate(begin, patten);
        Date e = getDate(end, patten);

        return subTractTime(b, e);
    }

    public static long subTractTime(Date begin, Date end) throws Exception {
        if(null == begin || null == end) {
            throw new NullPointerException("begin or end time is null!");
        }
        return begin.getTime() - end.getTime();
    }

    /**
     * 获取当前时间的0点0分0秒
     * @param date  原时间
     * @return 获取当前时间的0点0分0秒
     * @throws Exception
     */
    public static Date getYMDDate(Date date) throws Exception{
        return getDate(getDateString(date,YYYYMMDD), YYYYMMDD);
    }

    /**
     * 日期加N天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date setDatesForday(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, days);
        Date newDate = cal.getTime();
        return newDate;
    }

    /**
     * 获取当前时间的下个月5日（发券日）时间
     * @param date
     * @return
     */
    public static Date getFifthNextMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        return calendar.getTime();
    }

    /**
     * 获取年份
     * @return
     */
    public static String getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    public static Date get1YearLaterDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

    public static Date getLaterMonth(Date date,int offSet) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, offSet);
        return calendar.getTime();
    }


    public static int getMonthsByPy(Date start)
    {

        String end = date2strByMonth(new Date());

        String st = date2strByMonth(start);

        int s1 = GloubFunc.initInt(st,0);

        int e1 =GloubFunc.initInt(end,0);

        return e1-s1;
    }


    public static Date str2DateByday(String time)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date =null;
        try
        {
            if (time != null)
            {
                date = df.parse(time);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }



    public static String date2strByMonth(Date time)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        String date =null;
        try
        {
            if (time != null)
            {
                date = df.format(time);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }


    public static String  getPoductDay(String startTime){




        Date start = GloubFunc.strToDateByWxDone(startTime);

        String s = date2StringByday(start);

        return s;
    }


    public static String date2StringByday(Date time)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date = "";
        try
        {
            if (time != null)
            {
                date = df.format(time);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取一天中剩余的时间（秒数）
     */
    public static Integer getDayRemainingTime(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }



    public static boolean isEffectiveDate(Date startTime, Date endTime) {
        long start_Time = startTime.getTime();
        long end_time = endTime.getTime();
        Date date = new Date();
        long current = date.getTime();
        if(current > end_time || current < start_Time){
            return true;
        }

        return false;
    }
}
