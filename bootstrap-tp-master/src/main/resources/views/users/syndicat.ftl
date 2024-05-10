<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">
<h1>Bienvenue sur la page gérant les syndicat affiliés à notre entreprise</h1>
    <br>
    <br>

    <p>Liste des syndicat appartenant à notre groupe</p>
        <#list syndicats as syndicat >
            <li>${syndicat.idSyndicat} - ${syndicat.name} ${syndicat.adresse} ${syndicat.personneReference} ${syndicat.numeroDeTelephone} ${syndicat.adresseEmail}</li>
        </#list>

        
  

<li><a href="/modifsyndicat">Modif syndicat(admin only)</a></li>

<li><a href="/main">Page d'accueil</a></li>
</ul>
</body>