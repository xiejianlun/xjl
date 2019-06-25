package com.xjl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	public static final String YYYY_MM= "yyyy/MM";

	public static final String YYYY_MM_DD= "yyyy-MM-dd";
	
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String YYYY_MM_DD_HH_MM= "yyyy-MM-dd HH:mm";

	public static final String HH_MM_DD_MM_YYYY= "HH:mm dd-MM-yyyy";
	
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	public static String getDate(String dateStyle) {
		SimpleDateFormat sf = new SimpleDateFormat(dateStyle);
		return sf.format(new Date());
	} 
	
	public static String getDate(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	} 
	
	public static int getWeekOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK);
		//一周第一天是否为星期天
		boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
		//获取周几
		//若一周第一天为星期天，则-1
		if(isFirstSunday){
			w = w - 1;
			if(w == 0){
				w = 7;
			}
		}
        return w;
	}

	public static Date getCurrYmdDate() {
		String ymd = formatDate(new Date(),YYYY_MM_DD);
		return getDateFromStr(ymd,YYYY_MM_DD);
	}

	public static Date getCurrMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		return cal.getTime();
	}

	public static Date getCurrMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		return cal.getTime();
	}

	public static Date getMonthLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	public static String getHourMinOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY) + ":"+cal.get(Calendar.MINUTE);
	} 
	
	public static String getBeforeDate(String dateStyle) {
		SimpleDateFormat sf = new SimpleDateFormat(dateStyle);
		
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(new Date());//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
		Date before = calendar.getTime();   //得到前一天的时间
		return sf.format(before);
	}

	public static Date getBeforeDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为后n天
		Date afterDate = calendar.getTime();   //得到前一天的时间
		return afterDate;
	}

	public static Date getAfterDate(int days) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(new Date());//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, days);  //设置为后n天
		Date date = calendar.getTime();   //得到前一天的时间
		return date;
	}
	
	public static Date getAfterDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, days);  //设置为后n天
		Date afterDate = calendar.getTime();   //得到前一天的时间
		return afterDate;
	}

	public static Date getBeforeMonth(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -month);
		Date beforeDate = calendar.getTime();
		return beforeDate;
	}

	public static Date getBeforeMinuteDate(Date date, int mins) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -mins);
		Date afterDate = calendar.getTime();
		return afterDate;
	}
	
	public static Date getAfterMinuteDate(Date date, int mins) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, mins);
		Date afterDate = calendar.getTime();
		return afterDate;
	}

	public static Date getBeforeSecondDate(Date date, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, -second);
		Date afterDate = calendar.getTime();
		return afterDate;
	}

	public static Date getAfterSecondDate(Date date, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		Date afterDate = calendar.getTime();
		return afterDate;
	}

	public static Date getDateFromStr(String dateStr) {

		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone tz = TimeZone.getTimeZone("GMT+8");
		sdf.setTimeZone(tz);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getDateFromStr(String dateStr, String format) {
		SimpleDateFormat sdf =   new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * date2比date1多的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) {//同一年
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
				{
					timeDistance += 366;
				} else    //不是闰年
				{
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		} else {   //不同年
			return day2 - day1;
		}
	}

	public static String dateDiff(Date date1, Date date2) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数

		// 获得两个时间的毫秒时间差异
		long diff = date1.getTime() - date2.getTime();
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh + day * 24;// 计算差多少小时
		long min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
		long sec = diff % nd % nh % nm / ns;// 计算差多少秒
		// 输出结果
		System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
				+ (min - day * 24 * 60) + "分钟" + sec + "秒。");
		System.out.println("hour=" + hour + ",min=" + min);
		return day +"-"+(hour - day * 24)+"-"+(min - day * 24 * 60)+"-"+sec;
	}

	public static int getAge(Date birthDay) {
		//获取当前系统时间
		Calendar cal = Calendar.getInstance();
		//如果出生日期大于当前时间，则抛出异常
		if (cal.before(birthDay)) {
			System.out.println("The birthDay is before Now.It's unbelievable!");
			return 0;
		}
		//取出系统当前时间的年、月、日部分
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		//将日期设置为出生日期
		cal.setTime(birthDay);
		//取出出生日期的年、月、日部分
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		//当前年份与出生年份相减，初步计算年龄
		int age = yearNow - yearBirth;
		//当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
		if (monthNow <= monthBirth) {
			//如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

	/**
	 * 获取这周周一的日期
	 * @param date
	 * @return
	 */
	public static Date getThieWeekMonday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		if(1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);
		return cal.getTime();
	}

	/**
	 * 获取这周的周日时间（按中国的，周日在最后）
	 * @param date
	 * @return
	 */
	public static Date getThieWeekSunday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		if(1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
		//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);
		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}

	public static void main(String[] args) throws ParseException {
//		Date date1 = DateKit.getDateFromStr("2016-09-16 00:00:00");
//		Date date2 = DateKit.getDateFromStr("2016-09-16 23:56:00");
//		int day = DateKit.differentDays(date1, date2);
//		System.out.println(date1);
		String date = "2018-02-01 00:00:00";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		TimeZone tz = TimeZone.getTimeZone("GMT+9");
//		sdf.setTimeZone(tz);
//		Date timestamp = sdf.parse(date);
//		System.out.println(timestamp);

		Date d = getMonthFirstDay(getDateFromStr(date));
		Date d2 = getMonthLastDay(getDateFromStr(date));
		System.out.println(formatDate(d,YYYY_MM_DD));
		System.out.println(formatDate(d2,YYYY_MM_DD));
	}
	
}
