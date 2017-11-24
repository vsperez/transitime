<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.transitime.reports.GenericJsonQuery" %>
<%@ page import="org.transitime.reports.SqlUtils" %>
<%@ page import="org.transitime.reports.ChartGenericJsonQuery" %>
<%
// Get the params
String agencyId = request.getParameter("a");
String routeId = request.getParameter("r");
String stopId = request.getParameter("s");

//Group into timebuckets of 30 seconds
int BUCKET_TIME = 30;

String sql =
"SELECT "
+ "  COUNT(*) AS counts_per_time_period, \n"
+ "  FLOOR(headway / " + BUCKET_TIME + ")*" + BUCKET_TIME + " AS time_period \n"

// Put into time buckets of every BUCKET_TIME seconds. 

+ "FROM headway hw\n"
+ "WHERE "
+ "hw.stopId='"+stopId +"'\n" 
// Only need headways 
+ "and hw.headway IS NOT NULL \n"
// Ignore stops where schedule adherence really far off

// Specifies which routes to provide data for
//+ SqlUtils.routeClause(request, "hw") + "\n"
//+ SqlUtils.timeRangeClause(request, "hw.creationtime", 7) + "\n"
// Grouping needed to put times in time buckets
+ " GROUP BY time_period \n"
// Order by lateness so can easily understand results
+ " ORDER BY time_period;";

String jsonString = GenericJsonQuery.getJsonString(agencyId, sql);

// Respond with the JSON string
response.setContentType("application/json");
response.setHeader("Access-Control-Allow-Origin", "*");
response.getWriter().write(jsonString);
%>