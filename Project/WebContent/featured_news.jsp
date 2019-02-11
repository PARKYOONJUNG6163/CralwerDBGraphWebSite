<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="rank.getRankReplyCount"%>
<%@page import="rank.getRankLikeBad"%>
<%@page import="rank.L_News"%>
<%@page import="rank.R_News"%>
<%@page import="java.util.List"%>
 <%@ page import="java.sql.*"%>
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
<div id="header-wrapper2">
		<div id="header" class="container">
			<div id="logo">
				<h1>
					<a href="#">Assembly</a>
				</h1>
			</div>
			<div id="menu">
				<ul>
					<li class="active"><a href="Main.html" accesskey="1" title="">Homepage</a></li>
					<li><a href="#" accesskey="2" title="">Our Clients</a></li>
					<li><a href="#" accesskey="3" title="">About Us</a></li>
					<li><a href="#" accesskey="4" title="">Careers</a></li>
					<li><a href="#" accesskey="5" title="">Contact Us</a></li>
				</ul>
			</div>
				<div id="banner" class="container">
			<div class="title">
				<h2>주요 관심 뉴스</h2>
			</div>
			</div>
		</div>
	</div>
<div id="wrapper">
	<div id="three-column" class="container">
<%
	request.setCharacterEncoding("UTF-8"); 

	String select = request.getParameter("select_rank");
	
	if(select.equals("replyCount")){ // 댓글 수 순인 경우
%>
			<div class="title">
				<h2>댓글 수 Rank</h2>
			</div>
<div class="tbox1">
<h2>Naver</h2></br>
	<table>
		<tr>
			<th>순위</th>
			<th>제목</th>
			<th>댓글 수</th>
		</tr>
<%
	getRankReplyCount replycount = new getRankReplyCount();
	List<R_News> rNaverNewsList = replycount.getNaverReplyCount();
	for(int i=0;i<10;i++){
		R_News rNews = rNaverNewsList.get(i);
%>
		<tr>
			<td><%=i+1 %></td>
			<td><a href="<%=rNews.getURL()%>" target="_blank"><%=rNews.getTitle() %></a></td>
			<td><%=rNews.getCount() %></td>
		</tr>
<% 
	}
%>
	</table></div>
<div class="tbox2">
<h2>Daum</h2></br>
	<table>
		<tr>
			<th>순위</th>
			<th>제목</th>
			<th>댓글 수</th>
		</tr>
<%
	List<R_News> rDaumNewsList = replycount.getDaumReplyCount();
	for(int i=0;i<10;i++){
		R_News rNews = rDaumNewsList.get(i);
%>
		<tr>
			<td><%=i+1 %></td>
			<td><a href="<%=rNews.getURL()%>" target="_blank"><%=rNews.getTitle() %></a></td>
			<td><%=rNews.getCount() %></td>
		</tr>
<% 
	}
%>
	</table></div>

<%		
	}else if(select.equals("LikeBad")){ // 좋아요+나빠요 합 순인 경우
%>
			<div class="title">
				<h2>Like+Bad Rank</h2>
			</div>
			<div class="tbox1">
<h2>Naver</h2></br>
	<table>
		<tr>
			<th>순위</th>
			<th>제목</th>
			<th>Like</th>
			<th>Bad</th>
			<th>Like+Bad</th>
		</tr>
<%
	getRankLikeBad likebad = new getRankLikeBad();
	List<L_News> lNaverNewsList = likebad.getNaverLikeBad();
	for(int i=0;i<10;i++){
		L_News lNews = lNaverNewsList.get(i);
%>
		<tr>
			<td><%=i+1 %></td>
			<td><a href="<%=lNews.getURL()%>" target="_blank"><%=lNews.getTitle() %></a></td>
			<td><%=lNews.getLike() %></td>
			<td><%=lNews.getBad() %></td>
			<td><%=lNews.getSum() %></td>
		</tr>
<% 
	}
%>
	</table></div>
<div class="tbox2">
<h2>Daum</h2></br>
	<table>
		<tr>
			<th>순위</th>
			<th>제목</th>
			<th>Like</th>
		</tr>
<%
	List<L_News> lDaumNewsList = likebad.getDaumLikeBad();
	for(int i=0;i<10;i++){
		L_News lNews = lDaumNewsList.get(i);
%>
		<tr>
			<td><%=i+1 %></td>
			<td><a href="<%=lNews.getURL()%>" target="_blank"><%=lNews.getTitle() %></a></td>
			<td><%=lNews.getLike() %></td>
		</tr>
<% 
	}
%>
	</table></div>
<% 
	}
%>
</div></div>
</body>
</html>