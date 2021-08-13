package com.cmw.keywords;


import java.sql.Date;

/**
 * @description: final关键字 static关键字
 * @author: cmw
 * @data: 2021/8/13
 */
public class FinalKeywords {
    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        final String f = getHello();
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        String h = f + 2;
        /*true 当final变量是基本数据类型以及String类型时，如果在编译期间能知道它的确切值，
         则编译器会把它当做编译期常量使用。也就是说在用到该final变量的地方，相当于直接访问的这个常量*/
        System.out.println((a == c));
        //false 同上 只有在编译期知道确切值时才会被当做常量 运行期不会
        System.out.println((a == h));
        //false
        System.out.println((a == e));


        final MyClass m = new MyClass();
        // 输出'2' 引用变量被final修饰之后，虽然不能再指向其他对象，但是它指向的对象的内容是可变的
        System.out.println(++m.i);

        MyCa myCa1 = new MyCa();
        MyCa myCa2 = new MyCa();

        //final 和 static的区别  用static修饰的变量被所有的对象所共享，在内存中只有一个副本
        //final 修饰的同一个对象调用时相同的  不同对象调用则不同
        System.out.println(myCa1.i);
        System.out.println(myCa1.i);
        System.out.println(myCa1.j);
        System.out.println(myCa2.i);
        System.out.println(myCa2.j);

    }

    public static String getHello() {
        return "hello";
    }
}

class MyClass {
    public int i = 1;
}


class MyCa {
    public final double i = Math.random();
    public static double j = Math.random();
}

/*  static关键字还有一个比较关键的作用就是 用来形成静态代码块以优化程序性能
    static块可以置于类中的任何地方，类中可以有多个static块。
    在类初次被加载的时候，会按照static块的顺序来执行每个static块，并且只会执行一次
    isBornBoomer是用来这个人是否是1946-1964年出生的，而每次isBornBoomer被调用的时候，
    都会生成startDate和birthDate两个对象，造成了空间浪费  修改成PersonAfter提升效率*/
class PersonBefore {
    private Date birthDate;

    public PersonBefore(Date birthDate) {
        this.birthDate = birthDate;
    }

    boolean isBornBoomer() {
        Date startDate = Date.valueOf("1946");
        Date endDate = Date.valueOf("1964");
        return birthDate.compareTo(startDate) >= 0 && birthDate.compareTo(endDate) < 0;
    }
}

class PersonAfter {
    private Date birthDate;

    public PersonAfter(Date birthDate) {
        this.birthDate = birthDate;
    }

    private static Date startDate, endDate;

    static {
        startDate = Date.valueOf("1946");
        endDate = Date.valueOf("1964");
    }

    boolean isBornBoomer() {
        return birthDate.compareTo(startDate) >= 0 && birthDate.compareTo(endDate) < 0;
    }
}
