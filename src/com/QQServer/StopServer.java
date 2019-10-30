package com.QQServer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StopServer extends JFrame implements ActionListener {
	JButton jb;
	JPanel jp,jp1;
	JLabel jl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StopServer ss=new StopServer();
	}
	public StopServer(){
		jb=new JButton("关闭服务器");
		jb.setFont(new Font("方正舒体", Font.BOLD, 16));

		jb.addActionListener(this);
		jp=new JPanel();
		jp1=new JPanel();
		jl=new JLabel("欢迎使用");
		jl.setFont(new Font("方正舒体", Font.BOLD, 60));

		jp1.add(jl);
		jp.add(jb);
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp,BorderLayout.CENTER);
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
			System.exit(0);
		} 
	}

}
