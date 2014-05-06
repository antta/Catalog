package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VMInstanciable;
import controller.VMInstantiated;
import controller.VMpowerOps;

public class CatalogServlets extends HttpServlet {

	final public VMInstantiated myVMInstatiated = new VMInstantiated("https://serveurbeta.univ-savoie.fr/sdk", "root", "dcfvgbh");

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//VMInstantiated myVMInstatiated = new VMInstantiated("https://serveurbeta.univ-savoie.fr/sdk", "root", "dcfvgbh");
		//VMInstantiated myVMInstatiated = new VMInstantiated("https://192.168.176.15/sdk", "root", "*master%lbdl+");
		VMInstanciable myInstanciableVM = new VMInstanciable("/home/nicolas/listVM");

		request.setAttribute("listevm", myVMInstatiated.getMyVMs());

		this.getServletContext().getRequestDispatcher( "/WEB-INF/affiche_catalog.jsp" ).forward( request, response );

	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		ArrayList<String> listofVMName = this.myVMInstatiated.getListofVMName();

		for (String vmName : listofVMName) {
			if (request.getParameter(vmName) != null) {
				System.out.println(vmName);
				//VMpowerOps myOps = new VMpowerOps("https://serveurbeta.univ-savoie.fr/sdk", "root", "dcfvgbh", "VM_demo", "poweron");
				//VMpowerOps myOps = new VMpowerOps("https://192.168.176.15/sdk", "root", "*master%lbdl+", vmName, "poweron");
				VMpowerOps myOps = new VMpowerOps("https://serveurbeta.univ-savoie.fr/sdk", "root", "dcfvgbh", vmName, "poweron");
				try {
					myOps.go();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
