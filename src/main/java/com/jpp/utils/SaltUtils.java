package com.jpp.utils;

import java.util.Random;

/**
 * @author lcv8
 * @data 2021.02.19
 */
public class SaltUtils {

    /**
     * 生成salt的静态方法
     * */
    public static String getSalt(Integer num){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i = 0; i < num; i++) {
            char code = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(code);
        }
        return stringBuilder.toString();
    }
}
