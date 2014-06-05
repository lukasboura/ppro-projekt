<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="date" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
<script src="${pageContext.request.contextPath}/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui.min.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script>
$(document).ready(function(){
$('#smallButtonGreen').click(
		function(){
			
			var rok = $('#rok').val();
			
			$(location).attr('href',rok);
		}
);
});
</script>
</head>
<body>
<div id="content">
	<jsp:include page="include/menu.jsp" />
	<jsp:include page="include/lefthome.jsp" />
	<section>

		<ul id="statistikyMenuMain">
			<li><a href="${pageContext.request.contextPath}/statistiky/rocni/${date.year + 1900}">Roční přehled</a></li>
			<li><a href="${pageContext.request.contextPath}/statistiky/mesic/${date.year + 1900}/${date.month + 1}/Kolo">Měsíční statistiky</a></li>
		</ul>
		<div id="statistikyMenu">
			<table>
				<tr>
					<td>Rok:</td>
					<td><select id="rok">

							<c:forEach items="${rokData }" var="rok">
								<c:if test="${rok == vybranyrok }">
								<option value="${rok }" selected="selected">${rok }</option>
								</c:if>
								<c:if test="${rok != vybranyrok }">
								<option value="${rok }">${rok }</option>
								</c:if>
								
							</c:forEach>

					</select></td>
					<td><input type="submit" value="Zobrazit"
						id="smallButtonGreen" /></td>
				</tr>
			</table>
		</div>
		
		<c:if test="${empty aktivity}">
		<div id="mainContent">
		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádné aktivity</p>
		</div>
		</c:if>
		<c:if test="${!empty aktivity}">
		<div id="mainContent2">

			<div id="grafMesicu">
				<span id="nadpisgraf">Nejaktivnější měsíce</span>
				<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Měsíc', 'Počet aktivit'],
          
          ['Leden',    ${leden}],
          ['Únor',   ${unor}],
          ['Březen',    ${brezen}],
          ['Duben',    ${duben}],
          ['Květen',    ${kveten}],
          ['Červen',    ${cerven}],
          ['Červenec',    ${cervenec}],
          ['Srpen',    ${srpen}],
          ['Září',    ${zari}],
          ['Říjen',   ${rijen}],
          ['Listopad',    ${listopad}],
          ['Prosinec',    ${prosinec}],
          
          
        ]);

        var options = {
        		width:200,
        		height:200,
        		chartArea: { left:"5%",top:"5%",width:"90%",height:"90%" },
        		legend:'none',
        		backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('chartMonths'));
        chart.draw(data, options);
      }
    </script>
				<div id="chartMonths" style="width: 300px; height: 200px;"></div>

			</div>
			<div id="grafAktivit">
				<span id="nadpisgraf">Nejčastější aktivity</span>
				<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Aktivita', 'Počet aktivit'],
          ['Kolo',    ${kolo}],
          ['Běh',    ${beh}],
          ['Chůze',    ${chuze}],
        ]);

        var options = {
        		width:200,
        		height:200,
        		chartArea: { left:"5%",top:"5%",width:"90%",height:"90%" },
        		legend:'none',
        		backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('chartActivities'));
        chart.draw(data, options);
      }
    </script>
				<div id="chartActivities" style="width: 300px; height: 200px;"></div>

			</div>

			<table id="homeStatTable" style="width: 650px">
				<tr id="hodnota">
					<td><c:out value="${pocetaktivit }" /></td>
					<td><fmt:formatNumber maxFractionDigits="0">${kalorie }</fmt:formatNumber>
						kcal</td>
					<td><fmt:formatNumber minIntegerDigits="2" value="${hodiny }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minuty }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vteriny }" /></td>
					<td>
					<a href="${pageContext.request.contextPath}/aktivity/detail/${nejnAktivita.id }">
					<fmt:formatNumber value="${nejnAktivita.kalorie}"
							maxFractionDigits="0" /> kcal</a></td>
							<td>
							<c:if test="${empty nejkomAktivita.komentare }">0</c:if>
							<c:if test="${!empty nejkomAktivita.komentare }">
							<a href="${pageContext.request.contextPath}/aktivity/detail/${nejkomAktivita.id }">
							<c:out value="${fn:length(nejkomAktivita.komentare)}" /></a></c:if></td>
				</tr>
				<tr id="popisek">
					<td>celkem aktivit</td>
					<td>celkem spálených kalorií</td>
					<td>celkový strávený čas</td>
					<td>nejnáročnější aktivita</td>
					<td>nejkomentovanější aktivita</td>
				</tr>
			</table>

		</div>

		<div id="homeStatistiky" style="height: 220px;">
			<span id="nadpis3">Statistiky aktivit za rok ${vybranyrok}</span>
			<div id="tabs">

				<div id="homeStatMenu" style="height: 182px;">
					<ul>
						<li><a href="#tabs-2" id="tab2">Kolo</a></li>
						<li><a href="#tabs-3" id="tab3">Běh</a></li>
						<li><a href="#tabs-4" id="tab4">Chůze</a></li>
					</ul>
				</div>

				<div id="homeStatTabs">
					<div id="tabs-2">
						<table id="homeStatTable">
							<tr id="hodnota">
								<td><fmt:formatNumber value="${kalorieKolo}"
										maxFractionDigits="0" /> kcal</td>
								<td><c:out value="${vzdalenostKolo } km" /></td>
								<td><fmt:formatNumber minIntegerDigits="2" value="${hodinyKolo }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minutyKolo }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vterinyKolo }" /></td>
								<td><c:out value="${pocetAktivitKolo }" /></td>
							</tr>
							<tr id="popisek">
								<td>spálené kalorie</td>
								<td>vzdálenost</td>
								<td>stávený čas</td>
								<td>počet aktivit</td>
							</tr>
							
							<tr id="hodnota">
								<td>
								<c:if test="${pocetAktivitKolo == 0}">0</c:if>
								<c:if test="${pocetAktivitKolo != 0}"><fmt:formatNumber value="${kalorieKolo/pocetAktivitKolo}"
										maxFractionDigits="1" /> </c:if> kcal</td>
								<td>
								<c:if test="${pocetAktivitKolo == 0}">0</c:if>
								<c:if test="${pocetAktivitKolo != 0}"><fmt:formatNumber value="${vzdalenostKolo/pocetAktivitKolo}"
										maxFractionDigits="1" /> </c:if> km</td>
								<td>
								<c:if test="${pocetAktivitKolo == 0}">0</c:if>
								<c:if test="${pocetAktivitKolo != 0}"><fmt:formatNumber value="${vzdalenostKolo/(hodinyKolo+minutyKolo/60+vterinyKolo/360)}"
										maxFractionDigits="1" /></c:if> km/h</td>
								<td></td>
							</tr>
							<tr id="popisek">
								<td>spálené kalorie (průměr)</td>
								<td>průměrná vzdálenost</td>
								<td>průměrná rychlost</td>
								<td></td>
							</tr>
						</table>
					</div>
					<div id="tabs-3">
						<table id="homeStatTable">
							<tr id="hodnota">
								<td><fmt:formatNumber value="${kalorieBeh}"
										maxFractionDigits="0" /> kcal</td>
								<td><c:out value="${vzdalenostBeh } km" /></td>
								<td><fmt:formatNumber minIntegerDigits="2" value="${hodinyBeh }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minutyBeh }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vterinyBeh }" /></td>
								<td><c:out value="${pocetAktivitBeh }" /></td>
							</tr>
							<tr id="popisek">
								<td>spálené kalorie</td>
								<td>vzdálenost</td>
								<td>stávený čas</td>
								<td>počet aktivit</td>
							</tr>
							
							<tr id="hodnota">
								<td>
								<c:if test="${pocetAktivitBeh == 0}">0</c:if>
								<c:if test="${pocetAktivitBeh != 0}"><fmt:formatNumber value="${kalorieBeh/pocetAktivitBeh}"
										maxFractionDigits="1" /> </c:if> kcal</td>
								<td>
								<c:if test="${pocetAktivitBeh == 0}">0</c:if>
								<c:if test="${pocetAktivitBeh != 0}"><fmt:formatNumber value="${vzdalenostBeh/pocetAktivitBeh}"
										maxFractionDigits="1" /> </c:if> km</td>
								<td>
								<c:if test="${pocetAktivitBeh == 0}">0</c:if>
								<c:if test="${pocetAktivitBeh != 0}"><fmt:formatNumber value="${vzdalenostBeh/(hodinyBeh+minutyBeh/60+vterinyBeh/360)}"
										maxFractionDigits="1" /></c:if> km/h</td>
								<td></td>
							</tr>
							<tr id="popisek">
								<td>spálené kalorie (průměr)</td>
								<td>průměrná vzdálenost</td>
								<td>průměrná rychlost</td>
								<td></td>
							</tr>
						</table>
					</div>
					<div id="tabs-4">
						<table id="homeStatTable">
							<tr id="hodnota">
								<td><fmt:formatNumber value="${kalorieChuze}"
										maxFractionDigits="0" /> kcal</td>
								<td><c:out value="${vzdalenostChuze } km" /></td>
								<td><fmt:formatNumber minIntegerDigits="2" value="${hodinyChuze }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minutyChuze }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vterinyChuze }" /></td>
								<td><c:out value="${pocetAktivitChuze }" /></td>
							</tr>
							<tr id="popisek">
								<td>spálené kalorie</td>
								<td>vzdálenost</td>
								<td>stávený čas</td>
								<td>počet aktivit</td>
							</tr>
							
							<tr id="hodnota">
								<td>
								<c:if test="${pocetAktivitChuze == 0}">0</c:if>
								<c:if test="${pocetAktivitChuze != 0}"><fmt:formatNumber value="${kalorieChuze/pocetAktivitChuze}"
										maxFractionDigits="1" /> </c:if> kcal</td>
								<td>
								<c:if test="${pocetAktivitChuze == 0}">0</c:if>
								<c:if test="${pocetAktivitChuze != 0}"><fmt:formatNumber value="${vzdalenostChuze/pocetAktivitChuze}"
										maxFractionDigits="1" /> </c:if> km</td>
								<td>
								<c:if test="${pocetAktivitChuze == 0}">0</c:if>
								<c:if test="${pocetAktivitChuze != 0}"><fmt:formatNumber value="${vzdalenostChuze/(hodinyChuze+minutyChuze/60+vterinyChuze/360)}"
										maxFractionDigits="1" /></c:if> km/h</td>
								<td></td>
							</tr>
							<tr id="popisek">
								<td>spálené kalorie (průměr)</td>
								<td>průměrná vzdálenost</td>
								<td>průměrná rychlost</td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>

			</div>
		</div>
		</c:if>
	</section>
	</div>
</body>
</html>

