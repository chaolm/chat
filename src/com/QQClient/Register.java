package com.QQClient;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;

public class Register extends JFrame implements ActionListener {
	JLabel jl1, jl2, jl3, jl4, jl5;
	JTextField jtf1, jtf2, jtf5;
	JPasswordField jtf3, jtf4;
	JButton jb1, jb2;
	JPanel jp1, jp2, jp3;
	SqlHelper sh;

	public Register() {
		jp1 = new JPanel();
		jl1 = new JLabel(" ���� :");
		jl1.setFont(new Font("��������", Font.BOLD, 16));
		jl2 = new JLabel(" ���� :");
		jl2.setFont(new Font("��������", Font.BOLD, 16));
		jl3 = new JLabel(" �Ա� :");
		jl3.setFont(new Font("��������", Font.BOLD, 16));

		jl4 = new JLabel(" ���� :");
		jl4.setFont(new Font("��������", Font.BOLD, 16));

		jl5 = new JLabel(" ȷ������ :");
		jl5.setFont(new Font("��������", Font.BOLD, 16));

		jp2 = new JPanel();
		jtf1 = new JTextField(15);
		jtf2 = new JTextField(15);
		jtf5 = new JTextField(15);
		jtf3 = new JPasswordField(15);
		jtf4 = new JPasswordField(15);

		jp3 = new JPanel();
		jb1 = new JButton("ȷ��");
		jb1.setFont(new Font("��������", Font.BOLD, 16));

		jb1.addActionListener(this);
		jb2 = new JButton("����");
		jb2.setFont(new Font("��������", Font.BOLD, 16));

		jb2.addActionListener(this);

		jp1.add(jl1);
		jp2.add(jtf1);
		jp1.add(jl2);
		jp2.add(jtf2);
		jp1.add(jl3);
		jp2.add(jtf5);
		jp1.add(jl4);
		jp2.add(jtf3);
		jp1.add(jl5);
		jp2.add(jtf4);

		jp3.add(jb1);
		jp3.add(jb2);

		jp1.setLayout(new GridLayout(5, 1));
		jp2.setLayout(new GridLayout(5, 1));

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		this.setSize(325, 220);
		this.setLocation(430, 250);
		this.setTitle("�û�ע��(�û���Ϊ1��20)");
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());

		this.setVisible(true);

	}
	/*
	 * public static void main(String[] args) { Register Qqregister=new
	 * Register(); }
	 */

	// �Ƿ���ӳɹ�
	public boolean registers(String uid, String Name, String Sex, String pass1, String pass2) {
		boolean b = false;

		try {

			String sql = "insert into login values(?,?,?,?,?)";

			String paras[] = { uid, Name, Sex, pass1, pass2 };

			sh = new SqlHelper();
			boolean boo = sh.Insert(sql, paras);
			if (boo) {
				b = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			b = false;
		} finally {
			sh.close();
		}
		return b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1)// ȷ����ť
		{
			int uid1 = 0;
			String uid = jtf1.getText().trim();
			String Name = jtf2.getText().trim();
			String Sex = jtf5.getText().trim();
			String pass11 = new String(jtf3.getPassword());
			String pass22 = new String(jtf4.getPassword());
			String pass1 = Md5.MD5(pass11);
			String pass2 = Md5.MD5(pass22);

			try {
				uid1 = Integer.parseInt(uid);
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "��������ȷ���˺�!");

			}
			if (("").equals(uid) || ("").equals(Name) || ("").equals(Sex) || ("").equals(pass1) || ("").equals(pass2)) {
				JOptionPane.showMessageDialog(this, "�뽫ע����Ϣ��д����");

			} else if (20 < uid1 || uid1 < 1) {
				JOptionPane.showMessageDialog(this, "�Բ���ע���˺Ų���1��20��Χ!");
			} else if (1 < uid1 && uid1 < 20) {

				if (pass1.equals(pass2)) {
					boolean a = registers(uid, Name, Sex, pass1, pass2);

					if (a) {
						JOptionPane.showMessageDialog(this, "��ϲ����ע��ɹ�!");
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(this, "�Բ���ע��ʧ�ܣ����û��Ѿ�����!");
					}

				} else {
					JOptionPane.showMessageDialog(this, "�Բ����������벻һ��!");
				}
			}

		} else if (e.getSource() == jb2) {// ���ð�ť
			jtf1.setText("");
			jtf2.setText("");
			jtf3.setText("");
			jtf4.setText("");
			jtf5.setText("");
		}

	}

}
