<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de l'immeuble ${immeuble.idImmeuble}</h1>
<p>Donnée immeuble :  ${immeuble.nom} ${immeuble.adresse}</p>
<p>Il est rattaché au Syndicat : <a href="/syndicat/${immeuble.idSyndicat}"> ${immeuble.idSyndicat} </a></p>

<p>Liste des appartements liés à l'immeuble</p>
 <#list appartements as appartement >
            <li><a href="/appartement/${appartement.idAppartement}">{appartement.idAppartement}</a> ${appartement.etage} ${appartement.superficie} ${appartement.idImmeuble}</li>
        </#list>


<ul>
<li><a href="/immeuble">Main Immeuble</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>