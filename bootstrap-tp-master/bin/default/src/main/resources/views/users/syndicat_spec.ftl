<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">


<h1>Page representative de l'syndicat ${syndicat.idSyndicat}</h1>
           
<p>Donnée syndicat : ${syndicat.name} ${syndicat.adresse} 
        <#if syndicat.personneReference??>
            ${syndicat.personneReference}
        <#else>
            (Personne de référence non définie)
        </#if>
        <#if syndicat.numeroDeTelephone??>
            ${syndicat.numeroDeTelephone}
        <#else>
            (Numéro de téléphone non défini)
        </#if>
        <#if syndicat.adresseEmail??>
            ${syndicat.adresseEmail}
        <#else>
            (Adresse e-mail non définie)
        </#if></p>
<p>Mettre a jour votre syndicat</p>
<form action="/majSyndicat/${syndicat.idSyndicat}/name" method="POST">
    <div id="name_containe">
        <input name="name" id="name" class="initial" type="text" placeholder="name" value="">
    </div>
    <input type="submit" value="Modfier">

</form>
<form action="/majSyndicat/${syndicat.idSyndicat}/adresse" method="POST">
    <div id="adresse_container">
        <input name="adresse" id="adresse" class="initial" type="text" placeholder="adresse" value="">
    </div>
    <input type="submit" value="Modfier">

</form>

<form action="/majSyndicat/${syndicat.idSyndicat}/personneReference" method="POST">

    <div id="personneReference container">
        <input name="personneReference" id="personneReference" class="initial" type="text" placeholder="personneReference" value="">
    </div>
    <input type="submit" value="Modfier">
</form>

<form action="/majSyndicat/${syndicat.idSyndicat}/numeroDeTelephone" method="POST">

    <div id="numeroDeTelephone container">
        <input name="numeroDeTelephone" id="numeroDeTelephone" class="initial" type="text" placeholder="numeroDeTelephone" value="">
    </div>
    <input type="submit" value="Modfier">
</form>

<form action="/majSyndicat/${syndicat.idSyndicat}/adresseEmail" method="POST">

    <div id="adresseEmail container">
        <input name="adresseEmail" id="adresseEmail" class="initial" type="text" placeholder="adresseEmail" value="">
    </div>
    <input type="submit" value="Modfier">
</form>
    <br>
    <br>


    <p>Suppression de l'syndicat(seulement pour les admins)</p>

        <form action="/supprimerSyndicat/ ${syndicat.idSyndicat}" method="post">
            <input type="submit" value="Supprimer">
        </form>
<p>Liste des immeuble liés au syndicat</p>
<#if immeubles?has_content>
    <#list immeubles as immeuble>
        <li><a href="/immeuble/${immeuble.idImmeuble}">${immeuble.idImmeuble}</a> - ${immeuble.nom} ${immeuble.idSyndicat} ${immeuble.adresse}</li>
    </#list>
<#else>
    <li>(Pas d'immeuble)</li>
</#if>




<ul>
<li><a href="/syndicat">Main syndicat</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>