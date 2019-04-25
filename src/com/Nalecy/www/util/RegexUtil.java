package com.Nalecy.www.util;

import java.util.regex.Pattern;

/**
 * 正则表达式匹配工具
 */
public final class RegexUtil {
    private RegexUtil(){
        throw  new AssertionError("请勿实例化ServiceFactory");
    }
    public static boolean isTelephone(String string) {
        return Pattern.matches("^[0-9]{11}$", string);
    }

    public static boolean isIdCard(String string) {
        return Pattern.matches("[1-9]\\d{13,16}[xX0-9]{1}$", string);
    }

    public static boolean isUserName(String string) {
        return Pattern.matches("^[0-9a-zA-Z_]{5,12}$", string);
    }

    public static boolean isNumber(String string) {
        return Pattern.matches("^[1-9]\\d*$", string);
    }

    public static boolean isPassword(String string) {
        return Pattern.matches("^[0-9a-zA-Z_]{6,12}$", string);
    }

    public static boolean isOneToThree(String string) {
        return Pattern.matches("^[1-3]$", string);
    }

    public static boolean isZh(String string) {
        return Pattern.matches("^[\\u4e00-\\u9fa5]+$", string);
    }

    public static boolean isOneToFive(String string) {
        return Pattern.matches("^[1-5]$", string);
    }
}
