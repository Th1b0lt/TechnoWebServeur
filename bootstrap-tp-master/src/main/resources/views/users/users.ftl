<#ftl encoding="utf-8">
<link rel="stylesheet" href="style.css">
<body xmlns="http://www.w3.org/1999/html">
<h1>Liste des users</h1>
<ul>
    <#list users as user>
        <li>${user.username} </li>
    </#list>
</ul>



<a href="/main">Page d'accueil</a>

