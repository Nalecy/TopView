package com.Nalecy.www.constantClass;

/** 用于表示订单是否已被评论的状态 */
public enum IsComment{
    /** 表示未订单评论 */
    NO,
    /** 表示订单已评论 */
    YES;

    public static IsComment get(int ordinal){
        switch (ordinal){
            case 0:return NO;
            case 1:return YES;
            default: throw new IndexOutOfBoundsException("枚举下标越界");
        }
    }
}
