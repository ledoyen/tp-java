# Architecture logicielle
## TP / Projet 4A 2018

#### Création d'un dépôt Git sur GitHub
* Création des groupes (de 5)
  * CFA : 5 groupes de 5, 1 groupe de 6

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
