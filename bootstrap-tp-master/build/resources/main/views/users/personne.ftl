<#ftl encoding="utf-8">

<body x<link rel="stylesheet" href="style.css">mlns="http://www.w3.org/1999/html">

    <h1>Bienvenue sur la page gérant les personnes affiliés à notre entreprise</h1>
    <br>
    <br>

    <p>Liste des personnes appartenant à notre groupe</p>
        <#list personnes as personne >
            <li><a href="/personne/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
        </#list>

    <br>
    <br>


<ul>
<#if role == 1>

<li><a href="/modifpersonne">Modif personne(admin only)</a></li>
</#if>
<li><a href="/main">Page d'accueil</a></li>
</ul>

</body>

