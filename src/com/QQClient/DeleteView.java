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
		jb1 = new JButton("提交");
		jb1.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb1.addActionListener(this);
		jb2 = new JButton("重置");
		jb2.setFont(new Font("方正舒体", Font.BOLD, 16));
		jb2.addActionListener(this);
		jl3 = new JLabel("提示：此操作会将此号关联所有聊天信息删除");
		jl3.setFont(new Font("方正舒体", Font.BOLD, 12));

		jl3.setForeground(Color.red);
		jl1 = new JLabel("请输入需要注销的本人账号（1-20）:");
		jl1.setFont(new Font("方正舒体", Font.BOLD, 16));

		jtf1 = new JTextField(15);
		jl2 = new JLabel("请输入此账号所对应的正确密码 ：");
		jl2.setFont(new Font("方正舒体", Font.BOLD, 16));

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
		this.setTitle("开心聊天");
		this.setLocation(430, 250);

		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());
		this.setVisible(true);
	}

	// 删除账号
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
		if (e.getSource() == jb1)// 确定按钮
		{
			String uid = jtf1.getText().trim();
			String pas1 = new String(jtf2.getPassword());
			String pas = Md5.MD5(pas1);

			View view = new View();
			String id = view.checkUser(uid, pas);

			if (uid.equals(id)) {
				boolean a = Delete(uid, pas);

				if (a) {
					JOptionPane.showMessageDialog(this, "删除成功!");
					this.dispose();
					DeleteMessage(uid);
				} else {
					JOptionPane.showMessageDialog(this, "删除失败，账号不存在或者账号为空!");
				}

			} else {
				JOptionPane.showMessageDialog(this, "请确认账号是否为自己账号或者密码是否正确！！！");
			}

		} else if (e.getSource() == jb2) {// 重置按钮
			jtf1.setText("");
			jtf2.setText("");
		}
	}

	// 删除聊天信息
	public void DeleteMessage(String uid) {
		boolean b = false;
		// String allid="%"+uid+"%";
		// System.out.println("jkjjjjjjllll"+allid);

		try {
			String sql = "delete from save where USERID like '%" + uid + "%' ";

			sh = new SqlHelper();
			boolean bool = sh.deleteMessage(sql);
			if (bool) {
				JOptionPane.showMessageDialog(this, "此账号关联聊天信息删除成功!");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "此账号关联聊天信息删除失败!");
			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("账号获取异常，一般不会出现这种问题");
		} finally {
			sh.close();
		}
	}

}
