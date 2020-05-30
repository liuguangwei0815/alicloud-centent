package com.alibaba.cententmyali.utils;

import java.util.Date;

/**
 * 字符串工具类
 *
 * @author liuwei
 * @date 2020/05/08
 */
public class StringUtil {

    /**
     * 得到前的数字
     *
     * @param number 数量
     * @param digits 数字
     * @return {@link String}
     */
    public static String getForeNumber(int number, int digits) {
        return String.format("%0" + String.valueOf(digits) + "d", number);
    }

    /**
     * 得到前空格
     *
     * @param number 数量
     * @param digits 数字
     * @return {@link String}
     */
    public static String getForeTrim(int number, int digits) {
        return String.format("%" + String.valueOf(digits) + "d", number);
    }

    /**
     * 得到钱的格式
     *
     * @param number 数量
     * @return {@link String}
     */
    public static String getMoneyFormat(int number) {
        return String.format("%,d", number);
    }

    /**
     * 格式化str位数
     *
     * @return {@link String}
     */
    public static String getFromStr(String str, int digits) {
        return String.format("%1$" + digits + "s", str);
    }

    /**
     * 当前时间
     *
     * @return {@link String}
     */
    public static String getDataForamt() {
        return String.format("%1$tF %1$tT", new Date());
    }


}
