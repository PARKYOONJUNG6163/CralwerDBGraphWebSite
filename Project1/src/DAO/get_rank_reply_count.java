package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class get_rank_reply_count {
	List<r_news> Naver_news_list; 
	List<r_news> Daum_news_list; 
	
	String jdbcUrl = null; 
	String dbId = null;
	String dbPass = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<r_news> getNaverReplyCount(String dbname) {
		Naver_news_list = new ArrayList<r_news>();
		
		this.jdbcUrl = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC&useSSL=false";
		this.dbId = "root";
		this.dbPass = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT Title,URL,article_date,R_count FROM naver_articles";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Naver_news_list.add(new r_news(rs.getString("Title"),rs.getString("URL"),rs.getString("article_date"),Integer.parseInt(rs.getString("R_count"))));
			}
			
			Collections.sort(Naver_news_list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!= null) try {rs.close();}catch (Exception e) {}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		
		return Naver_news_list;
	}
	
	public List<r_news> getDaumReplyCount(String dbname) {
		Daum_news_list = new ArrayList<r_news>();
		
		this.jdbcUrl = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC&useSSL=false";
		this.dbId = "root";
		this.dbPass = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT Title,URL,article_date,R_count FROM daum_articles";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Daum_news_list.add(new r_news(rs.getString("Title"),rs.getString("URL"),rs.getString("article_date"),Integer.parseInt(rs.getString("R_count"))));
			}
			
			Collections.sort(Daum_news_list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!= null) try {rs.close();}catch (Exception e) {}
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		
		return Daum_news_list;
	}
}
