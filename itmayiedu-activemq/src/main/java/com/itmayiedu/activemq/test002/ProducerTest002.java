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
		//��ȡmq���ӹ���
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		//��������
		Connection connection=connectionFactory.createConnection();
		
		//������
		connection.start();
		
		//�����Ự
		Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		
		//��������
		//Destination destination=session.createTopic("itmayiedu_topic");
		
		MessageProducer producer=session.createProducer(null);
		
		//���־û�
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		for(int i=1;i<=5;i++){
			System.out.println("���������ߣ�"+i);
			sendMsg(session, producer, "���������ߣ�"+i);
		}
		System.out.println("������������Ϣ��ϣ�");
		
	}
	
	public static void sendMsg(Session session,MessageProducer producer,String i) throws JMSException{
		TextMessage textMessage=session.createTextMessage("hello activemq"+i);
		Destination destination=session.createTopic("itmayiedu_topic");
		producer.send(destination,textMessage);
	} 
}
