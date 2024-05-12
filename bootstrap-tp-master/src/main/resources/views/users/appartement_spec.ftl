<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">
    <h1>Page représentative de l'appartement ${appartement.idAppartement}</h1>
    <p>Données appartement :  se trouve à l'étage ${appartement.etage} est d'une superficie de ${appartement.superficie} m² et est relié à l'immeuble ${appartement.idImmeuble}</p>

    <p>Mettre à jour votre appartement (seulement pour les admins)</p>
    <form action="/majAppartement/${appartement.idAppartement}/etage" method="POST">
        <div id="etage_containe">
            <input name="etage" id="etage" class="initial" type="text" placeholder="etage" value="">
        </div>
        <input type="submit" value="Modfier">

    </form>
    <form action="/majAppartement/${appartement.idAppartement}/superficie" method="POST">
        <div id="superficie_container">
            <input name="superficie" id="superficie" class="initial" type="text" placeholder="superficie" value="">
        </div>
        <input type="submit" value="Modfier">

    </form>

    <form action="/majAppartement/${appartement.idAppartement}/idImmeuble" method="POST">

        <div id="idImmeuble container">
            <input name="idImmeuble" id="idImmeuble" class="initial" type="text" placeholder="idImmeuble" value="">
        </div>
        <input type="submit" value="Modfier">
    </form>
        <br>
        <br>


        <p>Suppression de l'Appartement(seulement pour les admins)</p>

            <form action="/supprimerAppartement/ ${appartement.idAppartement}" method="post">
                <input type="submit" value="Supprimer">
            </form>
    <p>Liste des locataires liés à l'appartement</p>
    <#if locataires?has_content>
        <#list locataires as locataire>
            <li><a href="/personne/${locataire.idPersonne}">${locataire.idPersonne}</a> - ${locataire.numeroDeTelephone} ${locataire.nom} ${locataire.prenom}</li>
        </#list>
    <#else>
        <li>(Pas de locataire)</li>
    </#if>
    <p>Liste des proprietaire liés à l'appartement</p>
    <#if proprietaires?has_content>
        <#list proprietaires as proprietaire>
            <li><a href="/personne/${proprietaire.idPersonne}">${proprietaire.idPersonne}</a> - ${proprietaire.numeroDeTelephone} ${proprietaire.nom} ${proprietaire.prenom}</li>
        </#list>
    <#else>
        <li>(Pas de propriétaire)</li>
    </#if>


    <p>Ajout de personne dans l'appartement (cliquez sur les personnes à ajouter)</p>

    <#if personnes?has_content>
        <#list personnes as personne>
            <form id="postForm_${personne.idPersonne}" action="/appartement/ajouterLien/${appartement.idAppartement}/${personne.idPersonne}" method="POST">
                <li>
                    ${personne.idPersonne} - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}
                    <button type="submit" form="postForm_${personne.idPersonne}">Ajouter</button>
                </li>
            </form>
        </#list>
    <#else>
        <li>(Pas de personne)</li>
    </#if>

    <p>Suppression d'une personne dans l'appartement</p>
    <#if locataires?has_content>
        <#list locataires as locataire>
            <form id="deleteForm_locataire_${locataire.idPersonne}" action="/supprimeLien/${appartement.idAppartement}/${locataire.idPersonne}" method="POST">
                <li>
                    ${locataire.idPersonne} - ${locataire.numeroDeTelephone} ${locataire.nom} ${locataire.prenom}
                    <button type="submit" form="deleteForm_locataire_${locataire.idPersonne}">Supprimer</button>
                </li>
            </form>
        </#list>
    <#else>
        <li>(Pas de locataire)</li>
    </#if>
    <#if proprietaires?has_content>
        <#list proprietaires as proprietaire>
            <form id="deleteForm_proprietaire_${proprietaire.idPersonne}" action="/supprimeLien/${appartement.idAppartement}/${proprietaire.idPersonne}" method="POST" >
                <li>
                    ${proprietaire.idPersonne} - ${proprietaire.numeroDeTelephone} ${proprietaire.nom} ${proprietaire.prenom}
                    <button type="submit" form="deleteForm_proprietaire_${proprietaire.idPersonne}">Supprimer</button>
                </li>
            </form>
        </#list>
    <#else>
        <li>(Pas de propriétaire)</li>
    </#if>
    <ul>
        <li><a href="/appartement">Main appartement</a></li>
        <li><a href="/main">Page d'accueil</a></li>
    </ul>

