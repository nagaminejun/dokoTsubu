<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="model.User, model.Mutter, java.util.List" %>
<%-- <%
// セッションスコープに保存されたユーザー情報を取得
/* User loginUser = (User) session.getAttribute("loginUser"); */
/* List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList"); */
/* String errorMsg = (String) request.getAttribute("errorMsg"); */
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<%-- <%= loginUser.getName() %> --%>
さん、ログイン中
<a href="Logout">ログアウト</a>
</p>
<p><a href="Main">更新</a></p>
<form action="Main" method="post">
<input type="text" name="text">
<input type="submit" name="つぶやく">
</form>
<h2>ここからJSTLのCoreタグ活用した表示</h2>
<c:out value="${loginUser.name}"/>さん、Coreタグでnameを取得
<%-- <% if(errorMsg != null) {%>
<p><%= errorMsg %></p>
<% } %>
<% for(Mutter mutter : mutterList) { %>
	<P><%= mutter.getUserName() %> : <%= mutter.getText() %></P>
<% } %> --%>
<p>以下もCoreタグ</p>
<c:if test="${not empty errorMsg}">
	<p>${errorMsg}</p>
</c:if>
<c:forEach var="mutter" items="${mutterList}">
	<p><c:out value="${mutter.userName }" />：<c:out value="${mutter.text }" /></p>
</c:forEach>
<!-- これは動的include -->
<jsp:include page="footer.jsp" />
</body>
</html>