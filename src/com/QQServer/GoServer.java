package com.QQServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.QQServer.MyQqServer;

public class GoServer extends JFrame implements ActionListener {
	JButton jb1, jb2;
	JPanel jp1, jp2, jp3;
	JLabel jl1, jl2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoServer gs = new GoServer();
	}

	public GoServer() {
		jb1 = new JButton("启动服务器");
		jb1.setFont(new Font("方正舒体", Font.BOLD, 15));

		jb1.addActionListener(this);

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jl1 = new JLabel("欢迎使用");
		jl2 = new JLabel("提示：小组列表好友数可以根据实际情况修改！");
		jl1.setFont(new Font("方正舒体", Font.BOLD, 60));
		jl2.setFont(new Font("方正舒体", Font.BOLD, 12));
		jl2.setForeground(Color.red);

		jp1.add(jb1);

		jp2.add(jl1);
		jp3.add(jl2);
		this.add(jp2, BorderLayout.NORTH);
		this.add(jp1, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
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
		if (e.getSource() == jb1) {
			new MyQqServer();

		}
	}
}
