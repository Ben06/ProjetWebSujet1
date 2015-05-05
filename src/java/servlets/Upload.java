/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utilisateurs.gestionnaires.GestionnaireContacts;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Divz
 */
@WebServlet(name = "Upload", urlPatterns =
{
    "/Upload"
})

@MultipartConfig(location = "C:\\Users\\Divz\\Documents\\NetBeansProjects\\TP2\\web\\resources\\files", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Upload extends HttpServlet
{

    @EJB
    private GestionnaireContacts gestionnaireContacts;
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter())
	{
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet Upload</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Servlet Upload at " + request.getContextPath() + "</h1>");
	    out.println("</body>");
	    out.println("</html>");
	}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	String redirectTo = "";
	String actionMultipart = getParamFromMultipartRequest(request, "action");
	HttpSession session = request.getSession();
	Object current = session.getAttribute("sessionUtilisateur");
	Utilisateur currentUser = (Utilisateur) current;
	if (currentUser != null)
	{
	    System.out.println("Upload : session : " + currentUser.getId());
	}

	if (actionMultipart.equals("ajoutDePhoto"))
	{
	    System.out.println("dans ajout photo");
	    response.setContentType("text/html");
	    String contact = getParamFromMultipartRequest(request, "contact");
	    PrintWriter out = response.getWriter();
	    Collection<Part> parts = request.getParts();
	    for (Part part : parts)
	    {
		if (part.getName().equals("fichier"))
		{
		    System.out.println("dans le for");
		    if (part.getSubmittedFileName().endsWith(".jpg") || part.getSubmittedFileName().endsWith(".png"))
		    {

			part.write(part.getSubmittedFileName());
			String picName = part.getSubmittedFileName();

			gestionnaireContacts.ajouterPhoto(currentUser, contact, picName);

			System.out.println("photo ajouté à l'utilisateur d'id " + contact);

			redirectTo = "index.jsp?action=photoAjoute";
		    }
		    else
		    {
			System.out.println("pas une photo");
			redirectTo = "index.jsp?action=ajouterUnePhoto&erreurPhoto=Veuillez selectionner une photo";
		    }
		}
	    }

	}
	response.sendRedirect(redirectTo);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
	return "Short description";
    }// </editor-fold>

    private String getParamFromMultipartRequest(HttpServletRequest request, String paramName) throws IOException, ServletException
    {
	Part part = request.getPart(paramName);
	Scanner scanner = new Scanner(part.getInputStream());
	String myString = scanner.nextLine();
	return myString;
    }
}
