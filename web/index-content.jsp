<%--  
    Document   : index  
    Created on : 16 sept. 2009, 16:54:32  
    Author     : reale benjamin 
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

        <c:choose>
            <c:when test="${param.action=='erreurInscription'}">
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Inscription</legend>
                        Vous pouvez vous inscrire via ce formulaire.
                        <br/>
                        <form action="ServletUsers" method="post">
                            <p>
                                <label for="nom">Nom : </label><input type="text" name="nom" required/><span style="color: #f00;">${requestScope['erreurs']['nom']}</span><br/>
                            </p>

                            <p>
                                <label for="prenom">Prénom : </label><input type="text" name="prenom" required/><span style="color: #f00;">${requestScope['erreurs']['prenom']}</span><br/>
                            </p>

                            <p>
                                <label for="login">Login : </label><input type="text" name="login" required/><span style="color: #f00;">${requestScope['erreurs']['login']}</span><br/>
                            </p>

                            <p>
                                <label for="motdepasse">Password : </label><input type="password" name="motdepasse" required/><span style="color: #f00;">${requestScope['erreurs']['password']}</span><br/><br/>
                            </p>

                            <p>
                                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                                <input type="hidden" name="action" value="inscription"/>  
                                <input class="btn btn-default" type="submit" value="S'inscrire" name="submit"/>
                            </p>
                        </form> 
                        <br />
                    </fieldset>
                </form>
            </c:when>
            <c:when test="${param.action=='inscrireUtilisateur'}">
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Inscription</legend>
                        Vous pouvez vous inscrire via ce formulaire.
                        <br/>
                        <form action="ServletUsers" method="post">
                            <p>
                                <label for="nom">Nom : </label><input type="text" name="nom" required/><br> 
                            </p>

                            <p>
                                <label for="prenom">Prénom : </label><input type="text" name="prenom" required/><br>
                            </p>

                            <p>
                                <label for="login">Login : </label><input type="text" name="login" required/><br>
                            </p>

                            <p>
                                <label for="motdepasse">Password : </label><input type="password" name="motdepasse" required/><br><br/>
                            </p>

                            <p>
                                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                                <input type="hidden" name="action" value="inscription"/>  
                                <input type="submit" value="S'inscrire" name="submit"/>
                            </p>
                        </form> 
                        <br />
                    </fieldset>
                </form>
            </c:when>
            <%--<c:when test="${!empty sessionScope.sessionUtilisateur}">--%>
            <c:when test="${param.action=='userConnecte' || !empty sessionScope.sessionUtilisateur}">
                <fieldset>
                    <form method="post" action="ServletUsers">
                        Vous etes connecté(e) en tant que : ${sessionScope.sessionUtilisateur.login}<br/><br/>
                        <input type="hidden" name="action" value="Deconnexion"/>
                        <input type="submit" value="Déconnexion" class="sansLabel" />
                        <br/>
                    </form>
                </fieldset>
            </c:when>
            <c:when test="${param.action=='erreurConnexion'}">
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Connexion</legend>
                        Vous pouvez vous connecter via ce formulaire.
                        <br/>
                        <p>
                            <label for="nom">Login</label>
                            <input type="login" id="login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/>

                            <c:if test="${!empty requestScope['erreurs']['login']}">
                                <span style="color: #f00;">${requestScope['erreurs']['login']}</span>  
                            </c:if>
                            <c:if test="${!empty requestScope['erreurs']['wronglogin']}">
                                <span style="color: #f00;">${requestScope['erreurs']['wronglogin']}</span>  
                            </c:if>
                            <br/>
                        </p>

                        <p>
                            <label for="motdepasse">Password</label>
                            <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required/>

                            <c:if test="${!empty requestScope['erreurs']['password']}">
                                <span style="color: #f00;">${requestScope['erreurs']['password']}</span>  
                            </c:if>
                            <c:if test="${!empty requestScope['erreurs']['wrongpass']}">
                                <span style="color: #f00;">${requestScope['erreurs']['wrongpass']}</span>  
                            </c:if>
                            <br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="connexion"/>
                            <input type="submit" value="Connexion" class="sansLabel" />
                        </p>
                        <br />

                    </fieldset>
                </form>
                <a href="ServletUsers?action=inscrireUtilisateur">Vous n'êtes pas inscrit ? Cliquez ici !</a>
            </c:when>
            <c:when test="${param.action=='userDeconnecte' || empty sessionScope.sessionUtilisateur}">
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Connexion</legend>
                        Vous pouvez vous connecter via ce formulaire.
                        <br/>
                        <p>
                            <label for="login">Login</label>
                            <input type="login" id="login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/><br/>
                        </p>

                        <p>
                            <label for="motdepasse">Password</label>
                            <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required/><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="connexion"/>
                            <input type="submit" value="Connexion" class="sansLabel" />
                            <br />
                        </p>
                    </fieldset>
                </form>
                <a href="ServletUsers?action=inscrireUtilisateur">Vous n'êtes pas inscrit ? Cliquez ici !</a>
            </c:when>

            <c:when test="${param.action=='utilisateurInscrit'}">
                Vous êtes maintenant inscrit ! 
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Connexion</legend>
                        Vous pouvez vous connecter via ce formulaire.
                        <br/>
                        <p>
                            <label for="nom">Login</label>
                            <input type="login" id="login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/>
                            <span style="color: #f00;">${form.erreurs['login']}</span><br/>
                        </p>

                        <p>
                            <label for="motdepasse">Password</label>
                            <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required />
                            <span style="color: #f00;">${form.erreurs['motdepasse']}</span><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="connexion"/>
                            <input type="submit" value="Connexion" class="sansLabel" />
                            <br />
                        </p>
                    </fieldset>
                </form>
            </c:when>
            <c:otherwise>
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Connexion</legend>
                        Vous pouvez vous connecter via ce formulaire.
                        <br/>
                        <p>
                            <label for="nom">Login</label>
                            <input type="login" id="login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/><br/>
                        </p>

                        <p>
                            <label for="motdepasse" >Password</label>
                            <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required/><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="connexion"/>
                            <input type="submit" value="Connexion" class="sansLabel" />
                        </p>
                        <br />
                    </fieldset>
                </form>
            </c:otherwise>
        </c:choose>
        <br/><br/>
        <c:if test="${!empty sessionScope.sessionUtilisateur}">
            <div id="cssmenu">
                <!--<ul class="vertical_menu">-->
                <ul>
                    <!--<ul>-->
                    <li><a href="ServletUsers?action=listerLesUtilisateurs">Refresh</a></li>
                    <li><a href="ServletUsers?action=creationUtilisateur">Create</a></li>
                    <li><a href="ServletUsers?action=rechercheUtilisateur">Search</a></li>
                    <li><a href="ServletUsers?action=mettreajourUtilisateur">Update</a></li>
                    <li><a href="ServletUsers?action=supprimerUtilisateur">Delete</a></li>
                    <li><a href="ServletUsers?action=creerUtilisateursDeTest">Test</a></li>
                    <!--<li><a href="ServletUsers?action=supprimerAll">Delete All</a></li>-->
                </ul> 
            </div>
            <!--</div>-->
            <!--</div>-->
            <c:if test="${param.action == 'creationUtilisateur'}">
                <br/>
                <fieldset>
                    <legend>Création d'utilisateur</legend>
                    <form action="ServletUsers" method="post"> 

                        <p>
                            <label for="nom">Nom : </label><input type="text" name="nom" required/></div><br/>  
                        </p>

                        <p>
                            <label for="prenom">Prénom : </label><input type="text" name="prenom" required/></div><br/>
                        </p>

                        <p>
                            <label for="login">Login : </label><input type="text" name="login" required/></div><br/><br/>  
                        </p>

                        <p>
                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                            <input type="hidden" name="action" value="creerUnUtilisateur"/>  
                            <input type="submit" value="Créer l'utilisateur" name="submit"/>
                        </p>
                    </form>  
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'erreurCreationUtilisateur'}">
                <br/>
                <fieldset>
                    <legend>Création d'utilisateur</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                            <label for="nom">Nom : </label><input type="text" name="nom" requird/> <span style="color: #f00;">${requestScope['erreurs']['nom']}</span><br/>
                        </p>

                        <p>
                            <label for="prenom">Prénom : </label><input type="text" name="prenom" required/> <span style="color: #f00;">${requestScope['erreurs']['prenom']}</span><br/> 
                        </p>

                        <p>
                            <label for="login" >Login : </label><input type="text" name="login" required/> <span style="color: #f00;">${requestScope['erreurs']['login']}</span><br/><br/> 
                        </p>

                        <p>
                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                            <input type="hidden" name="action" value="creerUnUtilisateur"/>  
                            <input type="submit" value="Créer l'utilisateur" name="submit"/>
                        </p>
                    </form>
                </fieldset>
            </c:if>


            <c:if test="${param.action == 'rechercheUtilisateur'}" >
                <br/>
                <fieldset>
                    <legend>Recherche d'utilisateur</legend>
                    <form action="ServletUsers" method="get"> 
                        <p>
                            <label for="login">login : </login><input type="text" name="login" required/><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="chercherParLogin"/>  
                            <input type="submit" value="Chercher" name="submit"/>  
                        </p>
                    </form> 
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'mettreajourUtilisateur'}" >
                <br/>
                <fieldset>
                    <legend>Mise à jour un utilisateur</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                            <label for="login">Login : </label><input type="text" name="login" required/><br/>
                        </p>

                        <p>
                            <label for="nom">Nom : </label><input type="text" name="nom" required/><br/>  
                        </p>

                        <p>
                            <label for="prenom">Prénom : </label><input type="text" name="prenom" required/><br/><br>  
                        </p>

                        <p>
                            <input type="hidden" name="action" value="updateUtilisateur"/>  
                            <input type="submit" value="Mettre à jour" name="submit"/>
                        </p>

                    </form>
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'erreurMettreAJourUtilisateur'}" >
                <br/>
                <fieldset>
                    <legend>Mise à jour d'un utilisateur</legend>
                    <form action="ServletUsers" method="post">

                        <p>
                            <label for="login">Login : </label><input type="text" name="login" requird/> <span style="color: #f00;">${requestScope['erreurs']['login']}</span><br/> 
                        </p>

                        <p>
                            <label for="nom">Nom : </label><input type="text" name="nom" required/> <span style="color: #f00;">${requestScope['erreurs']['nom']}</span><br/>
                        </p>

                        <p>
                            <label for="prenom">Prénom : </label><input type="text" name="prenom" required/> <span style="color: #f00;">${requestScope['erreurs']['prenom']}</span><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="updateUtilisateur"/>  
                            <input type="submit" value="Mettre à jour" name="submit"/>  
                        </p>
                    </form>
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'erreurSupprimerUtilisateur'}" > 
                <br/>
                <fieldset>
                    <legend>Suppression d'utilisateur</legend>
                    <form action="ServletUsers" method="post"> 
                        <p>
                            <label for="login">Login : </label><input type="text" name="login" required/> <span style="color: #f00;">${requestScope['erreurs']['login']}</span><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="deleteUtilisateur"/>  
                            <input type="submit" value="Supprimer" name="submit"/>
                        </p>
                    </form>  
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'supprimerUtilisateur'}" > 
                <br/>
                <fieldset>
                    <legend>Suppression d'utilisateur</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                            <label for="login">Login : </label><input type="text" name="login" required/><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="deleteUtilisateur"/>  
                            <input type="submit" value="Supprimer" name="submit"/>  
                        </p>

                    </form>  
                </fieldset>
            </c:if>

            <%--<c:if test="${param.action=='deleteAllUsersConfirmation'}">--%>
            <!--<br/>-->
            <!--<span class="resultat">Etes vous sur de vouloir supprimer tous les utilisateurs ?</span>-->
            <!--<form action="ServletUsers" method="get">-->  
            <!--<input type="hidden" name="action" value="deleteAllUsers"/>-->  
            <!--<input type="submit" value="Oui" name="submit"/>-->  
            <!--</form>-->  
            <%--</c:if>--%>

            <%--<c:if test="${param.action=='allUsersDeleted'}">--%>
            <!--            <br/>
                        <span class="resultat">Tous les utilisateurs ont été supprimés !</span>-->
            <%--</c:if>--%>

            <c:if test="${param.action=='utilisateurSupprime'}">
                <br/>
                <span class="resultat">Utilisateur supprimé !</span>
            </c:if>

            <c:if test="${param.action=='utilisateurCree'}">
                <br/>
                <span class="resultat">Utilisateur crée !</span>
            </c:if>

            <c:if test="${param.action=='utilisateurMisAJour'}">
                <br/>
                <span class="resultat">Utilisateur mis à jour !</span>
            </c:if>

            <!-- Fin du menu -->  

            <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->  
            <c:if test="${param.action == 'listerLesUtilisateurs'}" >  
                <c:if test = "${requestScope['listeDesUsers'] != null}">
                    <table border="10">  
                        <!-- La ligne de titre du tableau des comptes -->  
                        <tr>  
                            <td><b>Login</b></td>  
                            <td><b>Nom</b></td>  
                            <td><b>Prénom</b></td>  
                        </tr>  

                        <!-- Ici on affiche les lignes, une par utilisateur -->  
                        <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                        <c:set var="total" value="0"/>  
                        <c:set var="page" value="0"/>
                        <c:forEach var="u" items="${requestScope['listeDesUsers']}"> 
                            <tr>
                                <td>${u.login}</td>  
                                <td>${u.lastname}</td>  
                                <td>${u.firstname}</td>
                                <!-- On compte le nombre de users -->  
                                <c:set var="total" value="${total+1}"/>  
                            </tr>
                            <%--</c:if>--%>
                        </c:forEach>  

                        <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                        <tr>
                            <td>
                                <b>
                                    <c:if test="${param.page > 0}">
                                        <form action="ServletUsers" method="get">  
                                            <input type="hidden" name="action" value="Previous"/>  
                                            <input type="submit" value="Précédente" name="submit"/>  
                                        </form>
                                    </c:if>
                                </b>
                            </td>
                            <td>${param.page+1}/${param.lastPage}</td>
                            <td>
                                <b>
                                    <c:if test="${param.page < param.lastPage-1}">
                                        <form action="ServletUsers" method="get">   
                                            <input type="hidden" name="action" value="Next"/>  
                                            <input type="submit" value="Suivante" name="submit"/>  
                                        </form>
                                    </c:if>
                                </b>
                            </td>
                            <td></td>
                        </tr>
                    </table>  
                </c:if> 
                <c:if test = "${requestScope['listeDesUsers'] == null}">
                    <br/>
                    La liste des utilisateurs est vide !
                </c:if>
            </c:if>

            <c:if test="${param.action == 'chercherParLogin'}" >  
                <!--<h2>Resultat de la recherche</h2>-->  
                <c:if test = "${requestScope['listeDesUsers'] != null}">
                    <table border="10">  
                        <!-- La ligne de titre du tableau des comptes -->  
                        <tr>  
                            <td><b>Login</b></td>  
                            <td><b>Nom</b></td>  
                            <td><b>Prénom</b></td>  
                        </tr>  

                        <!-- Ici on affiche les lignes, une par utilisateur -->  
                        <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                        <c:set var="total" value="0"/>  

                        <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                            <tr>  
                                <td>${u.login}</td>  
                                <td>${u.lastname}</td>  
                                <td>${u.firstname}</td>  
                                <!-- On compte le nombre de users -->  
                                <c:set var="total" value="${total+1}"/>  
                            </tr>  
                        </c:forEach>  
                    </table>  
                </c:if>
                <c:if test = "${requestScope['listeDesUsers'] == null}">
                    <br/>
                    Aucun résultat :(
                </c:if>
            </c:if>
        </c:if>
    </body>  
</html>  
