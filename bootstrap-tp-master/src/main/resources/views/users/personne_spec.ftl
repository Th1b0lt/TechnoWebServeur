<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de la personne ${personne.idPersonne}</h1>
<p>Donnée personne :  ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</p>

<p>Liste des locataires liés à l'personne</p>
 <#list locataires as locataire >
            <li><a href="/personne/${personne.idpersonne}">{personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
        </#list>
<p>Liste des appartements liés à la personne</p>
 <#list appartements as appartement >
            <li><a href="/personne/${personne.idPersonne}">${appartement.idAppartement}</a>  - ${appartement.etage} ${appartement.superficie} ${appartement.idImmeuble}</li>
        </#list>


<ul>
<li><a href="/personne">Main personne</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>