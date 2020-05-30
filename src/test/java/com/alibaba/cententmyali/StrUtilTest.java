package com.alibaba.cententmyali;

import com.alibaba.cententmyali.utils.StringUtil;

/**
 * str util测试
 *
 * @author liuwei
 * @date 2020/05/08
 */
public class StrUtilTest {

    public static void main(String[] args) {
        System.out.println(StringUtil.getForeNumber(3, 4));
        //System.out.println(String.format("%04d", 12));
        System.out.println(StringUtil.getForeTrim(3, 4));
        System.out.println(StringUtil.getMoneyFormat(3000));
        System.out.println(StringUtil.getFromStr("3", 4));
        System.out.println(StringUtil.getDataForamt());
    }

}
