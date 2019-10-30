package com.QQClient;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShowMessage extends JFrame {
	JLabel jl1, jl2, jl3;
	JTextField jtf1, jtf2, jtf3;
	JPanel jp1, jp2;

	public ShowMessage(String ower, String friendNo) {

		jl1 = new JLabel("账号    :    ");
		jl2 = new JLabel("姓名    :    ");
		jl3 = new JLabel("性别    :    ");
		jtf1 = new JTextField(15);

		jtf2 = new JTextField(15);

		jtf3 = new JTextField(15);

		jtf1.setEnabled(false);
		jtf2.setEnabled(false);
		jtf3.setEnabled(false);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp1.add(jl1);
		jp2.add(jtf1);
		jp1.add(jl2);
		jp2.add(jtf2);
		jp1.add(jl3);
		jp2.add(jtf3);

		jp1.setLayout(new GridLayout(3, 1));
		jp2.setLayout(new GridLayout(3, 1));

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.setSize(325, 220);
		this.setLocation(430, 250);
		this.setTitle("好友基本信息");
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());

		List list = showMess(ower, friendNo);
		if (list.size() != 0) {
			jtf1.setText(list.get(0).toString());
			jtf2.setText(list.get(1).toString());
			jtf3.setText(list.get(2).toString());
			this.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, "请确认是否选中好友或者好友是否已注册！");
			this.dispose();

			this.setVisible(false);

		}

	}

	SqlHelper sh;

	public List showMess(String ower, String friendNo) {
		String id;
		String name;
		String sex;
		List list = new ArrayList();
		if (!ower.equals(friendNo)) {
			try {
				String sql = "select Id,Name,Sex from login where Id = ? ";

				String paras[] = { friendNo };

				sh = new SqlHelper();
				ResultSet rs = sh.query(sql, paras);

				if (rs.next()) {
					id = rs.getString(1);
					name = rs.getString(2);
					sex = rs.getString(3);
					list.add(id);
					list.add(name);
					list.add(sex);
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("此好友目前还未存在！");
				// e.printStackTrace();
			} finally {
				sh.close();
			}
		} /*else {
			JOptionPane.showMessageDialog(this, "请选择好友");
			
		}*/
		return list;
	}
}
