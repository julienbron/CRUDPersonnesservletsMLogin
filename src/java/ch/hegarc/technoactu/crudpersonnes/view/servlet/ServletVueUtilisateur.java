/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.view.servlet;

import ch.hegarc.technoactu.crudpersonnes.business.Person;
import ch.hegarc.technoactu.crudpersonnes.business.User;
import ch.hegarc.technoactu.crudpersonnes.constant.cons;
import ch.hegarc.technoactu.crudpersonnes.services.PersonService;
import ch.hegarc.technoactu.crudpersonnes.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gary.criblez
 */
public class ServletVueUtilisateur extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("includes/header.jsp").include(request, response);
        request.getRequestDispatcher("includes/navbar.jsp").include(request, response);
        try {
            out.println("<div class=\"container\">");
            HtmlHttpUtils.doHeader("Vue Utilisateur", out);
            
            if (HtmlHttpUtils.isAuthenticate(request)) {             
                
                //Récupère la session
                HttpSession s = request.getSession(true);             
                
                //Récupère des paramètres de la session
                Integer idUser = (Integer) s.getAttribute("idUser");
                

               
                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                UserService service = new UserService(em);
                
                User u = service.findUser(idUser);
                //Fermeture de la connexion
                em.close();
                emf.close();

                out.println("<div class=\"container\">");
                out.println("<table class=\"table table-striped\">");
                out.println("<tr>");
                out.println("<td style=\"width:300px;\"><b>Nom d'utilisateur</b></td>");
                out.println("<td>"+u.getUsername()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Prénom et nom</b></td>");
                out.println("<td>"+u.getFirstName()+" "+u.getLastName()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Ville</b></td>");
                out.println("<td>"+u.getCity()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Date de naissance</b></td>");
                out.println("<td>"+u.getBirthday()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Adresse Mail</b></td>");
                out.println("<td>"+u.getEmail()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Employé depuis</b></td>");
                out.println("<td>"+u.getRecruited()+"</td>");
                out.println("</tr>");
                out.println("</table>");
            
                out.println("<p class=\"text-right\"><a href=\"modificationUtilisateur.jsp\" class= \"btn btn-default\"span class=\"glyphicon glyphicon-pencil\"></span>Modifier les informations</a></p>");
               
                
            }else{
                out.println("<p>Erreur d'authentification, veuillez préciser username , password");
                out.println("<a href='login.jsp'>reessayer</a>");
                out.println("</body></html>");
            }
            HtmlHttpUtils.doFooter(out);
            out.println("</div>");
        } finally {
            out.close();
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}