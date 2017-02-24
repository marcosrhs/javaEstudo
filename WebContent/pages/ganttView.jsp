<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gantt View</title>

<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/reset.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/jquery.ganttView.css" />

<script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../scriptGanttView/date.js"></script>
<script type="text/javascript"
	src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
<script type="text/javascript"
	src="../scriptGanttView/jquery.ganttView.js"></script>

<style type="text/css">
body {
	font-family: tahoma, verdana, helvetica;
	font-size: 0.8em;
	padding: 10px;
}
</style>

</head>
<body>
	<h1>Gantt View</h1>
	
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
</body>

<script type="text/javascript">

$(document).ready(function() {

		$.get("buscarDatasPlanejamento", function(response) {
			
			//var ganttDataResposta = JSON.parse(response);
			
			var ganttData = [ 
			                 
			              	{
			              		id: 1, name: "Projeto Java Web", series: [
			              			{ name: "Planejado", start: new Date(2017,00,05), end: new Date(2017,00,20) },
			              			{ name: "Real", start: new Date(2017,00,06), end: new Date(2017,00,17), color: "#f0f0f0" },
			              			{ name: "Projetado", start: new Date(2017,00,06), end: new Date(2017,00,25), color: "#e0e0e0" }
			              		]
			              	}
			
			
			              ];
		
			
		
					$("#ganttChart").ganttView({ 
						data: ganttData,
						slideWidth: 600,
						behavior: {
							onClick: function (data) { 
								var msg = "Evento de click: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
							},
							onResize: function (data) { 
								var msg = "Evento de redimencinar { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
							},
							onDrag: function (data) { 
								var msg = "Evento de errastar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
							}
						}
					});
				
        });	

});
	</script>
</html>