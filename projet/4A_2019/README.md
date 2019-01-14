# Architecture logicielle : TP / Projet 4A 2019

# Notation :
Le projet est à finir pour la date du 27 Février (23h59).
* Tous commit après cette date sera pénalisé (-1point / commit supplémentaire)
* L'historique GIT
  * celui-ci doit être lisible par une personne extérieure au projet afin de comprendre l'intention derrière chaque modification
  * **pas de message de commit type :**
    * fix, fix de fix, correction
    * plusieurs fois le même message de commit
    * ajouts des fichiers file1, file2
  * le message de commit doit répondre à la question : **Pourquoi cette modification a-t-elle été faite ?**
  * 2 commits non consécutifs par personnes
    * les étudiants n'ayant pas 2 commits non consécutifs **à leur nom** auront 0
* Les tests
  * doivent utiliser le framework **junit-jupiter**
  * doivent couvrir à 100% le code métier (hors adapteur web-service)
  * les méthodes de test doivent systématiquement comporter des vérifications (assertions)
  * il ne doit pas y avoir de copié/collé, si plusieurs méthodes se ressemblent, il est attendu d'utiliser des tests paramétrés
* Le code principal
  * sera essentiellement considéré le style :
    * longueur des classes
    * longueur des méthodes
    * nommage compréhensible des variables, champs, méthodes, classes
    * pas d'objets anémiques (anti-pattern)
    * pas de type de retour void (intestable)
    * découplage entre les objets (facilité à tester les objets individuellement)
    * lisibilité du code: **Peut-on comprendre sans lire l'intérieur des méthodes ce que fait le programme ?**
* Bonus :
  * Si les services HTTP/json fonctionnent (testable manuellement avec un navigateur web) = 2 points
  * Si les services HTTP/json sont testés (avec des tests d'intégrations) = 2 point

# TP 1
#### Création d'un dépôt Git sur GitHub
* Création des groupes (de 4)

* création d'un compte GitHub
* création d'un nouveau dépôt **supermarket-receipt**
  * avec README.md
  * avec .gitignore pour Maven
  * avec Licence Apache 2.0
  * envoi de l'adresse du dépot par mail à ledoyen.loic@gmail.com
* configuration Git avec clé SSH https://help.github.com/articles/connecting-to-github-with-ssh/
* récupération du dépôt en local (git clone)

#### Création du projet
* ajouter un fichier .editorconfig spécifiant (pour tous les fichiers)
  * caractère de fin de ligne : LF (unix)
  * encodage: UTF-8
  * fichiers doivent finir par une ligne vide
  * l'indentation est de 4 espaces
* commiter cet unique fichier avec le message "Setup project layout"
* initialisation avec Maven archetype (utiliser l'archetype `org.apache.maven.archetypes:maven-archetype-simple`)
* commit de ces changements

#### Mise en place de l'intégration continue
* création d'un compte TravisCI
* ajout du fichier .travis.yml configuré pour un build Maven https://docs.travis-ci.com/user/languages/java/
* commit de ces changements
* attente du premier build
* ajout du badge build dans le fichier README.md
* modification du commit précédent intégrant la modification du fichier README.md

#### Mise à jour du support des tests
> Pour chercher la référence vers une librairie accessible par Maven, utiliser https://mvnrepository.com
* remplacer la dépendance **JUnit3** par **JUnit5**
* ajouter la dépendance vers la librairie d'assertion **assertj-core**
* Faire les changements nécessaires dans le code de test
  * remplacer l'héritage de la classe `TestCase` par l'annotation `@Test`
  * utiliser l'API d'AssertJ pour la première assertion : `Assertions.assertThat(true).isTrue()`
* commiter ces changements

#### Mise en place de la couverture du code
* création d'un compte sur https://codecov.io
* Modifier le pom.xml afin de générer un rapport de couverture avec cobertura
* modifier le descripteur Travis pour envoyer ce rapport à CodeCov
* commiter ces changements
* vérifier la prise en compte dans CodeCov
* ajouter du badge CodeCov dans le readme
* modifier le commit précédent en ajoutant du badge de couverture

# TP 2

Nous allons dans cette partie faire le *kata* **recu de supermarché** (SupermarketReceipt) dont le pitch est ici : https://github.com/emilybache/SupermarketReceipt-Refactoring-Kata

#### Récupération du code initial du kata SupermarketReceipt
* copier les classes https://github.com/emilybache/SupermarketReceipt-Refactoring-Kata/tree/master/java
* changer les noms des packages en adéquation avec celui du projet: `fr.esiea`
* lancer les tests avec Maven : `mvn test`
* commiter ces changements

#### Développement des Tests Unitaires pour tester le code à 100%
* compléter le premier cas de test : combien coute 2.5 kg de pommes à 1.99€ compte tenu du fait qu'une réduction est en court sur les brosses à dents.
* commit
* vérification de la mise à jour du badge de couverture
* ajout de tous les tests nécessaires à la couverture complète du code, avec autant de commit que nécessaires

# TP 3

#### Refactoring
Le but de cette phase est de modifier le code *principal* afin de rendre le code évolutif.  
On appelle cette phase le *refactoring*.  
Maintenant que les tests sont en place, aucun bug ne devrait survenir car toutes les règles métiers sont testées.
Entre autres, la méthode `ShoppingCart#handleOffers` doit évoluer afin de respecter notamment le second principe SOLID (open/close).
Vous devriez arriver dans un état où l'équipe est satifaite du code produit, code que vous pensez lisible par d'autres personnes, extérieures à l'équipe.

#### Nouvelle fonctionnalité
Il s'agit ici d'ajouter la fonctionnalité décrite dans le pitch (https://github.com/emilybache/SupermarketReceipt-Refactoring-Kata#new-feature-discounted-bundles):
Cette fonctionnalité introduit un nouveau type d'offre, des offres ne s'appliquant que si un ensemble de produits a été acheté.
Il est recommandé ici de travailler en TDD :
* écrire un nouveau test (red)
* écrire le code correspondant (green)
* refactorer si nécessaire (blue)
* recommencer

# TP 4

*Cette partie sera notée sur 4 (2 points pour les web-services, 2 points pour les tests d'intégrtion), si elle n'est pas réalisée, vous ne serez noté que sur 16.*  
Dans cette partie, vous aller mettre en place un ensemble de webservices permettant d'interragir avec le code métier.  

Il s'agira de mettre en place, en respectant le principe d'architecture hexagonale vu en cours, les adapteurs nécessaires pour l'exposition de services HTTP/json.

Je vous recommande d'utiliser Spring-Boot (framework pour lequel je vous fournis des exemples), cependant, vous pouvez utiliser le framework de votre choix pour cette partie.
**Il faut néanmoins que l'application puisse être démarée avec une classe main.**

Pour ce faire:
* ajouter les dépendances (différentes si vous optez pour un autre framework)
  * org.springframework.boot:spring-boot-starter-web:1.5.10.RELEASE
  * com.fasterxml.jackson.core:jackson-databind:2.9.4
* En vous inspirant du projet [Exemple Spring-Web](../../samples/springweb), écrire les services suivants:
  * Consultation des produits pouvant être vendus (avec prix, qualité et quantité)
  * Ajout / suppression d'un produit dans le catalogue
  * Activation / désactivation d'une offre spéciale
  * Ajout / suppression d'un produit dans le panier d'un client (il peut donc y a voir **n** clients)
  * Passage en caisse d'un client (calcul du prix de son panier en tenant compte des offres **en cours**)
  * Passage en caisse d'un client (mode texte), le service ne retournera pas du JSON mais la note formattée par la classe `ReceiptPrinter` 

  
La documentation des différentes dépendances est disponible ici (y compris pour faire des tests d'intégration de l'adapteur HTTP) :
  * [Spring-Web](https://docs.spring.io/spring/docs/5.0.4.BUILD-SNAPSHOT/spring-framework-reference/web.html#spring-web)
  * [Spring-Boot](https://spring.io/guides/gs/spring-boot/)
  * [Jackson](https://github.com/FasterXML/jackson-docs/wiki)
