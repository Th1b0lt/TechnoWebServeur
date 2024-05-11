<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de l'syndicat ${syndicat.idSyndicat}</h1>
           
<p>Donnée syndicat :  ${syndicat.name} ${syndicat.adresse} ${syndicat.personneReference}  ${syndicat.numeroDeTelephone} ${syndicat.adresseEmail}</p>

<p>Liste des immeuble liés au syndicat</p>
 <#list immeubles as immeuble >
            <li><a href="/immeuble/${syndicat.idSyndicat}">${immeuble.idImmeuble}</a> - ${immeuble.nom} ${immeuble.idSyndicat} ${immeuble.adresse}</li>
        </#list>



<ul>
<li><a href="/syndicat">Main syndicat</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>