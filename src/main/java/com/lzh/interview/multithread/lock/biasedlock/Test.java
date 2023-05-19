package com.lzh.interview.multithread.lock.biasedlock;

/**
 * @author lizhuohang <lizhuohang@kuaishou.com>
 * Created on 2021-04-02
 */
public class Test {


    public static void main(String[] args) {
        char a = '0';
        int i = a;
        System.out.println(binaryToDecimal(i));
    }

    public static int binaryToDecimal(int n){
        int t = 0;  //用来记录位数
        int bin = 0; //用来记录最后的二进制数
        int r = 0;  //用来存储余数
        while(n != 0){
            r = n % 2;
            n = n / 2;
            bin += r * Math.pow(10,t);
            t++;
        }
        return bin;
    }


}
