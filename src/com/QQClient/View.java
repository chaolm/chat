package com.QQClient;

import java.awt.*;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;

import com.QQServer.MyQqServer;

import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class View extends JFrame implements ActionListener, MouseListener {

	URI uri1;
	private Desktop desktop = Desktop.getDesktop(); // 打开指定的连接
	// 定义北部需要的组件
	JLabel jlb, jlb2, jlb3, jlb4, jlb5;
	JTextField jtf1, jtf2;
	// 定义中部的组件
	// JTabbedPane jtp; 卡片布局 这里没有用上 jtp.add("qq号码",jp2);
	JPanel jp2, jp3, jp4;
	// 定义南部的组件
	JPanel jp1;
	JButton jb1, jb2, jb3;
	static User u;

	SqlHelper sh;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View v = new View();
		u = new User();
	}

	public View() {
		Cursor c = new Cursor(Cursor.HAND_CURSOR);

		// 北部
		jlb = new JLabel(new ImageIcon(this.getClass().getResource("/images/log554.png")));

		// 中部
		jp2 = new JPanel();
		jlb2 = new JLabel("账号　:", JLabel.CENTER);
		jlb2.setFont(new Font("方正舒体", Font.BOLD, 16));

		jlb3 = new JLabel("密码　:", JLabel.CENTER);
		jlb3.setFont(new Font("方正舒体", Font.BOLD, 16));
		jlb4 = new JLabel("申请账号");
		jlb4.setFont(new Font("方正舒体", Font.BOLD, 16));

		jlb4.setForeground(Color.BLUE);
		jlb4.setCursor(c);
		jlb4.addMouseListener(this);
		jlb5 = new JLabel("删除账号");
		jlb5.setFont(new Font("方正舒体", Font.BOLD, 16));

		jlb5.setForeground(Color.BLUE);
		jlb5.setCursor(c);
		jlb5.addMouseListener(this);

		jtf1 = new JTextField(15);
		jtf2 = new JPasswordField(15);

		jp2.add(jlb2);
		jp2.add(jtf1);
		jp2.add(jlb3);
		jp2.add(jtf2);
		jp2.add(jlb4);
		jp2.add(jlb5);

		this.add(jp2, BorderLayout.CENTER);
		// 南部
		jb1 = new JButton("登录");
		jb1.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb3 = new JButton("修改密码");
		jb3.setFont(new Font("方正舒体", Font.BOLD, 16));
		jb3.addActionListener(this);

		jb2.addActionListener(this);

		jp1 = new JPanel();

		this.add(jlb, BorderLayout.NORTH);

		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);

		this.add(jp1, BorderLayout.SOUTH);
		this.setSize(280, 230);
		this.setTitle("开心聊天");
		this.setLocation(430, 250);
		this.setVisible(true);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 检查数据库里是否有这个用户
	public String checkUser(String uid, String p) {
		String id = "";
		try {
			String sql = "select Id from login where Id = ? and Password = ?";

			String paras[] = { uid, p };
			sh = new SqlHelper();
			ResultSet rs = sh.query(sql, paras);

			if (rs.next()) {
				id = rs.getString(1);
			} else {
				JOptionPane.showMessageDialog(this, "用户名或密码错误!");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sh.close();
		}
		return id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			String name = jtf1.getText().trim();
			String pass1 = jtf2.getText().trim();
			String pass = Md5.MD5(pass1);

			if (name.equals("")) {
				JOptionPane.showMessageDialog(this, "用户名不能为空!");
			} else {
				String id = checkUser(name, pass);
				u.setUserId(name);
				u.setPassWd(pass);
				if (name.equals(id)) {
					QQFriend qf = new QQFriend(u.getUserId());
					ManageQqFriend.add(u.getUserId(), qf);
					MyQqClient m = new MyQqClient();
					try {
						ObjectOutputStream oos = new ObjectOutputStream(m.s.getOutputStream());
						oos.writeObject(u);
						// 发送一个要求返回在线好友的请求包
						ObjectOutputStream oos2 = new ObjectOutputStream(
								SaveSocket.get(u.getUserId()).getS().getOutputStream());
						// 做一个Message
						Message me = new Message();
						me.setMesType(MessageType.message_get_onLineFriend);
						// 指明我要的是这个qq号的好友情况
						me.setSender(u.getUserId());
						oos2.writeObject(me);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.dispose();

				} /*
					 * else { JOptionPane.showMessageDialog(this, "用户名或密码错误!");
					 * }
					 */
			}
		} else if (e.getSource() == jb2) {
			this.dispose();
		} else if (e.getSource() == jb3) {
			new UpdatePassword();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jlb5) {
			try {
				// 定义网址的内容
				new DeleteView();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("zheli");
			}

		} else if (e.getSource() == jlb4) {
			new Register();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jlb4) {
			jlb4.setText("<html><a href='#'>申请账号</a></html>");
		} else if (e.getSource() == jlb5) {
			jlb5.setText("<html><a href='http://id.qq.com'>删除账号</a></html>");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jlb4) {
			jlb4.setText("申请账号");
		} else if (e.getSource() == jlb5) {
			jlb5.setText("删除账号");
		}
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
