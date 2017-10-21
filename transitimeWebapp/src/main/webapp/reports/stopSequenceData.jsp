<%@ page import="java.util.Arrays" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="org.transitime.utils.Time" %>
<%@ page import="org.transitime.reports.ChartGenericJsonQuery" %>
<%
    // Get params from the query string
    String agencyId = request.getParameter("a");
	String beginDate = request.getParameter("dateRange");   
    String beginTime = request.getParameter("beginTime");
    String endTime = request.getParameter("endTime");
    String vehicleId = request.getParameter("v");

   
    if (agencyId == null || beginDate == null || vehicleId == null ) {
		response.getWriter().write("For stopSequenceData.jsp must "
			+ "specify parameters 'a' (agencyId), 'beginDate' "
			+ "and 'vehicle'."); 
		return;
    }
	
    try {
    	
    	String timeSql="";
    	if (beginTime != null && !beginTime.isEmpty())
    	{
    			timeSql = " AND time::time BETWEEN '" 
    						+ beginTime + "' AND '" + endTime + "' ";
    	}
    	
		
		String sql="select extract(minute from time)+ extract(hour from time) * 60 as minutes, gtfsstopseq from arrivalsdepartures where vehicleid='"+vehicleId+"'"
				+		"AND time BETWEEN cast(? as timestamp) " 
				+     	" AND cast(? as timestamp)" + " + INTERVAL '1 day' "
	
		+ timeSql;
			
		String jsonString = ChartGenericJsonQuery.getJsonString(agencyId, sql, Time.parseDate(beginDate), Time.parseDate(beginDate));

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