<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Aktivita', 'Počet'],
          ['Kolo',    ${kolo_pocet2}],
          ['Běh',      ${beh_pocet2}],
          ['Chůze',  ${chuze_pocet2}],
        ]);

        var options = {
        		width:240,
        		height:240,
        		chartArea: { left:"5%",top:"5%",width:"90%",height:"90%" },
        		legend:'none',
        		backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
<aside>
 <div id="home_user_info">
  <div id="foto"><img src="${pageContext.request.contextPath}/image/user/${aktivitauser.id }" width="60"></div>
  
  <div id="jmenofill">
  <span><c:out value="${aktivitauser.firstName}"/> <c:out value="${aktivitauser.lastName }"/></span>
  </div>
 
  <div id="usernamefill">
  <span><c:out value="${aktivitauser.username}"/></span>
  </div>
  <table>
	<tr><th>${fn:length(aktivitauser.aktivity)}</th><th>${fn:length(aktivitauser.pratele)}</th><th>${fn:length(aktivitauser.pritelem)}</th></tr>
	<tr><td>Aktivity</td><td>Sleduje</td><td>Sledován</td></tr>
</table>
   </div>
  <c:if test="${jeHomeUser == false }">
  <c:if test="${jesledovan == true }">
  <div id="pridatAktivituHome">
   <form action="${pageContext.request.contextPath}/user/${aktivitauser.username }/odebratUzivatele" method="get">
	<input type="submit"  value="Odebrat" id="odebratUzivatele">
   </form>
  </div>
  </c:if>
  <c:if test="${jesledovan == false }">
   <div id="pridatAktivituHome">
   <form action="${pageContext.request.contextPath}/user/${aktivitauser.username }/sledovatUzivatele" method="get">
	<input type="submit"  value="Sledovat" id="sledovatUzivatele">
   </form>
  </div>
  </c:if>
 </c:if>
 <c:choose>
    <c:when test="${empty aktivitauser.bio}">
    <div id="emptyBio"></div>
    </c:when>
    <c:otherwise>
        <div id="user_bio" style="margin-top: 15px;">
        	<c:out value="${aktivitauser.bio }"></c:out>
        </div>
    </c:otherwise>
</c:choose>
  
 
  <div id="homechart"><div id="chart_div"></div></div>
  
        <div id="user_telo" style="width: 120px;position:relative;top: -10px;left:20px;padding: 3px;">
        <table>
        	<tr><td>Věk: <c:out value="${aktivitauser.vek }"></c:out></td></tr>
        	<tr><td>Hmotnost: <c:out value="${aktivitauser.hmotnost }"></c:out></td></tr>
        </table>
        </div>
        
 </aside> 