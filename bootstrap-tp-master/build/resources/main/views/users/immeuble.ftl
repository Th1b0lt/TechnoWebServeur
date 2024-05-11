<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">

<h1>Bienvenue sur la page gérant les immeuble affiliés à notre entreprise</h1>
    <br>
    <br>

    <p>Liste des immeuble appartenant à notre groupe</p>
        <#list immeubles as immeuble >
            <li>numéro d'immeuble : <a href="/immeuble/${immeuble.idImmeuble}"> ${immeuble.idImmeuble}</a> - Nom de l'immeuble ${immeuble.nom} syndicat numéro ${immeuble.idSyndicat} se trouve au ${immeuble.adresse}</li>
        </#list>

    <br>
    <br>

<ul> 
<li><a href="/modifimmeuble">Modif immeuble(admin only)</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>