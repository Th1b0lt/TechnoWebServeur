<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

    <h1>List of Personnes</h1>
    <ul>
        <#list personnes as personne >
            <li>${personne.idPersonne} - ${personne.numeroDeTelephone} ${personne.nom} ${personne.prenom}</li>
        </#list>
           <div class="fond_form">

    <form action="/ajouterPersonne" method="POST">
        <div id="Lastname_container">
            <input name="lastname" id="lastname" class="initial" type="text" placeholder="Last Name" value="">
        </div>
        <div id="Firstname_container">
            <input name="firstname" id="firstname" class="initial" type="text" placeholder="First Name" value="">
        </div>
         <div id="Telephone container">
            <input name="Telephone" id="firstname" class="initial" type="text" placeholder="Telephone" value="">
        </div>
        <input type="submit" value="Ajouter">

    </form>
    </div>
        <form action="/supprimerPersonne" method="post">
            <#list personnes as personne >
                <input type="hidden" name="idPersonne" value="${personne.idPersonne}">
            </#list>
            <input type="submit" value="Supprimer">
        </form>
    </ul>
</body>