<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="vmware.controller.VMachine"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Axway's Catalog</title>
<link type="text/css" rel="stylesheet" href="form.css" />
</head>
<body>
	<% 	ArrayList<VMachine> listvm = (ArrayList<VMachine>)request.getAttribute("listevm");
	%>
	<form action="myCatalog" method="post" >
	<table>
		<caption>Liste des machines virtuelles</caption>
		
		<tr>
			<th>Nom de la VM</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<%
		for (VMachine vm : listvm)
		{ %>
		<tr>
		<td><% out.print(vm.getName()); %></td>
		<td><% out.print(vm.getDescription()); %></td>
		<td><input type="submit" name="button" value="Launch it !"/></td>
		</tr>	
		<%
		}
		%>
		
	</table>
	</form>
</body>
</html>