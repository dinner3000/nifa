package com.brh.p2p.nifa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;


public class JodaUtils {
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String DATE_TIME_MS_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    public static final String DATE_FORMAT = "yyyy/MM/dd";

    public static final String DATE_FORMAT2 = "yyyyMMdd";

    public static final String DATE_FORMAT_YYMMDD = "yyMMdd";

    public static final String TIME_FORMAT = "HHmmss";

    public static final String DATE_TIME_FORMAT2 = "yyyyMMddHHmmss";

    public static final String DATE_TIME_FORMAT3 = "yyyyMMddhhmmssSSS";

    public static final String DATE_TIME_FORMAT4 = "yyMMddhhmmssSSS";

    public static int daysBetween(DateTime start, DateTime end) {
        Period p = new Period(start, end, PeriodType.days());
        return p.getDays();
    }

    public static int monthsBetween(DateTime start, DateTime end) {
        Period p = new Period(start, end, PeriodType.months());
        return p.getMonths();
    }

    public static DateTime intToDateTime(Integer intDate) {
        return DateTimeFormat.forPattern(DATE_FORMAT2).parseDateTime(intDate.toString());
    }

    public static Integer dateTimeToInt(DateTime dateTime) {
        return Integer.valueOf(dateTime.toString(DATE_FORMAT2));
    }

    public static DateTime now() {
        return DateTime.now();
    }

    public static Date nowAsDate() {
        return new Date();
    }

    public static DateTime nowAsDateTime() {
        return DateTime.now();
    }

    public static LocalDate today() {
        return new LocalDate();
    }

    public static LocalDate parseLocalDate(String dateAsString, String format) {
        Date date = parseDate(dateAsString, format);
        return new LocalDate(date);
    }

    public static Date parseDate(String dateAsString, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateAsString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date string can not be parsed:" + dateAsString, e);
        }
    }

    public static LocalDate parseLocalDate(String localDateAsString) {
        return parseLocalDate(localDateAsString, DATE_FORMAT);
    }

    public static DateTime parseDateTime(String val) {
        return parseDateTime(val, DEFAULT_DATE_TIME_FORMAT);
    }

    public static DateTime parseDateTime(String text, String patten) {
        return DateTimeFormat.forPattern(patten).parseDateTime(text);
    }

    public static LocalDate yesterday() {
        return today().minusDays(1);
    }

    public static DateTime dayOfCurrentMonth(int day) {
        LocalDate today = JodaUtils.today();
        return new DateTime(today.getYear(), today.getMonthOfYear(), day, 0, 0, 0, 0);
    }

    /**
     * 当前日期格式化后转换成String型
     *
     * @param patten
     * @return
     */
    public static String nowDateToString(String patten) {
        try {
            return now().toString(patten);
        } catch (Exception e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + patten, e);
        }
    }

    /**
     * 对时间进行转换为日期的字符串
     *
     * @param date
     * @param patten
     * @return
     */
    public static String dateToString(Date date, String patten) {
        try {
            if (null == date) {
                date = new Date();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
            return dateFormat.format(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + patten, e);
        }
    }

    /**
     * 当前日期格式化后转成long类型
     *
     * @return
     */
    public static Long nowDateToLong(String patten) {
        try {
            return Long.valueOf(now().toString(patten));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + patten, e);
        }
    }

    /**
     * 当前日期格式化后转成Integer类型
     *
     * @return
     */
    public static Integer nowDateToInt(String patten) {
        try {
            return Integer.valueOf(now().toString(patten));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + patten, e);
        }
    }

    /**
     * 当前时间格式化后转成Integer类型
     *
     * @return
     */
    public static Integer currentTimeToInt() {
        return nowDateToInt(JodaUtils.TIME_FORMAT);
    }

    /**
     * 当前日期格式化后转成Integer类型
     *
     * @return
     */
    public static Integer currentDateToInt() {
        return nowDateToInt(JodaUtils.DATE_FORMAT2);
    }

    /**
     * 日期格式化转Long
     *
     * @param date
     * @param patten
     * @return
     */
    public static Long dateToLong(Date date, String patten) {
        try {
            return Long.valueOf(new DateTime(date).toString(patten));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + patten, e);
        }

    }

    /**
     * 日期格式化转Integer
     *
     * @param date
     * @param patten
     * @return
     */
    public static Integer dateToInt(Date date, String patten) {
        try {
            return Integer.valueOf(new DateTime(date).toString(patten));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + patten, e);
        }
    }

    /**
     *
     * @param date
     * @return
     */
    public static Integer dateToInt(Date date) {
        try {
            return Integer.valueOf(new DateTime(date).toString(DATE_FORMAT2));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + DATE_FORMAT2, e);
        }
    }

    /**
     * 日期格式化转Integer
     *
     * @param dateNum
     * @return
     */
    public static Date IntToDate(int dateNum) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT2);
            Date date1 = formatter.parse("" + dateNum);
            return date1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + dateNum, e);
        } catch (ParseException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + dateNum, e);

        }
    }

    /**
     * 日期格式化转Integer
     *
     * @param dateNum
     * @return
     */
    public static Date IntToDate(int dateNum, String format) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date date1 = formatter.parse("" + dateNum);
            return date1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + dateNum, e);
        } catch (ParseException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + dateNum, e);

        }
    }

    /**
     * 日期格式化转Integer
     *
     * @param dateNum
     * @return
     */
    public static String IntToString(int dateNum, String format) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date date = formatter.parse("" + dateNum);
            return new DateTime(date).toString(format);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + dateNum, e);
        } catch (ParseException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + dateNum, e);

        }
    }

    /**
     * 日期格式化转Long
     *
     * @param date
     * @return
     */
    public static Date LongTodate(Long date) {
        Date date1 = null;

        try {

            date1 = new DateTime(date).toDate();

        } catch (NumberFormatException e) {
        }
        return date1;
    }

    /**
     * 当前日期格式化后转成Integer类型 默认日期格式为“yyyyMMdd”
     *
     * @return
     */
    public static Integer nowDateToInt() {
        try {
            return nowDateToInt(JodaUtils.DATE_FORMAT2);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + JodaUtils.DATE_FORMAT2);
        }
    }

    /**
     * 返回当前日期加N天后的日期，在所在月是几号
     *
     * @param days
     * @return
     */
    public static Integer currentTimePlusDayReturnDay(int days) {
        return now().plusDays(days).getDayOfMonth();
    }

    /**
     * 当前日期加N天的日期
     *
     * @param days
     * @param patten
     * @return 按patten格式化，如20140505
     */
    public static Integer currentTimePlusDay(int days, String patten) {
        return Integer.valueOf(now().plusDays(days).toString(patten));
    }

    /**
     * 当前日期加N天的日期
     *
     * @param days
     * @return 按patten格式化，如20140505
     */
    public static Integer currentTimePlusDay(int days) {
        return Integer.valueOf(now().plusDays(days).toString(JodaUtils.DATE_FORMAT2));
    }

    public static Date getCurrentDate() {
        LocalDate today = JodaUtils.today();
        return new DateTime(today.getYear(), today.getMonthOfYear(), today.getDayOfMonth(), 0, 0, 0, 0).toDate();
    }

    public static Integer genLastRepayDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day >= 1 && day < 29) {
            c.add(Calendar.MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            return dateToInt(c.getTime(), JodaUtils.DATE_FORMAT2);
        } else {
            c.add(Calendar.MONTH, 2);
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            return dateToInt(c.getTime(), JodaUtils.DATE_FORMAT2);
        }
    }

    /**
     *
     * 描述: 日期按月数增加计算通用方法
     * <p>
     * 创建人：jrzhangwei , 2014年5月20日 下午6:00:38
     * </p>
     *
     * @param dateAsInteger
     *            日期
     * @param plusMonths
     *            需增加的月数
     * @return
     */
    public static Integer plusMonths(Integer dateAsInteger, Integer plusMonths) {
        if (null != dateAsInteger) {
            Date date = parseDate(String.valueOf(dateAsInteger), JodaUtils.DATE_FORMAT2);
            LocalDate localDate = new LocalDate(date);
            return Integer.valueOf(localDate.plusMonths(plusMonths).toString(JodaUtils.DATE_FORMAT2));
        }
        return null;
    }

    /**
     * 获取指定时间是否在当前时间之后
     *
     * @param yyyyMMdd
     * @return
     */
    public static boolean isAfterNow(Integer yyyyMMdd) {
        DateTime date = new DateTime(JodaUtils.parseDate(String.valueOf(yyyyMMdd), JodaUtils.DATE_FORMAT2));
        return date.isAfterNow();
    }

    /**
     * 获取指定时间是否在当前时间之前
     *
     * @param yyyyMMdd
     * @return
     */
    public static boolean isBeforeNow(Integer yyyyMMdd) {
        DateTime date = new DateTime(JodaUtils.parseDate(String.valueOf(yyyyMMdd), JodaUtils.DATE_FORMAT2));
        return date.isBeforeNow();
    }

    /**
     * 获取rigger表的还款事件的触发日期
     *
     * @return
     */
    public static Integer getActivationDateForPayment() {
        return JodaUtils.getLastRepayDate(-2);
    }

    /**
     * 获取最后还款日
     *
     * @return
     */
    public static Integer getLastRepayDate() {
        return JodaUtils.getLastRepayDate(0);
    }

    /**
     * 获取下一个分期入账日期
     *
     * @return
     */
    public static Integer getNextBillingDate() {
        return JodaUtils.getLastRepayDate(1);
    }

    /**
     * 获取最后还款日
     *
     * @param ahead
     *            提前量
     * @return
     */
    private static Integer getLastRepayDate(int ahead) {
        int DayOfMonth = JodaUtils.now().getDayOfMonth(); // 获取消费日期在所在月是几号
        Integer lastRepayDate = 0;
        if (DayOfMonth >= 1 && DayOfMonth <= 28) {// 1号到28号，还款日期加一个月
            lastRepayDate = Integer
                    .valueOf(JodaUtils.now().plusMonths(1).plusDays(ahead).toString(JodaUtils.DATE_FORMAT2));
        } else if (DayOfMonth <= 31) {// 29，30，31号，还款日期为下下个月1号
            lastRepayDate = Integer.valueOf(JodaUtils.now().plusMonths(2).dayOfMonth().withMinimumValue()
                    .plusDays(ahead).toString(JodaUtils.DATE_FORMAT2));
        }
        return lastRepayDate;
    }

    /**
     * 日期格式化转Integer
     *
     * @param date
     * @param
     * @return
     */
    public static Integer dateToInt(String date, String pattern) {
        try {
            if (date == null || pattern == null) {
                return null;
            }
            return Integer.valueOf(parseDateTime(date).toString(pattern));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + pattern, e);
        }
    }

    /**
     * 当前日期格式化后转成Integer类型 默认日期格式为“yyyyMMdd”
     *
     * @return
     */
    public static Integer nowDateTimeToInt() {
        try {
            return Integer.valueOf(now().toString(JodaUtils.TIME_FORMAT));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + JodaUtils.TIME_FORMAT);
        }
    }

    /**
     * 当前时间格式化后转成Integer类型
     *
     * @param dateTime
     *            时间
     * @param pattern
     *            格式类型
     */
    public static Integer parseDateTimeToInt(DateTime dateTime, String pattern) {
        try {
            return Integer.valueOf(dateTime.toString(pattern));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("patten string can not be parsed:" + JodaUtils.TIME_FORMAT);
        }
    }

    /**
     * 当前日期减N天的日期
     *
     * @param days
     * @param
     * @return
     */
    public static DateTime timeMinusDay(DateTime dateTime, int days) {
        dateTime = dateTime.minusDays(days);
        return dateTime;

    }

    /**
     * 验证字符串是否符合日期格式
     *
     * @param
     * @return
     */
    public static boolean isValidDate(String dateStr, String patten) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(patten);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(dateStr);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static void main(String[] args) {
        // System.out.println(now().);
		/*
		 * DateTime cur_time = new DateTime(System.currentTimeMillis());
		 * System.out.println(cur_time.toString("yyyyMMddHHmmss"));
		 */

        // String cur_time = new
        // DateTime(System.currentTimeMillis()).toString("yyyyMMddHHmmss");
        // String customerRefNo = String.format("%012s",
        // "c",cur_time,String.valueOf(Constants.BASE_ID));

        // System.out.println(JodaUtils.IntToDate(20140504));
        // System.out.println(currentTimePlusDayReturnDay(20));
        // System.out.println(Integer.valueOf(JodaUtils.now().plusMonths(2).dayOfMonth().withMinimumValue().toString(JodaUtils.DATE_FORMAT2)));
        // System.out.println(Integer.valueOf(JodaUtils.now().plusMonths(1).toString(JodaUtils.DATE_FORMAT2)));
        // System.out.println(dayOfCurrentMonth(today().getDayOfMonth()+1));

        // System.err.println(JodaUtils.plusMonths(20140131, 1));

		/*
		 * DateTime requestTime = new DateTime();
		 * System.err.println(JodaUtils.parseDateTimeToInt(requestTime,JodaUtils
		 * .DATE_FORMAT2));
		 * System.err.println(JodaUtils.parseDateTimeToInt(requestTime,JodaUtils
		 * .TIME_FORMAT)); System.err.println(requestTime.toDate());
		 */
		/*
		 * System.err.println(JodaUtils.dateToString(new Date(),
		 * DEFAULT_DATE_TIME_FORMAT));
		 * System.out.println(JodaUtils.IntToString(20150816,JodaUtils.
		 * DATE_FORMAT_YYMMDD));
		 */
        DateTime nowTime = new DateTime("2015-09-18");
        DateTime startOfDay = nowTime.withTimeAtStartOfDay();
        DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
        System.err.println(startOfDay.toString(DEFAULT_DATE_TIME_FORMAT));
        System.err.println(endOfDay.toString(DEFAULT_DATE_TIME_FORMAT));
        System.err.println(isValidDate("2015-09-18 26:00:000", DEFAULT_DATE_TIME_FORMAT));
        System.out.println(timeMinusDay(nowTime, 19).withTimeAtStartOfDay().toString(JodaUtils.DEFAULT_DATE_TIME_FORMAT));
        System.out.println(new Date());
        System.err.println(JodaUtils.nowAsDateTime());
    }
}
