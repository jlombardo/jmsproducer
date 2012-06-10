package edu.wctc.jms.demo1.jmsproducer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
	private static int msgCount = 0;
	private JPanel contentPane;
	private JTextField txtMessage;
    ApplicationContext ctx;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		setTitle("Message Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 125);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Message");
		lblNewLabel.setBounds(10, 14, 86, 14);
		contentPane.add(lblNewLabel);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(97, 11, 204, 20);
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send Message");
		this.getRootPane().setDefaultButton(btnSend);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSendBtn();
			}
		});
		btnSend.setBounds(97, 43, 118, 23);
		contentPane.add(btnSend);
	}
	
	private void handleSendBtn() {
		String msg = this.txtMessage.getText();       
        JmsQueueSender sender = (JmsQueueSender)ctx.getBean("messageSender");
//        sender.simpleSend(msg);
        sender.convertAndSend(msg);
        System.out.println("Message " + ++msgCount + " sent");
	}
}
