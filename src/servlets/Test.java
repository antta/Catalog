package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Nicolas;

public class Test extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomVisiteur = request.getParameter("nom");
		String txt_bienvenue = "Bienvenue "+nomVisiteur+" dans mon catalogue.";
		request.setAttribute("v_bienvenue", txt_bienvenue);
		
		Nicolas monPremierBean = new Nicolas();
		monPremierBean.setNom("Challut");
		monPremierBean.setPrenom("Nicolas");
		request.setAttribute("nicolas", monPremierBean);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/VMWareAPI.jsp" ).forward( request, response );
	}

}
