<!DOCTYPE html>



<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test for the front end</title>
<style>

html,body{
	margin: 0px;
	padding: 0px;
}

#graph{
	position:relative;
	width:100%;
	height:100%;
	top:25px;
	background-color: #528B8B;
	border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	-webkit-border-radius: 10px 10px 10px 10px;
	border: 0px solid #000000;
	
}
.outerRack{
	float: left;
	height: 400px;
	width: 250px;
	background-color: gray;
	margin: 25px;
	border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	-webkit-border-radius: 10px 10px 10px 10px;
	border: 0px solid #000000;
}
.innerRack{
	margin-left: 20px;
	margin-top: 20px;
	padding: 12px;
	float: left;
	height: 188px;
	width: 188px;
	background-color: white;
	border-radius: 10px 10px 0px 0px;
	-moz-border-radius: 10px 10px 0px 0px;
	-webkit-border-radius: 10px 10px 0px 0px;
	border: 0px solid #000000;
	
}
.innerRack img{
	padding-bottom: 12px;
}

#chartdiv {
	width	: 100%;
	height	: 500px;
}			

.addServer{
	float: left;
	width: 212px;
	height: 50px;
	background-color: #999;
	margin-left: 20px; 
}
.rackInfo{
	float: left;
	width: 212px;
	height: 100px;
	background-color: blue;
	margin-left: 20px;
	border-radius: 0px 0px 10px 10px;
	-moz-border-radius: 0px 0px 10px 10px;
	-webkit-border-radius: 0px 0px 10px 10px;
	border: 0px solid #000000;
}


#serverInfo{
	float: left;
	height: 200px;
	width: 250px;
	background-color: white;
	margin-left: 25px;
	margin-top: 25px;
	border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	-webkit-border-radius: 10px 10px 10px 10px;
	border: 0px solid #000000;
}
#information{
	
	margin-left: 0px;
	margin-top: 0px;
	padding: 12px;
	float: left;
	height: 150px;
	width: 228px;
	background-color: #528B8B;
	border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	-webkit-border-radius: 10px 10px 10px 10px;
	border: 0px solid #000000;
}
p{
	margin: 5px;
	padding-left: 10px;
	color: white;
	font-family: "Trebuchet MS", Helvetica, sans-serif;
}
</style>

<script>
    
    
    
	//These arrays hold the power information for each server
    var hostID = [1,2,3,4,5,6];
    var hostRackID = [1,1,1,2,2,3];
	var hostMax = [4.27,4.31,4.31,4.31,3.12,3.05];
    var hostAverage = [3.12,3.12,3.13,3.13,3.02,3.01];
    var hostName = ["Host1","Host2","Host3","Host4","80","Host 6"];

    var rackID = [1,2,3];
    var rackMax = [12.89,4.31,3.05];
    var rackAverage = [5.16,3.11,3.01];
    var rackName = ["R1","R2","Rack 3"];
    var test=0;
    var current_host = 0;
    var current_rack =0;
    var max_power;
    
  
      
    //This displays the server info
    function displayServerInfo(element, number){
    document.getElementById("information").innerHTML="<p>Server Number: "+parseInt(hostID[number])+"</p><p>Max Power: "+hostMax[number]+"</p><p>Average: "+hostAverage[number]+"</p>";
    
    var elements = document.getElementsByName("server");
    
    for(var i=0; i<elements.length; i++) {
		elements.item(i).style.opacity="1.0";
     
    }
   
    element.style.opacity = "0.2";
    element.style.filter  = 'alpha(opacity=90)'; // IE fallback
    
   
    }
    
    
    
    function drag(ev, passed_id) {
    	var id_length = passed_id.length; 	
    	current_host=passed_id.slice(1,id_length);
        ev.dataTransfer.setData("text/html", ev.target.id);
    }

    function drop(ev, passed_id) {		
        current_rack=passed_id;
        updateArrays();
       	
        ev.preventDefault();       
        var data = ev.dataTransfer.getData("text/html");
        ev.target.appendChild(document.getElementById(data));
		
        
  
    }
  
    function allowDrop(ev) {
        ev.preventDefault();
    }

    function updateArrays(){
  		//Max Update
 		
  		var old_rack_max = 0;
  		for(var i = 0; i < hostRackID.length; i++){ 			
  			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack 
  			if((hostRackID[i] === hostRackID[current_host]) && i!= current_host){
  				console.log("test1 - old rackID ".concat(hostRackID[current_host]));
  				console.log("test2 - loop index (array position) ".concat(i));
  				old_rack_max = old_rack_max + hostMax[i];
  				console.log("test3 - old rack max ".concat(old_rack_max));
  			}
  		}
  		rackMax[hostRackID[current_host]-1] = old_rack_max.toFixed(2);
  		document.getElementById("rm"+hostRackID[current_host]).innerHTML="Estimated Maximum: "+rackMax[hostRackID[current_host]-1];
  		
  		//Average Update
  		var old_rack_average = 0;  		
  		var old_host_count = 0; 
  		for(var i = 0; i < hostRackID.length; i++){
  			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack 
  			if((hostRackID[i] === hostRackID[current_host]) && i!= current_host){
  				old_host_count++;
  				old_rack_average = old_rack_average + hostAverage[i];
  			}
  		}

  		if(old_host_count === 0){ 			
  			rackAverage[hostRackID[current_host]-1] = 0;
  		}else{		
  			rackAverage[hostRackID[current_host]-1] = (old_rack_average/old_host_count).toFixed(2);
  		}
  		document.getElementById("ra"+hostRackID[current_host]).innerHTML="Estimated Average: "+rackAverage[hostRackID[current_host]-1];

  		//Switch the servers rack to the new one it was dropped into
  		hostRackID[current_host]=current_rack;  	
  		
  		var new_rack_max = 0;
  		for(var i = 0; i < hostRackID.length; i++){
  			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack 
  			if(hostRackID[i] == hostRackID[current_host]){
  				console.log("test4 - new rackID ".concat(hostRackID[current_host]));
  				console.log("test5 - loop index (array position) ".concat(i));
  				new_rack_max = new_rack_max + hostMax[i];
  				console.log("test3 - new rack max ".concat(new_rack_max));
  			}
  		}
  		rackMax[hostRackID[current_host]-1] = new_rack_max.toFixed(2);
  		document.getElementById("rm"+hostRackID[current_host]).innerHTML="Estimated Maximum: "+rackMax[hostRackID[current_host]-1];

  	// This changes the color of the racks, where red means the rack is not being used efficiently,
  		// amber means it could be better, and green means it's used effectively
  		
  		for(var i=0; i<rackID.length;i++){
  			var str = document.getElementById("rm"+rackID[i]).innerHTML.toString().split(":");
  			
  			if(parseFloat(str[1])>max_power){
  				document.getElementById("rack"+rackID[i]).style.background = "red";
  			}
  			
  			else if((parseFloat(str[1]))<max_power*0.85){
  				document.getElementById("rack"+rackID[i]).style.background = "orange";
  			}
  			
  			else if((parseFloat(str[1])>max_power*0.85 && parseFloat(str[1])<max_power)){
  				document.getElementById("rack"+rackID[i]).style.background = "green";
  			}
  		}
  		
  		
  		
  		//Average Update (reset the count and total averages)
  		var new_rack_average = 0;  		
  		var new_host_count = 0;   		
  		
  		for(var v = 0; v<hostRackID.length; v++){
  			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack 
  			if(hostRackID[v] == current_rack){
  				new_host_count++;
  				new_rack_average = new_rack_average + hostAverage[v];
  			}
  		}
  		rackAverage[hostRackID[current_host]-1] = (new_rack_average/new_host_count).toFixed(2);
  		document.getElementById("ra"+hostRackID[current_host]).innerHTML="Estimated Average: "+rackAverage[hostRackID[current_host]-1];
 
  	}
    
  
    
    
    </script>
</head>
<body>
<div id='serverInfo'>
	<div id='information'>
		
	</div>
	
	<a href="Graph.png">	<img  id="graph" src="Graph.png" width="100%"></a>
</div>


	


		<div class="outerRack" draggable="false">
		<div class="innerRack" draggable="false" id="1"  ondrop="drop(event, this.id)" ondragover="allowDrop(event)">
			<img name="server" onClick="displayServerInfo(this, 0)" src="server.png" id="h0" draggable="true" ondragstart="drag(event, this.id)" />
			<img name="server" onClick="displayServerInfo(this, 1)" src="server.png" id="h1" draggable="true" ondragstart="drag(event, this.id)" />
			<img name="server" onClick="displayServerInfo(this, 2)" src="server.png" id="h2" draggable="true" ondragstart="drag(event, this.id)" />
		</div>
<div class='addServer'>
		<img src='addserver.png'></div><div class='rackInfo' id='rack1'><p id="rn1">Rack Name:R1</p><p id="ra1">Average:5.16</p><p id ="rm1">Maximum:12.89</p></div>	</div>
	<div class="outerRack" draggable="false">
		<div class="innerRack" draggable="false" id="2"  ondrop="drop(event, this.id)" ondragover="allowDrop(event)">
			<img name="server" onClick="displayServerInfo(this, 3)" src="server.png" id="h3" draggable="true" ondragstart="drag(event, this.id)" />
			<img name="server" onClick="displayServerInfo(this, 4)" src="server.png" id="h4" draggable="true" ondragstart="drag(event, this.id)" />
		</div>
<div class='addServer'>
		<img src='addserver.png'></div><div class='rackInfo' id='rack2'><p id="rn2">Rack Name:R2</p><p id="ra2">Average:3.11</p><p id ="rm2">Maximum:4.31</p></div>	</div>
	<div class="outerRack" draggable="false">
		<div class="innerRack" draggable="false" id="3"  ondrop="drop(event, this.id)" ondragover="allowDrop(event)">
			<img name="server" onClick="displayServerInfo(this, 5)" src="server.png" id="h5" draggable="true" ondragstart="drag(event, this.id)" />
		</div>
<div class='addServer'>
		<img src='addserver.png'></div><div class='rackInfo' id='rack3'><p id="rn3">Rack Name:Rack 3</p><p id="ra3">Average:3.01</p><p id ="rm3">Maximum:3.05</p></div>	</div>





	<div id="sysinfo"></div>

	<div id="info"></div>
</body>



</html>
