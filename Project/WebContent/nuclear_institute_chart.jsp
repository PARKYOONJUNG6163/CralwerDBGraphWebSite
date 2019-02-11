<%@ page contentType="image/jpeg"%>
<%@ page import = "org.jfree.chart.*" %>
<%@ page import ="chart.*"%>
<%
	out.clear();
	out=pageContext.pushBody();

	ServletOutputStream sos = response.getOutputStream();
	NuclearInstituteLineChartBean p = new NuclearInstituteLineChartBean();
	JFreeChart chart = p.getLineChart();
	ChartUtilities.writeChartAsPNG(sos, chart, 500, 500);
%>