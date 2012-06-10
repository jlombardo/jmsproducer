package edu.wctc.jms.demo1.jmsproducer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class MyMessageCreator implements MessageCreator {
	private String msg;

	public MyMessageCreator() {

	}
	
	public MyMessageCreator(String msg) {
		this.msg = msg;
	}

	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage(msg);
	}

}
