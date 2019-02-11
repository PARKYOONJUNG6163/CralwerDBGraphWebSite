package chart;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.xy.*;
import org.jfree.data.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.plot.*;
import java.awt.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NuclearLineChartBean {
	List<String> naver_first = new ArrayList<String>();
	List<String> naver_second = new ArrayList<String>();
	List<String> naver_third = new ArrayList<String>();

	List<String> daum_first = new ArrayList<String>();
	List<String> daum_second = new ArrayList<String>();
	List<String> daum_third = new ArrayList<String>();

	String jdbcUrl = null;
	String dbId = null;
	String dbPass = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String arg[]) {
		NuclearLineChartBean lcb = new NuclearLineChartBean();
		JFreeChart chart = lcb.getLineChart();
		ChartFrame frame1 = new ChartFrame("Line Chart", chart);
		frame1.setSize(400, 350);
		frame1.setVisible(true);
	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series1 = "Naver";
		String series2 = "Daum";

		getNaverDB();
		getDaumDB();

		dataset.addValue(naver_first.size(), series1, "10days");
		dataset.addValue(naver_second.size(), series1, "20days");
		dataset.addValue(naver_third.size(), series1, "30days");

		dataset.addValue(daum_first.size(), series2, "10days");
		dataset.addValue(daum_second.size(), series2, "20days");
		dataset.addValue(daum_third.size(), series2, "30days");

		return dataset;
	}

	private void getNaverDB() {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/naver_원자력_newsonly_title?serverTimezone=UTC&useSSL=false";
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
						naver_first.add(first_date);
					} else if (split_date.equals(second_date)) {
						naver_second.add(second_date);
					} else if (split_date.equals(third_date)) {
						naver_third.add(third_date);
					} else if (split_date.equals(fourth_date)) {
						naver_third.add(fourth_date);
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

	private void getDaumDB() {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/daum_원자력_newsonly?serverTimezone=UTC&useSSL=false";
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
						daum_first.add(first_date);
					} else if (split_date.equals(second_date)) {
						daum_second.add(second_date);
					} else if (split_date.equals(third_date)) {
						daum_third.add(third_date);
					} else if (split_date.equals(fourth_date)) {
						naver_third.add(fourth_date);
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

	public JFreeChart getLineChart() {
		String chartTitle = "Nuclear";
		String categoryAxisLabel = "10-day interval articles";
		String valueAxisLabel = "Count";

		CategoryDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);

		return chart;
	}
}