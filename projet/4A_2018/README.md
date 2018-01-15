# Architecture logicielle : TP / Projet 4A 2018

# TP 1
#### Création d'un dépôt Git sur GitHub
* Création des groupes (de 5)

* création d'un compte GitHub
* création d'un nouveau dépôt **gilded-rose**
  * avec README.md
  * avec .gitignore pour Maven
  * avec Licence Apache 2.0
  * envoi de l'adresse du dépot par mail à ledoyen.loic@gmail.com
* configuration Git avec clé SSH https://help.github.com/articles/connecting-to-github-with-ssh/
* récupération du dépôt en local (git clone)

#### Création du projet
* initialisation avec Maven archetype
* commit de ces changements

#### Mise en place de l'intégration continue
* création d'un compte TravisCI
* ajout du fichier .travis.yml avec le build Maven https://docs.travis-ci.com/user/languages/java/
* commit de ces changements
* attente du premier build
* ajout du badge build dans le fichier README.md
* modification du commit précédent intégrant la modification du fichier README.md

#### Mise en place de la couverture du code
* création d'un compte Coveralls.io
* modification du pom.xml pour intégrer le plugin coveralls https://github.com/trautonen/coveralls-maven-plugin
* commit de ces changements
* vérification de la prise en compte dans Coveralls
* ajout du badge coverage dans le readme
* modification du commit précédent intégrant l'ajout du badge de couverture

# TP 2

Nous allons dans cette partie faire l'exercice GildedRose dont le pitch est ici : https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/master/GildedRoseRequirements.txt

#### Récupération du code initial du kata Gilded Rose
* Copie des deux classes `main` du projet https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/master/Java
* Changement du noms des packages en adéquation avec celui du projet: `fr.esiea`
* compilation & test : `mvn test`
* commit

#### Développement des Tests Unitaires pour tester à 100% GildedRose.java
* ajout des dépendances suivantes pour le lancement des tests et les assertions
  * junit:junit:4.12
  * org.assertj:assertj-core:3.9.0
* ajout d'une classe de test : **GildedRoseTest.java** dans le même package que le code `main`
* ajout d'un premier cas de test : un objet de qualité 10 doit valoir, après un jour, 9 (se dégrade de 1 par jour)
* commit
* vérification de la mise à jour du badge de couverture
* ajout de tous les tests nécessaires à la couverture complète du code

# TP 3

Le but de cette phase est de modifier le code des fichiers **GildedRose.java** et **Item.java** afin de rendre le code évolutif.  
On appelle cette phase le *refactoring*.  
Maintenant que les tests sont en place, aucun bug ne devrait survenir car toutes les règles métiers sont testées.

Vous devriez arriver dans un état où l'équipe est satifaite du code produit, code que vous pensez lisible par d'autres personnes, extérieures à l'équipe.

