<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">

<h1>Accueil</h1>
<div class="container">
    <h2>BIENVENUE VOUS POUVEZ NAVIGUER DEPUIS ICI</h1>
    <ul>
        <li><a href="/personne">Page personne</a></li>
        <li><a href="/appartement">Page appartement</a></li>
        <li><a href="/immeuble">Page immeuble</a></li>
        <li><a href="/login">Page de connexion</a></li>
        <#if role == 1>
        <li><a href="/register">Page d'ajout d'admin</a></li>
        </#if>
        <li><a href="/syndicat">Page des syndicats</a></li>

    </ul>
</div>

