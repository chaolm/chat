package com.QQServer;

/*
 * ����qq������,���ڼ���,�ȴ�ĳ��qq�ͻ���������
 */
import java.io.*;
import java.net.*;
import com.QQClient.*;

public class MyQqServer {

	/*public static void main(String args[]) {
		MyQqServer mqs = new MyQqServer();
	}*/

	public MyQqServer() {

		try {
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("�������Ѵ��ڿ���״̬");
			
			while (true) {
				//�ȴ���������
				
				Socket s = ss.accept();
				//���ܿͻ��˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				User u = (User) ois.readObject();
				MiddleServer scc = new MiddleServer(s);

				SaveSocket.add(u.getUserId(), scc);

				scc.start();
				scc.notifyOther(u.getUserId());
			}

		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("�������ظ�������");
		}
	}
}
