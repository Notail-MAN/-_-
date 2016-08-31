<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="../Manage?newpassage=1" method="post">
	<input type="text" value="文章名" name="passagename"><br>
	<hr>
	<br>
	<textarea rows="20" cols="40" name="content">在此键入文章内容</textarea>
	<br/>
	<label>碎言碎语</label><input type="radio" value="1" name="attribute">
	<label>慢生活</label><input type="radio" value="2" name="attribute">
	<label>资料分享</label><input type="radio" value="3" name="attribute"><br>
	<input type="submit" value="提交">
</form>
	
</body>
</html>