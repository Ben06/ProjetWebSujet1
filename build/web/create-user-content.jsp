<%-- 
    Document   : create-user
    Created on : 26 mars 2015, 16:10:04
    Author     : Divz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <form action="ServletUsers" method="get">  
            Nom : <input type="text" name="nom"/><br>  
            Prénom : <input type="text" name="prenom"/><br>  
            Login : <input type="text" name="login"/><br>  
            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
            <input type="hidden" name="action" value="creerUnUtilisateur"/>  
            <input type="submit" value="Créer l'utilisateur" name="submit"/>
        </form> 
    </body>
</html>