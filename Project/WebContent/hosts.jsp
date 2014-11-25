<!DOCTYPE html>
<%@page import="joiner.FrontScreen"
import="parser.DateParser"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test for the front end</title>
<style>

html,body {
	margin: 0px;
	padding: 0px;
	margin: 0px;
	color:#333;
}

#main{
	float:left;
	width: 100%;
	height: auto;
}

#footer{
	float:left;
	width: 100%;
    height: 120px;
    margin: 0px;
    padding: 20px;
    background-color: #008B45;
    font-size: 15px;
	font-family: "Trebuchet MS", Helvetica, sans-serif;
	color:#aaa;
	text-align: center;
	-webkit-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	-moz-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
}

#belowHeader{
	float:left;
	width: 100%;
    height: 60px;
    margin: 0px;
    padding: 10px;
    background-color: #333;
    -webkit-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	-moz-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
}

#belowHeader h2{
	font-size: 15px;
	font-family: "Trebuchet MS", Helvetica, sans-serif;
	color:#aaa;
	text-align: center;
	
}

#header {
	float:left;
    width: 100%;
    height: 240px;
    margin: 0px;
    padding: 0px;
}

#header img{
	max-height: 100%;
	min-height: 100%;
	max-width: 100%;
	min-width: 100%;
    margin: 0px;   
}

.outerRack {
	float: left;
	height: 400px;
	width: 250px;
	background-color: gray;
	margin: 25px;
	border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	-webkit-border-radius: 10px 10px 10px 10px;
	border: 0px solid #000000;
	-webkit-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	-moz-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
}

.innerRack {
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
	overflow-y: auto;
}

.innerRack img {
	padding-bottom: 12px;
}

.addServer {
	float: left;
	width: 212px;
	height: 50px;
	background-color: #999;
	margin-left: 20px;
}

.rackInfo {
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

#serverInfo {
	float: left;
	height: 212px;
	width: 250px;
	background-color: white;
	margin-left: 25px;
	margin-top: 25px;
	border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	-webkit-border-radius: 10px 10px 10px 10px;
	border: 0px solid #000000;
}

#information {
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
	-webkit-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	-moz-box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
	box-shadow:7px 7px 5px 0px rgba(50, 50, 50, 0.75);
}

p {
	margin: 5px;
	padding-left: 10px;
	color: white;
	font-family: "Trebuchet MS", Helvetica, sans-serif;
}
</style>
<script>
<% 
double max = Double.parseDouble(request.getParameter("max_rack_power"));
int year = Integer.parseInt(request.getParameter("start_date_year"));
int month = (Integer.parseInt(request.getParameter("start_date_month"))-1); //-1 as months start at 0 with epoch
int day = Integer.parseInt(request.getParameter("start_date_day"));
DateParser dp = new DateParser(year,month,day);
long start_epoch = dp.parse(); 

int year_end = Integer.parseInt(request.getParameter("end_date_year"));
int month_end = (Integer.parseInt(request.getParameter("end_date_month"))-1); //-1 as months start at 0 with epoch
int day_end = Integer.parseInt(request.getParameter("end_date_day"));
DateParser dp2 = new DateParser(year_end,month_end,day_end);
long end_epoch = dp2.parse();
%>
//These arrays hold the power information for each server
var max_power = <%out.print(max);%>;


<%//Initiates all the datacenters, floors, racks and hosts%>
	//These arrays hold the power information for each server
	var hostID = [
<%out.print(FrontScreen.hostToString(start_epoch, end_epoch));%>
	];
	var hostRackID = [
<%out.print(FrontScreen.hostRackToString(start_epoch, end_epoch));%>
	];
	var hostMax = [
<%out.print(FrontScreen.hostMax(start_epoch, end_epoch));%>
	];
	var hostAverage = [
<%out.print(FrontScreen.hostAverage(start_epoch, end_epoch));%>
	];
	var hostName = [
<%out.print(FrontScreen.hostName(start_epoch, end_epoch));%>
	];
	var rackID = [
<%out.print(FrontScreen.rackToString(start_epoch, end_epoch));%>
	];
	var rackMax = [
<%out.print(FrontScreen.rackMax(start_epoch, end_epoch));%>
	];
	var rackAverage = [
<%out.print(FrontScreen.rackAverage(start_epoch, end_epoch));%>
	];
	var rackName = [
<%out.print(FrontScreen.rackName(start_epoch, end_epoch));%>
	];
	var test = 0;
	var current_host = 0;
	var current_rack = 0;
	//This displays the server info
	function displayServerInfo(element, number) {
		document.getElementById("information").innerHTML = "<p>Server Number: "
				+ parseInt(hostID[number]) + "</p><p>Average: " + hostAverage[number] + "<p>Max Power: " + hostMax[number];
				+ "</p>";
		var elements = document.getElementsByName("server");
				
		for (var i = 0; i < elements.length; i++) {
			elements.item(i).style.opacity = "1.0";
		}
		element.style.opacity = "0.2";
		element.style.filter = 'alpha(opacity=90)'; // IE fallback
	}
	function drag(ev, passed_id) {
		var id_length = passed_id.length;
		current_host = passed_id.slice(1, id_length);
		ev.dataTransfer.setData("text/html", ev.target.id);
	}
	function drop(ev, passed_id) {
		current_rack = passed_id;
		updateArrays();
		ev.preventDefault();
		var data = ev.dataTransfer.getData("text/html");
		ev.target.appendChild(document.getElementById(data));
	}
	function allowDrop(ev) {
		ev.preventDefault();
	}
	
	
	function updateArrays() {
		//Max Update
		var old_rack_max = 0;
		for (var i = 0; i < hostRackID.length; i++) {
			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack
			if ((hostRackID[i] == hostRackID[current_host])
					&& i != current_host) {
				console.log("test1 - old rackID "
						.concat(hostRackID[current_host]));
				console.log("test2 - loop index (array position) ".concat(i));
				old_rack_max = old_rack_max + hostMax[i];
				console.log("test3 - old rack max ".concat(old_rack_max));
			}
		}
		rackMax[hostRackID[current_host] - 1] = old_rack_max.toFixed(2);
		document.getElementById("rm" + hostRackID[current_host]).innerHTML = "Estimated Maximum: "
				+ rackMax[hostRackID[current_host] - 1];
		
		//Average Update
		var old_rack_average = 0;
		var old_host_count = 0;
		for (var i = 0; i < hostRackID.length; i++) {
			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack
			if ((hostRackID[i] == hostRackID[current_host])
					&& i != current_host) {
				old_host_count++;
				old_rack_average = old_rack_average + hostAverage[i];
			}
		}
		if (old_host_count == 0) {
			rackAverage[hostRackID[current_host] - 1] = 0;
		} else {
			rackAverage[hostRackID[current_host] - 1] = (old_rack_average / old_host_count)
					.toFixed(2);
		}
		document.getElementById("ra" + hostRackID[current_host]).innerHTML = "Estimated Average: "
				+ rackAverage[hostRackID[current_host] - 1];
		
		//Switch the servers rack to the new one it was dropped into
		hostRackID[current_host] = current_rack;
		
		
		var new_rack_max = 0;
		for (var i = 0; i < hostRackID.length; i++) {
			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack
			if (hostRackID[i] == hostRackID[current_host]) {
				console.log("test4 - new rackID "
						.concat(hostRackID[current_host]));
				console.log("test5 - loop index (array position) ".concat(i));
				new_rack_max = new_rack_max + hostMax[i];
				console.log("test3 - new rack max ".concat(new_rack_max));
			}
		}
		rackMax[hostRackID[current_host] - 1] = new_rack_max.toFixed(2);
		document.getElementById("rm" + hostRackID[current_host]).innerHTML = "Estimated Maximum: "
				+ rackMax[hostRackID[current_host] - 1];
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
           
            else if((parseFloat(str[1]))>max_power*0.85 && parseFloat(str[1])<max_power){
                    document.getElementById("rack"+rackID[i]).style.background = "green";
            }
    	}
		
		//Average Update (reset the count and total averages)
		var new_rack_average = 0;
		var new_host_count = 0;
		for (var v = 0; v < hostRackID.length; v++) {
			//If the highlighted servers RackID mathces any other Servers RackID it increments the total servers on a rack
			if (hostRackID[v] == current_rack) {
				new_host_count++;
				new_rack_average = new_rack_average + hostAverage[v];
			}
		}
		rackAverage[hostRackID[current_host] - 1] = (new_rack_average / new_host_count)
				.toFixed(2);
		document.getElementById("ra" + hostRackID[current_host]).innerHTML = "Estimated Average: "
				+ rackAverage[hostRackID[current_host] - 1];
	}
</script>
</head>
<body>
<div id="header">
	<a href="index.jsp"><img src="logo.png" alt="Papillon Image" /></a>
</div>
<div id="belowHeader"><h2>Below show's all Racks, inside each rack show's their server. The box on the left will display any server data once clicked.</h2></div>
	<div id="main">
		<div id='serverInfo'>
			<div id='information'></div>
		</div>
		<%=FrontScreen.serverAndRackCreation(start_epoch, end_epoch)%>
		<div id="sysinfo"></div>
		<div id="info"></div>
	</div>
	<div id="footer">
		<p>
			- MSc Computer Science (conversion) -<br />
			Andrew Duignan<br />
			Colm Glennon<br />
			Jason De Paor<br />
			Jonathan Cody<br />
			Ronan Frawley
		</p>
	</div>
</body>
</html>