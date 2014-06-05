<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script  charset="UTF-8" src="${pageContext.request.contextPath}/jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    
</head>
<body>
<div id="content">
<jsp:include page="../include/menu.jsp"/>
<jsp:include page="../include/leftuser.jsp"/>
  <div id="mainContent" style="width:480px;">
  <span id="nadpis">Poslední aktivity</span>
  	<c:if test="${empty aktivity}">
  		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádné aktivity</p>
  	</c:if>
  	<c:forEach items="${aktivity }" var="aktivita" end="50">
  		<table id="aktivityTable">
		<tr>
		<c:if test="${aktivita.typ == 'Kolo' }">
			<td style="font-size:22px;color:blue;"><c:out value="${aktivita.typ }"/></td>
		</c:if>
		<c:if test="${aktivita.typ == 'Beh' }">
			<td style="font-size:22px;color:red;"><c:out value="Běh"/></td>
		</c:if> 
		<c:if test="${aktivita.typ == 'Chuze' }">
			<td style="font-size:22px;color:orange;"><c:out value="Chůze"/></td>
		</c:if>
		
		
		
		
		<td><c:out value="${aktivita.vzdalenost } km" /></td><td><fmt:formatNumber value="${aktivita.kalorie}" maxFractionDigits="1" /> kcal</td><td><a href="<%= request.getContextPath() %>/aktivity/detail/${aktivita.id }">Detail</a></td><td></td></tr>
		<tr><td><fmt:formatDate value="${aktivita.datum}" pattern="dd.MM.yyyy HH:mm"/></td><td><fmt:formatDate value="${aktivita.dobaTrvani}" pattern="HH:mm:ss"/></td><td><fmt:formatNumber value="${aktivita.rychlost}" maxFractionDigits="1" /> km/h</td><td><a href="<%= request.getContextPath() %>/aktivity/detail/${aktivita.id }">Komentáře: ${fn:length(aktivita.komentare)}</a></td><td></td></tr>
		</table>
		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 2px 0; padding: 0; width:480px;">
  	</c:forEach>
  </div>
<div id="aktivityMenu">
  	<ul>
  		<li><a href="${pageContext.request.contextPath}/user/${user.username }">Všechny aktivity</a><hr></li>
  		<li><a href="${pageContext.request.contextPath}/user/${user.username }/kolo">Jízda na kole</a><hr></li>
  		<li><a href="${pageContext.request.contextPath}/user/${user.username }/beh">Běhání</a><hr></li>
  		<li><a href="${pageContext.request.contextPath}/user/${user.username }/chuze">Chůze</a><hr></li>
  	</ul>
  </div>
  <div id="aktivityMenu">
  	<ul>
  		<li><a href="${pageContext.request.contextPath}/user/${user.username }/sledovani">Sledovaní uživatelé</a><hr></li>
  		<li><a href="${pageContext.request.contextPath}/user/${user.username }/sledovan">Sledován uživateli</a><hr></li>
  	</ul>
  </div>
  </div>
</body>
</html>

