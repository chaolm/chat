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
		jb = new JButton("����");
		jb.setFont(new Font("��������", Font.BOLD, 16));

		jb.addActionListener(this);
		jb1 = new JButton("�鿴�����¼");
		jb1.setFont(new Font("��������", Font.BOLD, 16));

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
		this.setTitle(ownerId + " ���ں� " + friend + " ����");
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());

		this.setLocation(350, 150);
	}
	//������������������Ϣ��������ܿ���
	public void show(Message m) {
		String info = m.getSendTime() + "\r\n" + m.getSender()+"����˵��"+m.getCon() + "\r\n";
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
				JOptionPane.showMessageDialog(this, "������Ϣ����Ϊ��!");
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			m.setSendTime(sdf.format(new java.util.Date()));
			// ������Ϣ
			SaveMessage.save(ownerId + friendId, jtf.getText(), sdf.format(new java.util.Date()));
			StringBuffer sb = new StringBuffer();

			try {
				ObjectOutputStream oos = new ObjectOutputStream(SaveSocket.get(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				// ���ͳɹ�����Ϣ�ƿ�
				
				// ����ϢҲ�����Լ��ı�����
				String st = sdf.format(new java.util.Date()) + ":" + "\r\n"+ "�Ҷ�" + friendId + "˵�� " + jtf.getText() + "\r\n";
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
