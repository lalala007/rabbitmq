package com.llj.tudou.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author llj
 * @date 2020/10/29 21:44
 *
 * 普通rabbitMq生产者
 */
public class SimpleProduer {

    private final static String EXCHANGE_NAME = "TUDOU_SIMPLE_EXCHANGE" ;

    public static void main(String[] args){
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //进行配置
        //连接IP
        factory.setHost("127.0.0.1");
        //连接端口
        factory.setPort(5672);
        //虚拟机 rabbitmq会有一个默认的虚拟机“/”
        factory.setVirtualHost("/");
        //用户
        factory.setUsername("guest");
        factory.setPassword("guest");

        //建立连接
        Connection connection = null ;

        //消息通道
        Channel channel = null ;

        try {
            connection = factory.newConnection();
            //创建消息通道
            channel = connection.createChannel();

            //
            String msg = "hello tudou,Rabbit MQ" ;

            channel.basicPublish(EXCHANGE_NAME,"tudou.best",null,msg.getBytes());
        }catch (Exception e){

        }finally {
            if (null!=channel){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (null!=connection){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


















