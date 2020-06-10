package com.alibaba.cententmyali.configure;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import ribbonconfiguration.RibbonRuleConfuguration;

/**
 * 用户中心  ribbobn控制 这里采用的是随机
 *  RibbonClient{name:这个是注册中心的名称，configuration 这个需要定义在com包外}
 * @author liuwei
 * @date 2020/05/31
 */
//@RibbonClient(name = "user-center" ,configuration = {RibbonRuleConfuguration.class}) //针对单个

//@RibbonClients(defaultConfiguration = {RibbonRuleConfuguration.class})

public class UserSenterRibbonConfiguration {
}
