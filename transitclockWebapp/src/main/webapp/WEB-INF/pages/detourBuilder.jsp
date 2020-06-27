<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="messages-detours" />

<html>
<head>
<style>
.divSelect {
	width: 100%;
	margin: 0 auto;
	margin-top: 20px;
}

.divSelect div {
	display: inline-block
}
</style>
<!-- So that get proper sized map on iOS mobile device -->
<%@include file="/template/includes.jsp"%>
<%@page import="org.transitclock.web.WebConfigParams"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/leaflet.css" />
<script src="<%=request.getContextPath()%>/javascript/leaflet.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/leaflet.draw.css" />
	<script src="<%=request.getContextPath()%>/javascript/leaflet.draw.js"></script>
	<!-- <script src="<%=request.getContextPath()%>/javascript/Leaflet.Draw.Events.js"></script>

 <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/leaflet.draw/0.2.3/leaflet.draw.js"></script>
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet.draw/0.2.3/leaflet.draw.css">	-->
<script src="<%=request.getContextPath()%>/maps/javascript/mapUiOptions.js"></script>
<script src="<%=request.getContextPath()%>/javascript/map/map.js"></script>
<script src="<%=request.getContextPath()%>/detours/js/detours.js"></script>
<!-- <script src="javascript/leafletRotatedMarker.js"></script>
<script src="javascript/mapUiOptions.js"></script>
 -->
<script
	src="<%=request.getContextPath()%>/javascript/jquery-dateFormat.min.js"></script>





<!-- Load in Select2 files so can create fancy selectors -->
<link
	href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css"
	rel="stylesheet" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
<!--  Override the body style from the includes.jsp/general.css files -->
<style>
body {
	margin: 0px;
}
</style>
</head>
<body>
	<%@include file="/template/header.jsp"%>
	${greeting}
	<div id="routesContainer" class="divSelect">
		<div id="routesDiv">
			<fmt:message key="label.routes" />
			<select id="routes" style="width: 380px"></select> <input
				type="hidden" id="routes" style="width: 380px" />
			<fmt:message key="label.direction" />
		</div>
		<div id="directionsDiv">
			<select id="directions" style="width: 380px"></select> <input
				type="hidden" id="directions" style="width: 380px" />
		</div>
	</div>
	<div class="divSelect">Valid from: <input type="text" id="validFrom"> Valid to: <input type="text" id="validTo"><input type="Button" value="Save" onclick="saveDetour()"> <input type="Button" value="Cancel">  </div>
	
	
	<div id="map"style="width: 800px;height:400px;	background-color:lightblue">
	
	</div>
</body>
<script type="text/javascript">


$("#validFrom").flatpickr({
    enableTime: true,
    dateFormat: "Y-m-d H:i",
});
$("#validTo").flatpickr({
    enableTime: true,
    dateFormat: "Y-m-d H:i",
});
params=
	{
		mapTileUrl:'<%= WebConfigParams.getMapTileUrl() %>',
		mapTileUrl:'https://a.tile.openstreetmap.org/{z}/{x}/{y}.png',
		mapTileCopyright:'<%= WebConfigParams.getMapTileCopyright() %>',
		zoomControl: false,  center: [51.505, -0.09],
		    zoom: 13
	}
	console.log(L);
$( document ).ready(function() {init(),
	/*createMap('<%= WebConfigParams.getMapTileUrl() %>','<%= WebConfigParams.getMapTileCopyright() %>') */
	
	map = new TTCMap('map', params);
			
});

</script>
</html>