<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">

<h1>Bienvenue sur la page gérant les appartement affiliés à notre entreprise</h1>
    <br>
    <br>

    <p>Liste des appartement appartenant à notre groupe</p>
        <#list appartements as appartement >
            <li><a href="/appartement/${appartement.idAppartement}">${appartement.idAppartement}</a>- ${appartement.etage} ${appartement.superficie} ${appartement.idImmeuble}</li>
        </#list>

<ul>
<#if role==1>
<li><a href="/modifappart">Modif appart(admin only)</a></li>
</#if>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>