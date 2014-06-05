<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
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
<body><div id="content">
<jsp:include page="include/menu.jsp"/>
<jsp:include page="include/lefthome.jsp"/>
<section>

  <div id="mainContent" style="width:480px;">
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
		
		
		
		
		<td><c:out value="${aktivita.vzdalenost } km" /></td><td><fmt:formatNumber value="${aktivita.kalorie}" maxFractionDigits="1" /> kcal</td><td><a href="detail/${aktivita.id }">Detail</a></td><td><a href="detail/${aktivita.id }">Komentáře: ${fn:length(aktivita.komentare)}</a></td></tr>
		<tr><td><fmt:formatDate value="${aktivita.datum}" pattern="dd.MM.yyyy HH:mm"/></td><td><fmt:formatDate value="${aktivita.dobaTrvani}" pattern="HH:mm:ss"/></td><td><fmt:formatNumber value="${aktivita.rychlost}" maxFractionDigits="1" /> km/h</td><td><a href="upravitAktivitu/${aktivita.id }">Upravit</a></td><td><a href="smazatAktivitu/${aktivita.id }"  onClick="return confirm('Opravdu smazat tuto aktivitu?');">Smazat</a></td></tr>
		</table>
		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1px 0; padding: 0; width:480px;">
  	</c:forEach>
  	
  </div>
  <div id="aktivityMenu" style="width:146px;">
  	<ul>
  		<li><a href="all">Všechny aktivity</a><hr></li>
  		<li><a href="kolo">Jízda na kole</a><hr></li>
  		<li><a href="beh">Běhání</a><hr></li>
  		<li><a href="chuze">Chůze</a><hr></li>
  	</ul>
  </div>
  <form:form action="../download/aktivity/${jakeaktivity}">
			<input type="submit" value="Stáhnout výběr" id="xlsDownload">
  </form:form>
  <form:form action="${pageContext.request.contextPath}/download/all" method="GET">
			<input type="submit" value="Stáhnout vše" id="xlsDownload">
  </form:form>
  <form:form action="../staktivity/${date.year + 1900}/${date.month + 1}/Vse" method="GET">
			<input type="submit" value="Starší aktivity" id="xlsDownload">
  </form:form>
</section>
</div>
</body>
</html>

