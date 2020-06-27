
var routeQueryStrParam;
var currentSelection;
var routeDetailsSelected = null;
var  map;
var routeFeatureGroup = null;
var initialStopId;

function setRouteQueryStrParam(param) {
	routeQueryStrParam = param;
}
function saveDetour()
{
	
	alert("saving");
	var layerGroup=map.getEditableLayers();
	 if(layerGroup.getLayers().length==0)
     {
		 alert("TODO: There is no detour");
		 return;
     }
    	
    	
   var layers= layerGroup.getLayers();
   var polyLine=null;
    for(var i=0;i<layers.length;i++)
    {
    	 if(layers[i] instanceof L.Polyline)
    	{
    		 polyLine=layers[i];
    	}
    	 
    }
    if(polyLine==null)
    {
		 alert("TODO: There is no detour polilyne");
		 return;
    }
    var obj={};
    obj.latLngs=polyLine.getLatLngs();
    console.log(routeDetailsSelected.routes.length)
    obj.routes=routeDetailsSelected;
    obj.selectedDirection=currentSelection;
    console.log(JSON.stringify(obj));
	$.ajax({
	    url: '/web/app/detourBuilderSave',
	    type: 'POST',
	    
	    contentType: "application/json",
	    data: JSON.stringify(obj),
	})
	.done(function() {
	    console.log("success");
	})
	.fail(function() {
	    console.log("error");
	})
	.always(function() {
	    console.log("complete");
	});
	
	
}

function detourRouteConfigCallback(routeDetails)
{
	
	console.log(routeDetails.length);
	routeDetailsSelected=routeDetails;
	console.log(routeDetailsSelected);
	var selectorData=[];
	console.log(routeDetails);
	for (var i in routeDetails.routes[0].direction) {
		console.log("VAR i"+i);
		var direction = routeDetails.routes[0].direction[i];
		selectorData.push({id: direction.id, text: direction.title})
	}
	console.log(routeDetails.routes[0].direction);
	$("#directions").select2({
			placeholder: "Select Direction", 				
			data : selectorData}).on("select2:select", function(e) {
				console.log("SELECTED direction "+e.params.data.id);
				currentSelection=e.params.data.id;
				drawSelectedRoute();
			});
	 
}

function initRouteSelector()
{
	console.log("INIT "+apiUrlPrefix);
	$.getJSON(apiUrlPrefix + "/command/routes", 
			function(routes) {
		console.log("JSON "+routes);
		console.log(routes);
		// Generate list of routes for the selector
		//var selectorData = [];
		var selectorData = [{id: '', text: 'Select Route'}];
		for (var i in routes.routes) {
			console.log("VAR i"+i);
			var route = routes.routes[i];
			selectorData.push({id: route.id, text: route.name})
		}
		console.log(selectorData);
		// Configure the selector to be a select2 one that has
		// search capability
		
		$("#routes").select2({
			placeholder: "Select Route", 				
			data : selectorData})
			.on("select2:select", function(e) {

				// First remove all old vehicles so that they don't
				// get moved around when zooming to new route
				//removeAllVehicles();
				// Configure map for new route	
				console.log("SELECTED "+e.params.data.id);
				var selectedRouteId = e.params.data.id;
				var url = apiUrlPrefix + "/command/routesDetails?r=" + selectedRouteId;
				$.getJSON(url, detourRouteConfigCallback);
				setRouteQueryStrParam(selectedRouteId)
				// Reset the polling rate back down to minimum value since selecting new route
				//avlPollingRate = MIN_AVL_POLLING_RATE;
				//if (avlTimer)
				//	clearTimeout(avlTimer);

				// Read in vehicle locations now
				//setRouteQueryStrParam("r=" + selectedRouteId); 					
				//updateVehiclesUsingApiData();

				// Disable tooltips. For some reason get an unwanted 
				// tooltip consisting of the current select once a selection
				// has been made. It is really distracting. So have to do
				// this convoluted thing after every selection in order to
				// make sure this annoying tooltip doesn't popup.
				$( "#select2-routes-container" ).tooltip({ content: 'foo' });
				$( "#select2-routes-container" ).tooltip("option", "disabled", true);
			});



	});	 
}
/**
 * Reads in route data obtained via AJAX and draws route and stops on map.
 * 
 * Uses global minorStopOptions, stopOptions, minorShapeOptions, shapeOptions, 
 * and map. 
 */
function drawSelectedRoute() {
	
	
	map.showRouteInMap(routeDetailsSelected,currentSelection);
//	console.log("drawSelectedRoute "+  routeDetailsSelected);
//	console.log(routeDetailsSelected);
//	// If there is an old route then remove it
//	if (routeFeatureGroup) {
//		map.removeLayer(routeFeatureGroup);
//	}
//	// Use a FeatureGroup to contain all paths and stops so that can use
//	// bringToBack() on the whole group at once in  order to make sure that
//	// the paths & stops are drawn below the vehicles even if the vehicles
//	// happen to be drawn first.
//	routeFeatureGroup = L.featureGroup();
//
//	// Draw stops for the route. Do stops before paths so that when call  
//	// bringToBack() the stops will end up being on top.
//	var locsToFit = [];
//	var route=routeDetailsSelected[0];
//	for (var i=0; i<route.direction.length; ++i) {
//		
//		var direction = route.direction[i];
//		if(direction.id!=currentSelection)
//			continue;
//		for (var j=0; j<direction.stop.length; ++j) {
//			var stop = direction.stop[j];
//			var options = stop.minor ? minorStopOptions : stopOptions;
//			
//			// Draw first non-minor stop differently to highlight it
//			if (stop.id == initialStopId) {
//				options = firstStopOptions;
//			}
//			
//			// Keep track of non-minor stop locations so can fit map to show them all
//			if (!stop.minor)
//				locsToFit.push(L.latLng(stop.lat, stop.lon));
//			
//			// Create the stop Marker
//			var stopMarker = L.circleMarker([stop.lat,stop.lon], options).addTo(map);
//			
//			routeFeatureGroup.addLayer(stopMarker);
//			
//			// Store stop data obtained via AJAX with stopMarker so it can be used in popup
//			stopMarker.stop = stop;
//			
//			// Store routeShortName obtained via AJAX with stopMarker so can be 
//			// used to get predictions for stop/route
//			stopMarker.routeShortName = route.shortName;
//			
//			// When user clicks on non-minor stop popup information box
//			if (!stop.minor) {
//				stopMarker.on('click', function(e) {
//					showStopPopup(this);
//					}).addTo(map);
//			}
//			
//			// If dealing with the selected stop then popup predictions for it.
//			// Making sure it is not a minor stop so that only happens for proper direction.
//			if (stop.id == initialStopId && !stop.minor)
//				showStopPopup(stopMarker);
//		}
//	}
//
//	// Draw the paths for the route
//	for (var i=0; i<route.shape.length; ++i) {
//		var shape = route.shape[i];
//		var options = shape.minor ? minorShapeOptions : shapeOptions;
//		
//		var latLngs = [];		
//		for (var j=0; j<shape.loc.length; ++j) {
//			var loc = shape.loc[j];			
//			latLngs.push(L.latLng(loc.lat, loc.lon));
//		}
//		var polyline = L.polyline(latLngs, options).addTo(map);
//		
//		routeFeatureGroup.addLayer(polyline);
//	}
//	
//	// Add all of the paths and stops to the map at once via the FeatureGroup
//	routeFeatureGroup.addTo(map);
//
//	// If stop was specified for getting route then locationOfNextPredictedVehicle
//	// is also returned. Use this vehicle location when fitting bounds of map
//	// so that user will always see the next vehicle coming.
//	if (route.locationOfNextPredictedVehicle) {
//		locsToFit.push(L.latLng(route.locationOfNextPredictedVehicle.lat, 
//				route.locationOfNextPredictedVehicle.lon));
//	}
//	
//	// Get map to fit route
//	map.fitBounds(locsToFit);
//	
//	// It can happen that vehicles get drawn before the route paths & stops.
//	// In this case need call bringToBack() on the paths and stops so that
//	// the vehicles will be drawn on top.
//	// Note: bringToBack() must be called after map is first specified 
//	// via fitBounds() or other such method.
//	routeFeatureGroup.bringToBack();
}
//function createMap(mapTileUrl, mapTileCopyright) {
//	// Create map. Don't use default zoom control so can set it's position
//	map = L.map('map', {zoomControl: true,
//	    center: [51.505, -0.09],
//	    zoom: 13
//	});
//	L.control.scale({metric: false}).addTo(map);
//	
//	// Add zoom control where it won't interfere with a back button in 
//	// upper left hand corner.
//	L.control.zoom({position: 'bottomleft'}).addTo(map);
//	
//	L.tileLayer(mapTileUrl,
//	  // Specifying a shorter version of attribution. Original really too long.
//	  //attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
//	  {attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> &amp; <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © ' + mapTileCopyright,
//	   maxZoom: 19
//	   }).addTo(map);
//
//	// Set the CLIP_PADDING to a higher value so that when user pans on map
//	// the route path doesn't need to be redrawn. Note: leaflet documentation
//	// says that this could decrease drawing performance. But hey, it looks
//	// better.
//	L.Path.CLIP_PADDING = 0.8;
//	console.log("MAP CREATED???")
//	// Initiate event handler to be called when a popup is closed. Sets 
//	// predictionsPopup to null to indicate that don't need to update predictions 
//	// anymore since stop popup not displayed anymore.
//	map.on('popupclose', function(e) {
//		predictionsPopup = null;
//		clearTimeout(predictionsTimeout);
//		predictionsTimeout = null;
//		
//		if (e.popup.parent)
//			e.popup.parent.popup = null;
//	});
//	
//	// See if user location specified in query string. If so, use it.
//	// This way can test out map, including walking directions, for
//	// particular locations.
//	if (getQueryVariable('userLat') && getQueryVariable('userLon')) {
//		var userLat = parseFloat(getQueryVariable('userLat'));
//		var userLon = parseFloat(getQueryVariable('userLon'));
//		userLatLng = L.latLng(userLat, userLon);
//		createUserLocationMarker(userLatLng);
//	} else {
//		// User location not specified in query string so start 
//		// continuous tracking of user location
//		//map.locate({watch: true});
//		//map.on("locationfound", locationFound);
//		//map.on("locationerror", locationError);
//	}	
//}
function init()
{
	initRouteSelector();
	
	
}


