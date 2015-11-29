package ch.hegarc.technoactu.crudpersonnes.view.servlet;

import ch.hegarc.technoactu.crudpersonnes.constant.cons;
import ch.hegarc.technoactu.crudpersonnes.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class ServletBadge extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("includes/header.jsp").include(request, response);
        request.getRequestDispatcher("includes/navbar.jsp").include(request, response);
        try {
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-heart-empty'></span> Liste des badges", out);

            if (HtmlHttpUtils.isAuthenticate(request)) {

                //Récupère la session
                HttpSession s = request.getSession();

                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                UserService service = new UserService(em);

                //Badges Profil
                out.println("<div class='row'>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "Profil completé", service.badgeProfilFull(s.getAttribute("username").toString()), "template/eye_blue.png");
                out.println("</div>");
                out.println("</div>");

                //Badges d'ajout
                out.println("<div class='row'>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "1 Personne ajoutée", service.badge5Insert(s.getAttribute("username").toString(), 1, "INS"), "template/eye_green.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "5 Personnes ajoutées", service.badge5Insert(s.getAttribute("username").toString(), 5, "INS"), "template/eye_green.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "10 Personnes ajoutées", service.badge5Insert(s.getAttribute("username").toString(), 10, "INS"), "template/eye_green.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "15 Personnes ajoutées", service.badge5Insert(s.getAttribute("username").toString(), 15, "INS"), "template/eye_green.png");
                out.println("</div>");
                out.println("</div>");

                //Badges de modification
                out.println("<div class='row'>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "1 Personne modifiée", service.badge5Insert(s.getAttribute("username").toString(), 1, "UPD"), "template/eye_ora.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "5 Personnes modifiées", service.badge5Insert(s.getAttribute("username").toString(), 5, "UPD"), "template/eye_ora.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "10 Personnes modifiées", service.badge5Insert(s.getAttribute("username").toString(), 10, "UPD"), "template/eye_ora.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "15 Personnes modifiées", service.badge5Insert(s.getAttribute("username").toString(), 15, "UPD"), "template/eye_ora.png");
                out.println("</div>");
                out.println("</div>");

                //Badges de suppression
                out.println("<div class='row'>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "1 Personne supprimée", service.badge5Insert(s.getAttribute("username").toString(), 1, "DEL"), "template/eye_red.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "5 Personnes supprimées", service.badge5Insert(s.getAttribute("username").toString(), 5, "DEL"), "template/eye_red.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "10 Personnes supprimées", service.badge5Insert(s.getAttribute("username").toString(), 10, "DEL"), "template/eye_red.png");
                out.println("</div>");
                out.println("<div class='col-sm-4 col-md-3'>");
                HtmlHttpUtils.printBadge(out, "10 Personnes supprimées", service.badge5Insert(s.getAttribute("username").toString(), 15, "DEL"), "template/eye_red.png");
                out.println("</div>");
                out.println("</div>");

                //Fermeture de la connexion
                em.close();
                emf.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletBadge.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ServletBadge.class.getName()).log(Level.SEVERE, null, ex);
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
