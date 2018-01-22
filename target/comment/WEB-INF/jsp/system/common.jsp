<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<% 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() ;
	request.setAttribute("basePath",basePath);
%>