<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.placeholder.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PPRO - projekt</title>

</head>
<body>

<script type="text/javascript">
$(function() {
	 $('input').placeholder();
	});
</script>

<div id="loginform">

<div id="login">

<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post" >
<table>
<tr><td><input id="j_username" name="j_username" type="text" placeholder="Uživatelské jméno"/></td></tr>
<tr><td><input id="j_password" name="j_password" type="password" placeholder="Heslo"/></td></tr>
<tr><td><input  type="submit" value="Přihlásit se"/></td></tr>
</table>
</form>

</div>

<div id="newaccount" style="margin-left:4px;">
<form action="newaccount" method="get">
<input type="submit" name="Vytvořit účet" value="Vytvořit účet">
</form>

</div>
<div style="color:white;margin:4px;margin-left:6px;" id="login-error">${error}</div>
</div>





</body>
</html>