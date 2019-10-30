package com.QQClient;

import java.sql.Date;

public class SaveMessage {
	static SqlHelper sh;
	public static boolean save(String userid,String mess,String date){
		boolean b = false;
		String id=Math.random()+"";

		try {
			String sql = "insert save values (?,?,?,?)";
			
			String paras[] = {id,userid,mess,date};
			
			sh = new SqlHelper();
			if(sh.save(sql, paras)){
				

				b = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			b = false;
			System.out.println("kkk");
		} finally {
			sh.close();
		}
		return b;
	}
	
}
