<%-- For creating a stop selector parameter via a jsp include. 
     User can select a single stop.
     Reads in stops via API for a route specified by the "r" param. --%>

<style type="text/css">
/* Set font for stop selector. Need to use #select2-drop because of 
 * extra elements that select2 adds 
 */
#select2-drop, #stopsDiv {
  font-family: sans-serif; font-size: large;
}
#select2-drop, #routesDiv {
  font-family: sans-serif; font-size: large;
}
</style>

<script>
$.getJSON(apiUrlPrefix + "/command/routes", 
 		function(routes) {
	        // Generate list of routes for the selector.
	        // Put in default value of Select Route but need to use
	        // an id of ' ' instead of '' since otherwise select2
	        // version 4.0.0 uses the text name as the id, which is wrong!
	 		var selectorData = [];
	 		for (var i in routes.routes) {
	 			var route = routes.routes[i];
	 			selectorData.push({id: route.shortName, text: route.name})
	 		}
	 		
	 		// Configure the selector to be a select2 one that has
	 		// search capability
 			$("#route").select2({
 				data : selectorData})
 			// Need to reset tooltip after selector is used. Sheesh!
 			.on("select2:select", function(e) {
 				var configuredTitle = $( "#route" ).attr("title");
 				getDirections(e);
 				$( "#select2-route-container" ).tooltip({ content: configuredTitle,
 						position: { my: "left+10 center", at: "right center" } });
 			});
	 		
	 		// Tooltips for a select2 widget are rather broken. So get
	 		// the tooltip title attribute from the original route element
	 		// and set the tooltip for the newly created element.
	 		var configuredTitle = $( "#route" ).attr("title");
	 		$( "#select2-route-container" ).tooltip({ content: configuredTitle,
	 				position: { my: "left+10 center", at: "right center" } });
 	});
 	
 	
function getDirections(route)
{
	$.getJSON(apiUrlPrefix + "/command/stops?r="+route, 
	 		function(stops) {
			   var stopSelectorData = [];
			   var directionSelectorData = [];
			   
				for (var i in stops.direction) {
		 			var direction = stops.direction[i];
		 			directionSelectorData.push({id: direction.id, text: direction.title})
		 		}
			   
			   $("#direction").select2({
	 				data : directionSelectorData}).
	 				on("select2:select", function(e) {
	 	 				var configuredTitle = $( "#route" ).attr("title");
	 	 				getStops(route, e);
	 	 				$( "#select2-route-container" ).tooltip({ content: configuredTitle,
	 	 						position: { my: "left+10 center", at: "right center" } });
	 	 			});
			   $("#stop").select2({
	 				data : stopSelectorData})
	 		   	 	
	 	});
};
</script>
 	<div id="routesDiv"  class="param">
      <label for="route">Route:</label>
      <select id="route" name="r" style="width: 380px" 
      	title="Select which route you want data for. " ></select>    
    </div>
     <div id="directionsDiv"  class="param">    
      <label for="direction">Direction:</label>
      <select id="direction" name="s" style="width: 380px" 
      	title="Select which direction you want data for. " ></select>
    </div>
    <div id="stopsDiv"  class="param">    
      <label for="stop">Stop:</label>
      <select id="stop" name="s" style="width: 380px" 
      	title="Select which stop you want data for. " ></select>
    </div>
    