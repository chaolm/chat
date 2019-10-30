package com.QQClient;

import java.awt.BorderLayout;
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

public class UpdatePassword extends JFrame implements ActionListener{
	JLabel jl1,jl2,jl3;
	JTextField jtf1;
	JPasswordField jpf2,jpf3;
	JButton jb;
	JPanel jp1,jp2,jp3;
	SqlHelper sh;
	
	public UpdatePassword(){
		jl1=new JLabel("输入需要修改密码的账号（1-20）:");
		jtf1=new JTextField(15);
		jl1.setFont(new Font("方正舒体", Font.BOLD, 16));
		jl2=new JLabel("请输入此账号目前密码   ：");
		jpf2=new JPasswordField(15);
		jl2.setFont(new Font("方正舒体", Font.BOLD, 16));
		jl3=new JLabel("请输入此号修改后密码  ：");
		jpf3=new JPasswordField(15);
		jl3.setFont(new Font("方正舒体", Font.BOLD, 16));
		jb=new JButton("提交");
		jb.setFont(new Font("方正舒体", Font.BOLD, 16));
		jb.addActionListener(this);
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp2.add(jl1);
		jp2.add(jtf1);
		jp2.add(jl2);
		jp2.add(jpf2);
		jp2.add(jl3);
		jp2.add(jpf3);
		jp3.add(jb);
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		this.setSize(280, 230);
		this.setTitle("开心聊天");
		this.setLocation(430, 250);
		this.setVisible(true);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){

			String uid = jtf1.getText().trim();
			String pas = new String(jpf2.getPassword());
			String pas1 = new String(jpf3.getPassword());
			if(!pas1.equals("")){
				View view=new View();
				String id=view.checkUser(uid,pas);
				if(uid.equals(id)){
					boolean up_word=updatePassword(pas1,uid);
					System.out.println(up_word);
					if(up_word){
						JOptionPane.showMessageDialog(this, "密码修改成功！");
						this.dispose();
					}else{
						JOptionPane.showMessageDialog(this, "密码修改失败！");
						this.dispose();

					}

				}
			}else{
				JOptionPane.showMessageDialog(this, "修改后密码数据不允许为空！");

			}
			

		}else{
			JOptionPane.showMessageDialog(this, "原始密码不正确！");

		}
	}
	public boolean updatePassword(String pas1,String uid) {
		// TODO Auto-generated method stub
boolean b = false;
		
		try {
			String sql = "update  login set Password=? where ID IN (?)";
			
			String paras[] = {pas1,uid};
			
			sh = new SqlHelper();
			if(sh.updatePassword1(sql, paras)){
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
	
}
