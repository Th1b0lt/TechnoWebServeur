<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">


  <p>Ajout d'un Syndicat (seulement pour les admins)</p>

    <form id="syndicat_form" action="/ajouterSyndicat" method="POST">
    <div id="name_container">
        <input name="name" id="name" class="initial" type="text" placeholder="name" value="">
    </div>
    <div id="adresse_container">
        <input name="adresse" id="adresse" class="initial" type="text" placeholder="adresse" value="">
    </div>
    <div id="personneReference_container">
        <input name="personneReference" id="personneReference" class="initial" type="text" placeholder="personneReference" value="">
    </div>
    <div id="numeroDeTelephone_container">
        <input name="numeroDeTelephone" id="numeroDeTelephone" class="initial" type="text" placeholder="numeroDeTelephone" value="">
    </div>
    <div id="adresseEmail_container">
        <input name="adresseEmail" id="adresseEmail" class="initial" type="text" placeholder="adresseEmail" value="">
    </div>
    <input type="submit" value="Ajouter" onclick="return validateForm()">
</form>

<script>
    function validateForm() {
        var name = document.getElementById("name").value;
        var adresse = document.getElementById("adresse").value;
        var personneReference = document.getElementById("personneReference").value;
        var numeroDeTelephone = document.getElementById("numeroDeTelephone").value;
        var adresseEmail = document.getElementById("adresseEmail").value;

        if (name == "" || adresse == "" || personneReference == "" || numeroDeTelephone == "" || adresseEmail == "") {
            alert("Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }
</script>
    <br>
    <br>


    <p>Suppression d'un syndicat(seulement pour les admins)</p>

        <form action="/supprimerSyndicat" method="post">
            <input type="text" name="id" placeholder="idSyndicat" value="">
            <input type="submit" value="Supprimer">
        </form>

<a href="/main">Page d'accueil</a>
</body>