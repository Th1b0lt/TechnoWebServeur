<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

    <h1>List of Personnes</h1>
    <ul>
        <#list personne as personne >
            <li>${personne.id_personne} - ${personne.num_tel_pers} - ${personne.nom_pers} ${personne.prenom_pers}</li>
        </#list>
         <#list personnes as personne >
            <li>${personne.getIdPersonne()} - ${personne.getNumeroDeTelephone()} - ${personne.getNom()} ${personne.getPrenom()}</li>
        </#list>
    </ul>
</body>
