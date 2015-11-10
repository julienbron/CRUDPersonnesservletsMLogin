<%-- 
    Document   : login
    Created on : 6 janv. 2010, 14:19:14
    Author     : termine
--%>
 
<%@include file="includes/header.jsp" %>
        <h1>Connection</h1>
        
        <div class="col-md-2 col-md-offset-5">
            <form method="POST" action="ServletLogin">
                <div class="form-group">
                    <label for="Username">Nom d'utilisateur</label>
                    <input type="text" name="username" class="form-control" id="exampleInputEmail1" placeholder="Nom d'utilisateur">
                </div>
                <div class="form-group">
                    <label for="Password">Password</label>
                    <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-default">Se connecter</button>
            </form>
        </div>
        
<%@include file="includes/footer.jsp" %>
 
