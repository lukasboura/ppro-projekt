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
  <div id="mainContent" style="width: 480px;">
  	<span id="nadpis">Sledovaní uživatelé</span>
  	<c:if test="${empty user.pratele}">
  		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádní uživatelé</p>
  	</c:if>
  	<div id="listPratel">
  		
  		<c:forEach items="${user.pratele }" var="pritel">
  		<div id="pritel">
  		<div id="foto"><img alt="" src="../image/user/${pritel.id}"></div>
  		<span id="jmeno"><a href="../user/${pritel.username }"><c:out value="${pritel.firstName }"></c:out> <c:out value="${pritel.lastName }"></c:out></a></span><br>
  		<span id="vekaktivity"><c:out value="Věk: ${pritel.vek }"></c:out> | <a href="../user/${pritel.username }">Aktivity: ${fn:length(pritel.aktivity)}</a></span>
  		</div>
  	</c:forEach>
  	</div>
  	
  </div>
  <div id="aktivityMenu">
  	<ul>
  		<li><a href="all">Poslední aktivity</a></li>
  	</ul>
  </div>
  <div id="aktivityMenu">
  	<ul>
  		<li><a href="sledovani">Sledovaní uživatelé</a><hr></li>
  		<li><a href="sledovan">Sledován uživateli</a><hr></li>
  	</ul>
  </div>
</div>
</body>
</html>

