<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de la personne ${personne.idPersonne}</h1>
<p>Donnée personne :  ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</p>
<p>Mettre a jour votre personne</p>
<form action="/majPersonne/${personne.idPersonne}/numeroDeTelephone" method="POST">
    <div id="numeroDeTelephone_container">
        <input name="numeroDeTelephone" id="numeroDeTelephone" class="initial" type="text" placeholder="numeroDeTelephone" value="">
    </div>
    <input type="submit" value="Modfier">

</form>
<form action="/majPersonne/${personne.idPersonne}/nom" method="POST">
    <div id="nom_container">
        <input name="nom" id="nom" class="initial" type="text" placeholder="nom" value="">
    </div>
    <input type="submit" value="Modfier">

</form>

<form action="/majPersonne/${personne.idPersonne}/prenom" method="POST">

    <div id="prenom container">
        <input name="prenom" id="prenom" class="initial" type="text" placeholder="prenom" value="">
    </div>
    <input type="submit" value="Modfier">
</form>
    <br>
    <br>


    <p>Suppression de la personne(seulement pour les admins)</p>

        <form action="/supprimerPersonne/${personne.idPersonne}" method="post">
            <input type="submit" value="Supprimer">
        </form>
<br>
<br>

<p>Liste des locataires liés à la personne</p>
<#if locataires?has_content>
    <#list locataires as locataire>
        <li><a href="/personne/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
    </#list>
<#else>
    <li>(Pas de locataire)</li>
</#if>

<p>Liste des appartements liés à la personne</p>
<#if appartements?has_content>
    <#list appartements as appartement>
        <li><a href="/appartement/${appartement.idAppartement}">${appartement.idAppartement}</a>  - ${appartement.etage} ${appartement.superficie} ${appartement.idImmeuble}</li>
    </#list>
<#else>
    <li>(Pas d'appartement)</li>
</#if>

<ul>
<li><a href="/personne">Main personne</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>