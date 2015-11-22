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
        try {
             HtmlHttpUtils.doHeader("Vue Utilisateur", out);
            if (HtmlHttpUtils.isAuthenticate(request)) {

                //Récupère les paramètres de la requête
                int idUser = Integer.parseInt(request.getParameter("idUser"));

                //Récupère la session
                HttpSession s = request.getSession();

                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                UserService service = new UserService(em);

                User u = service.findUser(idUser);
                
                //Fermeture de la connexion
                em.close();
                emf.close();

                
                out.println("<form method='POST' action='ServletFaireMAJUtilisateur'>");
                out.println("<input type='hidden' name='id' value='" + u.getId() + "'><br>");
                out.println("id: <input type='text' name='id' value='" + u.getId() + "' DISABLED><br>");
                out.println("username: <input type='text' name='username' value='" + u.getUsername()+ "'><br>");
                out.println("password: <input type='text' name='password' value='" + u.getPassword()+ "'><br>");
                out.println("nom: <input type='text' name='nom' value='" + u.getLastName()+ "'><br>");
                out.println("prenom : <input type='text' name='prenom' value='" + u.getFirstName()+ "'><br>");
                out.println(" ville :  <input type='text' name='ville' value='" + u.getCity() + "'><br>");
                out.println(" date de naissance :  <input type='text' name='date_nais' value='" + u.getBirthday()+ "'><br>");
                out.println(" email :  <input type='text' name='email' value='" + u.getEmail()+ "'><br>");
                out.println(" date de recrutement :  <input type='text' name='date_recrut' value='" + u.getRecruited()+ "'><br>");
                out.println("<input type='submit' value='MAJ Utilisateur'>");
                out.println("</form>");

            }
            HtmlHttpUtils.doFooter(out);
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
