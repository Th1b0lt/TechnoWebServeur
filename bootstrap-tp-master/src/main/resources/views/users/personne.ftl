<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">

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
    <li><a class="modif-link" href="/modifpersonne">Modif personne(admin only)</a></li>

    <li><a href="/main">Page d'accueil</a></li>
    </ul>



