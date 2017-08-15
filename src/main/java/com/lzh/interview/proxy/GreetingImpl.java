package com.lzh.interview.proxy;

/**
 * 直接写死了
 *
 * Created by lizhuohang on 17/8/15.
 */
public class GreetingImpl implements Greeting {
    public void sayHello(String name) {
        before();
        System.out.println("Hello! " + name);
        after();
    }

    private void before() {
        System.out.println("Before");
    }

    private void after() {
        System.out.println("after");
    }
}
