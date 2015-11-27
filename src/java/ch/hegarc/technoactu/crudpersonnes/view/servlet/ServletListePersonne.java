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
            out.println("<div class=\"container\">");
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-th-list'></span> Liste des personnes", out);

            if (HtmlHttpUtils.isAuthenticate(request)) {

                //Récupère la session
                HttpSession s = request.getSession();

                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                PersonService service = new PersonService(em);

                //Récupère toutes les personnes et les affiches
                List<Person> personnes = service.findAllPerson();
                Person p;
                Iterator i = personnes.iterator();

                out.println("<table class=\"table table-striped\">");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Prénom</th>");
                out.println("<th>Nom</th>");
                out.println("<th>Adresse</th>");
                out.println("<th>Ville</th>");
                out.println("<th>Action</th>");
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

                out.println("<p class='text-right'><a href='ServletCreationPersonne' class='btn btn-success'><span class='glyphicon glyphicon-plus'></span> Ajouter</a></p>");

                //Fermeture de la connexion
                em.close();
                emf.close();
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
