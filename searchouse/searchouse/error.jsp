<%@ page import="searchouse.bean.ErrorBean"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>    
    <%
    
    	// Per utilizzare una sola jsp per gli errori passiamo un bean errore contenente il messaggio dell'errore e 
    	// una variabile indicante la jsp e la servlet collegata alla jsp di provenienza
    	String message = "";
    	String comeBack = "";
    	ErrorBean error = new ErrorBean();
    	if(request.getSession()!=null && request.getSession().getAttribute("ERROR")!=null){
    		error = (ErrorBean) request.getSession().getAttribute("ERROR");
    		message = error.getMessage()+"";
    		comeBack = error.getComeBack()+"";
    	}
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Searchouse: Error</title>
</head>
<body>
	<table>
		<tr>
			<td><a href="<%=comeBack %>" target ="_top">Indietro</a>&nbsp;</td>
		</tr>
	</table>
	<%= message %>
</body>
</html>