<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de l'appartement ${appartement.idAppartement}</h1>
<p>Donnée appartement :  ${appartement.etage} ${appartement.superficie} ${appartement.idImmeuble}</p>

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
<ul>
<li><a href="/appartement">Main appartement</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>