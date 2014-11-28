WebApp
======


README

Overview of Application

This is a web-based application which monitors racks and servers in a data center.This application monitors
and identifies racks that are being used ineffectively or are close to their operational limit (i.e. they 
have a small number of active servers or a  small power consumption, or the collective power consumption of 
the servers is close to the maximum power limits of the rack’s Power Units). Furthermore,  users of this tool
are able to set thresholds for defining these levels. Once candidate racks for decommissioning are identified, 
a user can,  through the use of a dashboard GUI,  view the racks in the data centre, click on a server in a 
rack and drag and drop it into a rack that has spare capacity. The tool  then uses the information on the server’s 
power consumption (provided by interaction with the Papillon system) to determine if the new rack can accommodate 
the new server. The impact on the rack of the new server is then highlighted, together with the impact on the rack 
from where it was taken. Any violations of the centre’s or Power Distribution Units (PDU’s) operating conditions  
triggers an automatic warning to the user. The overall effect is to allow the user to manually consolidate racks 
subject to the constraints and regulations of the operational conditions of the data centre.

System Architecture

The software behind this application was built in java. The functionality of the application is incorporated into 
a web based GUI with JSP and some Javascript as well for handling forms provided by users and dynamically changing 
representations of data. When a user views racks and servers via the GUI, this is a result of JSP calling on Java
methods and generating HTML in the FrontScreen.java class in order to create GUI placeholders populated by datacenter
objects (i.e. Server, racks, floors, etc...) from the hardware package.  These objects are  populated with the 
necessary data (i.e. Power usage, etc...) obtained from the Papillon System through classes and methods in the 
Connection package. These classes connect with the Papillon system in a RESTful manner and retrieve JSON data which 
is then parsed (using methods in the parser class) .
