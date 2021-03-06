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
import java.util.Iterator;
import java.util.List;
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
public class ServletListePersonne extends HttpServlet {

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
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-th-list'></span> Liste des personnes", out);

            if (HtmlHttpUtils.isAuthenticate(request)) {

                //Paramètre de la recherche
                String recherche = request.getParameter("recherche");

                //Récupère la session
                HttpSession s = request.getSession();

                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                PersonService service = new PersonService(em);

                List<Person> personnes;
                Person p;
                
                if (recherche == null) {
                    //Récupère toutes les personnes et les affiches
                    personnes = service.findAllPerson();
                } else {
                    //Récupère toutes les personnes avec le paramètre de recherche et les affiche
                    personnes = service.findAllPersonWhere(recherche.toUpperCase());
                }
                Iterator i = personnes.iterator();

                out.println("<form method='GET' action='ServletListePersonne'>");
                out.println("<div class=\"form-group\">");
                out.println("<div class=\"input-group input-group-sm icon-addon addon-sm\">");
                out.println("<input type=\"text\" placeholder=\"Texte\" name=\"recherche\" id=\"schbox\" class=\"form-control input-sm\">");
                out.println("<i class=\"icon icon-search\"></i>");
                out.println("<span class=\"input-group-btn\">");
                out.println("<button type=\"submit\" class=\"btn btn-inverse\">Rechercher</button>");
                out.println("</span>");
                out.println("</div>");
                out.println("</div>");
                out.println("</form>");

                out.println("<table class=\"table table-striped\">");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Prénom</th>");
                out.println("<th>Nom</th>");
                out.println("<th>Adresse</th>");
                out.println("<th>Ville</th>");
                out.println("<th class='text-center'>Action</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                while (i.hasNext()) {
                    p = (Person) i.next();
                    out.println("<tr>");
                    out.println("<td>" + p.getPrenom() + "</td>");
                    out.println("<td>" + p.getNom() + "</td>");
                    out.println("<td class='hidden-xs'>" + p.getAdresse() + "</td>");
                    out.println("<td class='hidden-xs'>" + p.getVille() + "</td>");
                    out.println("<td class='text-center'><a class='btn btn-xs btn-warning' href='ServletMAJPersonne?id=" + p.getId() + "'><span class='glyphicon glyphicon-pencil'></span> Editer</a>" + " " + "<a class='btn btn-xs btn-danger' href='ServletEffacerPersonne?id=" + p.getId() + "'><span class='glyphicon glyphicon-trash'></span> Supprimer</a></td>");
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");
                
                out.println("<p class='text-right'>");
                out.println("<a href='ServletBadge' class='btn btn-info'><span class='glyphicon glyphicon-heart-empty'></span> Badges</a>");
                out.println("<a href='creationPersonne.jsp' class='btn btn-success'><span class='glyphicon glyphicon-plus'></span> Ajouter</a>");
                out.println("</p>");

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
            Logger.getLogger(ServletListePersonne.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletListePersonne.class.getName()).log(Level.SEVERE, null, ex);
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
