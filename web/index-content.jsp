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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="resources/js/kickstart.js"></script> <!-- KICKSTART -->
        <link rel="stylesheet" href="resources/css/kickstart.css" media="all" /> <!-- KICKSTART -->
        
        <style>
        label
       {
	display: block; /* La balise devient de type block. */
	width: 150px;
       }
        </style>
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
                                <!-- <input class="btn btn-default" type="submit" value="S'inscrire" name="submit"/> -->
                                <button type="submit" value="S'inscrire" class="medium green"><i class="fa fa-plus-square"></i> Inscription</button>
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
                                <!-- <input class="btn btn-default" type="submit" value="S'inscrire" name="submit"/> -->
                                <button type="submit" value="S'inscrire" class="medium green"><i class="fa fa-plus-square"></i> Inscription</button>
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
                        <!-- <input type="submit" value="Déconnexion" class="sansLabel" /> -->
                        <button type="submit" value="Déconnexion" class="medium red"><i class="fa fa-times-circle"></i> Deconnexion</button>
                        <br/>
                    </form>
                </fieldset>
                        </div> 
            </c:when>
            <c:when test="${param.action=='erreurConnexion'}">
                
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Connexion</legend>
                        Vous pouvez vous connecter via ce formulaire.
                        <br/>
                        <p>
                            <label for="nom">login</label>
                            <input type="text" id="login" placeholder="Entrer votre login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/>

                            <c:if test="${!empty requestScope['erreurs']['login']}">
                                <span style="color: #f00;">${requestScope['erreurs']['login']}</span>  
                            </c:if>
                            <c:if test="${!empty requestScope['erreurs']['wronglogin']}">
                                <span style="color: #f00;">${requestScope['erreurs']['wronglogin']}</span>  
                            </c:if>
                            <br/>
                        </p>

                        <p>
                            <label for="motdepasse"></label>
                            <input  id="motdepasse" placeholder="Entrer votre password" name="motdepasse" value="" size="20" maxlength="20" required/>

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
                             <!-- <input type="submit" value="Connexion" class="sansLabel" /> -->
                           <button type="submit" class="medium blue" value="Connexion"><i class="fa fa-user"></i> Connexion </button>
                        </p>
                        <br />

                    </fieldset>
                </form>
                    <h3><a href="ServletUsers?action=inscrireUtilisateur">Vous n'êtes pas inscrit ? Cliquez ici !</a></h3>
            </c:when>
            <c:when test="${param.action=='userDeconnecte' || empty sessionScope.sessionUtilisateur}">
                
                <form method="post" action="ServletUsers">
                    <fieldset>
                        <legend>Connexion</legend>
                        Vous pouvez vous connecter via ce formulaire.
                        <br/>
                        <p>
                            <label for="nom">Login</label>
                            <input type="text" id="login" placeholder="Entrer votre login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/>
                        </p>

                        <p>
                            <label for="motdepasse">Password</label>
                            <input  type="password" id="motdepasse" placeholder="Entrer votre password" name="motdepasse" value="" size="20" maxlength="20" required/><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="connexion"/>
                             <!-- <input type="submit" value="Connexion" class="sansLabel" /> -->
                           <button type="submit" class="medium blue" value="Connexion"><i class="fa fa-user"></i> Connexion </button>
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
                            <input type="text" id="login" placeholder="Entrer votre login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/>
                            <span style="color: #f00;">${form.erreurs['login']}</span><br/>
                        </p>

                        <p>
                            <label for="motdepasse">Password</label>
                            <input  type="password" id="motdepasse" placeholder="Entrer votre password" name="motdepasse" value="" size="20" maxlength="20" required />
                            <span style="color: #f00;">${form.erreurs['motdepasse']}</span><br/><br/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="connexion"/>
                             <!-- <input type="submit" value="Connexion" class="sansLabel" /> -->
                           <button type="submit" class="medium blue" value="Connexion"><i class="fa fa-user"></i> Connexion </button>
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
                            <label for="nom"></label>
                            <input type="text" id="login" placeholder="Entrer votre login" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/>
                        </p>

                        <p>
                            <label for="motdepasse">Password</label>
                            <input type="password" id="login" placeholder="Entrer votre password" name="login" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="60" required/>
                        </p>

                        <p>
                            <input type="hidden" name="action" value="connexion"/>
                             <!-- <input type="submit" value="Connexion" class="sansLabel" /> -->
                           <button type="submit" class="medium blue" value="Connexion"><i class="fa fa-user"></i> Connexion </button>
                        </p>
                        <br />
                    </fieldset>
                </form>
            </c:otherwise>
        </c:choose>
        <br/><br/>
        <c:if test="${!empty sessionScope.sessionUtilisateur}">
            <div id="cssmenu">
                <ul class="menu">
                    <li><a href="ServletUsers?action=listerLesUtilisateurs">Refresh</a></li>
                    <li><a href="ServletUsers?action=creationUtilisateur">Create</a></li>
                    <li><a href="ServletUsers?action=rechercheUtilisateur">Search</a></li>
                    <!--<li><a href="ServletUsers?action=mettreajourUtilisateur">Update</a></li>-->
                    <li><a href="ServletUsers?action=creerUtilisateursDeTest">Test</a></li>
                    <!--<li><a href="ServletUsers?action=supprimerAll">Delete All</a></li>-->
                   
                </ul>

            </div>

            <c:if test="${param.action == 'ajouterUnePhoto'}">
                <form action="Upload" method="post" enctype="multipart/form-data">
                    <!--${param.contactID}-->
                    <fieldset>
                        <legend>Ajout d'une photo</legend>
                        <label for="fichier">Emplacement de la photo<span class="requis">*</span></label>
                        <input type="file" id="fichier" name="fichier" accept="image/gif,image/jpeg,image/png"/>
                        <br />


                        <input type="hidden" name="contactNom" value="${param.contactNom}"/>
                        <input type="hidden" name="contactPrenom" value="${param.contactPrenom}"/>
                        <input type="hidden" name="contact" value="${param.contactID}"/>
                        <input type="hidden" name="action1" value="ajoutDePhoto"/> 
                        <input type="hidden" name="action" value="ajoutDePhoto"/> 
                        <input type="submit" value="Envoyer" class="sansLabel" />
                        <br />                
                    </fieldset>
                </form>
            </c:if>

            <c:if test="${param.action == 'erreurajouterUnePhoto'}">
                <form action="Upload" method="post" enctype="multipart/form-data">
                    <!--${param.contactID}-->
                    <fieldset>
                        <legend>Ajout d'une photo</legend>
                        <c:if test="${param.erreurPhoto!=null}">
                            <span style="color: #f00;">${param.erreurPhoto}</span><br/><br/>
                        </c:if>
                        <label for="fichier">Emplacement de la photo<span class="requis">*</span></label>
                        <input type="file" id="fichier" name="fichier" accept="image/gif,image/jpeg,image/png"/>
                        <br />

                        <input type="hidden" name="contactNom" value="${param.contactNom}"/>
                        <input type="hidden" name="contactPrenom" value="${param.contactPrenom}"/>
                        <input type="hidden" name="contact" value="${param.contactID}"/>
                        <input type="hidden" name="action" value="ajoutDePhoto"/> 
                        <input type="hidden" name="action1" value="ajoutDePhoto"/> 
                        <input type="submit" value="Envoyer" class="sansLabel" />
                        <br />                
                    </fieldset>
                </form>
            </c:if>

            <c:if test="${param.action == 'erreurmodifierPhoto'}">
                <form action="Upload" method="post" enctype="multipart/form-data">
                    <!--${param.contactID}-->
                    <fieldset>
                        <legend>Modification de la photo ${param.contactNom} ${param.contactPrenom}</legend>

                        <c:if test="${param.erreurPhoto!=null}">
                            <span style="color: #f00;">${param.erreurPhoto}</span><br/><br/>
                        </c:if>
                        <label for="fichier">Emplacement de la photo<span class="requis">*</span></label>
                        <input type="file" id="fichier" name="fichier" accept="image/gif,image/jpeg,image/png"/> <span style="color: #f00;">${requestScope['erreurs']['photo']}</span><br/><br/>
                        <br />

                        <input type="hidden" name="contactNom" value="${param.contactNom}"/>
                        <input type="hidden" name="contactPrenom" value="${param.contactPrenom}"/>
                        <input type="hidden" name="contact" value="${param.contactID}"/>
                        <input type="hidden" name="action" value="ajoutDePhoto"/> 
                        <input type="hidden" name="action1" value="modification"/>
                        <input type="submit" value="Envoyer" class="sansLabel" />
                        <br />                
                    </fieldset>
                </form>
            </c:if>

            <c:if test="${param.action == 'modifierPhoto'}">
                <form action="Upload" method="post" enctype="multipart/form-data">
                    <!--${param.contactID}-->
                    <fieldset>
                        <legend>Modification de la photo ${param.contactNom} ${param.contactPrenom}</legend>

                        <label for="fichier">Emplacement de la photo<span class="requis">*</span></label>
                        <input type="file" id="fichier" name="fichier" accept="image/gif,image/jpeg,image/png"/> <span style="color: #f00;">${requestScope['erreurs']['photo']}</span><br/><br/>
                        <br />

                        <input type="hidden" name="contactNom" value="${param.contactNom}"/>
                        <input type="hidden" name="contactPrenom" value="${param.contactPrenom}"/>

                        <input type="hidden" name="contact" value="${param.contactID}"/>
                        <input type="hidden" name="action1" value="modification"/> 
                        <!-- <input type="submit" value="Envoyer" class="sansLabel" /> -->
                        <button type="submit" value="Envoyer" class="green medium fa fa-picture-o"> Ajouter</button>
                        <br />                
                    </fieldset>
                </form>
            </c:if>
            
            <c:if test="${param.action == 'erreurCreationUtilisateur'}">
                <br/>
                <fieldset>
                    <legend>Création de contact</legend>
                    <form action="ServletUsers" method="post"> 

                        <p>
                            <label for="nom">Nom : </label><input type="text" name="nom" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Prénom : </label><input type="text" name="prenom" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Numéro : </label><input type="number" name="numero" required/></div><span style="color: #f00;">${requestScope['erreurs']['numero']}</span><br/>
                        </p>

                        <p>
                            <label for="prenom">Adresse : </label><input type="text" name="nomRue" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Code Postal : </label><input type="number" name="codePostal" required/></div><span style="color: #f00;">${requestScope['erreurs']['codePostal']}</span><br/>
                        </p>

                        <p>
                            <label for="prenom">Ville : </label><input type="text" name="nomVille" required/></div><br/>
                        </p>

                        <p>
                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                            <input type="hidden" name="action" value="creerUnUtilisateur"/>  
                            <input type="submit" value="Créer le contact" name="submit"/>
                        </p>
                    </form>  
                </fieldset>
            </c:if>

            <!--</div>-->
            <!--</div>-->
            <c:if test="${param.action == 'creationUtilisateur'}">
                <br/>
                <fieldset>
                    <legend>Création de contact</legend>
                    <form action="ServletUsers" method="post"> 

                        <p>
                            <label for="nom">Nom : </label><input type="text" name="nom" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Prénom : </label><input type="text" name="prenom" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Numéro : </label><input type="number" name="numero" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Adresse : </label><input type="text" name="nomRue" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Code Postal : </label><input type="number" name="codePostal" required/></div><br/>
                        </p>

                        <p>
                            <label for="prenom">Ville : </label><input type="text" name="nomVille" required/></div><br/>
                        </p>

                        <p>
                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                            <input type="hidden" name="action" value="creerUnUtilisateur"/>  
                            <input type="submit" value="Créer le contact" name="submit"/>
                        </p>
                    </form>  
                </fieldset>
            </c:if>
                
                

            <c:if test="${param.action == 'rechercheUtilisateur'}" >
                <br/>
                <fieldset>
                    <legend>Recherche de contact</legend>
                    <form action="ServletUsers" method="get"> 
                        <p>
                            <label for="search"></label><input type="text" name="search" required/>

                            <select name="option">
                                <option value="nom">Nom</option>
                                <option value="prenom">Prénom</option>
                                <option value="numero">Numero</option>
                                <option value="nomRue">Adresse</option>
                                <option value="codePostal">Code Postal</option>
                                <option value="nomVille">Ville</option>
                            </select>
                        </p>
                        <br/><br/>
                        <p>
                            <input type="hidden" name="action" value="chercherParLogin"/>  
                            <input type="submit" value="Chercher" name="submit"/>  
                        </p>
                    </form> 
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'suppression'}" > 
                <br/>
                <fieldset>
                    <legend>Etes vous sur de vouloir supprimer ${param.contactNom} ${param.contactPrenom} ? </legend>
                    <form action="ServletUsers" method="post">
                        <p>
                            <input type="hidden" name="contact" value="${param.contactID}"/> 
                            <input type="hidden" name="action" value="deleteUtilisateur"/>  
                            <!-- <input type="submit" value="Supprimer" name="submit"/> --> 
                            <button type="submit" value="Supprimer" class="large red"><i class="fa fa-minus-square"></i> Supprimer</button>
                        </p>
                    </form>  
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'modification'}" > 
                <br/>
                <fieldset>
                    <legend>Modification de ${param.contactNom} ${param.contactPrenom} ? </legend>
                    <form action="ServletUsers" method="post">
                        <p>
                            <label for="nom">Nom : </label><input type="text" name="nom"/> <span style="color: #f00;">${requestScope['erreurs']['nom']}</span><br/>
                        </p>

                        <p>
                            <label for="prenom">Prénom : </label><input type="text" name="prenom"/> <span style="color: #f00;">${requestScope['erreurs']['prenom']}</span><br/><br/>
                        </p>
                        Séléctionnez l'adresse à modifier : 
                        <p>
                            <select name="adresse">
                                <c:forEach var="u" items="${requestScope['adressesList']}">  
                                    <option value="${u.id}">${u.nomRue}, ${u.codePostal}, ${u.nomVille}</option>
                                </c:forEach>
                            </select>
                        </p>

                        Nouvelles valeurs : 
                        <p>
                            <label for="nomRue">Adresse : </label><input type="text" name="nomRue"/> <span style="color: #f00;">${requestScope['erreurs']['adresse']}</span><br/><br/>
                        </p>

                        <p>
                            <label for="codePostal">Code Postal : </label><input type="text" name="codePostal"/> <span style="color: #f00;">${requestScope['erreurs']['codePostal']}</span><br/><br/>
                        </p>

                        <p>
                            <label for="nomVille">Ville : </label><input type="text" name="nomVille"/> <span style="color: #f00;">${requestScope['erreurs']['ville']}</span><br/><br/>
                        </p>


                        Sélectionnez le numéro de téléphone à modifier : 

                        <p>
                            <select name="telephone">
                                <c:forEach var="u" items="${requestScope['phonesList']}">  
                                    <option value="${u.id}">${u.numero}</option>
                                </c:forEach>
                            </select>
                        </p>

                        Nouveau numéro : 
                        <p>
                            <label for="numero"></label><input type="text" name="numero"/> <span style="color: #f00;">${requestScope['erreurs']['numero']}</span><br/><br/>
                        </p>



                        <p>

                            <input type="hidden" name="contact" value="${param.contactID}"/>
                            <input type="hidden" name="action" value="updateUtilisateur"/>  
                            <input type="submit" value="Mettre à jour" name="submit"/>  
                        </p>
                    </form>

                    <br/>

                    <form action="ServletUsers" method="get">
                        <p>
                            <input type="hidden" name="contact" value="${param.contactID}"/>
                            <input type="hidden" name="contactNom" value="${param.contactNom}"/>
                            <input type="hidden" name="contactPrenom" value="${param.contactPrenom}"/>
                            <input type="hidden" name="action" value="modifierPhoto"/>  
                            <input type="submit" value="Mettre à jour la photo" name="submit"/> 
                        </p>
                    </form>
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'ajouterUnNumero'}" > 
                <br/>
                <fieldset>
                    <legend>Ajout d'un numero pour le contact ${param.contactNom} ${param.contactPrenom}</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                        <p>
                            <label for="nom">Numéro :  </label><input type="text" name="numero" required/><span style="color: #f00;">${requestScope['erreurs']['numero']}</span><br/><br/>
                        </p>
                        <input type="hidden" name="contact" value="${param.contactID}"/> 
                        <input type="hidden" name="action" value="ajoutNumero"/>  
                       <!-- <input type="submit" value="Ajouter le numéro" name="submit"/> -->
                        <button type="submit" value="Ajouter" class="green small fa fa-phone-square"> Ajouter</button>
                        </p>
                    </form>  
                </fieldset>
            </c:if>
                
            <c:if test="${param.action == 'erreurajouterUneAdresse'}" > 
                <br/>
                <fieldset>
                    <legend>Ajout d'une adresse pour le contact ${param.contactNom} ${param.contactPrenom}</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                        <p>
                            <label for="nom">Adresse : </label><input type="text" name="nomRue" required/>
                        </p>

                        <p>
                            <label for="nom">Code Postal : </label><input type="number" name="codePostal" required/><span style="color: #f00;">${requestScope['erreurs']['codePostal']}</span><br/><br/>
                        </p>

                        <p>
                            <label for="nom">Ville :  </label><input type="text" name="nomVille" required/>
                        </p>

                        <input type="hidden" name="contact" value="${param.contactID}"/> 
                        <input type="hidden" name="action" value="ajoutAdresse"/>  
                        <input type="submit" value="Ajouter une adresse" name="submit"/>  
                        </p>
                    </form>  
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'ajouterUneAdresse'}" > 
                <br/>
                <fieldset>
                    <legend>Ajout d'une adresse pour le contact ${param.contactNom} ${param.contactPrenom}</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                        <p>
                            <label for="nom">Adresse : </label><input type="text" name="nomRue" required/>
                        </p>

                        <p>
                            <label for="nom">Code Postal : </label><input type="number" name="codePostal" required/><span style="color: #f00;">${requestScope['erreurs']['codePostal']}</span><br/><br/>
                        </p>

                        <p>
                            <label for="nom">Ville :  </label><input type="text" name="nomVille" required/>
                        </p>

                        <input type="hidden" name="contact" value="${param.contactID}"/> 
                        <input type="hidden" name="action" value="ajoutAdresse"/>  
                        <button type="submit"class="green middle" value="Ajouter une adresse" name="submit"> Ajouter une adresse </button>

                        </p>
                    </form>  
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'supprimerUnNumero'}" > 
                <br/>
                <fieldset>
                    <legend>Suppression d'un numero pour le contact ${param.contactNom} ${param.contactPrenom}</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                        <p>
                            <select name="numero">
                                <c:forEach var="u" items="${requestScope['phonesList']}">  
                                    <option value="${u.id}">${u.numero}</option>
                                </c:forEach>
                            </select>
                        </p>
                        <input type="hidden" name="contact" value="${param.contactID}"/> 
                        <input type="hidden" name="action" value="supprimerNumero"/>  
                       <!-- <input type="submit" value="Supprimer le numéro" name="submit"/> --> 
                        <button type="submit" value="Supprimer le numéro" class="red small fa fa-phone-square">X</button>
                        </p>
                    </form>  
                </fieldset>
            </c:if>

            <c:if test="${param.action == 'supprimerUneAdresse'}" > 
                <br/>
                <fieldset>
                    <legend>Suppression d'une adresse pour le contact ${param.contactNom} ${param.contactPrenom}</legend>
                    <form action="ServletUsers" method="post">
                        <p>
                        <p>
                            <select name="adresse">
                                <c:forEach var="u" items="${requestScope['adressesList']}">  
                                    <option value="${u.id}">${u.nomRue}, ${u.codePostal}, ${u.nomVille}</option>
                                </c:forEach>
                            </select>
                        </p>
                        <input type="hidden" name="contact" value="${param.contactID}"/> 
                        <input type="hidden" name="action" value="supprimerAdresse"/>  
                        <button type="submit" class="red small fa fa-book" value="SupprimerAdresse"> X</button>
                        
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
            
            <c:if test="${param.action=='adresseAjoutee'}">
                <br/>
                <span class="resultat">Adresse crée !</span>
            </c:if>

            <c:if test="${param.action=='utilisateurSupprime'}">
                <br/>
                <span class="resultat">Contact supprimé !</span>
            </c:if>

            <c:if test="${param.action=='utilisateurCree'}">
                <br/>
                <span class="resultat">Contact crée !</span>
            </c:if>

            <c:if test="${param.action=='utilisateurMisAJour'}">
                <br/>
                <span class="resultat">Contact mis à jour !</span>
            </c:if>

            <c:if test="${param.action=='photoAjoute'}">
                <br/>
                <span class="resultat">Photo ajoutée avec succès !</span>
            </c:if>

            <c:if test="${param.action=='numeroAjoute'}">
                <br/>
                <span class="resultat">Numéro ajouté avec succès !</span>
            </c:if>

            <c:if test="${param.action=='telephoneSupprime'}">
                <br/>
                <span class="resultat">Numéro supprimé !</span>
            </c:if>

            <c:if test="${param.action=='adresseSupprimee'}">
                <br/>
                <span class="resultat">Adresse supprimée !</span>
            </c:if>


            <!-- Fin du menu -->  

            <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->  

            <c:if test="${param.action == 'listerLesUtilisateurs'}" >  
                <!--<h2>Resultat de la recherche</h2>-->  
                <c:if test = "${requestScope['listeDesUsers'] != null}">
                     <table classe="sortable" border="4">  
                        <!-- La ligne de titre du tableau des comptes -->  
                        <tr>  
                            <td><b>Nom</b></td>  
                            <td><b>Prénom</b></td>
                            <td><b>Photo</b></td>
                            <td><b>Telephone</b></td>
                            <td><b>Adresse</b></td>
                             <td><b>Suppression</b></td>
                            <td><b>Modification</b></td>
                            <!--<td><b>Telephone</b></td>-->
                        </tr>  

                        <!-- Ici on affiche les lignes, une par utilisateur -->  
                        <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                        <c:set var="total" value="0"/>  

                        <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                            <tr>  
                                <td>${u.firstname}</td>  
                                <td>${u.lastname}</td>
                                <c:if test="${empty u.pictureName}">
                                    <td>
                                        <form action="ServletUsers" method="get">
                                            <input type="hidden" name="contact" value="${u.id}"/>
                                            <input type="hidden" name="action" value="ajouterUnePhoto"/>  
                                            <!-- <input type="submit" value="Envoyer" class="sansLabel" /> -->
                                            <button type="submit" value="Envoyer" class="green medium fa fa-picture-o"> Ajouter</button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${!empty u.pictureName}">
                                    <td><img src="resources/files/${u.pictureName}" alt="contact id:${u.id} picture" height="60" width="60"></td>
                                    </c:if>
                                <td>
                                    <%--<c:if test="${!empty requestScope['phonesOf'.concat(id)]}">--%>
                                    <c:set var="id" value="${u.id}"/>
                                    <!--${phoneList}-->
                                    <c:forEach var="tel" items="${requestScope['phonesOf'.concat(id)]}">
                                        ${tel.numero}
                                        <br/>
                                    </c:forEach>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="supprimerUnNumero"/>  
                                        <!-- <input type="submit" value="X" name="submit"/> -->
                                        <button type="submit" value="SupprimerunNumero" class="small fa fa-phone-square red"> X</button>
                                    </form>

                                    <%--<c:if test = "${requestScope['phonesOf'.concat(id)] == null}">--%>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="ajouterUnNumero"/>  
                                        <!-- <input type="submit" value="Ajouter" name="submit"/> -->
                                        <button type="submit" value="Ajouter" class=" green small fa fa-phone-square"> Ajouter</button>
                                    </form>
                                    <%--</c:if>--%>
                                </td>
                                <td>
                                    <%--<c:if test="${!empty requestScope['phonesOf'.concat(id)]}">--%>
                                    <c:set var="id" value="${u.id}"/>
                                    <c:forEach var="adresse" items="${requestScope['adressesOf'.concat(id)]}">
                                        ${adresse.nomRue}, ${adresse.codePostal}, ${adresse.nomVille}
                                        <br/>
                                    </c:forEach>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                       <!-- <input type="hidden" name="action" value="supprimerUneAdresse"/>  -->
                                        <button type="submit" value="SupprimerUneAdresse" class="small red"><i class="fa fa-book"></i> X</button>
                                    </form>

                                    <%--<c:if test = "${requestScope['phonesOf'.concat(id)] == null}">--%>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="ajouterUneAdresse"/>  
                                        <!-- <input type="submit" value="Ajouter" name="submit"/> -->
                                        <button type="submit" value="Ajouter" class="green small fa fa-book"> Ajouter</button>
                                    </form>
                                    <%--</c:if>--%>
                                </td>
                                <td>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="suppression"/>  
                                       <!--  <input type="submit" value="Supprimer" name="submit"/> -->
                                        <button type="submit" value="Supprimer" class="large red square"><i class="fa fa-minus-square"></i> Supprimer Contact</button>
                                    </form>
                                </td>

                                <td>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="modification"/>  
                                        <!-- <input type="submit" value="Modifier" name="submit"/> -->
                                        <button class="large orange pill"><i class="fa fa-wrench"></i> Modifier</button>
                                    </form>
                                </td>
                                <!-- On compte le nombre de users -->  
                                <c:set var="total" value="${total+1}"/>  
                            </tr>  
                        </c:forEach> 
                        <tr>
                            <td>
                                <b>
                                    <c:if test="${param.page > 0}">
                                        <form action="ServletUsers" method="get">  
                                            <input type="hidden" name="action" value="Previous"/>  
                                            <button type="submit" class='medium blue' value="Précèdente" name="submit"> Précèdente </button>  
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
                                            <button type="submit" class='medium blue' value="Suivante" name="submit"> Suivante </button>
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
                    La liste est nulle
                </c:if>
            </c:if>
            <c:if test="${param.action == 'chercherParLogin'}" >  
                <!--<h2>Resultat de la recherche</h2>-->  
                <c:if test = "${requestScope['listeDesUsers'] != null}">
                     <table classe="sortable" border="4">  
                        <!-- La ligne de titre du tableau des comptes -->  
                        <tr>  
                            <td><b>Nom</b></td>  
                            <td><b>Prénom</b></td>
                            <td><b>Photo</b></td>
                            <td><b>Telephone</b></td>
                            <td><b>Adresse</b></td>
                             <td><b>Suppression</b></td>
                            <td><b>Modification</b></td>
                            <!--<td><b>Telephone</b></td>-->
                        </tr>  

                        <!-- Ici on affiche les lignes, une par utilisateur -->  
                        <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                        <c:set var="total" value="0"/>  

                        <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                            <tr>  
                                <td>${u.firstname}</td>  
                                <td>${u.lastname}</td>
                                <c:if test="${empty u.pictureName}">
                                    <td>
                                        <form action="ServletUsers" method="get">
                                            <input type="hidden" name="contact" value="${u.id}"/>
                                            <input type="hidden" name="action" value="ajouterUnePhoto"/>  
                                           <!-- <input type="submit" value="Envoyer" class="sansLabel" /> -->
                                           <button type="submit" value="Envoyer" class="green medium fa fa-picture-o"> Ajouter</button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${!empty u.pictureName}">
                                    <td><img src="resources/files/${u.pictureName}" alt="contact id:${u.id} picture" height="60" width="60"></td>
                                    </c:if>
                                <td>
                                    <%--<c:if test="${!empty requestScope['phonesOf'.concat(id)]}">--%>
                                    <c:set var="id" value="${u.id}"/>
                                    <!--${phoneList}-->
                                    <c:forEach var="tel" items="${requestScope['phonesOf'.concat(id)]}">
                                        ${tel.numero}
                                        <br/>
                                    </c:forEach>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="supprimerUnNumero"/>  
                                       <!-- <input type="submit" value="X" name="submit"/> -->
                                        <button type="submit" value="SupprimerUnNumero" class="small fa fa-phone-square red"> X</button>
                                    </form>

                                    <%--<c:if test = "${requestScope['phonesOf'.concat(id)] == null}">--%>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="ajouterUnNumero"/>  
                                        <!-- <input type="submit" value="Ajouter" name="submit"/> -->
                                        <button type="submit" value="Ajouter" class="green small fa fa-phone-square"> Ajouter</button>
                                    </form>
                                    <%--</c:if>--%>
                                </td>
                                <td>
                                    <%--<c:if test="${!empty requestScope['phonesOf'.concat(id)]}">--%>
                                    <c:set var="id" value="${u.id}"/>
                                    <c:forEach var="adresse" items="${requestScope['adressesOf'.concat(id)]}">
                                        ${adresse.nomRue}, ${adresse.codePostal}, ${adresse.nomVille}
                                        <br/>
                                    </c:forEach>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        
                                       <!-- <input type="submit" value="X" name="submit"/> -->
                                        <button type="submit" value="SupprimerUneAdresse" class="small red"><i class="fa fa-minus-square"></i> X</button>
                                    </form>

                                    <%--<c:if test = "${requestScope['phonesOf'.concat(id)] == null}">--%>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="ajouterUneAdresse"/>  
                                        <!-- <input type="submit" value="Ajouter" name="submit"/> -->
                                        <button type="submit" value="Ajouter" class="green small fa fa-book"> Ajouter</button>
                                    </form>
                                    <%--</c:if>--%>
                                </td>
                                <td>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="suppression"/>  
                                        <!-- <input type="submit" value="Supprimer" name="submit"/> -->
                                        <button type="submit" value="Suppression" class="large red square"><i class="fa fa-minus-square"></i> Supprimer Contact</button>
                                    </form>
                                </td>

                                <td>
                                    <form action="ServletUsers" method="get">
                                        <input type="hidden" name="contact" value="${u.id}"/>
                                        <input type="hidden" name="contactNom" value="${u.firstname}"/>
                                        <input type="hidden" name="contactPrenom" value="${u.lastname}"/>
                                        <input type="hidden" name="action" value="modification"/>  
                                        <!-- <input type="submit" value="Modifier" name="submit"/> -->
                                        <button class="large orange pill"><i class="fa fa-wrench"></i> Modifier</button>
                                    </form>
                                </td>
                                <!-- On compte le nombre de users -->  
                                <c:set var="total" value="${total+1}"/>  
                            </tr>  
                        </c:forEach> 
                        <tr>
                            <td>
                                <b>
                                    <c:if test="${param.page > 0}">
                                        <form action="ServletUsers" method="get">  
                                            <input type="hidden" name="action" value="Previous"/>  
                                            <button type="submit" class='medium blue' value="Précèdente" name="submit"> Précèdente </button> 
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
                                            <button type="submit" class='medium blue' value="Suivante" name="submit"> Suivante </button>
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
                    Aucun résultat :(
                </c:if>
            </c:if>
        </c:if>
    </body>  
</html>  
