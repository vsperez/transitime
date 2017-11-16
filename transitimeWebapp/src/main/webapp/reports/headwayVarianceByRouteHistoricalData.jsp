<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.transitime.reports.GenericJsonQuery" %>
<%@ page import="org.transitime.reports.SqlUtils" %>
<%@ page import="org.transitime.reports.ChartGenericJsonQuery" %>
<%
// Get the params
String agencyId = request.getParameter("a");
String vehicleId = request.getParameter("v");
String routeId = request.getParameter("r");

String sql="select  extract(minute from creationtime)+ extract(hour from creationtime) * 60 as minutes, coefficientofvariation  from headway where coefficientofvariation> 0 and numvehiclesonroute=numvehicles and routeid='"+routeId +"'"+  SqlUtils.timeRangeClause(request, "creationtime", 7);

String jsonString = ChartGenericJsonQuery.getJsonString(agencyId, sql);

// Respond with the JSON string
response.setContentType("application/json");
response.setHeader("Access-Control-Allow-Origin", "*");
response.getWriter().write(jsonString);
%>