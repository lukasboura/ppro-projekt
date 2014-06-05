<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../style.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.min.js"></script>
<script  charset="UTF-8" src="../jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    
</head>
<body>
<div id="content">
<jsp:include page="include/menu.jsp"/>
<jsp:include page="include/lefthome.jsp"/>
  <div id="mainContent" style="padding:20px;width:610px;">
  	<h1 id="nadpis2">Upravit profil</h1>
  	<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1em 0; padding: 0; width:610px;">
  	<form:form action="upravProfil" commandName="userforupdate">
  		<table>
  		
  			
  			<tr><td>Jméno</td>
  			<td>
  			<form:input path="firstName"/></td><td>
  				<form:errors path="firstName"/>
  			</td>
  			</tr>
  			
  			<tr><td>Příjmení</td>
  			<td>
  			<form:input path="lastName"/></td><td>
  				<form:errors path="lastName"/>
  			</td>
  			</tr>
  			
  			<tr><td>Email</td>
  			<td>
  			<form:input path="email"/></td><td>
  				<form:errors path="email"/>
  			</td>
  			</tr>
  			
  			<tr><td>Věk</td>
  			<td>
  			<form:input path="vek"/>
  			</td>
  			<td>
  				<form:errors path="vek"/>
  			</td>
  			</tr>
  			<tr><td>Hmotnost</td>
  			<td>
  				<form:input path="hmotnost"/>
  			</td>
  			<td>
  				<form:errors path="hmotnost"/>
  			</td>
  			</tr>
  			
  			<tr><td>Bio</td>
  			<td>
  			<form:textarea path="bio" cols="30" rows="6"/>
  			</td></tr>
  			<form:hidden path="password"/>
  			<form:hidden path="confirmpassword"/>
  			<form:hidden path="photo"/>
  			<form:hidden path="username"/>
  			<tr><td></td><td><input type="submit" value="Upravit"/></td></tr>
  		</table>
  	</form:form>
  	<h1 id="nadpis2">Změnit profilové foto</h1>
  	<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1em 0; padding: 0; width:610px;">
  	<form:form action="zmenitFoto" commandName="imageupload" enctype="multipart/form-data">
  		Vyberte soubor: <form:input type="file" path="image"/><form:errors path="image"></form:errors>
  		<input type="submit" value="Změnit"/>
  	</form:form>
  		
  </div>
</div>
</body>
</html>

