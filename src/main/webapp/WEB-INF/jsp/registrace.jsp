
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.placeholder.min.js"></script>
<link rel="stylesheet" href="../style.css">

<title>New Account</title>
</head>
<body>

<script type="text/javascript">
$(function() {
	 $('input').placeholder();
	});
</script>

<div id="registrace">
<form:form method="POST" action="createaccount" commandName="newuser">
	<table>
	<tr>
		<td>
		<form:input path="username"  placeholder="Uživatelské jméno"/> <form:errors path="username" id="error"></form:errors>
		</td>
	</tr>
	<tr><td><form:password path="password" placeholder="Heslo"/> <form:errors path="password" id="error"></form:errors> <p id="error">${passnotmatch}</p></td></tr>
	<tr><td><form:password path="confirmpassword" placeholder="Heslo znovu"/> <form:errors path="confirmpassword" id="error"></form:errors></td></tr>
	<tr><td><form:input path="firstName" placeholder="Jméno"/> <form:errors path="firstName" id="error"></form:errors></td></tr>
	<tr><td><form:input path="lastName" placeholder="Příjmení"/> <form:errors path="lastName" id="error"></form:errors></td></tr>
	<tr><td><form:input path="email" placeholder="E-mail"/> <form:errors path="email" id="error"></form:errors></td></tr>
	<tr><td><input type="submit" value="Vytvořit účet"/></td></tr>
	</table>
</form:form>
<form action="login" id="zpetbutton">
<input type="submit" value="Zpět"/>
</form>
</div>
</body>
</html>