package com.lzh.interview.proxy;

/**
 * Created by lizhuohang on 17/8/15.
 */
public class Main {
    public static void main(String[] args) {
        Greeting greeting = new GreetingImpl();
        greeting.sayHello("Li");

        System.out.println("----------------------");

        Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
        greetingProxy.sayHello("Li");

        System.out.println("----------------------");

        Greeting jdkGreeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
        jdkGreeting.sayHello("Li");

        System.out.println("----------------------");

        Greeting cglibGreeting = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
        cglibGreeting.sayHello("Li");

        System.out.println("----------------------");
    }
}
