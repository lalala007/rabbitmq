package com.llj.tudou.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * @author llj
 * @date 2020/10/29 21:37
 */
public class ResourceUtil {

    private static Logger log = LoggerFactory.getLogger(ResourceUtil.class) ;

    private static final ResourceBundle resourceBundle ;

    static {
        resourceBundle = ResourceBundle.getBundle("config")  ;
    }

    private static String getKey(String key){
        return resourceBundle.getString(key) ;
    }

    public static void main(String[] args){
        System.out.println(ResourceUtil.getKey("rabbitmq.uri")+"----------");
        log.info(ResourceUtil.getKey("rabbitmq.uri")+"----------");
        log.info("结束");
    }

}
