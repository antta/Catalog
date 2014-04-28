<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test des expressions EL</title>
    </head>
    <body>
    <p>
        <!-- Initialisation d'un bean de type Coyote avec une action standard, pour l'exemple : -->
        <jsp:useBean id="nicolas" class="beans.Nicolas" />
        <!-- Initialisation de sa propriété 'prénom' : -->
        <jsp:setProperty name="nicolas" property="prenom" value="nicolas"/>
        <jsp:setProperty name="nicolas" property="nom" value="challut"/>
        <!-- Et affichage de sa valeur : -->
        <jsp:getProperty name="nicolas" property="prenom" />
        ${ nicolas.nom }<br/>
        Prenom : ${ paramValues.nom[0] }<br/>
        Nom : ${ paramValues.nom[1] }<br/>
        test : ${ param.test }<br/>
    </p>
    <form method="post" action="">
   <p>
       <label for="pays">Dans quel(s) pays avez-vous déjà voyagé ?</label><br />
       <select name="pays" id="pays" multiple="multiple">
           <option value="france">France</option>
           <option value="espagne">Espagne</option>
           <option value="italie">Italie</option>
           <option value="royaume-uni">Royaume-Uni</option>
           <option value="canada">Canada</option>
           <option value="etats-unis">Etats-Unis</option>
           <option value="chine" selected="selected">Chine</option>
           <option value="japon">Japon</option>
       </select>
   </p>
</form>
    </body>
</html>