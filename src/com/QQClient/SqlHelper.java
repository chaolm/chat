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
		//连接数据库
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//执行查询
	public ResultSet query(String sql,String paras[]){
		try {
		
			ps = conn.prepareStatement(sql);
			
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i+1, paras[i]);
			}
			
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("此好友目前还未存在！1");
			//e.printStackTrace();
		}
		
		return rs;
	}
	
	
	//执行添加
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
	System.out.println("对不起，注册失败，此用户已经存在!");
		}
		
		return b;
	}
	//执行密码修改
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
		
		System.out.println("对不起,密码修改失败!");
			}
			
			return b;
		}
	//执行添加
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
				System.out.println("信息保存失败失败！");
			}
			
			return b;
		}
	//执行删除
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
				System.out.println("删除失败！");
			}
			
			return b;
		}
		//执行删除聊天记录
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
			
	//关闭数据库
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
