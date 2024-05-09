<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">
<h1>Liste des users</h1>
<ul>
    <#list users as user>
        <li>${user.username} ${user.passwordHash}</li>
    </#list>
</ul>

</body>
