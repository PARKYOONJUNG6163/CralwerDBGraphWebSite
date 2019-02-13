package DAO;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class line_chart_bean {
	List<String> first = new ArrayList<String>();
	List<String> second = new ArrayList<String>();
	List<String> third = new ArrayList<String>();

	String jdbcUrl = null;
	String dbId = null;
	String dbPass = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private CategoryDataset createDataset(String dbname) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		String[] temp = dbname.split("_");
		String series = temp[0];
		
		if(temp[0].equals("naver")) {
			getNaverDB(dbname);
		}else if(temp[0].equals("daum")){
			getDaumDB(dbname);
		}else {
			System.out.println("네이버나 다음이 아님!!!!");
		}

		dataset.addValue(first.size(), series, "1~9days");
		dataset.addValue(second.size(), series, "10~19days");
		dataset.addValue(third.size(), series, "20~30days");

		return dataset;
	}

	private void getNaverDB(String dbname) {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC&useSSL=false";
		this.dbId = "root";
		this.dbPass = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar oCalendar = Calendar.getInstance();
		int year = oCalendar.get(Calendar.YEAR);
		int last_month = oCalendar.get(Calendar.MONTH);

		String first_date = year + "-0" + last_month + "-0";
		String second_date = year + "-0" + last_month + "-1";
		String third_date = year + "-0" + last_month + "-2";
		String fourth_date = year + "-0" + last_month + "-3";

		String sql = "SELECT * FROM naver_articles";
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("article_date").length() > 10) {
					String split_date = rs.getString("article_date").substring(0, 9);
					if (split_date.equals(first_date)) {
						first.add(first_date);
					} else if (split_date.equals(second_date)) {
						second.add(second_date);
					} else if (split_date.equals(third_date)) {
						third.add(third_date);
					} else if (split_date.equals(fourth_date)) {
						third.add(fourth_date);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	}

	private void getDaumDB(String dbname) {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC&useSSL=false";
		this.dbId = "root";
		this.dbPass = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar oCalendar = Calendar.getInstance();
		int year = oCalendar.get(Calendar.YEAR);
		int last_month = oCalendar.get(Calendar.MONTH);

		String first_date = year + ".0" + last_month + ".0";
		String second_date = year + ".0" + last_month + ".1";
		String third_date = year + ".0" + last_month + ".2";
		String fourth_date = year + "-0" + last_month + "-3";

		String sql = "SELECT * FROM daum_articles";
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("article_date").length() > 10) {
					String split_date = rs.getString("article_date").substring(0, 9);
					if (split_date.equals(first_date)) {
						first.add(first_date);
					} else if (split_date.equals(second_date)) {
						second.add(second_date);
					} else if (split_date.equals(third_date)) {
						third.add(third_date);
					} else if (split_date.equals(fourth_date)) {
						third.add(fourth_date);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	}

	public JFreeChart getLineChart(String dbname) {
		String chartTitle = "< " + dbname + " >";
		String categoryAxisLabel = "10-day interval articles";
		String valueAxisLabel = "Count";

		CategoryDataset dataset = createDataset(dbname);

		JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);
		chart.getTitle().setFont(new Font("돋움",Font.BOLD,20));
		return chart;
	}
}
