<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="../../jquery-ui.css">
<link rel="stylesheet" href="../../style.css">
<link rel="stylesheet" href="../../lightbox.css"> 
<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'></script>
  <script type="text/javascript" src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"></script>
<script  charset="UTF-8" src="../../jquery-ui-timepicker-addon.js"></script>
<script  charset="UTF-8" src="../../jquery-ui-sliderAccess.js"></script>
<script src="../../js/lightbox.js"></script>
<script  charset="UTF-8" src="../../jquery.js"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script>
    $(function() {
    	$('#datepicker').datetimepicker({
    	    addSliderAccess: true, 
    	    sliderAccessArgs: { touchonly: false },
    		dateFormat: "d.m.yy"
    	
    	});
    	$('#timepicker').timepicker({
    	    addSliderAccess: true, 
    	    sliderAccessArgs: { touchonly: false },
    		showSecond: true,
    		timeFormat: "HH:mm:ss"
    	});
    });
    </script>
    
    <script type="text/javascript" src="../../jquery.wysiwyg.js"></script>
    <link rel="stylesheet" href="../../jquery.wysiwyg.css" type="text/css" />

<script type="text/javascript">

$(function()

{

$('#wysiwyg').wysiwyg();

});

</script>
</head>
<body>
<div id="content">
<jsp:include page="include/menu.jsp"/>
<jsp:include page="include/lefthome.jsp"/>
  <div id="mainContent" style="padding:20px;width:610px;">
  	<h1 id="nadpis2">Upravit aktivitu</h1>
  	<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1em 0; padding: 0; width:610px;">
  	<form:form action="${aktivitaKUpraveni.id }/upravAktivitu" commandName="aktivitaKUpraveni">
  		<table>
  		
  			<tr><td>Typ</td>
  			<td>
  			<form:select path="typ">
  				<form:option value="Kolo">Kolo</form:option>
  				<form:option value="Beh">Běh</form:option>
  				<form:option value="Chuze">Chůze</form:option>
  			</form:select>
  			<form:errors path="typ"/>
  			</td></tr>
  			
  			<tr><td>Vzdálenost (km)</td>
  			<td>
  			<form:input path="vzdalenost"/>
  			<form:errors path="vzdalenost"/>
  			</td></tr>
  			
  			<tr><td>Začátek</td>
  			<td>
  			<form:input path="datum" id="datepicker"></form:input>
  			<form:errors path="datum"/>
  			</td></tr>
  			
  			<tr><td>Čas</td>
  			<td>
  			<form:input path="dobaTrvani" id="timepicker"/>
  			<form:errors path="dobaTrvani"/>
  			</td></tr>
  			
  			<tr><td>Text</td>
  			<td>
  			<form:textarea path="text" name="wysiwyg" id="wysiwyg" cols="50" rows="10" style="width:430px; max-width: 430px;"/>
  			<form:errors path="text"/>
  			</td></tr>
  			<form:hidden path="kalorie"/>
  			<form:hidden path="rychlost"/>
  			<tr><td></td><td><input type="submit" value="Upravit" id="pridatKomentarButton" style="position:static;"/></td></tr>
  		</table>
  	</form:form>
  		<h1 id="nadpis2">Fotogalerie</h1>
  		<hr style="display: block; height: 1px;
    border: 0; border-top: 1px solid #ccc;
    margin: 1em 0; padding: 0; width:610px;">
  		
  		
  		
  		<div style="width:260px;float:left;">
  		<form:form action="${aktivitaKUpraveni.id }/pridatFoto" commandName="imageupload" enctype="multipart/form-data">
  		<table>
  		<tr>
  			<td>Foto </td>
  			<td><form:input type="file" path="image"/><form:errors path="image"></form:errors></td>
  		</tr>
  		<tr>
  			<td>Text </td>
  			<td><form:textarea path="text" style="width:195px; max-width: 195px;" rows="3"/></td>
  		</tr>
  		<tr>
  			<td></td>
  			<td><input type="submit" value="Přidat" id="smallButtonGreen"/></td>
  		</tr>
  		</table>
  	</form:form>
  		</div>
  	
  	
  	<div style="width:340px;float:left;">
  		<c:if test="${empty aktivitaKUpraveni.fotogalerie}">
			<p style="font-size: 32px;font-weight:lighter;text-align: center ;font-family: georgia;font-style: italic;margin-top:50px;color:#bbbbbb">Fotogalerie je prázdná</p>
		</c:if>
		
		
		<c:forEach items="${aktivitaKUpraveni.fotogalerie }" var="foto">
		
		<table id="fotoTable">
		
			<tr>
			<td id="foto" rowspan="2"><a href="${pageContext.request.contextPath}/image/fotogalerie/${foto.id}/big" rel="lightbox" title="${foto.text }"><img src="${pageContext.request.contextPath}/image/fotogalerie/${foto.id}" width="80"></a></td>
  			<td id="text"><c:out value="${foto.text }"></c:out> </td>
  			</tr>
  			
  			<tr>
  			<td style="vertical-align: top;"><form:form action="${aktivitaKUpraveni.id }/smazatFoto/${foto.id}"><input type="submit" value="Smazat" id="smallButtonRed"/></form:form></td>
  			</tr>
		
  		
  		
  		</table>
  		</c:forEach>
  	</div>
  </div>
</div>
</body>
</html>

