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
  <div id="mainContent" style="width: 480px">
  <span id="nadpis">Poslední aktivity sledovaných uživatelů</span>
  	<div id="aktivityPratel">
  		<c:if test="${empty aktivitypratel}">
  		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádné aktivity</p>
  	</c:if>
  	<c:forEach items="${aktivitypratel }" var="aktivita">
  		
		<div id="aktivitaPritele">
		<div id="foto"><img alt="" src="../image/user/${aktivita.user.id}"></div>
		<span id="jmeno"><a href="../user/${aktivita.user.username }"><c:out value="${aktivita.user.firstName }"/> <c:out value="${aktivita.user.lastName }"/></a></span> <span><fmt:formatDate value="${aktivita.datum}" pattern="dd.MM.yyyy HH:mm"/></span><br>
		<span><c:if test="${aktivita.typ == 'Kolo' }">
			<span style="color:blue;"><c:out value="${aktivita.typ }"/></span>
		</c:if>
		<c:if test="${aktivita.typ == 'Běh' }">
			<span style="color:red;"><c:out value="${aktivita.typ }"/></span>
		</c:if>
		<c:if test="${aktivita.typ == 'Chůze' }">
			<span style="color:orange;"><c:out value="${aktivita.typ }"/></span>
		</c:if> | <c:out value="${aktivita.vzdalenost } km" /> | <a href="../aktivity/detail/${aktivita.id }">Detail</a> | <a href="../aktivity/detail/${aktivita.id }">Komentáře: ${fn:length(aktivita.komentare)}</a></span>
		</div>
		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 3px 0; padding: 0; width:480px;float:left">
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

