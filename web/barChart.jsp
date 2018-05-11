<%-- 
    Document   : barChart
    Created on : Jul 10, 2015, 11:55:05 AM
    Author     : Geofrey Nyabuto
--%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Bar Chart Reports</title>

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<style type="text/css">
${demo.css}
		</style>
            
                
		<script type="text/javascript">
         $(document).ready(function(){
             var elementSize,programArea,period;
             var allData="";
               $.ajax({
        url:"facilityBarChart",
        type:"post",
        data:"",
        dataType:"html",
         success:function(data){
         allData="";
//         alert("called");
         allData=data.split("<br>");
         
var titles = allData[1].split('^^');
var datavalues=allData[0].split("@@");
elementSize=datavalues.length;
programArea=allData[2];
period=allData[3];
//alert("title : "+titles);
//alert("values : "+datavalues);    
//   alert("element size : "+elementSize) ;    
     var options={
            chart: {
            type: 'column'
        },
        title: {
            text: period
        },
        subtitle: {
            text: 'PROGRAM AREA : '+programArea
        },
        
        xAxis: {
//            categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
//            categories: ,
            categories: titles,
                title: {
                text: "INDICATOR NAMES "
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Number Achieved',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' People'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'horizontal',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 70,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: []
    }; 
    
    for(var a=0;a<elementSize;a++){
        var jsonobj=datavalues[a].split("XX");

var variableName=jsonobj[0];
var variableValues=JSON.parse(jsonobj[1].split(","));
//alert(variableValues);
//var variableName='Recounted Data';
//var variableValues=[1,135,16,1,0,151];
    options.series.push({
    name: variableName,
//    data: JSON.parse("[" + datavalues[a].data+ "]")
    data: variableValues
});
            
        }    
        $('#container').highcharts(options);
    }
    });
         });             
        </script>
	</head>
	<body>
<script src="highcharts/js/highcharts.js"></script>
<script src="highcharts/js/modules/exporting.js"></script>

<div id="container" style="min-width: 900px; max-width: 1100px; height: 600px; margin: 0 auto"></div>

	</body>
</html>
