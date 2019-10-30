package com.QQServer;
import java.util.*;

public class SaveSocket {

	public static HashMap h = new HashMap<String, MiddleServer>();
	
	public static void add(String uid,MiddleServer ms){
		h.put(uid, ms);
	}
	
	public static MiddleServer getClientThread(String uid){
		return (MiddleServer) h.get(uid);
	}
	
	public static String getAllOnlineUserId(){
		Iterator it = h.keySet().iterator();
		String res = "";
		while(it.hasNext()){
			res += it.next().toString()+" ";
		}
		return res;
	}
}
