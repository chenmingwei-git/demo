package com.cmw.tools;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 陈明伟 on 2017/11/2.
 */
public class PubCheckFun {
    /**
     * @param value 入参为一个字符串
     * @return 非空返回false，否则返回true
     */
    public static boolean isNull(String value) {

        if ("".equals(value) || value == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param dateStr     日期字符串
     * @param dateFormate 日期格式
     * @return 如果传入的字符串满足对应的格式，返回一个true，否则返回false
     */
    public static boolean isValiDate(String dateStr, String dateFormate) {
        if ("yyyyMMdd".equals(dateFormate) && dateStr.length() != 8) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormate);
        simpleDateFormat.setLenient(false);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * @param dateStr     日期字符串
     * @param dateFormate 日期格式
     * @return 如果传入的字符串满足对应的格式，返回一个解析后的Date类型，否则返回null
     */
    public static Date getDate(String dateStr, String dateFormate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormate);
        simpleDateFormat.setLenient(false);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param value 要校验的字符串对象
     * @param rule  正则表达式
     * @return 匹配成功返回true，匹配失败返回false
     */
    public static boolean regExp(String value, String rule) {
        String strRule = rule == null ? "" : rule;
        String strValue = value == null ? "" : value;
        boolean flag = false;
        Pattern pattern = Pattern.compile(strRule);
        Matcher matcher = null;
        matcher = pattern.matcher(strValue);
        if (matcher != null && matcher.find()) {
            flag = true;
        }
        return flag;
    }

    /**
     * @param number 一个纯数字的字符串
     * @return 如果每一个字符都相等，返回true，否则返回false
     */
    public static boolean isSameNumber(String number) {

        //先判断字符串中是否包含其他字符，如果有，直接返回true
        if (!PubCheckFun.regExp(number, "^\\d+$")) {
            return false;
        }
        int flag = 0;
        if (number.length() == 2) {
            if (number.charAt(0) == number.charAt(1)) {
                return false;
            }
        }
        if (number.length() == 1) {
            return true;
        }
        for (int i = 1; i <= number.length() - 2; i++) {
            char a = number.charAt(i - 1);
            char b = number.charAt(i);
            char c = number.charAt(i + 1);
            if (b - a == c - b && b - a == 0) {
                ++flag;
            }
        }
        if (flag == number.length() - 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str 一个纯数字的字符串
     * @return 如果字符连续（正序或者倒序），返回true，否则返回false
     */
    public static boolean isSeriaNumber(String str) {
        //先判断字符串中是否包含其他字符，如果有，直接返回
        if (!PubCheckFun.regExp(str, "^\\d+$")) {
            return false;
        }
        if (str.length() == 1) {
            return false;
        } else if (str.length() == 2) {
            int result = str.charAt(1) - str.charAt(0);
            if (result == 1 || result == -1 || result == 9 || result == -9) {
                return true;
            } else {
                return false;
            }
        } else {
            int flag = 0;
            for (int i = 1; i < str.length() - 1; i++) {
                int a = str.charAt(i - 1);
                int b = str.charAt(i);
                int c = str.charAt(i + 1);
                if (b == '0') {
                    if ((b - a == -9 && c - b == 1) || (b - a == -1 && c - b == 9) || (b - a == -1 && b - c == -1)) {
                        flag += 1;
                    }
                } else if (b == '9') {
                    if ((b - a == 1 && c - b == -9) || (b - a == 9 && c - b == -1) || (b - a == 1 && b - c == 1)) {
                        flag += 1;
                    }
                } else {
                    if ((b - a == c - b && b - a == 1) || (b - a == c - b && c - b == -1) || b - a == 1 && b-c == 1 || b-a == -1 && b-c == -1) {
                        flag += 1;
                    }
                }
            }
            if (flag == str.length() - 2) {
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * @param str 入参为一个字符串
     * @return 如果 包含空格，返回true，否则返回fasle
     */
    public static boolean hasSpaceFlag(String str) {
        if (str.indexOf(' ') >= 0) {
            return true;
        }
        return false;
    }

    /**
     * @param str 入参为一个字符串
     * @return 返回一个去掉空格后的结果，所有的空格（包含首尾和中间）
     */
    public static String trim(String str) {
        return str.replaceAll(" ", "");
    }


    public static boolean isValiNumberID(String number) {
        return !(PubCheckFun.isSameNumber(number)
                || PubCheckFun.isSeriaNumber(number));
    }

    public static boolean isValiDate(String dateStr) {
        if (PubCheckFun.isValiDate(dateStr, "yyyyMMdd")) {
            return true;
        }
        if (PubCheckFun.isValiDate(dateStr, "yyyy/MM/dd")) {
            return true;
        }
        if (PubCheckFun.isValiDate(dateStr, "yyyy-MM-dd")) {
            return true;
        }
        return false;
    }

    // 校验名字中是否有数字
    public static boolean checkHasNumber(final char[] names) {
        for (int i = 0; i < names.length; i++) {
            if (names[i] >= '0' && names[i] <= '9') {
                return true;
            }
        }
        return false;
    }

    // 校验名字中是否全为汉字
    public static boolean checkAllChineseName(final char[] names) {
        for (int i = 0; i < names.length; i++) {
            if (names[i] < 255) {
                return false;
            }
        }
        return true;
    }

    //校验名字是否是全英文
    public static boolean checkLatinName(final char[] names) {
        for (int i = 0; i < names.length; i++) {
            if (!((names[i] >= 'a' && names[i] <= 'z') || (names[i] >= 'A' && names[i] <= 'Z') || names[i] == ' '
                    || names[i] == '.')) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIdValidate(String date) {

        if ("长期".equals(date)) {
            return true;
        } else {
            if (PubCheckFun.isValiDate(date)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 计算客户年龄
     *
     * @param birthdayStr-日期字符串
     * @return
     */
    public static int getPersonAge(String birthdayStr,String cvalidateStr) {
        if (birthdayStr.contains("-") && birthdayStr.length() == 10) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            Date cvalidate =null;
            try {
                birthday = format.parse(birthdayStr);
                cvalidate=format.parse(cvalidateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(cvalidate);
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(birthday);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    // monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    // monthNow>monthBirth
                    age--;
                }
            }
            return age;
        } else {
            throw new RuntimeException("出生日期不合法");
        }
    }
    /**
     * 计算客户年龄
     *
     * @param birthdayStr-日期字符串
     * @return
     */
    public static int getPersonAge(String birthdayStr) {
        if (birthdayStr.contains("-") && birthdayStr.length() == 10) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = format.parse(birthdayStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(birthday);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    // monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    // monthNow>monthBirth
                    age--;
                }
            }
            return age;
        } else {
            throw new RuntimeException("出生日期不合法");
        }
    }

    /**
     * 计算年龄方法（出生日期 到 当前日期）
     *
     * @Param 生日
     * @Return 年龄
     */
    public static int calculateAge(String birthday) {
        int age = 0;
        if (!PubCheckFun.isNull(birthday)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            SimpleDateFormat format1 = new SimpleDateFormat("MM");
            SimpleDateFormat format2 = new SimpleDateFormat("dd");
            String nowYear = format.format(new Date());
            String nowMonth = format1.format(new Date());
            String nowDay = format2.format(new Date());
            String birYear = birthday.substring(0, 4);
            String birMonth = birthday.substring(5, 7);
            String birDay = birthday.substring(8, 10);

            age = Integer.parseInt(nowYear) - Integer.parseInt(birYear);
            if (nowMonth.compareTo(birMonth) < 0) {
                age = age - 1;
            }
            if (nowMonth.compareTo(birMonth) == 0) {
                if (nowDay.compareTo(birDay) < 0) {
                    age = age - 1;
                }
            }
        }
        return age;
    }
    //通过身份证号反算出生日期
    public static String getBirthDay(String idNo){
        String birthday="";
        if (idNo.length()==15){
            String mYear="19"+idNo.substring(6,8);//年份
            String mMonth=idNo.substring(8,10);//月份
            String mDay=idNo.substring(10,12);//日
            String mBirthday=mYear+"-"+mMonth+"-"+mDay;
            birthday=mBirthday;

        }
        if (idNo.length()==18){
            String mYear=idNo.substring(6).substring(0,4);//年份
            String mMonth=idNo.substring(10).substring(0,2);//月份
            String mDay=idNo.substring(12).substring(0,2);//日
            String mBirthday=mYear+"-"+mMonth+"-"+mDay;
            birthday=mBirthday;
        }
        return birthday;
    }

    //通过身份证反算校验出生日期
    public static boolean checkBirthday(String idNo,String birthday){
        boolean flag=false;
        if (getBirthDay(idNo).equals(birthday)){
            flag=true;
        }

        return flag;
    }
    //通过身份证反算校验性别
    public static boolean checkSex(String idNo,String sex){
        boolean flag=true;
        if (idNo.length()==15){
            String mSex=idNo.substring(14,15);
            String tSex="";
            if(Integer.parseInt(mSex)%2==0){
                tSex="1";//女
            }
            if (Integer.parseInt(mSex)%2!=0){
                tSex="0";//男
            }
            if (!tSex.equals(sex)){
                flag=false;
            }
        }
        if (idNo.length()==18){
            String mSex=idNo.substring(16).substring(0,1);
            String tSex="";
            if (Integer.parseInt(mSex)%2==0){
                tSex="1";//女
            }
            if (Integer.parseInt(mSex)%2!=0){
                tSex="0";//男
            }
            if (!tSex.equals(sex)){
                flag=false;
            }

        }
        return flag;
    }

    public  static String getSex(String idNo){
        String tSex="";
        if (idNo.length()==15){
            String mSex=idNo.substring(14,15);
            if(Integer.parseInt(mSex)%2==0){
                tSex="1";
            }
            if (Integer.parseInt(mSex)%2!=0){
                tSex="0";
            }
        }
        if (idNo.length()==18){
            String mSex=idNo.substring(16).substring(0,1);
            if (Integer.parseInt(mSex)%2==0){
                tSex="1";
            }
            if (Integer.parseInt(mSex)%2!=0){
                tSex="0";
            }
        }
        return tSex;
    }

    //判断两个日期的早晚  第一个参数晚于第二个参数
    public static boolean firdate_After_lasdate(String firDate,String lasDate){
        Date date1 = PubCheckFun.getDate(firDate,"yyyy-MM-dd");
        Date date2 = PubCheckFun.getDate(lasDate,"yyyy-MM-dd");
        return date1.after(date2);
    }


    /*//校验数字连续，正向、反向、89012/90987都算连续
    public static boolean isSerNum(String str) {
        //先判断字符串中是否包含其他字符，如果有，直接返回
        if (!PubCheckFun.regExp(str, "^\\d+$")) {
            return false;
        }
        if (str.length() == 1) {
            return false;
        } else if (str.length() == 2) {
            int result = str.charAt(1) - str.charAt(0);
            if (result == 1 || result == -1 || result == 9 || result == -9) {
                return true;
            } else {
                return false;
            }
        } else {
            int flag = 0;
            for (int i = 1; i < str.length() - 1; i++) {
                int a = str.charAt(i - 1);
                int b = str.charAt(i);
                int c = str.charAt(i + 1);
                if (b == '0') {
                    if((b-a==-9 && c-b==1)||(b-a==-1 && c-b == 9)){
                        flag +=1;
                    }
                } else if (b == '9') {
                    if((b-a == 1 && c-b == -9)||(b-a == 9 && c-b == -1)){
                        flag +=1;
                    }
                } else {
                    if((b-a == c-b && b-a == 1)||(b-a == c-b && c-b == -1)){
                        flag +=1;
                    }
                }
            }
            if(flag == str.length()-2){
                return true;
            }else{
                return false;
            }
        }

    }*/


    /**
     * 计算指定日期的下一天
     *
     * @param date 入参为一个字符串 满足日期格式 yyyy-MM-dd
     * @return 返回一个字符串 满足日期格式 yyyy-MM-dd
     */
    public static String nextDate(String date){
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        String nextDay=null;
        try {
            Date temp = dft.parse(date);
            Calendar cld = Calendar.getInstance();
            cld.setTime(temp);
            cld.add(Calendar.DATE, 1);
            temp = cld.getTime();
            //获得下一天日期字符串
             nextDay = dft.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nextDay;
    }


    /**
     * 获取当前时间
     *
     * @param
     */
    public static String getNowTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 判断当天是否为本月第一天
     *
     * @return
     */
    public static boolean isFirstDayOfMonth() {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(calendar.DAY_OF_MONTH);
        if (1 == today) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取当前月份最后一天
     *
     * @param
     * @return
     * @throws ParseException
     */
    public static String getMaxMonthDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     *
     * 描述:获取下一个月的第一天.
     *
     * @return
     */
    public static String getPerFirstDayOfMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     *
     * 描述:获取上个月的最后一天.
     *
     * @return
     */
    public static String getLastMaxMonthDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 获取上一个月
     *
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     *
     * 描述:获取下一个月.
     *
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String preMonth = dft.format(cal.getTime());
        return preMonth;
    }

    // 是否是最后一天
    public static boolean isLastDayOfMonth() {
        boolean flag = false;
        if (StringUtils.isNotBlank(getNowTime()) && StringUtils.isNotBlank(getMaxMonthDate()) && StringUtils.equals(getNowTime(), getMaxMonthDate())) { // getMaxMonthDate().equals(getNowTime())
            flag = true;
        }
        return flag;
    }

    /**
     * 获取任意时间的下一个月
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getPreMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    /**
     * 获取任意时间的上一个月
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static String getLastMonth(String repeatDate) {
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month-2,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }
    //
    /**
     * 获取任意时间的月的最后一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    private static String getMaxMonthDate(String repeatDate) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            if(StringUtils.isNotBlank(repeatDate) && !"null".equals(repeatDate)){
                calendar.setTime(dft.parse(repeatDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 获取任意时间的月第一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    private static String getMinMonthDate(String repeatDate){
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            if(StringUtils.isNotBlank(repeatDate) && !"null".equals(repeatDate)){
                calendar.setTime(dft.parse(repeatDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }
    /**
     * 不论是当前时间，还是历史时间  皆是时间点的前天
     * repeatDate  任意时间
     */
    public static String getModify2DaysAgo(String repeatDate) {
        Calendar cal = Calendar.getInstance();
        String daysAgo = "";
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        if (repeatDate == null || "".equals(repeatDate)) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 2);

        } else {
            int year = Integer.parseInt(repeatDate.substring(0, 4));
            String monthsString = repeatDate.substring(4, 6);
            int month;
            if ("0".equals(monthsString.substring(0, 1))) {
                month = Integer.parseInt(monthsString.substring(1, 2));
            } else {
                month = Integer.parseInt(monthsString.substring(0, 2));
            }
            String dateString = repeatDate.substring(6, 8);
            int date;
            if ("0".equals(dateString.subSequence(0, 1))) {
                date = Integer.parseInt(dateString.substring(1, 2));
            } else {
                date = Integer.parseInt(dateString.substring(0, 2));
            }
            cal.set(year, month-1, date - 1);
            System.out.println(dft.format(cal.getTime()));
        }
        daysAgo = dft.format(cal.getTime());
        return daysAgo;
    }

    /**
     * 不论是当前时间，还是历史时间  皆是时间点的T-N天
     * repeatDate 任意时间    param 数字 可以表示前几天
     */
    public static String getModifyNumDaysAgo(String repeatDate,int param) {
        Calendar cal = Calendar.getInstance();
        String daysAgo = "";
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        if (repeatDate == null || "".equals(repeatDate)) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - param);

        } else {
            int year = Integer.parseInt(repeatDate.substring(0, 4));
            String monthsString = repeatDate.substring(4, 6);
            int month;
            if ("0".equals(monthsString.substring(0, 1))) {
                month = Integer.parseInt(monthsString.substring(1, 2));
            } else {
                month = Integer.parseInt(monthsString.substring(0, 2));
            }
            String dateString = repeatDate.substring(6, 8);
            int date;
            if ("0".equals(dateString.subSequence(0, 1))) {
                date = Integer.parseInt(dateString.substring(1, 2));
            } else {
                date = Integer.parseInt(dateString.substring(0, 2));
            }
            cal.set(year, month-1, date - param+1);
            System.out.println(dft.format(cal.getTime()));
        }
        daysAgo = dft.format(cal.getTime());
        return daysAgo;
    }





    public static void main(String[] args) throws ParseException {

//        System.out.println(PubCheckFun.isValiDate("2017-13-01"));

      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse("201711078");
        System.out.println(date);*/
        /*String regZipCode = "^\\d{6}+$"; //匹配邮编
        System.out.println(PubCheckFun.regExp(null,null));*/
//        System.out.println(PubCheckFun.regExp("12","^[1-4]{1}+$"));
//        System.out.println(PubCheckFun.isSeriaNumber("0123456789"));
//        System.out.println(PubCheckFun.isSerNum("76543210987"));
       // System.out.println(PubCheckFun.isSeriaNumber("76543210123456"));

      System.out.println(PubCheckFun.nextDate("2000-01-01"));

    }

}
