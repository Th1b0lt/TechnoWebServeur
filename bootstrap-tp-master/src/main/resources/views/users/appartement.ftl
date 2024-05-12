<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">

    <h1>Bienvenue sur la page gérant les appartement affiliés à notre entreprise</h1>
        <br>
        <br>

        <p>Liste des appartement appartenant à notre groupe</p>
            <#list appartements as appartement >
                <li>Appartement numéro <a href="/appartement/${appartement.idAppartement}">${appartement.idAppartement}</a> - A l'étage ${appartement.etage} d'une supérficie de  ${appartement.superficie} m² dans l'immeuble numéro ${appartement.idImmeuble}</li>
            </#list>

<ul>
<#if role==1>
<li><a href="/modifappart">Modif appart(admin only)</a></li>
</#if>

    <li><a href="/main">Page d'accueil</a></li>
    </ul>
