package com.QQServer;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.QQClient.Message;
import com.QQClient.MessageType;

/*
 * 处理客户端和服务器
 */

public class MiddleServer extends Thread {

	Socket s;
	
	public MiddleServer(Socket s) {
		this.s = s;
	}

	public void notifyOther(String im) {
		HashMap hm = SaveSocket.h;
		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			Message m = new Message();
			m.setCon(im);
			m.setMesType(MessageType.message_ret_onLineFriend);

			String onLine = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(SaveSocket
						.getClientThread(onLine).s.getOutputStream());
				m.setGetter(onLine);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void run() {
		while (true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();

				/*System.out.println(m.getSender() + " 对  " + m.getGetter()
						+ " 说  " + m.getCon());*/

				if (m.getMesType().equals(MessageType.message_comm)) {
					MiddleServer ms = SaveSocket.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(ms.s
							.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMesType().equals(
						MessageType.message_get_onLineFriend)) {
					String res = SaveSocket.getAllOnlineUserId();

					Message m2 = new Message();

					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());

					ObjectOutputStream oos2 = new ObjectOutputStream(s
							.getOutputStream());
					oos2.writeObject(m2);
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("服务器断开连接");
				
			}
		}
	}

}
