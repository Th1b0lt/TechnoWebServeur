<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">
  
    
    
     <p>Ajout d'un appartement (seulement pour les admins)</p>

    <form action="/ajouterAppartement" method="POST">
    <div id="etage_container">
        <input name="etage" id="nom" class="etage" type="text" placeholder="etage" value="">
    </div>
    <div id="superficie_container">
        <input name="superficie" id="superficie" class="initial" type="text" placeholder="superficie" value="">
    </div>
    <div id="idImmeuble container">
        <input name="idImmeuble" id="idImmeuble" class="initial" type="text" placeholder="idImmeuble" value="">
    </div>
   
    <input type="submit" value="Ajouter">
</form>
    <br>
    <br>


    <p>Suppression d'un appartement(seulement pour les admins)</p>

        <form action="/supprimerAppartement" method="post">
            <input type="text" name="id" placeholder="idAppartement" value="">
            <input type="submit" value="Supprimer">
        </form>
<a href="/main">Page d'accueil</a>
