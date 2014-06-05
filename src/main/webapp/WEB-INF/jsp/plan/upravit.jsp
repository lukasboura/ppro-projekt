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
<jsp:include page="../include/menu.jsp"/>
<jsp:include page="../include/lefthome.jsp"/>
  <div id="mainContent" style="width:480px;">
  	<span id="nadpis">Upravit tréninkový plán</span>
  	<div style="margin:20px;">
  	<h1 id="nadpis2">Přidat položku: </h1>
  	<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1px 0; padding: 0; width:430px;">
  	<form:form action="pridatPolozku" commandName="novapolozka">
  		<table>
  			<tr>
  			<td>Den: </td>
  			<td>
  				<form:select path="den">
  					<form:option value="Pondělí">Pondělí</form:option>
  					<form:option value="Úterý">Úterý</form:option>
  					<form:option value="Středa">Středa</form:option>
  					<form:option value="Čtvrtek">Čtvrtek</form:option>
  					<form:option value="Pátek">Pátek</form:option>
  					<form:option value="Sobota">Sobota</form:option>
  					<form:option value="Neděle">Neděle</form:option>
  				</form:select>
  			</td>
  			</tr>
  			<tr>
  			<td>Aktivita: </td>
  			<td>
  				<form:select path="typAktivity">
  					<form:option value="Kolo">Kolo</form:option>
  					<form:option value="Beh">Běh</form:option>
  					<form:option value="Chuze">Chůze</form:option>
  				</form:select>
  			</td>
  			</tr>
  			<tr>
  			<td>Úkol: </td>
  			<td>
  				<form:textarea path="ukol" cols="40" rows="7"/>
  			</td>
  			</tr>
  			<tr>
  			<td></td>
  			<td>
  				<input type="submit" value="Přidat">
  			</td>
  			</tr>
  		</table>
  		</form:form>
  		</div>
  		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1px 0; padding: 0; width:480px;">
  		
  		<table id="upravitPlanTable">
  			<tr><th>Den</th><th>Aktivita</th><th>Úkol</th></tr>
  		<c:forEach items="${user.treninkovyplan }" var="polozka">
  			<tr>
  			<td id="den"><c:out value="${polozka.den }"/></td>
  			<td id="typ">
  			
  			<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td>
  			<td id="ukol"><c:out value="${polozka.ukol }"/></td></tr>
  		</c:forEach>
  		</table>
  	
  	
  	
  </div>
  <div id="aktivityMenu">
  	<ul>
  		<li><a href="aktualni">Aktuální plán</a><hr></li>
  		<li><a href="upravit">Upravit</a><hr></li>
  	</ul>
  </div>
  <c:if test="${not empty user.treninkovyplan}">
  <form:form action="smazatPlan">
			<input type="submit" name="Smazat plán" value="Smazat plán" id="smazatPlanButton">
   </form:form>
	</c:if>
	</div>
</body>
</html>

