<%-- 
    Document   : login
    Created on : 6 janv. 2010, 14:19:14
    Author     : termine
--%>

<%@include file="includes/header.jsp" %>

<h1>Connection</h1>

<div class="container" id="containerLogin">
    <header id="header">
        <h1>CRUD Personnes</h1>
    </header>
    <div id="content">
        <div class="well">
            <header>
                <h2><i class="fa fa-lock"></i> Connexion</h2>
            </header>

            <form method="POST" action="ServletLogin" id="UserLoginForm"  accept-charset="utf-8">
                <div class="form-group">
                    <label for="Username">Nom d'utilisateur</label>
                    <input type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="Nom d'utilisateur">
                </div>
                <div class="form-group">
                    <label for="Password">Password</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-info">Se connecter</button>
            </form>    
        </div>
    </div>
</div>


<%@include file="includes/footer.jsp" %>

