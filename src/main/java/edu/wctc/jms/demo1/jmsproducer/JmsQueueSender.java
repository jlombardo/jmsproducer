package edu.wctc.jms.demo1.jmsproducer;

import java.util.*;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

public class JmsQueueSender {

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setConnectionFactory(ConnectionFactory cf) {
        this.jmsTemplate = new JmsTemplate(cf);
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void simpleSend(String msg) {
	    this.jmsTemplate.send(new MyMessageCreator(msg));
    }
    
    public void convertAndSend(String msg) {
    	Map map = new HashMap();
        map.put("Name", "Mark");
        map.put("Age", new Integer(47));
        map.put("Note", msg);
        jmsTemplate.convertAndSend("sample.byteshop.queue", map, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                message.setIntProperty("AccountID", 1234);
                message.setJMSCorrelationID("123-00001");
                return message;
            }
        });
    }

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Queue getQueue() {
		return queue;
	}
    
    
}