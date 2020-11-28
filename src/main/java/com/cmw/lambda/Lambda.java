package com.cmw.lambda;

/**
 * @autor: Mr.Chen
 * @create:
 * @description: Lambda表达式练习
 */
public class Lambda {
    public static void main(String[] args) {
        Lambda lambda = new Lambda();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + lambda.operate(10, 5, addition)); //10 + 5 = 15
        System.out.println("10 - 5 = " + lambda.operate(10, 5, subtraction));//10 - 5 = 5
        System.out.println("10 x 5 = " + lambda.operate(10, 5, multiplication));//10 x 5 = 50
        System.out.println("10 / 5 = " + lambda.operate(10, 5, division));//10 / 5 = 2

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);//Hello Runoob

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);//Hello Google

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

}

interface MathOperation {
    int operation(int a, int b);
}

interface GreetingService {
    void sayMessage(String message);
}


interface A extends Runnable,MathOperation{

}

