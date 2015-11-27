<%-- 
    Document   : login
    Created on : 6 janv. 2010, 14:19:14
    Author     : termine
--%>

<%@include file="includes/header.jsp" %>

<div class="container" id="containerLogin">
    <header id="header" style="margin-bottom:20px;" class="col-md-4 col-md-offset-4">
        <h1>ArcCommerce</h1>
    </header>
    <div id="content">
        <div class="well col-md-4 col-md-offset-4">
            <header>
                <h2><i class="glyphicon glyphicon-log-in"></i> Connexion</h2>
            </header>
            
            <div class="alert alert-danger" role="alert">Echec de la connexion. Nom d'utilisateur ou mot de passe incorrecte !</div>

            <a class="btn btn-info" href="login.jsp" role="button">Réessayer</a>
            
        </div>
    </div>
</div>

