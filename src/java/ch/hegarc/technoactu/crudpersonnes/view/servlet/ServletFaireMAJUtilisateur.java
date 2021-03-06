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
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ServletFaireMAJUtilisateur extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String  id = null, username = null, password = null, nom = null, prenom = null, ville = null, date_nais = null, email = null, date_recrut = null;
        
        try {
            if (HtmlHttpUtils.isAuthenticate(request)) {
                
                //Récupère les paramètres de la requête
                
                
                id = request.getParameter("id");
                username = request.getParameter("username");
                password = request.getParameter("password");
                nom = request.getParameter("nom");
                prenom = request.getParameter("prenom");
                ville = request.getParameter("ville");
                date_nais = request.getParameter("date_nais");
                email = request.getParameter("email");
                date_recrut = request.getParameter("date_recrut");
                
                Date dateN = new Date();
                Date dateR = new Date();
            
                 //formatage pour les champs dates  
                if(date_nais != null){
                    dateN = new SimpleDateFormat("dd.MM.yyyy").parse(date_nais);
                }
                
                if(date_recrut != null){
                    dateR = new SimpleDateFormat("dd.MM.yyyy").parse(date_recrut);
                }
                
                     
                
                //Récupère la session
                HttpSession s = request.getSession(true);             
                
                //Récupère des paramètres de la session
                Integer idUser = (Integer) s.getAttribute("idUser");
                
               
                //Crée l'utilisateur
                User u = new User(idUser, username, password, prenom, nom, ville, new java.sql.Date(dateN.getTime()), email, new java.sql.Date(dateR.getTime()));
                
                

                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                UserService service = new UserService(em);
                
                //Mise à jour
                service.updateUser(u);
                
                //Fermeture de la connexion
                em.close();
                emf.close();

                response.sendRedirect("ServletVueUtilisateur");
            }else{
                out.println("<p>Erreur d'authentification, veuillez préciser username , password");
                out.println("<a href='login.jsp'>reessayer</a>");
                out.println("</body></html>");
            }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletFaireMAJUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletFaireMAJUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
