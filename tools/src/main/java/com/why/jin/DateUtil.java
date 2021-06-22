package com.why.jin;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;

/**
 * @author Jin
 * dateTime 2021-06-22-12:56
 */
public class DateUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);


    /**
     * 时间格式化
     * @param d
     * @return
     */
    public static LocalDateTime dateTimeTransform(Object d) {
        if (d instanceof LocalDateTime) {
            return (LocalDateTime)d;
        }
        //使用正则表达式，将非数字所有符号替换为"-"，并替换掉可能出现"--"的情况
        String time = ((String) d).trim().replaceAll("\\D", "-")
                .replace("--", "-");
        String[] split = time.split("-");
        //分割之后，按照年、月、日 三个一组划分为一个LocalData时间
        String yyyy = split[0];
        String MM = split[1].length() == 2 ? split[1] : "0" + split[1];
        String dd = split[2].length() == 2 ? split[2] : "0" + split[2];

        String HH = split[3].length() == 2 ? split[3] : "0" + split[3];
        String mm = split[4].length() == 2 ? split[4] : "0" + split[4];
        String ss = split[5].length() == 2 ? split[5] : "0" + split[5];

        return LocalDateTime.parse(yyyy +"-"+MM+"-"+ dd +" "+ HH+":"+mm+":"+ss, formatters);
    }

    /**
     * @param year
     * @param week
     * @return java.time.LocalDate
     * @description 根据年份以及周数计算日期
     */
    public static LocalDate getLocalDateOfWeekYear(int year, int week) {
        if (week == 0) {
            return LocalDate.now()
                    .withYear(year - 1)
                    .with(TemporalAdjusters.lastDayOfYear());
        } else {
            return LocalDate.now()
                    .withYear(year)
                    .with(weekFields.weekOfYear(), week);
        }
    }

    /**
     * @param localDate
     * @return int
     * @description 获取当前的周数
     * @description 获取当前的周数
     */
    public static int getWeekOfYear(LocalDate localDate) {
        return localDate.get(weekFields.weekOfYear());
    }


    /**
     * 获取本月的第一天
     */
    public static LocalDate getFirstDateOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    /**
     * 获取本月的最后一天
     */
    public static LocalDate getLastDateOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取本周的第一天
     */
    public static LocalDateTime getFirstDateTimeBeginOfWeek(LocalDate date) {
        date = date.with(DayOfWeek.MONDAY);
        return LocalDateTime.of(date, LocalTime.of(0, 0, 0));
    }

    /**
     * 获取本周的最后一天
     */
    public static LocalDateTime getFirstDateTimeEndOfWeek(LocalDate date) {
        date = date.with(DayOfWeek.SUNDAY);
        return LocalDateTime.of(date, LocalTime.of(23, 59, 59));
    }

    /**
     * 获取本周的第一天
     */
    public static LocalDate getDateBeginOfWeek(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }

    /**
     * 获取本周的第一天
     */
    public static LocalDate getDateEndOfWeek(LocalDate date) {
        return date.with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取昨天
     */
    public static LocalDateTime getYesterdayTimeBegin(LocalDate date) {
        date = date.minusDays(1);
        return LocalDateTime.of(date, LocalTime.of(0, 0, 0));
    }

    /**
     * 获取昨天
     */
    public static LocalDateTime getYesterdayTimeEnd(LocalDate date) {
        date = date.minusDays(1);
        return LocalDateTime.of(date, LocalTime.of(23, 59, 59));
    }

    /**
     * 获取本月的第一天
     *
     * @param date
     * @return
     */
    public static LocalDate getMonthBeginDay(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取本月的最后一天
     *
     * @param date
     * @return
     */
    public static LocalDate getMonthEndDay(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long compareDay(LocalDate beginDate, LocalDate endDate) {
        return beginDate.toEpochDay() - endDate.toEpochDay();
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long beforeMinutes(LocalDateTime date){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(date,now);
        return duration.toMinutes();
    }

    /**
     * 根据年周获取开始日期
     *
     * @param year
     * @param week
     * @return
     */
    public static LocalDate parseWeekBegin(int year, int week) {
        return LocalDate.parse(year + " " + week,
                new DateTimeFormatterBuilder().appendPattern("YYYY w").
                        parseDefaulting(WeekFields.ISO.dayOfWeek(), 1).toFormatter());
    }

    /**
     * 根据开始时间获取周的结束时间
     *
     * @param begin
     * @return
     */
    public static LocalDate parseWeekEnd(LocalDate begin) {
        return begin.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    }


}
