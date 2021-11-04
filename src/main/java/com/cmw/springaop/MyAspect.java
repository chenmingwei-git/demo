package com.cmw.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @autor:  cmw
 * @create:
 * @description: 定义切面
 */

@Aspect
public class MyAspect {


    /**
     * execution ：表示在执行的时候 拦截里面的正则匹配的方法
     * * ：表示任意返回类型的方法
     * com.cmw.springaop.UserServiceImpl： 指定目标对象的全限定名称
     * printUser：指定目标对象的方法
     * (..) : 表示任意参数进行匹配
     *
     *
     * 定义完该方法切点,后续方法只需要引用该方法名 就可以引用该切点
     * */
    @Pointcut("execution(* com.cmw.springaop.BaseServiceImpl.printUser(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(){
        System.out.println("before.....");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after.....");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning.....");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing.....");
    }

    //环绕通知
    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("around before.....");
        //回调目标对象的原有方法
        jp.proceed();
        System.out.println("around after....");
    }
}
