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
public class ServletFaireMAJPersonne extends HttpServlet {

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
        String id = null, nom = null, prenom = null, adresse = null, ville = null;
        try {
            if (HtmlHttpUtils.isAuthenticate(request)) {

                //Récupère les paramètres de la requête
                id = request.getParameter("id");
                nom = request.getParameter("nom");
                prenom = request.getParameter("prenom");
                adresse = request.getParameter("adresse");
                ville = request.getParameter("ville");

                //Récupère la session
                HttpSession s = request.getSession();

                //Crée la personne
                Person p = new Person(Integer.parseInt(id), nom, prenom, adresse, ville,s.getAttribute("username").toString());

                //Ouverture de la connexion
                EntityManagerFactory emf;
                emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
                EntityManager em = emf.createEntityManager();
                PersonService service = new PersonService(em);

                //Mise à jour
                service.updatePerson(p);

                //Fermeture de la connexion
                em.close();
                emf.close();

                request.getRequestDispatcher("ServletListePersonne").forward(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(ServletFaireMAJPersonne.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletFaireMAJPersonne.class.getName()).log(Level.SEVERE, null, ex);
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
