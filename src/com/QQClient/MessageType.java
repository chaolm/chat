package com.QQClient;

public interface MessageType {

	String message_succeed = "1";          //表名登录成功
	String message_login_feil = "2";       //表名登录失败
	String message_comm = "3";             //普通包
	String message_get_onLineFriend = "4"; //要求返回在线好友的包
	String message_ret_onLineFriend = "5"; //返回在线好友的包
	
}
