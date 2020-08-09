<center>
<form action="table.jsp">
<table border="1">
	<tr><td>Rows</td><td><input name="rows" type="text"/></td></tr>
	<tr><td>Columns</td><td><input name="columns" type="text"/></td></tr>
</table>
<table border="0">
	<tr><td></td><td align=right"><input value="start" type="submit"/></td></td>
</table>
</form>
<%
String rowCntString = request.getParameter("rows");
String colCountString = request.getParameter("columns");
boolean isShowTable = true;
StringBuilder table = new StringBuilder();
int rowsCount = 0;
int colmnsCount = 0;
int count = 0;
if(rowCntString!=null && colCountString!=null)
	try{
		rowsCount = Integer.parseInt(rowCntString);
		colmnsCount = Integer.parseInt(colCountString);
	}catch(NumberFormatException e){
		table.append("Просьба ввести числа!<br>");
		isShowTable=false;
	}
	if(isShowTable){
		table.append("<table style='background-color: #eee'>");
		for(int i=0; i<rowsCount; i++){
			table.append("<tr>");
				for(int j=0; j<colmnsCount; j++){
					count++;
					if(i==j){table.append("<td style='color:red;background-color: #D3EDF6;border:1px solid red; padding: 5px; text-align:center'>");
					}
					else {table.append("<td style='padding: 5px;border:1px solid green; text-align:center'>");
					}
						table.append(count+"</td>");
						
				}
				table.append("</tr>");
		}
		table.append("</table>");
		out.write(table.toString());
	}
%>