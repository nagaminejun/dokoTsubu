<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="model.User" %>
<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶログイン</h1>
<% if(loginUser != null) { %>
  <p>ログインに成功しました</p>
  <p>ようこそ<%= loginUser.getName() %>さん</p>
  <a href="Main">つぶやき投稿・閲覧へ</a>
<% } else { %>
  <p>ログインに失敗しました</p>
  <a href="index.jsp">トップへ</a>
<% } %>
<br>
<p>ここから、JSTLのCoreタグ表示例</p>

<%-- 変数のセット --%>
<c:set var="user" value="${loginUser}" />

<%-- 条件分岐（c:if） --%>
<c:if test="${not empty user}">
	<p>
		Coreタグで表示しています。
		<br>こんにちは、
		<%-- 正しいEL式の記述 --%>
		<c:out value="${user.name}" />さん。
	</p>
</c:if>

<c:if test="${empty user}">
	<p>ログインに失敗しました。</p>
</c:if>
<hr>

</body>
</html>