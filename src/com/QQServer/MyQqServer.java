package com.QQServer;

/*
 * 这是qq服务器,他在监听,等待某个qq客户端来连接
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
			System.out.println("服务器已处于开启状态");
			
			while (true) {
				//等待连接阻塞
				
				Socket s = ss.accept();
				//接受客户端发来的信息
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
			System.out.println("服务器重复开启了");
		}
	}
}
