/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.view.servlet;

import ch.hegarc.technoactu.crudpersonnes.constant.cons;
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
 * @author termine
 */
public class ServletLogin extends HttpServlet {

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
        String username = null, password = null;
        try {

            HtmlHttpUtils.doHeader("Login Page - Gestion de personnes (CRUD)", out);

            //Récupère les paramètres de la requête
            username = request.getParameter("username");
            password = request.getParameter("password");
            boolean errorlogin = false;
            if (username != null && password != null) {
                if (!username.equals("") && !password.equals("")) {

                    //Ouverture de la connexion
                    EntityManagerFactory emf;
                    emf = Persistence.createEntityManagerFactory("persistance_techno");
                    EntityManager em = emf.createEntityManager();
                    UserService service = new UserService(em);

                    // SessionDB sessionDB = new DBConnection().openSession();
                    int idUser = service.verifyUser(username, password);
                    if (idUser != -1) {
                        //CREATION HTTP SESSION
                        //request.getRequestDispatcher("/index.jsp").forward(request, response);
                        HttpSession s = request.getSession(true);
                        //s.setAttribute("sessionDB", sessionDB);
                        s.setAttribute("username", username);
                        //Attribut utiliser pour faire la MAJ Utilisateur
                        s.setAttribute("idUser", idUser);
                        
                        
                        //Fermeture de la connexion
                        em.close();
                        emf.close();
                        
                        response.sendRedirect("ServletListePersonne");
                    } else {
                        errorlogin = true;
                    }
                } else {
                    errorlogin = true;
                }
            } else {
                errorlogin = true;
            }
 
            if (errorlogin) {
                out.println("<p>Erreur d'authentification, veuillez préciser username , password");
                out.println("<a href='login.jsp'>reessayer</a>");
                out.println("</body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
