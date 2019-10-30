package com.QQClient;

import java.awt.*;
import com.QQServer.*;
import java.util.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class QqChat extends JFrame implements ActionListener {

	JTextArea jta;
	JTextField jtf;
	JScrollPane jsp;
	JButton jb, jb1;
	JPanel jp;
	String ownerId;
	String friendId;

	public QqChat(String ownerId, String friend) {
		this.ownerId = ownerId;
		this.friendId = friend;

		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("发送");
		jb.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb.addActionListener(this);
		jb1 = new JButton("查看聊天记录");
		jb1.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb1.addActionListener(this);
		jp = new JPanel();

		jsp = new JScrollPane(jta);

		jp.add(jtf);
		jp.add(jb);
		jp.add(jb1);
		this.add(jsp, BorderLayout.CENTER);
		this.add(jp, BorderLayout.SOUTH);

		this.setSize(430, 250);
		this.setVisible(true);
		this.setTitle(ownerId + " 正在和 " + friend + " 聊天");
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());

		this.setLocation(350, 150);
	}
	//将服务器传过来的信息输出到接受框中
	public void show(Message m) {
		String info = m.getSendTime() + "\r\n" + m.getSender()+"对我说："+m.getCon() + "\r\n";
		jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb) {
			Message m = new Message();
			m.setMesType(MessageType.message_comm);
			m.setSender(ownerId);
			m.setGetter(friendId);
			m.setCon(jtf.getText());
			try {
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			if("".equals(jtf.getText())){
				JOptionPane.showMessageDialog(this, "发送信息不能为空!");
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			m.setSendTime(sdf.format(new java.util.Date()));
			// 保存信息
			SaveMessage.save(ownerId + friendId, jtf.getText(), sdf.format(new java.util.Date()));
			StringBuffer sb = new StringBuffer();

			try {
				ObjectOutputStream oos = new ObjectOutputStream(SaveSocket.get(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				// 发送成功后将消息制空
				
				// 将消息也加入自己文本框中
				String st = sdf.format(new java.util.Date()) + ":" + "\r\n"+ "我对" + friendId + "说： " + jtf.getText() + "\r\n";
				sb.append(st);
				jta.setText(sb + "");
				jtf.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == jb1) {
			new LookHistoryMess(ownerId, friendId);

		}

	}
}
