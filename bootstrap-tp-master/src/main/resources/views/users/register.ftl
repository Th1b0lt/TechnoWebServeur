<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">

<h1>CREER UN COMPTE (seulement pour les admins)</h1>

 <form action="/register" method="POST">
       <div class="Username_container">
            <input name="username" id="username" class="initial" type="text" placeholder="Username :xX_PaulB0cuseDu63_Xx" value="">
        </div>
        <div class="Password_container">
            <input name="password" id="password" class="initial" type="password" placeholder="Password" value="">
        </div>
        <div id="error_log"></div>
        <div class="Submit_container">
            <input class="initial" type="submit" value="Valider">
        </div>

    </form>