<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">


  <p>Ajout d'un Syndicat (seulement pour les admins)</p>

    <form action="/ajouterSyndicat" method="POST">
    <div id="name_container">
        <input name="name" id="name" class="initial" type="text" placeholder="name" value="">
    </div>
    <div id="adresse_container">
        <input name="adresse" id="adresse" class="initial" type="text" placeholder="adresse" value="">
    </div>
    <div id="personneReference container">
        <input name="personneReference" id="personneReference" class="initial" type="text" placeholder="personneReference" value="">
    </div>
    <div id="numeroDeTelephone container">
        <input name="numeroDeTelephone" id="numeroDeTelephone" class="initial" type="text" placeholder="numeroDeTelephone" value="">
    </div>
    <div id="numeroDeTelephone container">
        <input name="adresseEmail" id="adresseEmail" class="initial" type="text" placeholder="adresseEmail" value="">
    </div>
    <input type="submit" value="Ajouter">
</form>
    <br>
    <br>


    <p>Suppression d'une personne(seulement pour les admins)</p>

        <form action="/supprimerPersonne" method="post">
            <input type="text" name="id" placeholder="idSyndicat" value="">
            <input type="submit" value="Supprimer">
        </form>

<a href="/main">Page d'accueil</a>
</body>