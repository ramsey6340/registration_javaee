<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Veuillez vous connecté</h1>
    <p class="error-message">
        <c:out value="${errorMessage}"/>
    </p>
    <div class="form-login">
        <div class="head-login"><p>Connexion</p></div>
        <br>
        <form action="login" method="post">!
            <div class="form-input">
                <label for="login">Pseudo</label>
                <input type="text" id="login" name="login">
            </div>

           <div class="form-input">
               <label for="password">Mot de passe</label>
               <input type="password" id="password" name="password">
           </div>

            <div class="submit-button">
                <input type="submit" value="Connexion">
            </div>
            <br>
        </form>
    </div>
<p style="text-align: center"><a href="registration" class="go-page">S'inscrire</a></p>
</body>
</html>
