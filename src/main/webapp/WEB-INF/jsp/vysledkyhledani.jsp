<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="../style.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.min.js"></script>
<script  charset="UTF-8" src="jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    
</head>
<body>
<div id="content">
<jsp:include page="include/menu.jsp"/>
<jsp:include page="include/lefthome.jsp"/>
  <div id="mainContent" style="padding:20px; width:610px;">
  	
  	<c:forEach items="${uzivatele }" var="uzivatel"> 
  		<div id="vysledekHledani">
  		<div id="foto"><img alt="" src="image/user/${uzivatel.id}"></div>
  		<span id="jmeno"><a href="user/${uzivatel.username }"><c:out value="${uzivatel.firstName }"></c:out> <c:out value="${uzivatel.lastName }"></c:out></a></span><span id="username"><c:out value="${uzivatel.username }"/></span><br>
  		<span id="vekaktivity"><c:out value="Věk: ${uzivatel.vek }"></c:out> | <a href="user/${uzivatel.username }">Aktivity: ${fn:length(uzivatel.aktivity)}</a></span>
  		</div>
  	</c:forEach>
  	
  	
  </div>
</div>
</body>
</html>

