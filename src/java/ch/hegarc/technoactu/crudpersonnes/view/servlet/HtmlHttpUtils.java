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
        out.println("<div class='container'>");
        out.println("<h1 class='page-header'>" + titre + "</h1><br>");
    }

    public static void printBadge(PrintWriter out, final String badgeName, final Boolean awarded, final String avatar_url) {
        out.println("<div class='panel panel-defautl' style='border-color:#C8C8C8;'>");
        out.println("<div class='panel-body'>");
        out.println("<div class='pull-right img'>");
        out.println("<img class='avatar-sm' style='height:30px;' src='" + avatar_url + "'/>");
        out.println("</div>");
        out.println("<div class='pull-left'>");
        out.println("<div class='push-5'>" + badgeName + "</div>");
        if (awarded) {
            out.println("<div class='text-muted'>Badge obtenu</div>");
        } else {
            out.println("<div class='text-muted'>Badge pas obtenu</div>");
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }

    public static void doFooter(PrintWriter out) {
        out.println("<div><a class='btn btn-info' href='ServletListePersonne'><span class='glyphicon glyphicon-th-list'></span> Accueil</a> &nbsp;<a class='btn btn-warning' href='ServletLogout'><span class='glyphicon glyphicon-log-out'></span> Déconnexion</a></div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    public static boolean isAuthenticate(HttpServletRequest request) {
        return request.getSession(false).getAttribute("username") != null;
    }

}
