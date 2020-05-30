package com.alibaba.cententmyali.utils;

import java.util.regex.Pattern;

/**
 * validata跑龙套
 *
 * @author liuwei
 * @date 2020/05/13
 */
public class ValidataUtil {

    public static final Pattern Match = Pattern.compile("1\\d{10}");

    /**
     * validata电话
     *
     * @return boolean
     */
    public static boolean validataPhone(String phone){
        return Match.matcher(phone).matches();
    }

}
