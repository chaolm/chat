package com.QQClient;

import java.util.*;
public class ManageQqFriend {
	
	private static HashMap hm = new HashMap<String, QQFriend>();
	
	public static void add(String qqId,QQFriend qf){
		hm.put(qqId, qf);
	}
	
	public static QQFriend get(String qqId){
		return (QQFriend) hm.get(qqId);
	}
	
}
