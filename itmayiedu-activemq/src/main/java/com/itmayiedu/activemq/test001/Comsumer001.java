package com.itmayiedu.activemq.test001;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Comsumer001 {

	public static void main(String[] args) throws JMSException {
		// 获取mq连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// 创建连接
		Connection connection = connectionFactory.createConnection();

		// 打开连接
		connection.start();

		// 创建会话
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		// 创建队列
		Destination destination = session.createQueue("itmayiedu_queue");

		MessageConsumer consumer = session.createConsumer(destination);
		while (true) {
			TextMessage textMessage=(TextMessage)consumer.receive();
			if(textMessage != null){
				String text=textMessage.getText();
				System.out.println("消费者获取信息 text:"+text);
			}else{
				break;
			}
			
		}
	}
}
