<!DOCTYPE html>


<%@page import="joiner.FrontScreen"%>
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
	height: 400px;
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
	height: 188px;
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
    
    <%//Initiates all the datacenters, floors, racks and hosts
     
    %>
    
	//These arrays hold the power information for each server
    var hostID = [<% out.print(FrontScreen.hostToString()); %>];
    var hostMax = [<% out.print(FrontScreen.hostMax()); %>];
    var hostAverage = [<% out.print(FrontScreen.hostAverage()); %>];
    var hostName = [<% out.print(FrontScreen.hostName()); %>]

    var rackID = [<% out.print(FrontScreen.rackToString()); %>];
    var rackMax = [<% out.print(FrontScreen.rackMax()); %>];
    var rackAverage = [<% out.print(FrontScreen.rackAverage()); %>];
    var rackName = [<% out.print(FrontScreen.rackName()); %>]
      
    //This displays the server info
    function displayServerInfo(element, number){
    document.getElementById("sysinfo").innerHTML="<p>Server Number: "+parseInt(hostID[number])+"</p><p>Max Power: "+hostMax[number]+"</p><p>Average: "+hostAverage[number]+"</p>";
    
    var elements = document.getElementsByName("server");
    
    for(var i=0; i<elements.length; i++) {
		elements.item(i).style.opacity="1.0";
     
    }
   
    element.style.opacity = "0.2";
    element.style.filter  = 'alpha(opacity=90)'; // IE fallback
    
   
    }
    
    function drag(ev) {
        ev.dataTransfer.setData("text/html", ev.target.id);
    }

    function drop(ev) {
        ev.preventDefault();
        
        var data = ev.dataTransfer.getData("text/html");
        ev.target.appendChild(document.getElementById(data));

        images--;
  
    }
  
    function allowDrop(ev) {
        ev.preventDefault();
    }

    function change() {
      var stuff = "";
      for (var i = 0; i < images; i++) {
          stuff += "<img src='server.jpg' id='drag' draggable='true' ondragstart='drag(event)' />";
      };
      //alert("innerHTML" + stuff);
      document.getElementById("rackinner1").innerHTML = stuff;
    }
    </script>
</head>
<body>
<div id='serverInfo'>
	<div id='information'>
		<p>1</p>
		<p>2</p>
		<p>3</p>
		<p>4</p>
	</div>
</div>

	


	<%= FrontScreen.serverAndRackCreation() %>




	<div id="sysinfo"></div>

	<div id="info"></div>
</body>



</html>
