<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="model.Account" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶへようこそ！！！</h1>

<!-- action（宛先）には@WebServletの引数を指定 -->
<form action="LoginServlet" method="post">
ユーザーID：<input type="text" name="userId"><br>
パスワード：<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>

<c:if test="${not empty param.error}">
	<p style="color: red;">ログインに失敗しました。</p>
</c:if>
<jsp:include page="footer.jsp" />
</body>
</html>