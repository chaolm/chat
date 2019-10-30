package com.QQClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class MiddleClient extends Thread {

	private Socket s;

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public MiddleClient(Socket s) {
		this.s = s;
	}

	public void run() {
		ObjectInputStream ois;		
		while (true) {
			// 不停地读取从服务器发来的信息
			try {
				ois= new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();

			/*	System.out.println("读取到从服务器端发来的信息" + m.getSender() + " 对  "
						+ m.getGetter() + " 说 " + m.getCon());*/

				// 表示是普通包
				if (m.getMesType().equals(MessageType.message_comm)) {
					// 显示信息到界面
					QqChat qc = ManageQqChat.get(m.getGetter() + " "
							+ m.getSender());
					qc.show(m);
				} else if (m.getMesType().equals(
						MessageType.message_ret_onLineFriend)) {
					// 表名是回送包
					String getter = m.getGetter();

					// 修改相应的好友列表
					QQFriend qqf = ManageQqFriend.get(getter);

					if (qqf != null) {
						qqf.updateFriend(m);
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				
			}

		}
	}
}
