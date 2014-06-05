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
  	<span id="nadpis">Aktuální tréninkový plán</span>
  	<c:if test="${empty user.treninkovyplan}">
  		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádný tréninkový plán</p>
  	</c:if>
  	<div id="treninkovyPlan">
  	<span id="nadpisDen">Pondělí </span>
  	<hr id="oddelovac">
  	<table id="teninkovyPlanTable">
  	<c:forEach items="${pondeli}" var="polozka">
  		<tr><td id="typ">
  		
  		<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td><td id="ukol"><c:out value="${polozka.ukol}"/></td><td id="smazat"><a href="smazatPolozku/${polozka.id }">Smazat</a></td></tr>  	
  	</c:forEach>
  	</table>
  	
  	<hr id="oddelovac">
  	<span id="nadpisDen">Úterý </span>
  	<hr id="oddelovac">
  	<table id="teninkovyPlanTable">
  	<c:forEach items="${utery }" var="polozka">
<tr><td id="typ">
  		
  		<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td><td id="ukol"><c:out value="${polozka.ukol}"/></td><td id="smazat"><a href="smazatPolozku/${polozka.id }">Smazat</a></td></tr>  	
  	  	</c:forEach>
  	</table>
  	
  	<hr id="oddelovac">
  	<span id="nadpisDen">Středa </span>
  	<hr id="oddelovac">
  	<table id="teninkovyPlanTable"><c:forEach items="${streda }" var="polozka">
<tr><td id="typ">
  		
  		<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td><td id="ukol"><c:out value="${polozka.ukol}"/></td><td id="smazat"><a href="smazatPolozku/${polozka.id }">Smazat</a></td></tr>  	
  	  	</c:forEach>
  	</table>
  	
  	<hr id="oddelovac">
  	<span id="nadpisDen">Čtvrtek </span>
  	<hr id="oddelovac">
  	<table id="teninkovyPlanTable"><c:forEach items="${ctvrtek }" var="polozka">
<tr><td id="typ">
  		
  		<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td><td id="ukol"><c:out value="${polozka.ukol}"/></td><td id="smazat"><a href="smazatPolozku/${polozka.id }">Smazat</a></td></tr>  	
  	  	</c:forEach>
  	</table>
  	
  	<hr id="oddelovac">
  	<span id="nadpisDen">Pátek </span>
  	<hr id="oddelovac">
  	<table id="teninkovyPlanTable"><c:forEach items="${patek }" var="polozka">
<tr><td id="typ">
  		
  		<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td><td id="ukol"><c:out value="${polozka.ukol}"/></td><td id="smazat"><a href="smazatPolozku/${polozka.id }">Smazat</a></td></tr>  	
  	  	</c:forEach>
  	</table>
  	
  	<hr id="oddelovac">
  	<span id="nadpisDen">Sobota </span>
  	<hr id="oddelovac">
  	<table id="teninkovyPlanTable"><c:forEach items="${sobota }" var="polozka">
<tr><td id="typ">
  		
  		<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td><td id="ukol"><c:out value="${polozka.ukol}"/></td><td id="smazat"><a href="smazatPolozku/${polozka.id }">Smazat</a></td></tr>  	
  	  	</c:forEach>
  	</table>
  	
  	<hr id="oddelovac">
  	<span id="nadpisDen">Neděle </span>
  	<hr id="oddelovac">
  	<table id="teninkovyPlanTable"><c:forEach items="${nedele }" var="polozka">
<tr><td id="typ">
  		
  		<c:if test="${polozka.typAktivity == 'Kolo' }">
			<span style="color:blue;"><c:out value="${polozka.typAktivity }"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${polozka.typAktivity == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if>
		
		</td><td id="ukol"><c:out value="${polozka.ukol}"/></td><td id="smazat"><a href="smazatPolozku/${polozka.id }">Smazat</a></td></tr>  	
  	  	</c:forEach>
  		</table>
  	
  </div>
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

