package com.llj.tudou.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author llj
 * @date 2020/10/29 21:55
 * 普通rabbitmq消费者
 */
public class SimpleConsumer {

    private final static String EXCHANGE_NAME = "TUDOU_SIMPLE_EXCHANGE" ;
    private final static String QUEUE_NAME = "TUDOU_SIMPLE_QUEUE" ;

    public static void main(String[] args) throws IOException, TimeoutException {

        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //连接IP
        factory.setHost("127.0.0.1");

        factory.setPort(5672);

        factory.setVirtualHost("/");

        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = null ;
        Channel channel = null ;
            //创建连接
            connection = factory.newConnection();

            //创建消息通道
            channel = connection.createChannel();

            //声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",false,false,null) ;

            //声明队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null) ;

            System.out.println("Waiting for message...");

            //绑定队列和交换机
            channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"tudou.best") ;

            //创建消费者
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"UTF-8") ;
                    System.out.println("Received message : '" + msg + "'");
                    System.out.println("consumerTag : " + consumerTag );
                    System.out.println("deliveryTag : " + envelope.getDeliveryTag() );
                }
            };

            //开始获取消息
            channel.basicConsume(QUEUE_NAME,true,consumer) ;

            //关闭了channel和connection就无法模拟消费消息，程序会直接跑结束。
//            channel.close();
//            connection.close();

    }

}

























