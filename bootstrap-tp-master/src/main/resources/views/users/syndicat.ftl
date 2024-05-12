<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">
<h1>Bienvenue sur la page gérant les syndicats affiliés à notre entreprise</h1>
    <br>
    <br>

  <#list syndicats as syndicat>
    <li><a href="/syndicat/${syndicat.idSyndicat}">${syndicat.idSyndicat}</a> - ${syndicat.name} ${syndicat.adresse} 
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
        </#if>
    </li>
</#list>

        
  
<ul>
<#if role == 1>

<li><a href="/modifsyndicat">Modif syndicat(admin only)</a></li>
</#if>
<li><a href="/main">Page d'accueil</a></li>
</ul>
