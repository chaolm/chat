package com.QQClient;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LookHistoryMess extends JFrame {
	JTextArea jta;
	JScrollPane jsp;
	SqlHelper sh;

	public LookHistoryMess(String ownerId, String friendId) {
		StringBuffer sb = new StringBuffer();
		jta = new JTextArea(12, 34);
		jsp = new JScrollPane(jta);
		Map map1 = lookMess(ownerId, friendId);
		if (map1.size() != 0) {
			Iterator iter = map1.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Entry<String, String>) iter.next();
				// 根据数据区别谁是谁的消息
				String st = entry.getKey() + ":" + "\r\n" + ownerId + "对" + friendId + "说： " + entry.getValue()
						+ "\r\n";
				sb.append(st);
			}
			jta.setText(sb + "");
			this.add(jsp);
			this.setSize(300, 200);
			this.setVisible(true);
			this.setTitle(ownerId + "和 " + friendId + "的聊天记录");
			this.setIconImage(new ImageIcon(this.getClass().getResource("/images/1-1.png")).getImage());
			this.setLocation(350, 150);
		} else {
			JOptionPane.showMessageDialog(this, ownerId + "和好友" + friendId + "还未有聊天记录！");
		}
	}

	public Map lookMess(String ownerId, String friendId) {
		String date;
		String mess;
		String sex;
		Map map = new HashMap();
		try {
			String sql = "select date,mess from save where userid in(?) order by date  asc";
			String paras[] = { ownerId + friendId };
			sh = new SqlHelper();
			ResultSet rs = sh.query(sql, paras);
			while (rs.next()) {
				date = rs.getString(1);
				mess = rs.getString(2);
				map.put(date, mess);
			}
		} catch (Exception e) {
			System.out.println("读取信息失败");
		} finally {
			sh.close();
		}
		return map;
	}
}
