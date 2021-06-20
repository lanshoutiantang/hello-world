package org.jeecg;

import org.junit.Test;

import java.util.Arrays;

public class ceshi3 {
    @Test
    public  void mainm() {
        String string = "aabbdddeee";
        char[] chars = string.toCharArray();
        Arrays.sort(chars);
        for(int i=0;i<string.length();i++){
            int num = 1;
            while(i+1<string.length() && chars[i] == chars[i+1]){
                num++;
            }
            System.out.println(chars[i]+" : " +num);
            System.out.println();
        }
    }
}
