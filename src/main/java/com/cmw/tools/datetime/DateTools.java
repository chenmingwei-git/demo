package com.cmw.tools.datetime;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools { 
    
    public static String getNowDate(){
    	return dateToStr(new Date(), "yyyyMMdd");
    }
    
    public static String getNowTime(){
    	return dateToStr(new Date(), "HHmmss");
    }
    
    public static String getNowDateTime(){
    	return dateToStr(new Date(), "yyyyMMddHHmmss");
    }

    /**  
     * 将日期转换成字符串   
     */  
    public static String dateToStr(Date dateTime) {   
        return dateToStr(dateTime, "yyyyMMdd");   
    }   
    
    public static String dateToStr(Date dateTime,String format) {   
        SimpleDateFormat df = new SimpleDateFormat(format);   
        return df.format(dateTime);   
    }   
    
    /**  
     * 将字符串转换为时间
     */  
    public static Date strToDate(String strDate) {     
        return strToDate(strDate, "yyyyMMdd");   
    }   

    public static Date strToDate(String strDate, String format) {   
        SimpleDateFormat formatter = new SimpleDateFormat(format);   
        ParsePosition pos = new ParsePosition(0);   
        Date strtodate = formatter.parse(strDate, pos);   
        return strtodate;   
    }
    
    /**  
     * 两个时间之间的时间差
     */  
    public static long getTwoDaysDiff(String date1, String date2, String format) throws ParseException {   
        SimpleDateFormat df = new SimpleDateFormat(format);  
        Date d1 = df.parse(date1);  //后的时间  
        Date d2 = df.parse(date2);  //前的时间  
        Long diff = d1.getTime() - d2.getTime();   //两时间差，精确到毫秒   
        return diff;
    }   
    
    /**  
     * 得到二个日期间的间隔天数  
     * @throws ParseException 
     */  
    public static String getTwoDaysBetween(String date1, String date2, String format) throws ParseException {   
    	long day = getTwoDaysDiff(date1, date2, format) / (24 * 60 * 60 * 1000);   
        return day + "";   
    }   
  
    /**  
     * 根据一个日期，返回是星期几的字符串  
     */  
    public static String getWeek(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        // hour中存的就是星期几了，其范围 1~7   
        // 1=星期日 7=星期六，其他类推   
        return new SimpleDateFormat("EEEE").format(c.getTime());   
    }

    /**  
     * 根据一个日期，返回是那个月
     */  
    public static String getMonth(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        return new SimpleDateFormat("MM").format(c.getTime());
    }

    /**  
     * 根据一个日期，返回是那个年
     */  
    public static String getYear(Date date) {   
        Calendar c = Calendar.getInstance();   
        c.setTime(date);   
        return new SimpleDateFormat("yyyy").format(c.getTime());
    }
    
    /**  
     * 获得date所属季度第一天， 返回yyyyMMdd
     */  
    public static String getSeasonFirstTime(Date date) {
    	int month = Integer.valueOf(getMonth(date));
        int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };   
        int season = 1;   
        if (month >= 1 && month <= 3) {   
            season = 1;   
        }   
        if (month >= 4 && month <= 6) {   
            season = 2;   
        }   
        if (month >= 7 && month <= 9) {   
            season = 3;   
        }   
        if (month >= 10 && month <= 12) {   
            season = 4;   
        }   
        int start_month = array[season - 1][0];   
  
        String years = new SimpleDateFormat("yyyy").format(date);   
        int years_value = Integer.parseInt(years);    
        
        Calendar cal=Calendar.getInstance();
    	cal.set(years_value, start_month-1, 1);
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());   
    }   
  
    /**  
     * 获得date所属季度最后一天 ， 返回yyyyMMdd
     */  
    public static String getSeasonFinallyTime(Date date) {
    	int month = Integer.valueOf(getMonth(date));
        int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };   
        int season = 1;   
        if (month >= 1 && month <= 3) {   
            season = 1;   
        }   
        if (month >= 4 && month <= 6) {   
            season = 2;   
        }   
        if (month >= 7 && month <= 9) {   
            season = 3;   
        }   
        if (month >= 10 && month <= 12) {   
            season = 4;   
        }   
        int end_month = array[season - 1][2];   
  
        int years_value = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));   
        
        Calendar cal=Calendar.getInstance();
        cal.set(years_value, end_month-1, 1);
        cal.roll(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());   
    }   
  
    /**  
     * 获取date的最后一天  
     */  
    public static int getLastDayOfMonth(Date date) {   
    	int year = Integer.valueOf(getYear(date));
    	int month = Integer.valueOf(getMonth(date));
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8  
                || month == 10 || month == 12) {   
            return 31;   
        }   
        if (month == 4 || month == 6 || month == 9 || month == 11) {   
            return 30;   
        }   
        if (month == 2) {   
            if (isLeapYear(year)) {   
                return 29;   
            } else {   
                return 28;   
            }   
        }   
        return 0;   
    }   
  
    /**  
     * 是否闰年  
     */  
    public static boolean isLeapYear(int year) {   
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);   
    }
    
    /**
     * 获取与指定日期偏移量的日期  offset可以为负数 
     * @param date yyyyMMdd
     * @param offset
     * @return yyyyMMdd
     */
    public static String getOffsetDate(String date ,int offset){
    	Calendar cal=Calendar.getInstance();
    	cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))-1, Integer.parseInt(date.substring(6, 8)));
        cal.add(Calendar.DATE,offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");// 可以方便地修改日期格式   
        return dateFormat.format(cal.getTime()); 
    }
    
    public static String getOffSetSecond(String date,int offset){
    	Calendar cal=Calendar.getInstance();
    	cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))-1, Integer.parseInt(date.substring(6, 8)), Integer.parseInt(date.substring(8, 10)), Integer.parseInt(date.substring(10, 12)), Integer.parseInt(date.substring(12, 14)));
        cal.add(Calendar.SECOND,offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式   
        return dateFormat.format(cal.getTime());
    }
    
    public static String getOffSetMinute(String date,int offset){
    	Calendar cal=Calendar.getInstance();
    	cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))-1, Integer.parseInt(date.substring(6, 8)), Integer.parseInt(date.substring(8, 10)), Integer.parseInt(date.substring(10, 12)), Integer.parseInt(date.substring(12, 14)));
        cal.add(Calendar.MINUTE,offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式   
        return dateFormat.format(cal.getTime());
    }

    //返回yyyyMMdd
    public static String getOffsetMonth(String date ,int offset){
    	Calendar cal=Calendar.getInstance();
    	cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))-1, Integer.parseInt(date.substring(6, 8)));
        cal.add(Calendar.MONTH,offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");// 可以方便地修改日期格式   
        return dateFormat.format(cal.getTime()); 
    }
    
    //返回yyyyMM
    public static String getOffsetMonth6(String date ,int offset){
    	Calendar cal=Calendar.getInstance();
    	cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))-1, Integer.parseInt(date.substring(6, 8)));
        cal.add(Calendar.MONTH,offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");// 可以方便地修改日期格式   
        return dateFormat.format(cal.getTime()); 
    }
    
    //获取时间date所属月第一天 , 返回 yyyyMMdd
    public static String getFirstDayOfMonth(Date date) {   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");   
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);// 设为当前月的1号
        return sdf.format(cal.getTime());
    } 
    
    //获取日期相减天数
  	public  static long  date(String date1,String date2) throws ParseException {
  		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
  		Date a = format.parse(date1);
  		Date b = format.parse(date2);
  		long c = (a.getTime() - b.getTime()) / (24 * 60 * 60 * 1000);
  		return c;
  	}

  	//校验 当前时间在某个时间段内
  	public void chechTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            //now = df.parse(df.format(new Date()));
            now = df.parse("05:00");
            beginTime = df.parse("06:00");
            endTime = df.parse("21:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar date = Calendar.getInstance();
        date.setTime(now);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
    
    /*public static String[] args) {
		System.out.println(getSeasonFinallyTime(new Date()));
		System.out.println(getSeasonFirstTime(new Date()));
	}*/
    
}