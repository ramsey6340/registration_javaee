<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <nav>
        <div class="deconnexion">
            <a href="logout">
                <input type="button" value="Deconnexion"  >
            </a>
        </div>
    </nav>
</header>
    <section class="home-page">
        <c:if test="${!empty sessionScope.currentUser.getNom()}">
            <p>Bienvenue <span class="current-user">${sessionScope.currentUser.getPrenom()} ${sessionScope.currentUser.getNom()}</span></p>
        </c:if>
        <table>
            <thead>
                <td>Prenom</td>
                <td>Nom</td>
                <td>Pseudo</td>
                <td>Email</td>
                <td>Password</td>
            </thead>
            <c:forEach items="${users}" var="user">
                <tbody>
                   <tr>
                       <td>${user.prenom}</td>
                       <td>${user.nom}</td>
                       <td>${user.pseudo}</td>
                       <td>${user.email}</td>
                       <td>${user.password}</td>
                   </tr>
                </tbody>
            </c:forEach>
            <tfoot>
                <td>Prenom</td>
                <td>Nom</td>
                <td>Pseudo</td>
                <td>Email</td>
                <td>Password</td>
            </tfoot>
        </table>
    </section>
</body>
</html>
