package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VMInstanciable;
import controller.VMInstantiated;
import controller.VMpowerOps;

public class CatalogServlets extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VMInstantiated myVMInstatiated = new VMInstantiated("https://serveurbeta.univ-savoie.fr/sdk", "root", "dcfvgbh");
		VMInstanciable myInstanciableVM = new VMInstanciable("/home/nicolas/listVM");

		request.setAttribute("listevm", myVMInstatiated.getMyVMs());

		this.getServletContext().getRequestDispatcher( "/WEB-INF/affiche_catalog.jsp" ).forward( request, response );

	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		if (request.getParameter("button") != null) {
			VMpowerOps myOps = new VMpowerOps("https://serveurbeta.univ-savoie.fr/sdk", "root", "dcfvgbh", "VM_demo", "poweron");
			try {
				myOps.go();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
