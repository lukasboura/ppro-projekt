<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

</head>
<body>
<div id="content">
<jsp:include page="include/menu.jsp"/>
<jsp:include page="include/lefthome.jsp"/>
 
 <c:if test="${user.hmotnost == 0 }">
 <div id="mainContent" style="min-height:0px;">
 <p style="font-size: 20px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:20px;color:#dd5555">
 Pro správné fungování aplikace prosím vyplňte svojí hmotnost!
 </p>
  	
 </div>
  </c:if> 
  <div id="homeStatistiky">
  	<span id="nadpis3">Statistiky za posledních 7 dní</span>
  	<div id="tabs">
    
    <div id="homeStatMenu">
    <ul>
        <li><a href="#tabs-1" id="tab1">Celkem</a></li>
        <li><a href="#tabs-2" id="tab2">Kolo</a></li>
        <li><a href="#tabs-3" id="tab3">Běh</a></li>
        <li><a href="#tabs-4" id="tab4">Chůze</a></li>
    </ul>
    </div>
    
    <div id="homeStatTabs">
    <div id="tabs-1">
     <table id="homeStatTable">
     	<tr id="hodnota"><td><fmt:formatNumber value="${kalorieVse}" maxFractionDigits="1" /> kcal</td><td><c:out value="${vzdalenostVse } km"/></td>
     	
     	<td><fmt:formatNumber minIntegerDigits="2" value="${hodinyVse }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minutyVse }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vterinyVse }" /></td>
							
			<td><c:out value="${pocetAktivitVse }"/></td></tr>
     	<tr id="popisek"><td>spálené kalorie</td><td>vzdálenost</td><td>stávený čas</td><td>počet aktivit</td></tr>
     </table>
    </div>
    <div id="tabs-2">
    <table id="homeStatTable">
     	<tr id="hodnota"><td><fmt:formatNumber value="${kalorieKolo}" maxFractionDigits="1" /> kcal</td><td><c:out value="${vzdalenostKolo } km"/></td>

     	<td><fmt:formatNumber minIntegerDigits="2" value="${hodinyKolo }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minutyKolo }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vterinyKolo }" /></td>
			
			<td><c:out value="${pocetAktivitKolo }"/></td></tr>			
     	<tr id="popisek"><td>spálené kalorie</td><td>vzdálenost</td><td>stávený čas</td><td>počet aktivit</td></tr>
     </table>
    </div>
    <div id="tabs-3">
    <table id="homeStatTable">
     	<tr id="hodnota"><td><fmt:formatNumber value="${kalorieBeh}" maxFractionDigits="1" /> kcal</td><td><c:out value="${vzdalenostBeh } km"/></td>
     	
     	<td><fmt:formatNumber minIntegerDigits="2" value="${hodinyBeh }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minutyBeh }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vterinyBeh }" /></td>
			
			<td><c:out value="${pocetAktivitBeh }"/></td></tr>
     	<tr id="popisek"><td>spálené kalorie</td><td>vzdálenost</td><td>stávený čas</td><td>počet aktivit</td></tr>
     </table>
    </div> 
    <div id="tabs-4">
     <table id="homeStatTable">
     	<tr id="hodnota"><td><fmt:formatNumber value="${kalorieChuze}" maxFractionDigits="1" /> kcal</td><td><c:out value="${vzdalenostChuze } km"/></td>
     	
     	<td><fmt:formatNumber minIntegerDigits="2" value="${hodinyChuze }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minutyChuze }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vterinyChuze }" /></td>
							
     	<td><c:out value="${pocetAktivitChuze }"/></td></tr>
     	<tr id="popisek"><td>spálené kalorie</td><td>vzdálenost</td><td>stávený čas</td><td>počet aktivit</td></tr>
     </table>
    </div>
    </div>
    
</div>
  </div>
  <section>
  <div id="mainContent" style="width:420px;min-height:0px;">
  	<span id="nadpis4">Mé 3 poslední aktivity</span>
  	<c:if test="${empty aktivity}">
  		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádné aktivity</p>
  	</c:if>
  	<c:forEach items="${aktivity }" var="aktivita" end="2">
  		<table id="aktivityTable" style="width:400px;text-align:left;">
		<tr>
		<c:if test="${aktivita.typ == 'Kolo' }">
			<td style="font-size:20px;color:blue;"><c:out value="${aktivita.typ }"/></td>
		</c:if>
		<c:if test="${aktivita.typ == 'Beh' }">
			<td style="font-size:20px;color:red;"><c:out value="Běh"/></td>
		</c:if>
		<c:if test="${aktivita.typ == 'Chuze' }">
			<td style="font-size:20px;color:orange;"><c:out value="Chůze"/></td>
		</c:if>
		
		
		
		
		<td><c:out value="${aktivita.vzdalenost } km" /></td><td><fmt:formatNumber value="${aktivita.kalorie}" maxFractionDigits="1" /> kcal</td><td></td></tr>
		<tr><td><fmt:formatDate value="${aktivita.datum}" pattern="dd.MM.yyyy HH:mm"/></td><td><fmt:formatDate value="${aktivita.dobaTrvani}" pattern="HH:mm:ss"/></td><td><fmt:formatNumber value="${aktivita.rychlost}" maxFractionDigits="1" /> km/h</td><td><a href="aktivity/detail/${aktivita.id }">Komentáře: ${fn:length(aktivita.komentare)}</a></td></tr>
		</table>
		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 2px 0; padding: 0; width:420px;">
  	</c:forEach>
</div>
<div id="planHomeDnes">
 		<span id="nadpis5">Tréninkový plán DNES</span>
 		<c:forEach items="${user.treninkovyplan }" var="polozka">
 			<c:if test="${polozka.den == dnes}">
 			
 			<table>
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
		
		</td></tr><tr><td id="ukol" style="color:#666;font-size:12px;"><c:out value="${polozka.ukol}"/></td></tr>
 			</table>
 			</c:if>
 			
 		</c:forEach>
 		<c:if test="${jednes == false}">
 			<span id="noPlan"><c:out value="Žádný plán"/></span>
 			</c:if>
 </div>  
 <div id="planHomeZitra">
 		<span id="nadpis5">Tréninkový plán ZÍTRA</span>
 		<c:forEach items="${user.treninkovyplan }" var="polozka">
 			<c:if test="${polozka.den == zitra }">
 			<table>
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
		
		</td></tr><tr><td id="ukol" style="color:#666;font-size:12px;"><c:out value="${polozka.ukol}"/></td></tr>
 			</table>
 			</c:if>
 		</c:forEach>
 		<c:if test="${jezitra == false}">
 			<span id="noPlan"><c:out value="Žádný plán"/></span>
 			</c:if>
 </div> 
<div id="mainContent" style="width:420px;min-height:0px;">
  	<span id="nadpis4">Poslední aktivity přátel</span>
  	<div id="aktivityPratel">
  		<c:if test="${empty aktivitypratel}">
  		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádné aktivity</p>
  	</c:if>
  	<c:forEach items="${aktivitypratel }" var="aktivita" end="50">
  		
		<div id="aktivitaPritele" style="width:390px;">
		<div id="foto"><img alt="" src="image/user/${aktivita.user.id}"></div>
		<span id="jmeno"><a href="user/${aktivita.user.username }"><c:out value="${aktivita.user.firstName }"/> <c:out value="${aktivita.user.lastName }"/></a></span> <span><fmt:formatDate value="${aktivita.datum}" pattern="dd.MM.yyyy HH:mm"/></span><br>
		<span>
		<c:if test="${aktivita.typ == 'Kolo' }">
			<span style="color:blue;"><c:out value="${aktivita.typ  }"/></span>
		</c:if>
		<c:if test="${aktivita.typ  == 'Beh' }">
			<span style="color:red;"><c:out value="Běh"/></span>
		</c:if>
		<c:if test="${aktivita.typ  == 'Chuze' }">
			<span style="color:orange;"><c:out value="Chůze"/></span>
		</c:if> | <c:out value="${aktivita.vzdalenost } km" /> | <a href="aktivity/detail/${aktivita.id }">Detail</a> | <a href="aktivity/detail/${aktivita.id }">Komentáře: ${fn:length(aktivita.komentare)}</a></span>
		
		</div>
		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 2px 0; padding: 0; width:420px; float:left;">
  	</c:forEach>
  		
  		
  	</div>
 </div>
 </section>
</div>
	
</body>
</html>

