<%@ page import="org.transitime.utils.web.WebUtils" %>
<%@ page import="org.transitime.utils.Time" %>
<%@page import="org.transitime.db.webstructs.WebAgency"%>
<%
// Create title for chart
String agencyId = request.getParameter("a");

//Determine list of routes for title using "r" param.
//Note that can specify multiple routes.
String routeIds[] = request.getParameterValues("r");
String titleRoutes = "";
if (routeIds != null && !routeIds[0].isEmpty()) {
 titleRoutes += " route ";
 if (routeIds.length > 1) 
     titleRoutes += "s";
 titleRoutes += routeIds[0];
 for (int i=1; i<routeIds.length; ++i) {
		String routeId = routeIds[i];
	    titleRoutes += " & " + routeId;
 }
}


String beginDate = request.getParameter("dateRange");
String numDays = request.getParameter("numDays");
String beginTime = request.getParameter("beginTime");
String endTime = request.getParameter("endTime");
String stopId = request.getParameter("s");

String chartTitle = "Headway distribution for "+ stopId; 


%>
<html>
  <head>
    <%@include file="/template/includes.jsp" %>
    
    <style>
      .google-visualization-tooltip {
        font-family: arial, sans-serif;
      }

      #loading {
        position: fixed;
		left: 0px;
		top: 0px;
		width: 100%;
		height: 100%;
		z-index: 9999;
		background: url('images/page-loader.gif') 50% 50% no-repeat rgb(249,249,249);
      }
      
      #errorMessage {
		  display: none;
          position: fixed;
	      top: 30px;
	      margin-left: 20%;
	      margin-right: 20%;
	      height: 100%;
	      text-align: center;
	      font-family: sans-serif;
	      font-size: large;
	      z-index: 9999;
		}
    </style>
  </head>

  <body>
    <%@include file="/template/header.jsp" %>
    
    <div id="chart_div" style="width: 100%; height: 100%;"></div>
    <div id="loading"></div>
    <div id="errorMessage"></div>
  </body>

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    
    <script type="text/javascript">
    var globalDataTable;
    var globalChartOptions;
    function drawChart() {
        // Actually create the chart
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(globalDataTable, globalChartOptions);    
    }
    function createDataTableAndDrawChart(jsonData) {
        // Initialize the data array with the header that describes the columns
    	var dataArray = [[
    		'Early/Late', 
    		'Stops', 
    		{ role: 'style' }, 
    		{ role: 'annotation' }, 
    		{ role: 'tooltip' }
    	]];
    	globalDataTable = google.visualization.arrayToDataTable(dataArray);
    	
    	// The options for the chart
        globalChartOptions = {
              animation: {
              	 startup: true,
              	 duration: 500, 
              	 easing: 'out'
              },
              chartArea: {top:10, width: '86%', height: '90%'},
              vAxis: {
            	  minValue: 0,
            	  title: "Number of stops per time interval",
            	  textStyle: {fontSize: 12},
            	  },
              hAxis: { 
            	  
            	  title: "Minutes vehicle late (negative) or early (positive)",
            	  // The chart always draws the baseline at value 0 over the chart.
            	  // Since the baseline isn't true zero, since using bars and 
            	  // putting tick marks at the edges of the bars, don't want this
            	  // the line to be so visible. Only thing that we can do is to
            	  // make it the same color as the bar, which which will be
            	  // the ontime color, or the early color if allowable early is
            	  // set to zero.
            	 
            	  },
              bar: {groupWidth: "100%"},
              legend: { position: 'none' },
              annotations: {
            	    textStyle: {fontSize: 10}},
        };
    	
    };
      // Updates chart when page is resized. But only does so at most
      // every 200 msec so that don't bog system down trying to repeatedly
      // update the chart.
      var globalTimer;
      window.onresize = function () {
                   clearTimeout(globalTimer);
                   globalTimer = setTimeout(drawChart, 100)
                 };
    

      function getDataTable() {
        var jsonTextData = $.ajax({
          url: "headwayDistributionByStopData.jsp",
      	  // Pass in query string parameters to page being requested
          data: {<%= WebUtils.getAjaxDataString(request) %>},
      	  // Needed so that parameters passed properly to page being requested
          traditional: true,
          dataType:"json",
          async: false,
          success: createDataTableAndDrawChart,
          error: function(request, status, error) {
          	$("#errorMessage").html(request.responseText +
			  "<br/><br/>Hit back button to try other parameters.")
			$("#errorMessage").fadeIn("slow");
            },
          }).responseJSON;
        
      };

    

      function getDataAndDrawChart() {
        getDataTable();
        if (globalDataTable != null)
      	    drawChart();

        // Now that chart has been drawn faceout the loading image
        $("#loading").fadeOut("slow");
      };

      // Start visualization after the body created so that the
      // page loading image will be displayed right away
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(getDataAndDrawChart);
</script>
</html>
