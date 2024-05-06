<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

    <h1>List of Personnes</h1>
    <ul>
        <#list personnes as personne >
            <li>${personne.idPersonne} - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
        </#list>
        
    </ul>
</body>
