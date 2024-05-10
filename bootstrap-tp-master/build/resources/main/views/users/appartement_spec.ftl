<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de l'appartement ${appartement.idAppartement}</h1>
<p>Donnée appartement :  ${appartement.etage} ${appartement.superficie} ${appartement.idImmeuble}</p>

<p>Liste des locataires liés à l'appartement</p>
 <#list locataires as locataire >
            <li><a href="/personne/${appartement.idAppartement}">{personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
        </#list>
<p>Liste des proprietaire liés à l'appartement</p>
 <#list proprietaires as proprietaire >
            <li><a href="/personne/${appartement.idAppartement}">{personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
        </#list>


<ul>
<li><a href="/appartement">Main appartement</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>