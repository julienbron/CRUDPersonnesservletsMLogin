/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.technoactu.crudpersonnes.view.servlet;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author termine
 */
public class HtmlHttpUtils {

    public static void doHeader(String titre, PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + titre + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + titre + "</h1><br>");

    }

    public static void doFooter(PrintWriter out) {
        out.println("<div><a class='btn btn-info' href='ServletListePersonne'><span class='glyphicon glyphicon-th-list'></span> Accueil</a> &nbsp;<a class='btn btn-warning' href='ServletLogout'><span class='glyphicon glyphicon-log-out'></span> Déconnexion</a></div>");
        out.println("</body>");
        out.println("</html>");
    }

    public static boolean isAuthenticate(HttpServletRequest request) {
        return request.getSession(false).getAttribute("username") != null;
    }

}
