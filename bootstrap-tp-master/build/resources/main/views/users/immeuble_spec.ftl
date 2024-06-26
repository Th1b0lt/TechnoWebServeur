<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


    <h1>Page representative de l'immeuble n° ${immeuble.idImmeuble}</h1>
    <p>Donnée immeuble :  ${immeuble.nom} se trouve au ${immeuble.adresse}</p>
    <p>Il est rattaché au Syndicat : <a href="/syndicat/${immeuble.idSyndicat}"> ${immeuble.idSyndicat} </a></p>
    <#if role ==1>
    <p>Mettre à jour votre immeuble (seulement pour les admins)</p>
    <form action="/majImmeuble/${immeuble.idImmeuble}/name" method="POST">
        <div id="name_container">
            <input name="nom" id="nom" class="initial" type="text" placeholder="nom" value="">
        </div>
        <input type="submit" value="Modfier">

    </form>
    <form action="/majImmeuble/${immeuble.idImmeuble}/idSyndicat" method="POST">
        <div id="idSyndicat_container">
            <input name="idSyndicat" id="idSyndicat" class="initial" type="text" placeholder="idSyndicat" value="">
        </div>
        <input type="submit" value="Modfier">

    </form>

    <form action="/majImmeuble/${immeuble.idImmeuble}/adresse" method="POST">

        <div id="adresse container">
            <input name="adresse" id="adresse" class="initial" type="text" placeholder="adresse" value="">
        </div>
        <input type="submit" value="Modfier">
    </form>
        <br>
        <br>


        <p>Suppression de l'Immeuble(seulement pour les admins)</p>

            <form action="/supprimerImmeuble/${immeuble.idImmeuble}" method="post">
                <input type="submit" value="Supprimer">
            </form>

</#if>
<p>Liste des appartements liés à l'immeuble</p>
<#if appartements?has_content>
    <#list appartements as appartement>
        <li><a href="/appartement/${appartement.idAppartement}">${appartement.idAppartement}</a> ${appartement.etage} ${appartement.superficie} ${appartement.idImmeuble}</li>
    </#list>
<#else>
    <li>(Pas d'appartement)</li>
</#if>
<#if role == 1>

<p>Ajouter un appartement à cette immeuble</p>
  

    <form action="/ajouterAppartement/${immeuble.idImmeuble}" method="POST">
    <div id="etage_container">
        <input name="etage" id="nom" class="etage" type="text" placeholder="etage" value="">
    </div>
    <div id="superficie_container">
        <input name="superficie" id="superficie" class="initial" type="text" placeholder="superficie" value="">
    </div>
   
    <input type="submit" value="Ajouter">
</form>
</#if>
<ul>
<li><a href="/immeuble">Main Immeuble</a></li>

    <li><a href="/main">Page d'accueil</a></li>
    </ul>
