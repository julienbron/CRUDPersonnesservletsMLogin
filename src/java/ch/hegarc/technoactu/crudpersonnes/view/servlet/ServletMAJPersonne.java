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
        try {
            HtmlHttpUtils.doHeader("MAJ personne", out);
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

                //PersonneDAO pdao = new PersonneDAO((SessionDB) s.getAttribute("sessionDB"));
                //Person p = pdao.researchPersonByID(id);
                out.println("<form method='GET' action='ServletFaireMAJPersonne'>");
                out.println("<input type='hidden' name='id' value='" + p.getId() + "'><br>");
                out.println("id: <input type='text' name='id' value='" + p.getId() + "' DISABLED><br>");
                out.println("nom: <input type='text' name='nom' value='" + p.getNom() + "'><br>");
                out.println("prenom : <input type='text' name='prenom' value='" + p.getPrenom() + "'><br>");
                out.println(" adresse: <input type='text' name='adresse' value='" + p.getAdresse() + "'><br>");
                out.println(" ville :  <input type='text' name='ville' value='" + p.getVille() + "'><br>");
                out.println("<input type='submit' value='MAJ personne'>");
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
