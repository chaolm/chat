package com.QQClient;

import java.sql.*;
import java.util.*;

public class SqlHelper {

	Connection conn = null;
	PreparedStatement ps = null;
	Statement ps1=null;
	ResultSet rs = null;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&characterEncoding=UTF-8";
	
	public SqlHelper(){
		//�������ݿ�
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//ִ�в�ѯ
	public ResultSet query(String sql,String paras[]){
		try {
		
			ps = conn.prepareStatement(sql);
			
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i+1, paras[i]);
			}
			
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("�˺���Ŀǰ��δ���ڣ�1");
			//e.printStackTrace();
		}
		
		return rs;
	}
	
	
	//ִ�����
	public boolean Insert(String sql,String paras[]){
		boolean b = true;
		
		try {
			ps = conn.prepareStatement(sql);
			
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i+1, paras[i]);
			}
			
			if(ps.executeUpdate() != 1){
				b = false;
			}else{
				b=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
	//e.printStackTrace();
	System.out.println("�Բ���ע��ʧ�ܣ����û��Ѿ�����!");
		}
		
		return b;
	}
	//ִ�������޸�
		public boolean updatePassword1(String sql,String paras[]){
			boolean b = true;
			
			try {
				ps = conn.prepareStatement(sql);
				
				for (int i = 0; i < paras.length; i++) {
					ps.setString(i+1, paras[i]);
				}
				
				if(ps.executeUpdate() != 1){
					b = false;
				}else{
					b=true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				b = false;
		
		System.out.println("�Բ���,�����޸�ʧ��!");
			}
			
			return b;
		}
	//ִ�����
		public boolean save(String sql,String paras[]){
			boolean b = true;
			
			try {
				ps = conn.prepareStatement(sql);
				
				for (int i = 0; i < paras.length; i++) {
					ps.setString(i+1, paras[i]);
				}
				
				if(ps.executeUpdate() != 1){
					b = false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				b = false;
				e.printStackTrace();
				System.out.println("��Ϣ����ʧ��ʧ�ܣ�");
			}
			
			return b;
		}
	//ִ��ɾ��
		public boolean Delete(String sql,String paras[]){
			boolean b = true;
			
			try {
				ps = conn.prepareStatement(sql);
				
				for (int i = 0; i < paras.length; i++) {
					ps.setString(i+1, paras[i]);
				}
				
				if(ps.executeUpdate() != 1){
					b = false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				b = false;
//				e.printStackTrace();
				System.out.println("ɾ��ʧ�ܣ�");
			}
			
			return b;
		}
		//ִ��ɾ�������¼
				public boolean deleteMessage(String sql){

					boolean b = true;
					
					try {

						 ps1 = conn.createStatement();
						int num= ps1.executeUpdate(sql);


						if(num<0){
							return false;
						}else{
							return true;
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						b = false;
						
					}
					
					return b;
				}
			
	//�ر����ݿ�
	public void close(){
		try {
			if(rs!=null){
				rs.close();
			}
			
			if(ps!=null){
				ps.close();
			}
			
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
