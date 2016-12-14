package com.lzh.interview.jdk;

/**
 * Created by lizhuohang on 2016/12/14.
 */
public class UseOfMath {
    /**
     * the Math.floor return the largest (closest to positive infinity)
     *          floating-point value that less than or equal to the argument
     *          and is equal to a mathematical integer.
     *
     * you can obtain results by the following method
     *      temp = input_value - 0.5
     *      returns = the nearest int value of temp
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Math.floor(20.5));
        System.out.println(Math.floor(-20.5));
    }
}
