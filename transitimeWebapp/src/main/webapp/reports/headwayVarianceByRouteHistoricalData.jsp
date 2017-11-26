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

String sql="select minutes, max(avg) from (SELECT  cast (extract(minute from h1.creationtime)+ extract(hour from h1.creationtime) * 60 as int) as minutes, avg(h2.coefficientofvariation) as avg FROM public.headway h1 join public.headway h2 on h2.creationtime >= h1.creationtime - interval '900 second' and h2.creationtime <= h1.creationtime "; 
sql = sql + " and h1.routeid='" +routeId +"'";
sql = sql + " and h1.routeid=h2.routeid ";
sql = sql + " and h2.coefficientofvariation> 0";
sql = sql + " and h2.numvehiclesonroute=h2.numvehicles";   
sql = sql + SqlUtils.timeRangeClause(request, "h1.creationtime", 7);
sql = sql + " group by h1.creationtime";
sql = sql + " order by h1.creationtime) hwc1 where hwc1.minutes % 10 =0 group by minutes";




String jsonString = ChartGenericJsonQuery.getJsonString(agencyId, sql);

// Respond with the JSON string
response.setContentType("application/json");
response.setHeader("Access-Control-Allow-Origin", "*");
response.getWriter().write(jsonString);
%>