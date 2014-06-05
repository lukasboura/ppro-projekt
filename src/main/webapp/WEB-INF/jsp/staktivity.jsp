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
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.min.js"></script>
<script  charset="UTF-8" src="${pageContext.request.contextPath}/jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script>
$(document).ready(function(){
	
$('#smallButtonGreen').click(
		function(){
			
			var rok = $('#rok').val();
			var mesic = $('#mesic').val();
			mesic++;
			var aktivita = $('#aktivita').val();
			
			$(location).attr('href',"../" + "../" + rok + "/" + mesic + "/" + aktivita);
		}
);
});
</script>
</head>
<body>
<div id="content">
<jsp:include page="include/menu.jsp"/>
<jsp:include page="include/lefthome.jsp"/>
<section>
<div id="statistikyMenu" style="margin-top:20px;">
<table>
	<tr>
		<td>Rok: </td>
		<td><select id="rok">
		
			<c:forEach items="${rokData }" var="rok">
			<c:if test="${rok == aktualniRok}">
			
				<option value="${rok }" selected="selected">${rok }</option>
			
			</c:if>
			<c:if test="${rok != aktualniRok}">
				
				<option value="${rok }">${rok }</option>
			
			</c:if>
		</c:forEach>
		</select></td>
		<td>Měsíc: </td>
		<td>
		<select id="mesic">
		<c:forEach items="${mesicData }" var="mesic">
				
				<c:if test="${mesic == aktualniMesic}">
				
				<option value="${mesic }" selected="selected">
						<c:if test="${mesic == 0}">Leden</c:if>		
						<c:if test="${mesic == 1}">Únor</c:if>	
						<c:if test="${mesic == 2}">Březen</c:if>	
						<c:if test="${mesic == 3}">Duben</c:if>	
						<c:if test="${mesic == 4}">Květen</c:if>	
						<c:if test="${mesic == 5}">Červen</c:if>	
						<c:if test="${mesic == 6}">Červenec</c:if>	
						<c:if test="${mesic == 7}">Srpen</c:if>	
						<c:if test="${mesic == 8}">Září</c:if>	
						<c:if test="${mesic == 9}">Říjen</c:if>	
						<c:if test="${mesic == 10}">Listopad</c:if>	
						<c:if test="${mesic == 11}">Prosinec</c:if>	
				</option>
				
				</c:if>
				
				<c:if test="${mesic != aktualniMesic }">
				
				<option value="${mesic }" >
								
						<c:if test="${mesic == 0}">Leden</c:if>		
						<c:if test="${mesic == 1}">Únor</c:if>	
						<c:if test="${mesic == 2}">Březen</c:if>	
						<c:if test="${mesic == 3}">Duben</c:if>	
						<c:if test="${mesic == 4}">Květen</c:if>	
						<c:if test="${mesic == 5}">Červen</c:if>	
						<c:if test="${mesic == 6}">Červenec</c:if>	
						<c:if test="${mesic == 7}">Srpen</c:if>	
						<c:if test="${mesic == 8}">Září</c:if>	
						<c:if test="${mesic == 9}">Říjen</c:if>	
						<c:if test="${mesic == 10}">Listopad</c:if>	
						<c:if test="${mesic == 11}">Prosinec</c:if>	
								
				</option>
				
				</c:if>
				
			</c:forEach>
			</select>
		</td>
		<td>Aktivita: </td>
		<td>
		<select id="aktivita">
		<option value="Vse">Vše</option>
		<c:forEach items="${aktivityData }" var="a">
		
				<c:if test="${a == aktualniAktivita}">
			
				<option value="${a }" selected="selected">${a }</option>
			
			</c:if>
			<c:if test="${a != aktualniAktivita}">
				
				<option value="${a }">${a }</option>
			
			</c:if>
			
			</c:forEach>
			</select>
		</td>
		<td><input type="submit" value="Zobrazit" id="smallButtonGreen"/></td>
	</tr>
</table>
  </div>
  <div id="mainContent" style="width:480px;">
  	<c:if test="${empty aktivity}">
  		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádné aktivity</p>
  	</c:if>
  	<c:forEach items="${aktivity }" var="aktivita">
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
		
		
		
		
		<td><c:out value="${aktivita.vzdalenost } km" /></td><td><fmt:formatNumber value="${aktivita.kalorie}" maxFractionDigits="1" /> kcal</td><td><a href="${pageContext.request.contextPath}/aktivity/detail/${aktivita.id }">Detail</a></td><td><a href="${pageContext.request.contextPath}/aktivity/detail/${aktivita.id }">Komentáře: ${fn:length(aktivita.komentare)}</a></td></tr>
		<tr><td><fmt:formatDate value="${aktivita.datum}" pattern="dd.MM.yyyy HH:mm"/></td><td><fmt:formatDate value="${aktivita.dobaTrvani}" pattern="HH:mm:ss"/></td><td><fmt:formatNumber value="${aktivita.rychlost}" maxFractionDigits="1" /> km/h</td><td><a href="${pageContext.request.contextPath}/aktivity/upravitAktivitu/${aktivita.id }">Upravit</a></td><td><a href="${pageContext.request.contextPath}/aktivity/smazatAktivitu/${aktivita.id }">Smazat</a></td></tr>
		</table>
		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1em 0; padding: 0; width:480px;" />
  	</c:forEach>
  	
  </div>
  <form:form action="${pageContext.request.contextPath}/download/aktivity/${aktualniRok}/${aktualniMesic + 1}/${aktualniAktivita }">
			<input type="submit" value="Stáhnout výběr" id="xlsDownload" style="margin-top:20px;">
  </form:form>
  <form:form action="${pageContext.request.contextPath}/download/all" method="GET">
			<input type="submit" value="Stáhnout vše" id="xlsDownload">
  </form:form>
  <form:form action="${pageContext.request.contextPath}/aktivity/all" method="GET">
			<input type="submit" value="Poslední aktivity" id="xlsDownload">
  </form:form>
  
</section>
</div>
</body>
</html>

