<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.*"%>
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
					<li class="active"><a href="logout.jsp" accesskey="1" title="">Logout</a></li>
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

	String tablename = request.getParameter("tablename");
	String select = request.getParameter("select_rank");
	String[] temp = tablename.split("_");

	if(select.equals("replyCount")){ // 댓글 수 순인 경우
		get_rank_reply_count replycount = new get_rank_reply_count();
%>
			<div class="title">
				<h2>< 댓글 수 Rank ></h2>
			</div>
	<%
		if(temp[0].equals("naver")) {
	%>
			<h2><%=tablename%></h2></br>
				<table>
					<tr>
						<th>순위</th>
						<th>제목</th>
						<th>날짜</th>
						<th>댓글 수</th>
					</tr>
			<%
				List<r_news> rNaverNewsList = replycount.getNaverReplyCount(tablename);
										for(int i=0;i<10;i++){
											r_news rNews = rNaverNewsList.get(i);
			%>
					<tr>
						<td><%=i+1%></td>
						<td><a href="<%=rNews.getURL()%>" target="_blank"><%=rNews.getTitle()%></a></td>
						<td><%=rNews.getDate()%></td>
						<td><%=rNews.getCount()%></td>
					</tr>
			<%
				}
			%>			
				</table>
			<%
				}else if(temp[0].equals("daum")){
			%>
			<h2><%=tablename%></h2></br>
				<table>
					<tr>
						<th>순위</th>
						<th>제목</th>
						<th>날짜</th>
						<th>댓글 수</th>
					</tr>
			<%
				List<r_news> rDaumNewsList = replycount.getDaumReplyCount(tablename);
										for(int i=0;i<10;i++){
											r_news rNews = rDaumNewsList.get(i);
			%>
					<tr>
						<td><%=i+1%></td>
						<td><a href="<%=rNews.getURL()%>" target="_blank"><%=rNews.getTitle()%></a></td>
						<td><%=rNews.getDate()%></td>
						<td><%=rNews.getCount()%></td>
					</tr>
			<%
				}
			%>
				</table>
			<%
				}else{
								System.out.println("네이버나 다음이 아님!!!!!!!");
							} 
							}else if(select.equals("LikeBad")){ // 좋아요+나빠요 합 순인 경우
								get_rank_like_bad likebad = new get_rank_like_bad();
			%>
			<div class="title">
				<h2>< Like+Bad Rank ></h2>
			</div>
		<%
			if(temp[0].equals("naver")) {
		%>
				<h2><%=tablename%></h2></br>
					<table>
						<tr>
							<th>순위</th>
							<th>제목</th>
							<th>날짜</th>
							<th>Like</th>
							<th>Bad</th>
							<th>Like+Bad</th>
						</tr>
				<%
					List<l_news> lNaverNewsList = likebad.getNaverLikeBad(tablename);
									for(int i=0;i<10;i++){
										l_news lNews = lNaverNewsList.get(i);
				%>
						<tr>
							<td><%=i+1%></td>
							<td><a href="<%=lNews.getURL()%>" target="_blank"><%=lNews.getTitle()%></a></td>
							<td><%=lNews.getDate()%></td>
							<td><%=lNews.getLike()%></td>
							<td><%=lNews.getBad()%></td>
							<td><%=lNews.getSum()%></td>
						</tr>
					<%
						}
					%>
						</table>
			<%
				}else if(temp[0].equals("daum")){
			%>
					<h2><%=tablename%></h2></br>
						<table>
							<tr>
								<th>순위</th>
								<th>제목</th>
								<th>날짜</th>
								<th>Like</th>
							</tr>
					<%
						List<l_news> lDaumNewsList = likebad.getDaumLikeBad(tablename);
												for(int i=0;i<10;i++){
													l_news lNews = lDaumNewsList.get(i);
					%>
							<tr>
								<td><%=i+1 %></td>
								<td><a href="<%=lNews.getURL()%>" target="_blank"><%=lNews.getTitle() %></a></td>
								<td><%=lNews.getDate() %></td>
								<td><%=lNews.getLike() %></td>
							</tr>
					<% 
						}
					%>
						</table>
	<% 
				}
			}
	%>
</div></div>
</body>
</html>