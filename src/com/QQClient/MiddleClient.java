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
			// ��ͣ�ض�ȡ�ӷ�������������Ϣ
			try {
				ois= new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();

			/*	System.out.println("��ȡ���ӷ������˷�������Ϣ" + m.getSender() + " ��  "
						+ m.getGetter() + " ˵ " + m.getCon());*/

				// ��ʾ����ͨ��
				if (m.getMesType().equals(MessageType.message_comm)) {
					// ��ʾ��Ϣ������
					QqChat qc = ManageQqChat.get(m.getGetter() + " "
							+ m.getSender());
					qc.show(m);
				} else if (m.getMesType().equals(
						MessageType.message_ret_onLineFriend)) {
					// �����ǻ��Ͱ�
					String getter = m.getGetter();

					// �޸���Ӧ�ĺ����б�
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
