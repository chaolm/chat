package com.QQClient;
/*
 * ���ǿͻ������ӷ�������̨
 */
import java.util.*;
import java.io.*;
import java.net.*;

public class MyQqClient {
	public Socket s;
	
	public MyQqClient(){
		
		try {
			s = new Socket("127.0.0.1",9999);
			
			MiddleClient mc = new MiddleClient(s);
			
			mc.start();
			
			SaveSocket.add(View.u.getUserId(), mc);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
}
