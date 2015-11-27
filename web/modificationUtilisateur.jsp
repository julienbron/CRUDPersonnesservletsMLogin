
<%-- 
    Document   : modificationUtilisateur
    Created on : Nov 25, 2015, 7:31:22 PM
    Author     : gary.criblez
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<c:set var="user" value="user"/>

<%@page import="ch.hegarc.technoactu.crudpersonnes.business.User"%>
<%@page import="ch.hegarc.technoactu.crudpersonnes.services.UserService"%>
<%@page import="ch.hegarc.technoactu.crudpersonnes.constant.cons"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="ch.hegarc.technoactu.crudpersonnes.view.servlet.HtmlHttpUtils"%>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>

<%
    if (HtmlHttpUtils.isAuthenticate(request)) {

        //Récupère la session
        HttpSession s = request.getSession(true);

        //Récupère des paramètres de la session
        Integer idUser = (Integer) s.getAttribute("idUser");

        //Ouverture de la connexion
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(cons.PERSISTANCE_UNIT);
        EntityManager em = emf.createEntityManager();
        UserService service = new UserService(em);

        User u = service.findUser(idUser);
        //Fermeture de la connexion
        em.close();
        emf.close();
        pageContext.setAttribute("u",u);
        
        
    }

%>
<c:out value=""/>

<link href="template/css/datepicker.css" rel="stylesheet">
<script src="template/js/bootstrap-datepicker.js"></script>

<h1 class="page-header">Information utilisateur</h1> 

<div class="container">
    <form class="form-horizontal"  method="POST" action="ServletFaireMAJUtilisateur">
        <input type="hidden" name="id" id="action" value="${u.getId()}" />
        <fieldset>
            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="username">Nom d'utilisateur</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="username" name="username" type="text" placeholder="" class="form-control input-md" required="" value="${u.getUsername()}">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="password">Mot de passe</label>
                <div class="col-md-4 col-sm-6">
                    <input id="password" name="password" type="password"  placeholder="" class="form-control input-md" required="" value="${u.getPassword()}">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="lastName">Nom</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="nom" name="nom" type="text" placeholder="" class="form-control input-md"  value="${u.getLastName()}">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="firstName">Prénom</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="prenom" name="prenom" type="text" placeholder="" class="form-control input-md"  value="${u.getFirstName()}">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="city">Ville</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="ville" name="ville" type="text" placeholder="" class="form-control input-md"  value="${u.getCity()}">
                </div>
            </div>


            <div class="form-group input-append date" >
                <label class="col-md-3 col-sm-4 control-label">Date de naissance</label>
                <div class="col-md-4 col-sm-6">
                    <input class="span2 form-control-static" readonly name="date_nais" id="date_nais" size="16" type="text" value="<fmt:formatDate pattern="dd.MM.yyyy" value="${u.getBirthday()}" />" data-date-format="dd.mm.yyyy">
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
            </div>
            <script>
                $('#date_nais').datepicker()
            </script>

            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="email">Email</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="email" name="email" type="text" placeholder="" class="form-control input-md"  value="${u.getEmail()}">
                </div>
            </div>


            <div class="form-group input-append date" >
                <label class="col-md-3 col-sm-4 control-label">Employé depuis</label>
                <div class="col-md-4 col-sm-6">
                    <input class="span2 form-control-static" readonly name="date_recrut" id="date_recrut" size="16" type="text" value="<fmt:formatDate pattern="dd.MM.yyyy" value="${u.getRecruited()}" />" data-date-format="dd.mm.yyyy">
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
            </div>
            <script>
                $('#date_recrut').datepicker()
            </script>


            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="submit"></label>
                <div class="col-md-4 col-sm-6 text-right">
                    <a href="ServletVueUtilisateur" class="btn btn-default">Annuler</a>
                    <button id="submit" name="submit" class="btn btn-success">Appliquer</button>
                </div>
            </div>
        </fieldset>
    </form>



    <%@include file="includes/footer.jsp" %>