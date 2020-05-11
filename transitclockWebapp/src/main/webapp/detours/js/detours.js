
var routeQueryStrParam;
function setRouteQueryStrParam(param) {
	routeQueryStrParam = param;
}
function detourRouteConfigCallback()
{
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
function init()
{
	initRouteSelector();
}