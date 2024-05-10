<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">
  
    
    <p>Ajout d'une personne (seulement pour les admins)</p>

    <form action="/ajouterPersonne" method="POST">
    <div id="Lastname_container">
        <input name="nom" id="nom" class="initial" type="text" placeholder="Last Name" value="">
    </div>
    <div id="Firstname_container">
        <input name="prenom" id="prenom" class="initial" type="text" placeholder="First Name" value="">
    </div>
    <div id="Telephone container">
        <input name="num_tel" id="num_tel" class="initial" type="text" placeholder="Telephone" value="">
    </div>
    <div id="Proprio container">
        <input name="proprio" id="proprio" class="initial" type="text" placeholder="proprietaire(oui ou non)" value="">
    </div>
    <input type="submit" value="Ajouter">
</form>
    <br>
    <br>


    <p>Suppression d'une personne(seulement pour les admins)</p>

        <form action="/supprimerPersonne" method="post">
            <input type="text" name="id" placeholder="idPersonne" value="">
            <input type="submit" value="Supprimer">
        </form>
<a href="/main">Page d'accueil</a>
</body>