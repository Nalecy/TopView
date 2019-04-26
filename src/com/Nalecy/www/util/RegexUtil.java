package com.Nalecy.www.util;

import java.util.regex.Pattern;

/**
 * 正则表达式匹配工具
 */
public final class RegexUtil {
    private RegexUtil(){
        throw  new AssertionError("请勿实例化ServiceFactory");
    }

    private final static String TELEPHONE_REGEX = "^[0-9]{11}$";
    private final static String ID_CARD_REGEX = "[1-9]\\d{13,16}[xX0-9]{1}$";
    private final static String USERNAME_REGEX = "^[0-9a-zA-Z_]{5,12}$";
    private final static String NUMBER_REGEX = "^[1-9]\\d*$";
    private final static String PASSWORD_REGEX ="^[0-9a-zA-Z_]{6,12}$";
    private final static String ZH_REGEX = "^[\\u4e00-\\u9fa5]+$";
    private final static String ONE_TO_THREE_REGEX = "^[1-3]$";
    private final static String ONE_TO_FIVE_REGEX = "^[1-5]$";




    public static boolean isTelephone(String string) {
        return Pattern.matches(TELEPHONE_REGEX, string);
    }

    public static boolean isIdCard(String string) {
        return Pattern.matches(ID_CARD_REGEX, string);
    }

    public static boolean isUserName(String string) {
        return Pattern.matches(USERNAME_REGEX, string);
    }

    public static boolean isNumber(String string) {
        return Pattern.matches(NUMBER_REGEX, string);
    }

    public static boolean isPassword(String string) {
        return Pattern.matches(PASSWORD_REGEX, string);
    }

    public static boolean isOneToThree(String string) {
        return Pattern.matches(ONE_TO_THREE_REGEX, string);
    }

    public static boolean isZh(String string) {
        return Pattern.matches(ZH_REGEX, string);
    }

    public static boolean isOneToFive(String string) {
        return Pattern.matches(ONE_TO_FIVE_REGEX, string);
    }
}
