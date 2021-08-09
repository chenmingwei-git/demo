package com.cmw.demo;


import com.alibaba.fastjson.JSON;
import com.cmw.pojo.People;
import com.cmw.tools.PubCheckFun;
import org.assertj.core.util.Sets;
import org.junit.Test;
import com.alibaba.fastjson.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;


//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void s (){
        //subDay("413026199310309171");
        System.out.println(subDay1("20201128"));
    }
    public String subDay(String idNo){
        String subDay =null;
        String newIdNo;
        if(idNo.length()==15){
            newIdNo = idNo.substring(8,12);
        }else {
            newIdNo =idNo.substring(10,14);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String currDate = format.format(new Date());

        newIdNo = currDate.substring(0,4)+newIdNo;

        try {
            Date parse = format.parse(newIdNo);
            Calendar cl = Calendar.getInstance();
            cl.setTime(parse);
            cl.add(Calendar.DATE,30);
            Date addParse =cl.getTime();
            String addDate = format.format(addParse);
            subDay = Long.toString((format.parse(addDate).getTime()-format.parse(currDate).getTime())/(24*60*60*1000));
            System.out.println(subDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return subDay;
    }


    public String subDay1(String endTime){
        String sunDay1 =null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date  endDate = sf.parse(endTime);
            Date nowDate = sf.parse(sf.format(new Date()));
            sunDay1=Long.toString((nowDate.getTime()-(endDate.getTime()))/(24*60*60*1000));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sunDay1;
    }



    //测试方法的反射
    @Test
    public void testInvoke() throws Exception {
        TestInvoke testInvoke = new TestInvoke();

        Method method = testInvoke.getClass().getMethod("add", new Class[]{int.class, int.class});
        method.invoke(testInvoke,10,10);
    }

    //测试日期格式转换
    @Test
    public void testDateTransFaomation(){


//        String s = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.S").format(new Date());
//        System.out.println(s);

//        String s1 = "20191227101010";
//       SimpleDateFormat ss1 =  new SimpleDateFormat("yyyyMMddHHmmss");
//       SimpleDateFormat ss2 =  new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
//       SimpleDateFormat ss3 =  new SimpleDateFormat("yyyyMMdd");
//        Date date =null;
//        Calendar c1 = Calendar.getInstance();
//        Calendar c2 = Calendar.getInstance();
//        try {
//            date = ss1.parse(s1);
//            s1 =  ss2.format(date);
//            c1.setTime(ss3.parse("20220917"));
//            c2.setTime(ss3.parse("20221011"));
//            if(c2.compareTo(c2)==-1){
//                System.out.println("111");
//            }else {
//                System.out.println("2222");
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

       // System.out.println(s1);


        SimpleDateFormat s1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String s3 = s2.format(s1.parse("20241011"));
            System.out.println(s3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //测试Optional 以及lambda
    @Test
    public void testOptional(){

        List<People> list = new ArrayList<>();
        list.add(new People(1,"a"));
        list.add(new People(2,"b"));

        People p3 = Optional.ofNullable(list.stream().sorted
                (Comparator.comparing(People::getId).reversed()).collect(Collectors.toList()).get(0)).orElse(new People());
        System.out.println(p3.getId());

    }

    //测试String
    @Test
    public void testString(){

         /*for(int i = 0;i< 5;i++){
            for(int j=0;j<6;j++){
                    if(j==2){
                        System.out.println("i = "+i+","+" j ="+j);
                        break;
                    }
            }
            System.out.println("最后一个 = "+i);
        }*/


       /* StringBuffer s = new StringBuffer();
        s.append("123-123-");
        String  ss =s.substring(0,7);
        System.out.println(ss);

        String s2 =  String.format("%03d",011);
        System.out.println(s2);*/

       /* String s =" ";
        System.out.println(s.length());*/

//       Map<String,String> map = new HashMap<>();
//       map.put("a","A ");
//       map.put("b","B");


      //  System.out.println(map.toString().replace(" ",""));//去除map转的字符串中空格使用trim不掉需替换才行

        /*String s ="a|b||";
        String[] ss = s.split("\\|",13);
        System.out.println(ss.length);
        for (String sss:ss){
            System.out.println(sss);
        }*/

        /*String [] arr ={"01","03","04","05","06","07","08"};
        int index=(int)(Math.random()*arr.length);
        System.out.println(index);*/


//        Object a ="null";
//
//        String b= valueOf(a);
//
//
//        if(b==null){
//            System.out.println(b);
//        }
//        String regEx = "^[A-Za-z0-9\\u4E00-\\u9FA5_-]+$";
//        Pattern pattern=Pattern.compile(regEx);
//        Matcher match=pattern.matcher("江苏省苏州市吴中区临湖镇灵湖村吴舍()吴舍104号");
//        System.out.println(match.matches());

        String regEx ="(([0-9]{1,4}))(\\.(\\d){1,2})?$";
        Pattern pattern=Pattern.compile(regEx); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher("0617.09");
        System.out.println(match.matches());


        //关于字符串中包含特殊字符的正则校验
//        String str="chenmingwei@qq.com.11";
//        String regEx1="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
//        Pattern p1 = Pattern.compile(regEx1);
//        Matcher m1 = p1.matcher(str);
//        System.out.println(m1.matches());
//
//        String regEx2="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
//        Pattern p2 = Pattern.compile(regEx2);
//        Matcher m2 = p2.matcher(str);
//        System.out.println(m2.matches());




        //关于字符串中包含特殊字符的正则校验
//        String regEx="[!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher("我们的居住地址@$在5-401");
//        System.out.println(m.find());

//        String regEx="[\\?\\!\\？\\！\\$\\*\\^%]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher("河南省固始县观堂乡$杨庙村胡圩组");
//        System.out.println(m.find());
//        Map<String,Integer> map = new HashMap<>();
//        map.put("ss",80);
//        System.out.println(map.get("ss").toString());

//        String s= "413026199310309171".substring(16).substring(0,1);
//        System.out.println(s);

//        String ss = "22";
//
//        if(ss!=null&&!ss.equals("22")){
//            System.out.println("111");
//        }else {
//            System.out.println("222");
//        }

//        String str = "0000001230";
//        String str1 = str.replaceAll("^(0+)", "");
//        System.out.println(str1);  // 1230

//        Map<String,String> m = new HashMap<>();
//        m.put("a","23");
//        if(m.get("b")!=null){
//            String s = m.get("b").replaceAll("^(0+)","");
//            System.out.println(s);
//        }



//        String  s="412930821082x";
//        if(s.endsWith("x")){
//            String s2 = s.replace("x","X");
//            System.out.println(s2);
//        }

//        String str1 = "abc";
//        String str2 = new String("ddd");
//        String str3 = str2.intern();
//        System.out.println(str1==str2);
//        System.out.println(str2==str3);
//        System.out.println(str1==str3);
//
//
//      //  String ss1 = "ing";
//        String ss2 = new String("ing");
//        String ss3= ss2.intern();
//        System.out.println(ss3==ss2);

//        String s2 = new String("张")+new String("三");
//        String s3 = s2.intern();
//        String s1 ="张三";
//
//        System.out.println(s1==s2);
//        System.out.println(s1==s3);
//        System.out.println(s2==s3);

    }

    @Test
    public void testIf(){

        SimpleDateFormat s =new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat s1 =new SimpleDateFormat("yyyy-MM-dd");
        try {
            String a = s1.format(s.parse("00000001"));
            System.out.println(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }






    //测试int类型
    @Test
    public void testIntChange(){

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            Date nowDat = format.parse(format.format(new Date()));
//            Date exDate =  format.parse("2020-04-23");
//            if(exDate.before(nowDat)){
//                System.out.println("1111");
//            }else {
//                System.out.println("22222");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        Calendar c1 = Calendar.getInstance();
//        Calendar c2 = Calendar.getInstance();
//        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
//        try {
//            c1.setTime(s.parse("20210607"));
//            c2.setTime(s.parse("20210607"));
//            if(c2.compareTo(c1)==-1){
//                System.out.println("11111");
//            }else {
//                System.out.println("222222");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        String str = "2020-10-05";
        if(str.length()>8){
            str= str.replaceAll("-","");
        }
        SimpleDateFormat df =new SimpleDateFormat("yyyyMMdd");
        Date now =null;
        Date end = null;
        try {
            now = df.parse(df.format(new Date()));
            end = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long result = (end.getTime()-now.getTime())/(24*60*60*1000);
        int days = Integer.parseInt(String.valueOf(result));
        System.out.println(days);
    }

    //测试switch的用法
    @Test
    public void testSwitch(){

        List<String> list = new ArrayList<>();
        list.add("lis");
        list.add("zhangsan");
        list.add("wangwu");
        for (int i =0;i<list.size();i++){
            String s = list.get(i);
            switch (s){
                case "lis":
                    System.out.println("lis");
                case "zhangsan":
                    System.out.println("zhangsan");
                case "wangwu":
                    System.out.println("wangwu");
                default:
                    break;
            }

        }

    }

    //测试Map
    @Test
    public void testMap(){

        HashMap hashmap = new HashMap();
        hashmap.put("Item0", "Value0");
        hashmap.put("Item1", "Value1");
        hashmap.put("Item2", "Value2");
        hashmap.put("Item3", "Value3");
        Set set = hashmap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry mapentry = (Map.Entry) iterator.next();
            System.out.println(mapentry.getKey() + "/" + mapentry.getValue());
        }
        System.out.println("222");

        /*Set<String> keySet=hashmap.keySet();
        Iterator<String> iterator2=keySet.iterator();
        while(iterator.hasNext()) {
            String key=iterator2.next();
            String book=(String) hashmap.get(key);
            System.out.println(key);
            System.out.println(book);
        }*/
    }

   // 测试JSONObject
    @Test
    public void JsonTest(){

//        JSONObject[] jsonObject = new JSONObject[]{new JSONObject()};
//        System.out.println(jsonObject.length);
        Map m = new HashMap();
        m.put("123","43");

        String ss =(String)m.get("222");
        System.out.println();

        String s="000000";
        String income="1";
        if(!"".equals(income)){
            System.out.println("1111111");
        }
    }

   private JSONObject[] tets11(JSONObject[] jsonObject){
        for (int i =0;i<jsonObject.length;i++){
            JSONObject jsonObject1 = jsonObject[i];
            String s= "222";
            jsonObject1.put("nini",s);
        }
        return jsonObject;
   }


   //测试文件的io
   @Test
   public void testFileIO(){

        //创建文件
       File file = new File("/Users/chenmingwei/Desktop/11112222");
       if(!file.exists()){
           file.mkdir();
       }

       try {

           FileOutputStream fileOutputStream =  new FileOutputStream(file+"/1.txt",true);
           fileOutputStream.write("ABCD".getBytes());
           fileOutputStream.close();

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   //测试Bifde
   @Test
   public void testBigDecimal(){
//        BigDecimal b1 = new BigDecimal(1500);
//       System.out.println(b1.divide(new BigDecimal(-100)));

       BigDecimal bd = new BigDecimal("12.99");
       String l1  = bd.setScale( 0, BigDecimal.ROUND_UP ).toString(); // 向上取整
       long l2  = bd.setScale( 0, BigDecimal.ROUND_DOWN ).longValue(); // 向下取整

       System.out.println(l1+" >>>> "+l2);

   }

    @Test
    public void testReflex(){
        Calendar c = Calendar.getInstance();
        int d = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int now = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(d - now+1);
    }

     int i;

    @Test
    public void contextLoads() {

//        for ( i=0; i < 5; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() +" i= "+i);
//            }).start();
//        }

//        Integer s =Integer.valueOf(String.valueOf("3.27"))*100;
//        System.out.println(s);

//        String s1 = (new BigDecimal("-13.11").multiply(new BigDecimal("100"))).setScale(0).toString();
//       // Integer a = Integer.valueOf(s1);
//        System.out.println(s1);

        // int s = new BigDecimal("0.00").compareTo(new BigDecimal("0.01"));
         BigDecimal s =(new BigDecimal("100").subtract(new BigDecimal("75"))).setScale(2);
        System.out.println(s);
    }





    @Test
    public  void test1(){

        double a=15.0;

        double d = a/100;
        BigDecimal b = new BigDecimal(d).setScale(4,BigDecimal.ROUND_HALF_UP);
        System.out.println(d);
        System.out.println(b);

//        BigDecimal a = new BigDecimal("4");
//        System.out.println(a.toPlainString());
//        System.out.println(BigDecimal.ZERO.compareTo(new BigDecimal("-1")));
//        System.out.println(Long.parseLong(getNowTime("yyyyyMMdd")));

     /* Integer a= 3;
      Integer b= 2;

      BigDecimal ba= BigDecimal.valueOf(a/b);
      System.out.println("ba "+ba );

//       BigDecimal c = ba.divide(bb,2);
//       System.out.println("ccc :"+c);
//
       BigDecimal s1 = new BigDecimal("5310.00");
       BigDecimal s2 = new BigDecimal("3840.00");

       BigDecimal s3 = s1.divide(s2,2,BigDecimal.ROUND_HALF_UP);
       System.out.println("s3 = "+s3);*/




    }

    //测试日期调用相关
    @Test
    public  void date(){

//        int[] range = new int[2];
////        Calendar ca = Calendar.getInstance();
////        ca.set(Calendar.YEAR,201902/100);
////        ca.set(Calendar.MONTH,201902%100-1);
////        System.out.println(ca.getTime());
////
//////        ca.set(Calendar.MONTH,ca.get(Calendar.MONTH)-2);
//////        ca.set(Calendar.YEAR,ca.get(Calendar.YEAR)-3);
//////        System.out.println(ca.getTime());
//////        ca.set(Calendar.MONTH,ca.get(Calendar.MONTH)-1);
//////        System.out.println(ca.getTime());
//////        range[1] = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(ca.getTime())) ;
//////        System.out.println(range[1]);
////
////        ca.set(Calendar.MONTH,ca.get(Calendar.MONTH)-2-1);
////        System.out.println(ca.getTime());


//        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
//        try {
//            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-24"));
//            calendar.add(5,360);
//            Date returnDate = new Date(calendar.getTime().getTime());
//            System.out.println(returnDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        String str = "2020-04-03";
//        try {
//
//            SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
//            Date date =  s.parse(str);
//            Date date1 = s.parse(s.format(new Date()));
//            if(date.before(date1)){
//                System.out.println("111");
//            } else {
//                System.out.println("222");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

       String s = "2020-02-16";
       Date date =null;
        try {
          date   = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DAY_OF_MONTH, -30);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
        System.out.println(endDate);

    }

    @Test
    public void cpmpare(){
        /*BigDecimal zero = BigDecimal.ZERO;

        BigDecimal prem = new BigDecimal("0.01");

        int a = prem.compareTo(zero);
        System.out.println(a);*/


        String s1 = "100.01";
        String s2 = "100";
        BigDecimal s11 = new BigDecimal(s1);
        BigDecimal s22 = new BigDecimal(s2);
        if(new BigDecimal(s1).compareTo(new BigDecimal(s2))>=0){

            System.out.println("1111");

        }
        System.out.println("2222");

    }

    @Test
     public void array(){
        int a =3;
        int b=4;
        if(a==b){
            System.out.println(222);
        }
        if(a!=b)
        {
            System.out.println(111);
        }

    }

    @Test
    public void testDate(){

        try{
//            Date  daate = new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toString()); 报错
//            System.out.println(daate);

            /*SimpleDateFormat dateFormat =   new SimpleDateFormat("yyyy-MM-dd");
            Object   d  = dateFormat.format(new Date());
            Date s  = dateFormat.parse(d.toString());
            System.out.println(s);*/

            LocalDate localDate = LocalDate.now().plusDays(1);
            System.out.println(localDate);

            LocalTime localTime = LocalTime.of(15,02);
            System.out.println(localTime);//15:02

            LocalTime nextHour = LocalTime.parse("15:02").plus(1, ChronoUnit.HOURS);
            System.out.println(nextHour);//16:02

        }catch (Exception e){
            e.printStackTrace();


         }
    }




    @Test
    public void mathTest(){
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        String b ="1990-09-10";
        try {
            Date d = simpleDateFormat.parse(b);
            int a = getAgeByBirth(d);
            System.out.println(a);
        }catch (ParseException e) {
            e.printStackTrace();
        }


    }
    private  int getAgeByBirth(Date birthday){
        //Calendar：日历
        /*从Calendar对象中或得一个Date对象*/
        Calendar cal = Calendar.getInstance();
        /*把出生日期放入Calendar类型的bir对象中，进行Calendar和Date类型进行转换*/
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);
        /*如果生日大于当前日期，则抛出异常：出生日期不能大于当前日期*/
        if(cal.before(bir)){
            throw new IllegalArgumentException("The birthday is before Now,It's unbelievable");
        }
        /*取出当前年月日*/
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        /*取出出生年月日*/
        int yearBirth = bir.get(Calendar.YEAR);
        int monthBirth = bir.get(Calendar.MONTH);
        int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
        /*大概年龄是当前年减去出生年*/
        int age = yearNow - yearBirth;
        /*如果出当前月小与出生月，或者当前月等于出生月但是当前日小于出生日，那么年龄age就减一岁*/
        if(monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)){
            age--;
        }
        return age;
    }

    @Test
    public void testInt(){
        System.out.println(new Integer(1)==null);

        Map<String,Integer> map = new HashMap<>();
        map.put("s",null);
        System.out.println(1==map.get("s"));

        System.out.println(Integer.valueOf(1).equals(map.get("s")));
    }


    private  int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            return 0;
        } else {
            int yearNow = cal.get(1);
            int monthNow = cal.get(2);
            int dayOfMonthNow = cal.get(5);
            cal.setTime(birthDay);
            int yearBirth = cal.get(1);
            int monthBirth = cal.get(2);
            int dayOfMonthBirth = cal.get(5);
            int age = yearNow - yearBirth;
            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        --age;
                    }
                } else {
                    --age;
                }
            }

            return age;
        }
    }





    public static void donation(Integer money, Consumer<Integer> consumer){
        consumer.accept(money);
    }

    public static Integer convert(String str, Function<String,Integer> function){
        return function.apply(str);
    }
    @Test
    public void testLambda(){
        donation(1000,money-> System.out.println("我捐赠了"+money));
        Integer vau = convert("28",x->Integer.parseInt(x));
        System.out.println(vau);
    }


    @Test
    public void testIo(){
        try (FileOutputStream o = new FileOutputStream("/market/res/q.jpg")){

        }catch (Exception e){
            System.out.println("QQQQQQQQQ报错了");
            e.printStackTrace();
        }

    }

    public static String getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
        String hehe = dateFormat.format(now);
        return hehe;
    }

    @Test
    public void testDebug(){
        List<Integer> list = Arrays.asList(1,2,4,6,7,8,9);
        for(Integer integer:list){
            System.out.println("integer = "+integer);
        }
    }

    @Test
    public void testCalendar(){

        String date = "20190801";
        int offset = 5;
        Calendar cal=Calendar.getInstance();
        cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))-1, Integer.parseInt(date.substring(6, 8)));
        cal.add(Calendar.DATE,offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");// 可以方便地修改日期格式
        String s = dateFormat.format(cal.getTime());
        System.out.println(s);
    }

    @Test
    public void testNull(){
        for (int i= 0;i<7;i++){
            if(i==1){

            }else if(i==3){

            }else{
                System.out.println("nihao i= "+i );
            }
        }
    }

    @Test
    public void testError(){
       int s = 3^2;
        System.out.println(s);
    }

    @Test
    public void listContains(){
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();

        l1.add("1");
        l1.add("2");
        l1.add("3");
        l1.add("4");
        l1.add("5");

        l2.add("2");
        l2.add("4");
        l2.add("5");

        if(l1.contains(l2)){
            System.out.println("22");
        }else {
            System.out.println("33");
        }
    }

    @Test
    public void testInteger(){
        int a = 1;
        Map<String,Integer> m = new HashMap<>();
        //m.put("count",a);
        //System.out.println(m.get("count").toString());

        if(m.get("count")!=null){
            if("1".equals(m.get("count").toString())){
                System.out.println("11");
            }else {
                System.out.println("2");
            }
        }

    }

    @Test
    public void test111(){
//        NumberFormat numberFormat = NumberFormat.getInstance();
//        numberFormat.setMaximumFractionDigits(2);
//
//        int a =1688;
//        int b =4;
//        String  c=numberFormat.format((float) (a-b) / (float)b * 100);
//        System.out.println(c);


    }

    @Test
    public void testCollectio(){
        List<Integer> list = Arrays.asList(4,2,1,6,9,12);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1,Integer i2) {
                int diff = Integer.valueOf(i1)-Integer.valueOf(i2);

                if(diff>0) {
                    return -1;
                }else if(diff<0) {
                    return 1;
                }
                return 0;
            }
        });

        list = list.subList(0,5);
        for(Integer i:list){
            System.out.println("@@@@ "+i);
        }

    }


    @Test
    public void testList() {

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
//        List<String> strings = new ArrayList<>();
//        strings.add("1");
//        strings.add("2");
//        strings.add("2");
//        strings.add("4");
//        strings.add("5");
//
//       Set s = Sets.newHashSet(strings);
//
//        System.out.println(strings.size()+" @@ "+s.size());
//
//        int a = 2;
//        int b = a++ + ++a * a++;
//        System.out.println("==a"+a+"==b"+b);

//        String s ="134";
//
//        BigDecimal s1 =new BigDecimal(s);
//        s1 = s1.multiply(new BigDecimal(10000)).multiply(new BigDecimal(0.7)).setScale(4,BigDecimal.ROUND_HALF_UP);
//        System.out.println(s1);
//
//        BigDecimal s2 = new BigDecimal(s) ;
//        s2 = s2.multiply(new BigDecimal(0.7)).setScale(4,BigDecimal.ROUND_HALF_UP);
//        System.out.println(s2);

//         Date date1 =new Date();
//         String s = "2020-07-16";
//
//        try {
//            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(s);
//
//            Long ss = date1.getTime()-date2.getTime();
//            String day = ss/(24*60*60*1000) +"";
//            System.out.println("day>>"+day);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }


        Double s = Double.parseDouble("0");
        String ss = Double.toString(s);
        BigDecimal a = new BigDecimal(ss);
        if (new BigDecimal("0").compareTo(a) == 0) {
            System.out.println("111");
        } else {
            System.out.println("2222");
        }

    }


    @Test
    public void testZiZeng() {

        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(dft.format(calendar.getTime()));


    }

    @Test
    public void testAge() {
        People people = new People();
        people.setId(12);

        People people1 =null;
        people1 = people;

        if(people==people1){
            System.out.println(people.hashCode());
            System.out.println(people1.hashCode());
        }else {

        }


    }


}

