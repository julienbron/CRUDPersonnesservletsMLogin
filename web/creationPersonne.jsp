<%-- 
    Document   : creationPersonne
    Created on : Nov 27, 2015, 8:26:42 PM
    Author     : alexandr.ducommun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>

<link href="template/css/datepicker.css" rel="stylesheet">

<div class="container">
    <h1 class="page-header"><span class='glyphicon glyphicon-plus'></span> Ajouter une personne</h1> 
    <form class="form-horizontal" method="POST" action="ServletCreationPersonne">
        <fieldset>

            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="prenom">Prénom</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="prenom" name="prenom" type="text" class="form-control input-md">
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="nom">Nom</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="nom" name="nom" type="text" class="form-control input-md">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="adresse">Adresse</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="adresse" name="adresse" type="text" class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="ville">Ville</label>  
                <div class="col-md-4 col-sm-6">
                    <input id="ville" name="ville" type="text" class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 col-sm-4 control-label" for="submit"></label>
                <div class="col-md-4 col-sm-6 text-right">
                    <a href="ServletListePersonne" class="btn btn-default">Annuler</a>
                    <button id="submit" name="submit" class="btn btn-success">Ajouter</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>