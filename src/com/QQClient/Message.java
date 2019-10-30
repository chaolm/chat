package com.QQClient;

public class Message implements java.io.Serializable{
	
	private String sender;
	private String getter;
	private String con;
	private String sendTime;
	private String MesType;
	
	public String getMesType() {
		return MesType;
	}
	public void setMesType(String mesType) {
		MesType = mesType;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
