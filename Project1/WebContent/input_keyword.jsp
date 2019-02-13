<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License

Name       : Assembly 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20140330

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="http://fonts.googleapis.com/css?family=Didact+Gothic"
	rel="stylesheet" />
<link href="./css/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="./css/fonts.css" rel="stylesheet" type="text/css"
	media="all" />

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

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
   <div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<h1>
					<a href="#">Assembly</a>
				</h1>
			</div>
			<div id="menu">
				<ul>
					<li><a href="input_keyword.jsp" accesskey="1">Search</a></li>
					<li class="active"><a href="logout.jsp" accesskey="1" title="">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="three-column" class="loginContainer">
			<div class="title">
				<h1 style="color:white">검색할 DATABASE 이름</h1>
			</div>
				<div class="inline"><input type="text" id="dbname" name="dbname" /></div></br>
				<button type="button" class = "button-submit" onclick="send_keyword()">저장</button>
	</div> 
		<script>
		function send_keyword(){
			var dbname = document.getElementById("dbname").value;
			location.href="keyword_chart.jsp?dbname="+dbname;
		}
		</script>
</body>
</html>