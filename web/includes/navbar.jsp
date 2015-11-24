<%-- 
    Document   : navbar
    Created on : 27 oct. 2015, 10:09:25
    Author     : Romain Ducret <romain.ducret1@he-arc.ch>
--%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Gestion du personnel</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <!--<li class="active"><a href="index.jps">Index<span class="sr-only">(current)</span></a></li>-->
                <li><a href="recherchePersonne.html">Recherche Personne</a></li>
                <li><a href="creationPersonne.html">Creation Personne</a></li>
                <li><a href="ServletVueUtilisateur">Profil</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>