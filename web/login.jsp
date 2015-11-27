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
            
            <form method="POST" action="ServletLogin" id="UserLoginForm"  accept-charset="utf-8">
                <div class="form-group">
                    <label for="Username">Nom d'utilisateur</label>
                    <input type="text" name="username" class="form-control" required="true" id="exampleInputEmail1" placeholder="Nom d'utilisateur">
                </div>
                <div class="form-group">
                    <label for="Password">Password</label>
                    <input type="password" name="password" class="form-control" required="true" id="password" placeholder="Password">
                </div>
                <div class="form-group">
                <button type="submit" class="btn btn-info" data-dismiss="alert">Se connecter</button>
                </div>
                <div class="form-group">
                    <span errorClass="alert alert-danger"/>
                </div>
            </form>    
        </div>
    </div>
</div>

