<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="date" class="java.util.Date" />
<script src="jquery-1.8.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<script  charset="UTF-8" src="jquery.js"></script>
<ul class="menu">
 
    <li><a href="${pageContext.request.contextPath}/home">Domů</a></li>
    <li><a href="${pageContext.request.contextPath}/aktivity/all">Aktivity</a></li>
    <li><a href="${pageContext.request.contextPath}/statistiky/rocni/${date.year + 1900}">Statistiky</a></li>
    <li><a href="${pageContext.request.contextPath}/pratele/all">Přátelé</a></li>
    <li><a href="${pageContext.request.contextPath}/plan/aktualni">Tréninkový plán</a></li>
    <li><form:form action="${pageContext.request.contextPath}/hledat" commandName="search"><form:input path="value" id="txtJQuery" placeholder="hledat"/></form:form></li>
    <li style="float: right;"><a href="${pageContext.request.contextPath}/auth/logout">Logout</a></li>
    <li style="float: right;"><a href="${pageContext.request.contextPath}/home/upravitProfil">Upravit profil</a></li>
    
 
</ul>