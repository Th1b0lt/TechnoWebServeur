<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body>

<h1>Page représentative de la personne n°${personne.idPersonne}</h1>
<p>Données personne : Numéro de téléphone : ${personne.numeroDeTelephone} - Nom : ${personne.nom} - Prénom : ${personne.prenom}</p>

<#if role == 1>
    <p>Mettre à jour les informations de la personne :</p>
    <form action="/majPersonne/${personne.idPersonne}/numeroDeTelephone" method="POST">
        <input name="numeroDeTelephone" id="numeroDeTelephone" type="text" placeholder="Nouveau numéro de téléphone" value="">
        <input type="submit" value="Modifier">
    </form>

    <form action="/majPersonne/${personne.idPersonne}/nom" method="POST">
        <input name="nom" id="nom" type="text" placeholder="Nouveau nom" value="">
        <input type="submit" value="Modifier">
    </form>

    <form action="/majPersonne/${personne.idPersonne}/prenom" method="POST">
        <input name="prenom" id="prenom" type="text" placeholder="Nouveau prénom" value="">
        <input type="submit" value="Modifier">
    </form>

    <p>Supprimer la personne :</p>
    <form action="/supprimerPersonne/${personne.idPersonne}" method="post">
        <input type="submit" value="Supprimer">
    </form>
</#if>

<p>Liste des appartements liés à la personne :</p>
<#if appartements?has_content>
    <ul>
    <#list appartements as appartement>
        <li><a href="/appartement/${appartement.idAppartement}">${appartement.idAppartement}</a> - Étage ${appartement.etage}, Superficie ${appartement.superficie}, Immeuble ${appartement.idImmeuble}</li>
    </#list>
    </ul>
<#else>
    <p>(Pas d'appartement lié)</p>
</#if>

<p>Statistiques globales :</p>
<p>Pourcentage de logements occupés : ${statglobal[0]?string("0.00")} %</p>
<p>Pourcentage de logements vacants : ${statglobal[1]?string("0.00")} %</p>

<p>Statistiques locales :</p>
<#if statLocal?has_content>
    <#list statLocal as stat>
        <p>Logement numéro ${stat[2]?string("0.00")} :</p>
        <p>Pourcentage de logements occupés : ${stat[0]?string("0.00")} %</p>
        <p>Pourcentage de logements vacants : ${stat[1]?string("0.00")} %</p>
    </#list>
<#else>
    <p>Pas de statistiques locales disponibles.</p>
</#if>

<ul>
    <li><a href="/personne">Retour à la liste des personnes</a></li>
    <li><a href="/main">Page d'accueil</a></li>
</ul>

</body>
