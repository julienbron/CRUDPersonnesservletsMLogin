<%-- 
    Document   : index
    Created on : 4 janv. 2010, 11:23:22
    Author     : termine
--%>

<%@page import="ch.hegarc.crudpersonnes.services.UtilisateurServices"%>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
<!-- <a href="recherchePersonne.html">Recherche Personne</a><br>
 <a href="creationPersonne.html">Creation Personne</a>-->



<!-- test -->
<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <!--<li class="sidebar-brand">
                <a href="#">Vue d'ensemble</a>
            </li>-->
            <li>
                <a href="#">Vue d'ensemble</a>
            </li>
                <li>
                <a href="#">Cr�ation de personne</a>
            </li>

        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard  <a href="#menu-toggle" class="btn btn-primary btn-sm" id="menu-toggle">Afficher Menu</a></h1> 
                    Tableau de liste de personne � ajouter ici.
                    
                    
                    <%  out.println(UtilisateurServices.afficheUtilisateur());  %>
                  
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

<%@include file="includes/footer.jsp" %>