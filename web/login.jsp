<%-- 
    Document   : login
    Created on : 6 janv. 2010, 14:19:14
    Author     : termine
--%>

<%@include file="includes/header.jsp" %>
        <h1> Login page - gestion des personnes (CRUD)</h1>
      <form method="POST" action="ServletLogin">
         username : <input type="text" name="username"><br>
         password : <input type="password" name="password"><br>

         <input type="submit" value="login">
      </form>
<%@include file="includes/footer.jsp" %>
