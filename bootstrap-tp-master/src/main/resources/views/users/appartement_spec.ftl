<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body>

<h1>Page représentative de l'appartement ${appartement.idAppartement}</h1>
<p>Données de l'appartement : ${appartement.etage} ${appartement.superficie} - Immeuble ${appartement.idImmeuble}</p>

<#if role == 1>
    <p>Mettre à jour les détails de l'appartement :</p>
    <form action="/majAppartement/${appartement.idAppartement}/etage" method="POST">
        <input name="etage" id="etage" type="text" placeholder="Étage" value="">
        <input type="submit" value="Modifier">
    </form>

    <form action="/majAppartement/${appartement.idAppartement}/superficie" method="POST">
        <input name="superficie" id="superficie" type="text" placeholder="Superficie" value="">
        <input type="submit" value="Modifier">
    </form>

    <form action="/majAppartement/${appartement.idAppartement}/idImmeuble" method="POST">
        <input name="idImmeuble" id="idImmeuble" type="text" placeholder="ID de l'immeuble" value="">
        <input type="submit" value="Modifier">
    </form>

    <p>Supprimer l'appartement (administrateurs seulement) :</p>
    <form action="/supprimerAppartement/${appartement.idAppartement}" method="POST">
        <input type="submit" value="Supprimer">
    </form>
</#if>

<p>Liste des locataires :</p>
<#if locataires?has_content>
    <ul>
    <#list locataires as locataire>
        <li><a href="/personne/${locataire.idPersonne}">${locataire.idPersonne}</a> - ${locataire.numeroDeTelephone} ${locataire.nom} ${locataire.prenom}</li>
    </#list>
    </ul>
<#else>
    <p>(Pas de locataire)</p>
</#if>

<p>Liste des propriétaires :</p>
<#if proprietaires?has_content>
    <ul>
    <#list proprietaires as proprietaire>
        <li><a href="/personne/${proprietaire.idPersonne}">${proprietaire.idPersonne}</a> - ${proprietaire.numeroDeTelephone} ${proprietaire.nom} ${proprietaire.prenom}</li>
    </#list>
    </ul>
<#else>
    <p>(Pas de propriétaire)</p>
</#if>

<#if role == 1>
    <p>Ajouter une personne à l'appartement :</p>
    <#if personnes?has_content>
        <ul>
        <#list personnes as personne>
            <li>
                ${personne.idPersonne} - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}
                <form action="/appartement/ajouterLien/${appartement.idAppartement}/${personne.idPersonne}" method="POST">
                    <button type="submit">Ajouter</button>
                </form>
            </li>
        </#list>
        </ul>
    <#else>
        <p>(Pas de personne à ajouter)</p>
    </#if>

    <p>Supprimer une personne de l'appartement :</p>
    <#if locataires?has_content>
        <ul>
        <#list locataires as locataire>
            <li>
                ${locataire.idPersonne} - ${locataire.numeroDeTelephone} ${locataire.nom} ${locataire.prenom}
                <form action="/supprimeLien/${appartement.idAppartement}/${locataire.idPersonne}" method="POST">
                    <button type="submit">Supprimer</button>
                </form>
            </li>
        </#list>
        </ul>
    <#else>
        <p>(Pas de locataire à supprimer)</p>
    </#if>

    <#if proprietaires?has_content>
        <ul>
        <#list proprietaires as proprietaire>
            <li>
                ${proprietaire.idPersonne} - ${proprietaire.numeroDeTelephone} ${proprietaire.nom} ${proprietaire.prenom}
                <form action="/supprimeLien/${appartement.idAppartement}/${proprietaire.idPersonne}" method="POST">
                    <button type="submit">Supprimer</button>
                </form>
            </li>
        </#list>
        </ul>
    <#else>
        <p>(Pas de propriétaire à supprimer)</p>
    </#if>
</#if>

<ul>
    <li><a href="/appartement">Retour à la liste des appartements</a></li>
    <li><a href="/main">Page d'accueil</a></li>
</ul>

</body>
