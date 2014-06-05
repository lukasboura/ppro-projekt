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
<ul id="statistikyMenuMain">
			<li><a href="${pageContext.request.contextPath}/statistiky/rocni/${date.year + 1900}">Roční přehled</a></li>
			<li><a href="${pageContext.request.contextPath}/statistiky/mesic/${date.year + 1900}/${date.month + 1}/Kolo">Měsíční statistiky</a></li>
		</ul>
<div id="statistikyMenu">
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
  
  <c:if test="${empty aktivity}">
		<div id="mainContent">
		<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:100px;color:#bbbbbb">Žádné aktivity</p>
		</div>
		</c:if>
		<c:if test="${!empty aktivity}">
  
	<div id="homeStatistiky" style="height: 180px;">
	<table id="homeStatTable2">
							<tr id="hodnota">
								<td><fmt:formatNumber value="${kalorie}"
										maxFractionDigits="0" /> kcal</td>
								<td><c:out value="${vzdalenost } km" /></td>
								<td><fmt:formatNumber minIntegerDigits="2" value="${hodiny }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${minuty }" />:<fmt:formatNumber
							minIntegerDigits="2" value="${vteriny }" /></td>
								<td><c:out value="${pocetAktivit }" /></td>
							</tr>
							<tr id="popisek">
								<td>spálené kalorie</td>
								<td>vzdálenost</td>
								<td>stávený čas</td>
								<td>počet aktivit</td>
							</tr>
							
							<tr id="hodnota">
								<td><fmt:formatNumber value="${kalorie/pocetAktivit}"
										maxFractionDigits="1" /> kcal</td>
								<td><fmt:formatNumber value="${vzdalenost/pocetAktivit}"
										maxFractionDigits="1" /> km</td>
								<td><fmt:formatNumber value="${vzdalenost/(hodiny+minuty/60+vteriny/360)}"
										maxFractionDigits="1" /> km/h</td>
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
  <div id="mainContent">
  	<script type="text/javascript">
  	
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Den', 'Kolo'],
          ['1.', ${den1 }],
          ['2.', ${den2 }],
          ['3.', ${den3 }],
          ['4.', ${den4 }],
          ['5.', ${den5 }],
          ['6.', ${den6 }],
          ['7.', ${den7 }],
          ['8.', ${den8 }],
          ['9.', ${den9 }],
          ['10.', ${den10 }],
          ['11.', ${den11 }],
          ['12.', ${den12 }],
          ['13.', ${den13 }],
          ['14.', ${den14 }],
          ['15.', ${den15 }],
          ['16.', ${den16 }],
          ['17.', ${den17 }],
          ['18.', ${den18 }],
          ['19.', ${den19 }],
          ['20.', ${den20 }],
          ['21.', ${den21 }],
          ['22.', ${den22 }],
          ['23.', ${den23 }],
          ['24.', ${den24 }],
          ['25.', ${den25 }],
          ['26.', ${den26 }],
          ['27.', ${den27 }],
          ['28.', ${den28 }],
          ['29.', ${den29 }],
          ['30.', ${den30 }],
          ['31.', ${den31 }],
          
          
        ]);

        var options = {
        		width:650,
        		height:300,
          title: 'Vzdálenosti v jednotlivých dnech',
          chartArea: { left:"5%",top:"10%",width:"94%",height:"80%" },
  		legend:'none',
  		backgroundColor: { fill:'transparent' },
  		color: ['009ACD']
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_main'));
        chart.draw(data, options);
      }
    </script>
    <div id="chart_main" style="width: 650px; height: 300px;"></div>
    <a href="${pageContext.request.contextPath}/download/image/vzdalenosti/${aktualniRok}/${aktualniMesic + 1}/${aktualniAktivita} " id="smallButtonGreen" style="text-decoration: none;margin:10px;padding:5px;position:relative;top:5px;" target="_blank">Uložit graf</a>
    <hr style="display: block; height: 1px;
    border: 0; border-top: 5px solid #ccc;
    margin: 1em 0; padding: 0; width:650px;">
    <script type="text/javascript">
  	
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Den', 'Kolo'],
          ['1.', ${den1k }],
          ['2.', ${den3k }],
          ['3.', ${den2k }],
          ['4.', ${den4k }],
          ['5.', ${den5k }],
          ['6.', ${den6k }],
          ['7.', ${den7k }],
          ['8.', ${den8k }],
          ['9.', ${den9k }],
          ['10.', ${den10k }],
          ['11.', ${den11k }],
          ['12.', ${den12k }],
          ['13.', ${den13k }],
          ['14.', ${den14k }],
          ['15.', ${den15k }],
          ['16.', ${den16k }],
          ['17.', ${den17k }],
          ['18.', ${den18k }],
          ['19.', ${den19k }],
          ['20.', ${den20k }],
          ['21.', ${den21k }],
          ['22.', ${den22k }],
          ['23.', ${den23k }],
          ['24.', ${den24k }],
          ['25.', ${den25k }],
          ['26.', ${den26k }],
          ['27.', ${den27k }],
          ['28.', ${den28k }],
          ['29.', ${den29k }],
          ['30.', ${den30k }],
          ['31.', ${den31k }],
          
          
        ]);

        var options = {
        		width:650,
        		height:300,
          title: 'Spálené kalorie v jednotlivých dnech',
          chartArea: { left:"5%",top:"10%",width:"94%",height:"80%" },
  		legend:'none',
  		backgroundColor: { fill:'transparent' },
  		colors: ['red']
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_main2'));
        chart.draw(data, options);
      }
    </script>
    <div id="chart_main2" style="width: 650px; height: 300px;"></div>
    <a href="${pageContext.request.contextPath}/download/image/kalorie/${aktualniRok}/${aktualniMesic + 1}/${aktualniAktivita}" id="smallButtonGreen" style="text-decoration: none;margin:10px;padding:5px;position:relative;top:5px;"  target="_blank">Uložit graf</a>
    
    <hr style="display: block; height: 1px;
    border: 0; border-top: 5px solid #ccc;
    margin: 1em 0; padding: 0; width:650px;">
    <script type="text/javascript">
  	
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Den', 'Kolo'],
          ['1.', ${den1r }],
          ['2.', ${den2r }],
          ['3.', ${den3r }],
          ['4.', ${den4r }],
          ['5.', ${den5r }],
          ['6.', ${den6r }],
          ['7.', ${den7r }],
          ['8.', ${den8r }],
          ['9.', ${den9r }],
          ['10.', ${den10r }],
          ['11.', ${den11r }],
          ['12.', ${den12r }],
          ['13.', ${den13r }],
          ['14.', ${den14r }],
          ['15.', ${den15r }],
          ['16.', ${den16r }],
          ['17.', ${den17r }],
          ['18.', ${den18r }],
          ['19.', ${den19r }],
          ['20.', ${den20r }],
          ['21.', ${den21r }],
          ['22.', ${den22r }],
          ['23.', ${den23r }],
          ['24.', ${den24r }],
          ['25.', ${den25r }],
          ['26.', ${den26r }],
          ['27.', ${den27r }],
          ['28.', ${den28r }],
          ['29.', ${den29r }],
          ['30.', ${den30r }],
          ['31.', ${den31r }],
          
          
        ]);

        var options = {
        		width:650,
        		height:300,
          title: 'Rychlost v jednotlivých dnech',
          chartArea: { left:"5%",top:"10%",width:"93%",height:"80%" },
  		legend:'none',
  		backgroundColor: { fill:'transparent' },
  		colors: ['green']
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_main3'));
        chart.draw(data, options);
      }
    </script>
    <div id="chart_main3" style="width: 650px; height: 300px;"></div>
  	<a href="${pageContext.request.contextPath}/download/image/rychlost/${aktualniRok}/${aktualniMesic + 1}/${aktualniAktivita} " id="smallButtonGreen" style="text-decoration: none;margin:10px;padding:5px;position:relative;top:5px;"  target="_blank">Uložit graf</a>
    <hr style="display: block; height: 1px;
    border: 0; border-top: 0px solid #ccc;
    margin: 1em 0; padding: 0; width:650px;">
  </div>
  </c:if>
</section>
</div>
</body>
</html>

