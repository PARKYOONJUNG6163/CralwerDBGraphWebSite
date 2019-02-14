<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>주요 관심 뉴스</title>
<link href="http://fonts.googleapis.com/css?family=Didact+Gothic"
	rel="stylesheet" />
<link href="./css/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="./css/fonts.css" rel="stylesheet" type="text/css"
	media="all" />
</head>	
<body>
<% 
       String id = (String)session.getAttribute("id");
       if(id==null || id.equals("")){
          %>
          <script>alert("로그인이 필요한 서비스입니다.");
                location.href = "index.html";
          </script>    
          <% 
       }
%>
<div id="header-wrapper2">
		<div id="header" class="container">
			<div id="logo">
				<h1>
					<a href="#">BigData</a>
				</h1>
			</div>
			<div id="menu">
				<ul>
					<li><a href="input_keyword.jsp" accesskey="1">Search</a></li>
					<li class="active"><a href="logout.jsp" accesskey="2" title="">Logout</a></li>
				</ul>
			</div>
				<div id="banner" class="container">
			<div class="title">
				<h2>지난 달 SNS 현황</h2>
			</div>
			</div>
		</div>
	</div>
	
		<div id="wrapper">
		<div id="three-column" class="container">
			<div>
			<%
				String tablename = request.getParameter("tablename");
				String encodeName = URLEncoder.encode(tablename,"UTF-8"); // 인코딩해서 보내야함
				String url = "chart.jsp?tablename="+ encodeName;
			%>
				<img src=<%=url %> class="margin">
				<div>
				<label class="custom-radio-button">
					 <input type="radio" name = "select_rank" value = "replyCount"/> 
					 <span class="helping-el"></span>
					 <span class="label-text">댓글 수</span>
				</label>
				<label class="custom-radio-button pad">
					 <input type="radio" name = "select_rank" value = "LikeBad"/> 
					 <span class="helping-el"></span>
					 <span class="label-text">Like+Bad 합</span>	
				</label>
				<button type="button" class = "button-submit" onclick="select_radio()">주요관심뉴스</button>			
				</div>
			</div>
	</div>
	</div>
		<script>
		function select_radio(){
			var radios = document.getElementsByName("select_rank"); 
			var select_rank = "";
			var tablename = "<%=tablename%>";
			for(var i = 0;i<radios.length;i++){
				if(radios[i].checked){
					select_rank = radios[i].value;
				}	
			}
			
			location.href="featured_news.jsp?tablename="+tablename+"&select_rank="+select_rank;
		}
		</script>
		
	</body>
	</html>