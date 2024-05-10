  <#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">
  
    
     
 <p>Ajout d'un Immeuble (seulement pour les admins)</p>

    <form action="/ajouterImmeuble" method="POST">
    <div id="name_container">
        <input name="nom" id="nom" class="initial" type="text" placeholder="nom" value="">
    </div>
    <div id="idSyndicat_container">
        <input name="idSyndicat" id="idSyndicat" class="initial" type="text" placeholder="idSyndicat" value="">
    </div>
    <div id="adresse container">
        <input name="adresse" id="adresse" class="initial" type="text" placeholder="adresse" value="">
    </div>
    <input type="submit" value="Ajouter">
</form>
    <br>
    <br>


    <p>Suppression d'un Immeuble(seulement pour les admins)</p>

        <form action="/supprimerImmeuble" method="post">
            <input type="text" name="id" placeholder="idImmeuble" value="">
            <input type="submit" value="Supprimer">
        </form>
<a href="/main">Page d'accueil</a>
</body>