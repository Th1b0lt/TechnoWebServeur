<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">

    <h1>Bienvenue sur la page gérant les personnes affiliées à notre entreprise</h1>
    <br>
    <br>

    <p>Liste des personnes appartenant à notre groupe</p>
        <#list personnes as personne >
            <li><a href="/personne/${personne.idPersonne}">${personne.idPersonne}</a> - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
        </#list>

    <br>
    <br>


<<<<<<< HEAD
<ul>
<#if role == 1>

<li><a href="/modifpersonne">Modif personne(admin only)</a></li>
</#if>
<li><a href="/main">Page d'accueil</a></li>
</ul>
=======
    <ul>
    <li><a class="modif-link" href="/modifpersonne">Modif personne(admin only)</a></li>

    <li><a href="/main">Page d'accueil</a></li>
    </ul>

>>>>>>> d857ec1f2b2ed28509513372a98a6ba18bf2c1e9


