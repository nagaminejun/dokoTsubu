<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Date date = new Date();
SimpleDateFormat sdf = newSimpleDateFormat();
String today = sdf.format(date);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>静的インクルードのサンプル</title>
</head>
<body>
<%= name %>さんの<%= today %>のうんせいは・・・
</body>
</html>