<%@ page contentType="image/jpeg"%>
<%@ page import = "org.jfree.chart.*" %>
<%@ page import = "DAO.*"%>

<%
	request.setCharacterEncoding("UTF-8"); 

	String tablename = request.getParameter("tablename");
	
	out.clear();
	out=pageContext.pushBody();

	ServletOutputStream sos = response.getOutputStream();
	line_chart_bean p = new line_chart_bean();
	JFreeChart chart = p.getLineChart(tablename);
	ChartUtilities.writeChartAsPNG(sos, chart, 500, 500);
%>