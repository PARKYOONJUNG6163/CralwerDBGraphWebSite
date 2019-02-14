package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class get_table {
	List<String> DB_table_list;

	String jdbcUrl = null;
	String dbId = null;
	String dbPass = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String dbname = "daum_원자력_newsonly";

	private void connectDB() {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimezone=UTC&useSSL=false";
		this.dbId = "root";
		this.dbPass = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void disConnectDB() {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException sqle) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException sqle) {
			}
	}

	public List<String> getTable() {
		DB_table_list = new ArrayList<String>();
		connectDB();
		String sql = "Show tables";
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DB_table_list.add((String) rs.getObject(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < DB_table_list.size(); i++) {
			System.out.println(DB_table_list.get(i));
		}
		disConnectDB();
		return DB_table_list;
	}
}
