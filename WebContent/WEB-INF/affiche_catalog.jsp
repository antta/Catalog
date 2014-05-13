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
	<% 	ArrayList<VMachine> listInstantiated = (ArrayList<VMachine>)request.getAttribute("listevm");
		ArrayList<VMachine> listInstanciable = (ArrayList<VMachine>)request.getAttribute("vminstanciable");
	%>
	<form action="myCatalog" method="post" >
	<input class="recherche" type="search" placeholder="Entrez un mot-clef" name="the_search">
	<input class="recherche" type="submit" name="recherche" value="Recherche">
	<table>
		<caption>List of instantiated virtual machines</caption>
		
		<tr>
			<th>VirtualMachine's Name</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<%
		for (VMachine vm : listInstantiated)
		{ %>
		<tr>
		<td><% out.print(vm.getName()); %></td>
		<td><% out.print(vm.getDescription()); %></td>
		<td><input type="submit" name="<% out.print(vm.getName()); %>" value="Launch it !"/></td>
		</tr>	
		<%
		}
		%>
		
	</table>
	<table>
		<caption>List of virtual machines instanciables</caption>
		
		<tr>
			<th>Nom de la VM</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<%
		for (VMachine vm : listInstanciable)
		{
		%>
		<tr>
		<td><% out.print(vm.getName()); %></td>
		<td><% out.print(vm.getDescription()); %></td>
		<td><input type="submit" name="<% out.print(vm.getName()); %>" value="Create it !"/></td>
		</tr>
		<%
		}
		%>
	</table>
	</form>
</body>
</html>