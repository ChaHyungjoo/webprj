package com.newlecture.webprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.entity.Notice;
import com.newlecture.webprj.entity.NoticeView;

public class JdbcNoticeDao implements NoticeDao{

	public List<NoticeView> getList(int page, String query) {
		
		int offset = 10*(page-1);
		List<NoticeView> list = null;
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT * FROM NoticeView WHERE title LIKE ? order by regDate desc limit ?, 10";
		
		// jdbc ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� /����
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ����
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, offset);

			// ��� ��������
			ResultSet rs = st.executeQuery();

			// Model--------------------------------------------------------------------------------------
			list = new ArrayList<NoticeView>();

			while (rs.next()) {
				NoticeView n = new NoticeView();
				n.setId(rs.getString("id"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setWriterId(rs.getString("writerId"));
				n.setWriterName(rs.getString("writerName"));
				n.setHit(rs.getInt("hit"));
				n.setCountCmt(rs.getInt("countCmt"));
				list.add(n);
			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int getCount() {
		
		int count = 0;
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		//String sql = "SELECT * FROM Notice WHERE title LIKE ? order by regDate desc limit ?, 10";
		String sqlCount = "SELECT COUNT(id) count FROM Notice";
		
		// jdbc ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� /����
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ����
			Statement stCount = con.createStatement();
			ResultSet rsCount = stCount.executeQuery(sqlCount);
			rsCount.next();
			count = rsCount.getInt("count");
			
			rsCount.close();
			stCount.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	public NoticeView get(String id) {
		
		NoticeView n=null;
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT * FROM NoticeView WHERE id=?";
		
		// jdbc ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� /����
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ����
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);

			// ��� ��������
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				n = new NoticeView();
				n.setId(rs.getString("id"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setWriterId(rs.getString("writerId"));
				n.setRegDate(rs.getDate("regDate"));
				n.setHit(rs.getInt("hit"));
			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}

	public int update(String id, String title, String content) {
		
		int result = 0;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "update Notice set title=?, content=? where id=?";

		// jdbc ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� /����
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ����
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, id);

			// ��� ��������
			result = st.executeUpdate();
			
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
