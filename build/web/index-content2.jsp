<%--  
    Document   : index  
    Created on : 16 sept. 2009, 16:54:32  
    Author     : michel buffa  
--%>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    </head>  
    <body>  
        <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->  
        <ul>  
            <li><a href="ServletUsers?action=liencreerUtilisateur">Créer un utilisateur</a></li>  
            <p>  
        </ul>
        <c:if test="${param.action == 'creerUtilisateur'}" >
            <form action="ServletUsers" method="get">  
                Nom : <input type="text" name="nom"/><br>  
                Prénom : <input type="text" name="prenom"/><br>  
                Login : <input type="text" name="login"/><br>  
                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                <input type="hidden" name="action" value="creerUnUtilisateur"/>  
                <input type="submit" value="Créer l'utilisateur" name="submit"/>
            </form> 
        </c:if>
    </body>  
</html>  
