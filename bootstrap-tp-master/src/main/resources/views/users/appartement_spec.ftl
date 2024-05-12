<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de l'appartement ${appartement.idAppartement}</h1>
<p>Donnée appartement :  ${appartement.etage} ${appartement.superficie} Relié à l'immeuble ${appartement.idImmeuble}</p>
<#if role == 1>
<p>Mettre a jour votre appartement</p>
<form action="/majAppartement/${appartement.idAppartement}/etage" method="POST">
    <div id="etage_containe">
        <input name="etage" id="etage" class="initial" type="text" placeholder="etage" value="">
    </div>
    <input type="submit" value="Modfier">

</form>
<form action="/majAppartement/${appartement.idAppartement}/superficie" method="POST">
    <div id="superficie_container">
        <input name="superficie" id="superficie" class="initial" type="text" placeholder="superficie" value="">
    </div>
    <input type="submit" value="Modfier">

</form>

<form action="/majAppartement/${appartement.idAppartement}/idImmeuble" method="POST">

    <div id="idImmeuble container">
        <input name="idImmeuble" id="idImmeuble" class="initial" type="text" placeholder="idImmeuble" value="">
    </div>
    <input type="submit" value="Modfier">
</form>
    <br>
    <br>


    <p>Suppression de l'Appartement(seulement pour les admins)</p>

        <form action="/supprimerAppartement/ ${appartement.idAppartement}" method="post">
            <input type="submit" value="Supprimer">
        </form>
</#if>

<p>Liste des locataires liés à l'appartement</p>
<#if locataires?has_content>
    <#list locataires as locataire>
        <li><a href="/personne/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
    </#list>
<#else>
    <li>(Pas de locataire)</li>
</#if>
<p>Liste des proprietaire liés à l'appartement</p>
<#if proprietaires?has_content>
    <#list proprietaires as proprietaire>
        <li><a href="/personne/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
    </#list>
<#else>
    <li>(Pas de propriétaire)</li>
</#if>

<#if role == 1>
<p>Ajout de personne dans l'appartement (cliqué sur les personnes a ajouter)</p>

<#if personne?has_content >
    <#list personnes as personne>
        <li><a href="/personne/ajouterLien/${appartement.idAppartement}/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
    </#list>
<#else>
    <li>(Pas de personne)</li>
</#if>

<p>Suppression d'une personne dans l'appartement</p>
<#if locataires?has_content>
    <#list locataires as locataire>
        <li><a href="/supprimeLien/${appartement.idAppartement}/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
    </#list>
<#else>
    <li>(Pas de locataire)</li>
</#if>
<#if proprietaires?has_content>
    <#list proprietaires as proprietaire>
        <li><a href="/supprimeLien/${appartement.idAppartement}/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
    </#list>
<#else>
    <li>(Pas de propriétaire)</li>
</#if>
</#if>
<ul>
<li><a href="/appartement">Main appartement</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>