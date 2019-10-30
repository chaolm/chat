package com.QQClient;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateMessage extends JFrame implements ActionListener{
	JLabel jl1, jl2, jl3;
	JTextField jtf1, jtf2, jtf3;
	JPanel jp1,jp2;
	JButton jb;

	public UpdateMessage(String ower) {
		jb=new JButton("提交");
		jb.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb.addActionListener(this);
		jl1 = new JLabel("账号       :     ");
		jl1.setFont(new Font("方正舒体", Font.BOLD, 16));

		jtf1 = new JTextField(15);
		jtf1.setEnabled(false);

		jl2 = new JLabel("姓名      :      ");
		jl2.setFont(new Font("方正舒体", Font.BOLD, 16));

		jtf2 = new JTextField(15);
		jl3 = new JLabel("性别      :      ");
		jl3.setFont(new Font("方正舒体", Font.BOLD, 16));

		jtf3 = new JTextField(15);
		List list = showMess(ower);
		jtf1.setText(list.get(0).toString());
		jtf2.setText(list.get(1).toString());
		jtf3.setText(list.get(2).toString());
		
		jp1 = new JPanel();
		jp2=new JPanel();
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jl2);
		jp1.add(jtf2);
		jp1.add(jl3);
		jp1.add(jtf3);
		jp2.add(jb);
		jp1.setLayout(new GridLayout(3, 2));
		
		this.add(jp1,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		this.setSize(325, 220);
		this.setLocation(430, 250);
		this.setTitle("本人信息修改");

		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());

		this.setVisible(true);
	}

	SqlHelper sh;

	public List showMess(String ower) {
		String id;
		String name;
		String sex;
		List list = new ArrayList();
		try {
			String sql = "select Id,Name,Sex from login where Id = ? ";

			String paras[] = { ower };

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
			e.printStackTrace();
		} finally {
			sh.close();
		}
		return list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){
			String id = jtf1.getText().trim();

			String name = jtf2.getText().trim();
			String sex = jtf3.getText().trim();
			if(name.equals("")||sex.equals("")){
				
				JOptionPane.showMessageDialog(this, "信息不允许为空!");
				
				
			}else{
				modify(id,name,sex);
			}
		}
	}
	
	
	private void modify(String id, String name, String sex) {
		// TODO Auto-generated method stub
		try {
			String sql = "update  login set Name=? , Sex=? where Id=?";

			String paras[] = {name, sex,id};

			sh = new SqlHelper();
			boolean rs = sh.Insert(sql, paras);
			if(rs){
				JOptionPane.showMessageDialog(this, "信息更新成功！");
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "信息更新失败！");
				this.dispose();
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "信息更新失败！");
		} finally {
			sh.close();
		}
		//return list;
	
	}

	
}
