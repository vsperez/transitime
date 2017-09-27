<%@ page import="org.transitime.reports.StopSequenceQuery" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.text.ParseException" %>

<%
    // Get params from the query string
    String agencyId = request.getParameter("a");
	String beginDate = request.getParameter("beginDate");   
    String beginTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");
    String vehicleId = request.getParameter("v");

   
    if (agencyId == null || beginDate == null || vehicleId == null || endTime == null) {
		response.getWriter().write("For stopSequenceData.jsp must "
			+ "specify parameters 'a' (agencyId), 'beginDate', 'beginTime', 'endTime' "
			+ "and 'vehicle'."); 
		return;
    }
	
    try {
		// Perform the query and convert results of query to a JSON string
		StopSequenceQuery query = new StopSequenceQuery(agencyId);
		String jsonString = query.getJsonString(beginDate, beginTime, endTime, vehicleId);

		// If no data then return error status with an error message
		if (jsonString == null || jsonString.isEmpty()) {
		    String message = "No data for beginDate=" + beginDate 			  
			    + " beginTime=" + beginTime
			    + " endTime=" + endTime
			    + " vehicle="+ vehicleId;
		    response.sendError(
			    416 /* Requested Range Not Satisfiable */, message);
		    return;
		}

		// Respond with the JSON string
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().write(jsonString);
    } catch (java.sql.SQLException e) {
		// Respond with error message of exception
    	response.setStatus(400);
    	response.getWriter().write(e.getMessage());
    }
%>