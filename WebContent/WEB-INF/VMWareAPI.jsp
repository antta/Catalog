<!DOCTYPE html>
<%@page import="beans.Nicolas"%>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>

<body>
	<p>Ceci est une page g�n�r�e depuis une JSP.</p>
	<p>
		<%
			String attribut = (String) request.getAttribute("v_bienvenue");
			out.println(attribut);
			
			String parametre = request.getParameter("nom");
			out.println(parametre);
		%>
	</p>
	<p>
		R�cup�ration du bean :
		<%
			beans.Nicolas monBean = (beans.Nicolas) request.getAttribute("nicolas");
			out.println(monBean.getNom());
			out.println(monBean.getPrenom());
		%>

</body>
</html>