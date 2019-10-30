package com.QQClient;

import java.util.HashMap;

public class SaveSocket {

	static HashMap  hm = new HashMap<String, MiddleClient>();
	
	public static void add(String qqId,MiddleClient mc){
		hm.put(qqId, mc);
	}
	
	public static MiddleClient get(String qqId){
		return (MiddleClient) hm.get(qqId);
	}
}
