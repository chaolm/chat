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
	private Desktop desktop = Desktop.getDesktop(); // ��ָ��������
	// ���山����Ҫ�����
	JLabel jlb, jlb2, jlb3, jlb4, jlb5;
	JTextField jtf1, jtf2;
	// �����в������
	// JTabbedPane jtp; ��Ƭ���� ����û������ jtp.add("qq����",jp2);
	JPanel jp2, jp3, jp4;
	// �����ϲ������
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

		// ����
		jlb = new JLabel(new ImageIcon(this.getClass().getResource("/images/log554.png")));

		// �в�
		jp2 = new JPanel();
		jlb2 = new JLabel("�˺š�:", JLabel.CENTER);
		jlb2.setFont(new Font("��������", Font.BOLD, 16));

		jlb3 = new JLabel("���롡:", JLabel.CENTER);
		jlb3.setFont(new Font("��������", Font.BOLD, 16));
		jlb4 = new JLabel("�����˺�");
		jlb4.setFont(new Font("��������", Font.BOLD, 16));

		jlb4.setForeground(Color.BLUE);
		jlb4.setCursor(c);
		jlb4.addMouseListener(this);
		jlb5 = new JLabel("ɾ���˺�");
		jlb5.setFont(new Font("��������", Font.BOLD, 16));

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
		// �ϲ�
		jb1 = new JButton("��¼");
		jb1.setFont(new Font("��������", Font.BOLD, 16));

		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
		jb2.setFont(new Font("��������", Font.BOLD, 16));

		jb3 = new JButton("�޸�����");
		jb3.setFont(new Font("��������", Font.BOLD, 16));
		jb3.addActionListener(this);

		jb2.addActionListener(this);

		jp1 = new JPanel();

		this.add(jlb, BorderLayout.NORTH);

		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);

		this.add(jp1, BorderLayout.SOUTH);
		this.setSize(280, 230);
		this.setTitle("��������");
		this.setLocation(430, 250);
		this.setVisible(true);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ������ݿ����Ƿ�������û�
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
				JOptionPane.showMessageDialog(this, "�û������������!");

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
				JOptionPane.showMessageDialog(this, "�û�������Ϊ��!");
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
						// ����һ��Ҫ�󷵻����ߺ��ѵ������
						ObjectOutputStream oos2 = new ObjectOutputStream(
								SaveSocket.get(u.getUserId()).getS().getOutputStream());
						// ��һ��Message
						Message me = new Message();
						me.setMesType(MessageType.message_get_onLineFriend);
						// ָ����Ҫ�������qq�ŵĺ������
						me.setSender(u.getUserId());
						oos2.writeObject(me);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.dispose();

				} /*
					 * else { JOptionPane.showMessageDialog(this, "�û������������!");
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
				// ������ַ������
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
			jlb4.setText("<html><a href='#'>�����˺�</a></html>");
		} else if (e.getSource() == jlb5) {
			jlb5.setText("<html><a href='http://id.qq.com'>ɾ���˺�</a></html>");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jlb4) {
			jlb4.setText("�����˺�");
		} else if (e.getSource() == jlb5) {
			jlb5.setText("ɾ���˺�");
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
