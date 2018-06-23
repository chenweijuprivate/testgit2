package com.itmayiedu.activemq.test002;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.support.StaticApplicationContext;

public class ProducerTest002 {

	public static void main(String[] args) throws JMSException {
		//获取mq连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		//创建连接
		Connection connection=connectionFactory.createConnection();
		
		//打开连接
		connection.start();
		
		//创建会话
		Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		
		//创建队列
		//Destination destination=session.createTopic("itmayiedu_topic");
		
		MessageProducer producer=session.createProducer(null);
		
		//不持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		for(int i=1;i<=5;i++){
			System.out.println("我是生产者："+i);
			sendMsg(session, producer, "我是生产者："+i);
		}
		System.out.println("生产者生产消息完毕！");
		
	}
	
	public static void sendMsg(Session session,MessageProducer producer,String i) throws JMSException{
		TextMessage textMessage=session.createTextMessage("hello activemq"+i);
		Destination destination=session.createTopic("itmayiedu_topic");
		producer.send(destination,textMessage);
	} 
}
