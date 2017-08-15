package com.lzh.interview.proxy;

/**
 * 静态代理
 * Created by lizhuohang on 17/8/15.
 */
public class GreetingProxy implements Greeting {

    private GreetingImpl greetingImpl;

    public GreetingProxy(GreetingImpl greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    public void sayHello(String name) {
        before();
        greetingImpl.sayHello(name);
        after();
    }

    private void before() {
        System.out.println("Before Static Proxy");
    }

    private void after() {
        System.out.println("After Static Proxy");
    }
}
