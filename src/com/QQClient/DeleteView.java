package com.QQClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DeleteView extends JFrame implements ActionListener {
	JLabel jl1, jl2, jl3;
	JPanel jp1, jp2, jp3;
	JTextField jtf1;
	JPasswordField jtf2;
	JButton jb1, jb2;
	SqlHelper sh;

	public DeleteView() {
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jb1 = new JButton("�ύ");
		jb1.setFont(new Font("��������", Font.BOLD, 16));

		jb1.addActionListener(this);
		jb2 = new JButton("����");
		jb2.setFont(new Font("��������", Font.BOLD, 16));
		jb2.addActionListener(this);
		jl3 = new JLabel("��ʾ���˲����Ὣ�˺Ź�������������Ϣɾ��");
		jl3.setFont(new Font("��������", Font.BOLD, 12));

		jl3.setForeground(Color.red);
		jl1 = new JLabel("��������Ҫע���ı����˺ţ�1-20��:");
		jl1.setFont(new Font("��������", Font.BOLD, 16));

		jtf1 = new JTextField(15);
		jl2 = new JLabel("��������˺�����Ӧ����ȷ���� ��");
		jl2.setFont(new Font("��������", Font.BOLD, 16));

		jtf2 = new JPasswordField(15);
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jl2);
		jp1.add(jtf2);
		jp2.add(jb1);
		jp2.add(jb2);
		jp3.add(jl3);
		this.add(jp3, BorderLayout.NORTH);
		this.add(jp1, BorderLayout.CENTER);
		this.add(jp2, BorderLayout.SOUTH);
		this.setSize(280, 230);
		this.setTitle("��������");
		this.setLocation(430, 250);

		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());
		this.setVisible(true);
	}

	// ɾ���˺�
	public boolean Delete(String uid, String pas) {
		boolean b = false;

		try {
			String sql = "delete from login where ID IN (?)";

			String paras[] = { uid };

			sh = new SqlHelper();
			if (sh.Delete(sql, paras)) {
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
			String uid = jtf1.getText().trim();
			String pas1 = new String(jtf2.getPassword());
			String pas = Md5.MD5(pas1);

			View view = new View();
			String id = view.checkUser(uid, pas);

			if (uid.equals(id)) {
				boolean a = Delete(uid, pas);

				if (a) {
					JOptionPane.showMessageDialog(this, "ɾ���ɹ�!");
					this.dispose();
					DeleteMessage(uid);
				} else {
					JOptionPane.showMessageDialog(this, "ɾ��ʧ�ܣ��˺Ų����ڻ����˺�Ϊ��!");
				}

			} else {
				JOptionPane.showMessageDialog(this, "��ȷ���˺��Ƿ�Ϊ�Լ��˺Ż��������Ƿ���ȷ������");
			}

		} else if (e.getSource() == jb2) {// ���ð�ť
			jtf1.setText("");
			jtf2.setText("");
		}
	}

	// ɾ��������Ϣ
	public void DeleteMessage(String uid) {
		boolean b = false;
		// String allid="%"+uid+"%";
		// System.out.println("jkjjjjjjllll"+allid);

		try {
			String sql = "delete from save where USERID like '%" + uid + "%' ";

			sh = new SqlHelper();
			boolean bool = sh.deleteMessage(sql);
			if (bool) {
				JOptionPane.showMessageDialog(this, "���˺Ź���������Ϣɾ���ɹ�!");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "���˺Ź���������Ϣɾ��ʧ��!");
			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("�˺Ż�ȡ�쳣��һ�㲻�������������");
		} finally {
			sh.close();
		}
	}

}
