package ch.hegarc.technoactu.crudpersonnes.view.servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ch.hegarc.technoactu.crudpersonnes.business.Person;
import ch.hegarc.technoactu.crudpersonnes.constant.cons;
import ch.hegarc.technoactu.crudpersonnes.services.PersonService;
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
 * @author termine
 */
public class ServletMAJPersonne extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("includes/header.jsp").include(request, response);
        request.getRequestDispatcher("includes/navbar.jsp").include(request, response);
        try {
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-pencil'></span> Modification", out);
            if (HtmlHttpUtils.isAuthenticate(request)) {

                //Récupère les paramètres de la requête
                int id = Integer.parseInt(request.getParameter("id"));

                //Récupère la session
                HttpSession s = request.getSession();

                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                PersonService service = new PersonService(em);

                Person p = service.findPerson(id);

                //Fermeture de la connexion
                em.close();
                emf.close();

                out.println("<div class=\"container\">");
                out.println("<form class=\"form-horizontal\" method=\"POST\" action=\"ServletFaireMAJPersonne\">");
                out.println("<input type='hidden' name='id' value='" + p.getId() + "'><br>");
                out.println("<fieldset>");
                
                out.println("<div class=\"form-group\">");
                out.println("<label class=\"col-md-3 col-sm-4 control-label\" for=\"prenom\">Prénom</label>");
                out.println("<div class=\"col-md-4 col-sm-6\">");
                out.println("<input id=\"prenom\" name=\"prenom\" type=\"text\" class=\"form-control input-md\" value='" + p.getPrenom() + "'>");
                out.println("</div>");
                out.println("</div>");
                
                out.println("<div class=\"form-group\">");
                out.println("<label class=\"col-md-3 col-sm-4 control-label\" for=\"nom\">Nom</label>");
                out.println("<div class=\"col-md-4 col-sm-6\">");
                out.println("<input id=\"nom\" name=\"nom\" type=\"text\" class=\"form-control input-md\" value='" + p.getNom() + "'>");
                out.println("</div>");
                out.println("</div>");
                
                out.println("<div class=\"form-group\">");
                out.println("<label class=\"col-md-3 col-sm-4 control-label\" for=\"adresse\">Adresse</label>");
                out.println("<div class=\"col-md-4 col-sm-6\">");
                out.println("<input id=\"adresse\" name=\"adresse\" type=\"text\" class=\"form-control input-md\" value='" + p.getAdresse() + "'>");
                out.println("</div>");
                out.println("</div>");
                
                out.println("<div class=\"form-group\">");
                out.println("<label class=\"col-md-3 col-sm-4 control-label\" for=\"ville\">Ville</label>");
                out.println("<div class=\"col-md-4 col-sm-6\">");
                out.println("<input id=\"ville\" name=\"ville\" type=\"text\" class=\"form-control input-md\" value='" + p.getVille() + "'>");
                out.println("</div>");
                out.println("</div>");
                
                out.println("<div class=\"form-group\">");
                out.println("<label class=\"col-md-3 col-sm-4 control-label\" for=\"submit\"></label>");
                out.println("<div class=\"col-md-4 col-sm-6\">");
                out.println("<a href=\"ServletListePersonne\" class=\"btn btn-default\">Annuler</a>");
                out.println("<button id=\"submit\" name=\"submit\" class=\"btn btn-success\">Appliquer</button>");
                out.println("</div>");
                out.println("</div>");
                
                out.println("</fieldset>");
                out.println("</form>");
                out.println("</div>");
                
//                out.println("<form  class=\"form-horizontal\" method='GET' action='ServletFaireMAJPersonne'>");
//                out.println("<input type='hidden' name='id' value='" + p.getId() + "'><br>");
//                out.println("id: <input class=\"form-control input-md\"  type='text' name='id' value='" + p.getId() + "' DISABLED><br>");
//                out.println("nom: <input class=\"form-control input-md\"  type='text' name='nom' value='" + p.getNom() + "'><br>");
//                out.println("prenom : <input class=\"form-control input-md\"  type='text' name='prenom' value='" + p.getPrenom() + "'><br>");
//                out.println(" adresse: <input class=\"form-control input-md\"  type='text' name='adresse' value='" + p.getAdresse() + "'><br>");
//                out.println(" ville :  <input class=\"form-control input-md\"  type='text' name='ville' value='" + p.getVille() + "'><br>");
//                out.println("<button id=\"submit\" name=\"submit\" class=\"btn btn-success\">Appliquer</button>");
//                out.println("</form>");
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
            Logger.getLogger(ServletMAJPersonne.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletMAJPersonne.class.getName()).log(Level.SEVERE, null, ex);
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
