package com.QQClient;

import java.awt.*;
import java.security.acl.Owner;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class QQFriend extends JFrame implements ActionListener, MouseListener {

	// 第一个卡片
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2, jb3;
	JScrollPane jsp = null;

	// 第二个卡片
	JPanel jp4, jp5, jp6;
	JButton jb4, jb5, jb6;
	JScrollPane jsp1;
	CardLayout cl;
	SqlHelper sh;
	String owner;
	JLabel j[];

	public QQFriend(String ownerId) {

		this.owner = ownerId;
		// 第一个卡片
		jp1 = new JPanel(new BorderLayout()); // 总的
		jp2 = new JPanel(new GridLayout(20, 1, 4, 4)); // 列表的
		jp3 = new JPanel(new GridLayout(2, 1)); // 按钮的

		jb1 = new JButton("我的好友");
		jb1.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb2 = new JButton("陌生人");
		jb2.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb2.addActionListener(this);
		jb3 = new JButton("修改自己信息");
		jb3.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb3.addActionListener(this);

		jp3.add(jb2);
		jp3.add(jb3);

		j = new JLabel[20];

		for (int i = 0; i < j.length; i++) {

			j[i] = new JLabel(i + 1 + "", new ImageIcon(this.getClass().getResource("/images/qq.gif")), JLabel.LEFT);

			j[i].setFont(new Font("方正舒体", Font.BOLD, 16));

			j[i].setEnabled(false);

			if (j[i].getText().equals(owner)) {
				j[i].setEnabled(true);
			}

			j[i].addMouseListener(this);
			jp2.add(j[i]);
		}

		jsp = new JScrollPane(jp2);

		jp1.add(jb1, BorderLayout.NORTH);
		jp1.add(jsp, BorderLayout.CENTER);
		jp1.add(jp3, BorderLayout.SOUTH);

		// --------------------------------------------------------------------------
		// 第二个卡片
		jp4 = new JPanel(new BorderLayout());
		jp5 = new JPanel(new GridLayout(2, 1));
		jp6 = new JPanel(new GridLayout(15, 1, 4, 4));

		jb4 = new JButton("我的好友");
		jb4.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb4.addActionListener(this);
		jb5 = new JButton("陌生人");
		jb5.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb6 = new JButton("修改自己信息");
		jb6.addActionListener(this);
		jb6.setFont(new Font("方正舒体", Font.BOLD, 16));


		jp5.add(jb4);
		jp5.add(jb5);

		// 初始化10个陌生人
		JLabel jj[] = new JLabel[15];
		for (int i = 0; i < jj.length; i++) {
			jj[i] = new JLabel(i + 1 + "", new ImageIcon(this.getClass().getResource("/images/qq.gif")), JLabel.LEFT);
			jj[i].setFont(new Font("方正舒体", Font.BOLD, 16));

			jj[i].setEnabled(false);

			if (jj[i].getText().equals(owner)) {
				jj[i].setEnabled(true);
			}

			jj[i].addMouseListener(this);
			jp6.add(jj[i]);
		}

		jsp1 = new JScrollPane(jp6);

		jp4.add(jp5, BorderLayout.NORTH);
		jp4.add(jsp1, BorderLayout.CENTER);
		jp4.add(jb6, BorderLayout.SOUTH);

		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jp1, "1");
		this.add(jp4, "2");
		this.setTitle(ownerId);
		this.setSize(180, 420);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(400, 100);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());
	}

	public void updateFriend(Message m) {
		String onLine[] = m.getCon().split(" ");
		int num = 0;
		int i = 0;
		for (; i < onLine.length; i++) {
			j[Integer.parseInt(onLine[i]) - 1].setEnabled(true);
			num = i + 1;
			// System.out.println("当前在线人数为"+num+"人");

		}
		JOptionPane.showMessageDialog(this, "当前在线人数为" + num + "人");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb2) {
			cl.show(this.getContentPane(), "2");
		} else if (e.getSource() == jb4) {
			cl.show(this.getContentPane(), "1");
		}else if(e.getSource()==jb3||e.getSource()==jb6){
			new UpdateMessage(this.owner);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// 双击
		if(e.getButton()==e.BUTTON3){
			ShowMessage sm = new ShowMessage(this.owner,((JLabel) e.getSource()).getText());
		}
		if (e.getClickCount() == 2) {
		
			String friendNo = ((JLabel) e.getSource()).getText(); // 得到编号
			QqChat qqChat = new QqChat(owner, friendNo);

			ManageQqChat.addQqChat(this.owner + " " + friendNo, qqChat);

		}
		/*if (e.getClickCount() == 1) {
			

		}
		*/
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.RED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
