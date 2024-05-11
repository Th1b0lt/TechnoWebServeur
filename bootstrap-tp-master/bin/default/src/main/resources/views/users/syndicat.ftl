<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">
<h1>Bienvenue sur la page gérant les syndicat affiliés à notre entreprise</h1>
    <br>
    <br>

  <#list syndicats as syndicat>
    <li>${syndicat.idSyndicat} - ${syndicat.name} ${syndicat.adresse} 
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

        
  

<li><a href="/modifsyndicat">Modif syndicat(admin only)</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>