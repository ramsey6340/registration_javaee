# Register-you
Il s'agit d'une simple application qui permet de s'inscrire et de se connecté pour voir la liste des autres personnes inscrite.

## Etape de Configuration

L'application a été crée avec l'IDE IntelliJ IDEA, ces etapes de configuration sont enumeré en conciderant la configuration de IntelliJ.

1- configuration de la JSTL: 
Le repertoire src\main\webapp\WEB-INF\lib\taglibs-standard* correspondent aux librairies pour la JSTL, cependant, il est necessaire d'ajour une troisième, pour cela suivez les étapes suivante:
    a- cliquer sur le nom du projet puis sur la touche F4 pour ouvrir la structure du projet.
    b- cliquer sur le symbole "+" pour ajouter une librairie.
    c- Dans la fenêtre "Choose Librairies" cliquer sur "New Library" et choisissez "From Maven..." .
    d- Dans la fenêtre de téléchargement coller la ligne suivante: "javax.servlet:jstl:1.2"
    e- Valider l'ajout en cliquant à chaque fois sur OK

2- Configuration du serveur Tomcat:
Pour le bon fonctionnement de l'application la configuration d'un serveur est necessaire.
    a- Dans la barre de menu cliquer sur "Run" puis sur "Edit Configuration..." .
    b- Dans la fenêtre "Run/Debug Configurations" cliquer sur "+"
    c- Defiler vers le bas puis choisissez "Tomcat Server > Local"
    d- Dans l'onglet "Deployment" ajouter un nouveau artifact en cliquant sur "+" puis "Artifact..."
    e- Cliquer sur OK

3- Configuration de la base de données:
L'application utilise la base de données MySQL pour enregistrer l'inscription de la personne.
Vous pouvez tout simplement utiliser les logiciels comme xamp ou wamp ou mamp.