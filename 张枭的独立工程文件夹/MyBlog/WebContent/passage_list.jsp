<%@page import="util.Passage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>show test</title>
</head>
<body>
	<table align="center" width="450" border="1">
	<tr>
		<td align="center" colspan="5">
		<h2>Passage</h2>
		</td>
	</tr>
	<%
		System.out.println("passage");
		ArrayList<Passage>list=(ArrayList<Passage>)request.getAttribute("list");
		for(Passage p:list){
	%>
	<tr align="center">
		<td><%=p.getId() %></td>
		<td><%=p.getPassagename() %></td>
		<td><%=p.getContent() %></td>
		<td><%=p.getDate() %></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td align="center">
				<%=request.getAttribute("bar") %>
				</td>
		</tr>
 	</table>
</body>
</html>