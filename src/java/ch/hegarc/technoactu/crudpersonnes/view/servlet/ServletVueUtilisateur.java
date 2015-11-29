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
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-user'></span> Profil", out);

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
                int Skillpoint = service.getSkillPoint(s.getAttribute("username").toString());
                int SkillLevel = service.getSkillLevel(Skillpoint);
                int SkillPercent = service.getSkillPercentage(Skillpoint);
                //Fermeture de la connexion
                em.close();
                emf.close();

                out.println("<div class=\"container\">");
                out.println("<table class=\"table table-striped\">");

                out.println("<tr>");
                out.println("<td style=\"width:300px;\"><b>Niveau " + SkillLevel + " </b></td>");
                out.println("<td><div class=\"progress\">\n"
                        + "  <div class=\"progress-bar progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\""+ SkillPercent +"\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "+ SkillPercent +"%\">\n"
                        + "    "+ SkillPercent +"% \n"
                        + "  </div>\n"
                        + "</div></td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td><b>Nom d'utilisateur</b></td>");
                out.println("<td>" + u.getUsername() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Nom</b></td>");
                out.println("<td>" + viewValue(u.getLastName()) + "</td>");
                out.println("</tr>");
                out.println("<td><b>Prénom</b></td>");
                out.println("<td>" + viewValue(u.getFirstName()) + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Ville</b></td>");
                out.println("<td>" + viewValue(u.getCity()) + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Date de naissance</b></td>");
                out.println("<td>" + viewValue(u.getBirthday()) + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Adresse Mail</b></td>");
                out.println("<td>" + viewValue(u.getEmail()) + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td><b>Employé depuis</b></td>");
                out.println("<td>" + viewValue(u.getRecruited()) + "</td>");
                out.println("</tr>");
                out.println("</table>");
                
                 //affiche une image et un texte si l'utilisateur a completer ces informations
                if(checkFullInfo(u)){
                     out.println("<img src = \"images/goodguy.png\" class = \"img-thumbnail center-block\">");
                     out.println("<div align=\"center\">");
                     out.println("<h3>Infos complétées</h3>");
                     out.println("</div>");
                }


                out.println("<p class='text-right'><a href='modificationUtilisateur.jsp' class='btn btn-default'><span class='glyphicon glyphicon-pencil'></span> Modifier les informations</a></p>");

            } else {
                out.println("<p>Erreur d'authentification, veuillez préciser username , password");
                out.println("<a href='login.jsp'>reessayer</a>");
                out.println("</body></html>");
            }
            HtmlHttpUtils.doFooter(out);
        } finally {
            out.close();
        }
    }

    //méthode pour éviter que null s'affiche si le champ est vide
    private String viewValue(Object value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }

    }
    //méthode pour voir si l'utilisateur a remplit toutes ces infos complémentaires
    private Boolean checkFullInfo(User u){
        if (u.getFirstName() !=null & u.getLastName()!=null & u.getCity()!=null & u.getBirthday()!=null & u.getEmail()!=null & u.getRecruited()!=null){
            return true;
        }else{
            return false;
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
