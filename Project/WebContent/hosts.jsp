<!DOCTYPE html>


<%@page import="joiner.FrontScreen"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Test for the front end</title>
 	<link rel="stylesheet" type="text/css" href="style.css"> 
    <script>
    <%//Initiates all the datacenters, floors, racks and hosts
     
    %>
    
	//These arrays hold the power information for each server
    var serverID = [<% out.print(FrontScreen.hostToString()); %>];
    var max = [];
    var average = [];

    var images = 8;
    
   
    
    
    //This displays the server info
    function displayServerInfo(element, number){
    document.getElementById("sysinfo").innerHTML="<p>Server Number: "+parseInt(serverID[number])+"</p><p>Max Power: "+max[number]+"</p><p>Average: "+average[number]+"</p>";
    
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

    <div id="test">Racks and Servers</div>
    
    
    <%= FrontScreen.serverAndRackCreation() %>
    
    <div id="sysinfo"></div>

    <div id="info"> </div>
  </body>
</html>