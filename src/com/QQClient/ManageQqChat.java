package com.QQClient;

import java.util.HashMap;

public class ManageQqChat {

	private static HashMap hm = new HashMap<String, QqChat>();
	
	public static void addQqChat(String allId,QqChat qq){
		hm.put(allId, qq);
	}
	
	public static QqChat get(String allId){
		return (QqChat) hm.get(allId);
	}
	
}
