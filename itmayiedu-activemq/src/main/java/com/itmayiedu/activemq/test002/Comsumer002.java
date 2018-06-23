package com.itmayiedu.activemq.test002;

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

public class Comsumer002 {

	public static void main(String[] args) throws JMSException {
		System.out.println("������002");
		// ��ȡmq���ӹ���
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		// ��������
		Connection connection = connectionFactory.createConnection();

		// ������
		connection.start();

		// �����Ự
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

		// ��������
		Destination destination = session.createTopic("itmayiedu_topic");

		MessageConsumer consumer = session.createConsumer(destination);
		while (true) {
			TextMessage textMessage=(TextMessage)consumer.receive();
			if(textMessage != null){
				String text=textMessage.getText();
				System.out.println("�����߻�ȡ��Ϣ text:"+text);
			}else{
				break;
			}
			
		}
	}
}
