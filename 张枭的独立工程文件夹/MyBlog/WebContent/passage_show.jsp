<%@page import="util.Passage"%>
<%@page import="dao.PassageDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%

	int passageshowid=Integer.parseInt(request.getParameter("passageshowid"));
	PassageDao dao=new PassageDao();
	Passage p=dao.showpassage(passageshowid);
	String newcontent=p.getContent().replace("\n", "<br/>");
	newcontent=newcontent.replace(" ", "&nbsp");	
%>
<h3><%=p.getPassagename() %></h3><br><br>
<%=newcontent%><br><hr>
</body>
</html>