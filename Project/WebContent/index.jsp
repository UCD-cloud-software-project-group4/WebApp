<!DOCTYPE html>


<%@page import="joiner.FrontScreen"%>


<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Test for the front end</title>
    <style type="text/css">


      html,body{
        margin:0px;
        padding:0px;
      }
      #test{
        float:left;
        color: #eee;
        background-color: #008000;
        width: 100%;
        height: 50px;
        padding: 20px;
      }
      .rack{
        float:left;
        width: 400px;
        height: 546px;
        background: url('rack.jpg');
        margin: 50px;
      }
      
      #sysinfo{
      	position:absolute;
      	top:140px;
      	left:970px;
      	background-color:#369;
      	height:120px;
      	width:150px;
      	padding-left:20px;
      	
      }

      #rackinner1{
        float:left;
        width:280px;
        height:380px;
        margin-top: 45px;
        margin-left: 45px;
        padding-left: 15px;
        padding-top: 15px;
        overflow:scroll;
      }

      #rackinner2{
        float:left;
        width:280px;
        height:380px;
        margin-top: 45px;
        margin-left: 45px;
        padding-left: 15px;
        padding-top: 15px;
        overflow:scroll;
      }
      
      #info{
        float: left;
        width: 100%;
        height: 200px;
        color: #eee;
        background-color: #222;
        padding: 10px;
        
        
        
      }
    </style>
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
<!-- 
    <div class="rack">
      <div id="rackinner1"  ondrop="drop(event)" ondragover="allowDrop(event)">
        <img name="server" onClick="displayServerInfo(this, 0)" src="server.jpg" id="drag0" draggable="true" ondragstart="drag(event)"/>
        <img name="server" onClick="displayServerInfo(this,1)" src="server.jpg" id="drag1" draggable="true" ondragstart="drag(event)" />
        <img name="server" onClick="displayServerInfo(this,2)" src="server.jpg" id="drag2" draggable="true" ondragstart="drag(event)" />        
        <img name="server" onClick="displayServerInfo(this,3)" src="server.jpg" id="drag3" draggable="true" ondragstart="drag(event)" />
        <img name="server" onClick="displayServerInfo(this,4)" src="server.jpg" id="drag4" draggable="true" ondragstart="drag(event)" />
        <img name="server" onClick="displayServerInfo(this,5)" src="server.jpg" id="drag5" draggable="true" ondragstart="drag(event)" /> 
        <img name="server" onClick="displayServerInfo(this,6)" src="server.jpg" id="drag6" draggable="true" ondragstart="drag(event)" />
        <img name="server" onClick="displayServerInfo(this,7)" src="server.jpg" id="drag7" draggable="true" ondragstart="drag(event)" />
        </div>
     
    </div>
    
    
    <div class="rack">
      <div id="rackinner2" ondrop="drop(event)" ondragover="allowDrop(event) ">
      </div>
    </div>    -->
    
    <div id="sysinfo"></div>

    <div id="info"> </div>
  </body>
</html>