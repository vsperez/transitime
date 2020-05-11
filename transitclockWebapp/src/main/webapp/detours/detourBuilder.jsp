<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<!-- So that get proper sized map on iOS mobile device -->
<%@include file="/template/includes.jsp"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet"
	href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
<script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
<script src="javascript/leafletRotatedMarker.js"></script>
<script src="javascript/mapUiOptions.js"></script>

<script
	src="<%= request.getContextPath() %>/javascript/jquery-dateFormat.min.js"></script>
<script
	src="<%= request.getContextPath() %>/synoptic/javascript/synoptic.js"></script>

<link rel="stylesheet" href="css/mapUi.css" />
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/synoptic/css/synoptic.css">

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
<div id="routesContainer">
	<div id="routesDiv">
		<select id="routes" style="width: 380px"></select> <input
			type="hidden" id="routes" style="width: 380px" />
	</div>
</div>
<div></div>
</html>