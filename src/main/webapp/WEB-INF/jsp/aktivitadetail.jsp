<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../../style.css">
<link rel="stylesheet" href="../../lightbox.css"> 
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.min.js"></script>
<script src="../../js/lightbox.js"></script>
<script  charset="UTF-8" src="../../jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    
</head>
<body>
<div id="content">
<jsp:include page="include/menu.jsp"/>

<c:if test="${homeuser.username == aktivitauser.username }">
	<jsp:include page="include/lefthome.jsp"/>
</c:if>
<c:if test="${homeuser.username != aktivitauser.username }">
	<jsp:include page="include/leftuseraktivita.jsp"/>
</c:if>


  <div id="mainContent" style="padding:20px;width:390px;font-family:georgia;">
  	
  		
		
		<c:if test="${aktivita.typ == 'Kolo' }">
			<span style="font-size:22px;color:blue;font-family:georgia;font-style:italic"><c:out value="${aktivita.typ }"/> </span>
		</c:if>
		<c:if test="${aktivita.typ == 'Beh' }">
			<span style="font-size:22px;color:red;font-family:georgia;font-style:italic"><c:out value="Běh"/> </span>
		</c:if>
		<c:if test="${aktivita.typ == 'Chuze' }">
			<span style="font-size:22px;color:orange;font-family:georgia;font-style:italic"><c:out value="Chůze"/> </span>
		</c:if>
		
		<span id="aktivitaDetailJmeno"><c:out value="${aktivita.user.firstName }"/> <c:out value="${aktivita.user.lastName }"/></span><br>
		<span id="aktivitaDetailDatum"><fmt:formatDate value="${aktivita.datum}" pattern="dd.MM.yyyy HH:mm"/></span>
		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1em 0; padding: 0; width:390px;">
    
		<div style="width:350px;float:left;"> 
		<table id="aktivitaDetail">
		<tr><td id="aktivitaDetailValue">Vzdálenost: </td><td><c:out value="${aktivita.vzdalenost } km" /></td></tr>
		<tr><td id="aktivitaDetailValue">Spálené kalorie: </td><td><fmt:formatNumber value="${aktivita.kalorie}" maxFractionDigits="1" /> kcal</td></tr>
		<tr><td id="aktivitaDetailValue">Doba trvání: </td><td><fmt:formatDate value="${aktivita.dobaTrvani}" pattern="HH:mm:ss"/></td></tr>
		<tr><td id="aktivitaDetailValue">Průměrná rychlost: </td><td><fmt:formatNumber value="${aktivita.rychlost}" maxFractionDigits="1" /> km/h</td></tr>
		</table>
		 
		<h2 id="detailNadpis">Text</h2>
  		
  		<c:if test="${empty aktivita.text }"><span style="font-size:18px;font-style:italic;color:#666666;">Uživatel nevložil žádný text</span></c:if>
  		<c:if test="${!empty aktivita.text }"><div style="font-size: 14px; width:390px;">${aktivita.text }</div></c:if>
  		
  		
  		<h2 id="detailNadpis">Komentáře</h2>
  		<form:form action="${aktivita.id }/pridatKomentar" commandName="newkomentar">
  			<form:textarea path="text" rows="4" style="max-width:300px;width:300px;"/>
			<input type="submit" name="Přidat komentář" value="Přidat komentář" id="pridatKomentarButton">
   		</form:form>
  		<div id="komentar">
  		<c:forEach items="${aktivita.komentare}" var="komentar">
  		<div id="komentar">
  			<div id="foto"><img alt="" src="../../image/user/${komentar.user.id}"></div>
  			<span id="jmeno"><c:out value="${komentar.user.firstName}"/> <c:out value="${komentar.user.lastName}" /></span> | <span id="datum"><fmt:formatDate value="${komentar.datum}" pattern="dd.MM.yyyy HH:mm"/></span><br>
  			<span id="text"><c:out value="${komentar.text}" /></span>
  		</div>
  		</c:forEach>
  		</div>
  		
  </div>
  
</div>
<div id="fotogalerie"> 
	<c:if test="${empty aktivita.fotogalerie }"><span style="font-family:georgia;font-style:italic;color: #999;font-size:19px;margin-left:10px;">Prázdná fotogalerie</span></c:if>
  	<c:forEach items="${aktivita.fotogalerie }" var="foto">
  		
			<a href="${pageContext.request.contextPath}/image/fotogalerie/${foto.id}/big" rel="lightbox[1]" title="${foto.text }"><img id="fotografie" src="${pageContext.request.contextPath}/image/fotogalerie/${foto.id}" width="90"></a>
  			
  		</c:forEach>
  </div>
  </div>
</body>
</html>

