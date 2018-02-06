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
* ajout des dépendances (avec le **scope** *test*) suivantes pour le lancement des tests et les assertions
  * junit:junit:4.12
  * org.assertj:assertj-core:3.9.0
* ajout d'une classe de test : **GildedRoseTest.java** dans le même package que le code `main`
* ajout d'un premier cas de test : un objet de qualité 10 doit valoir, après un jour, 9 (se dégrade de 1 par jour)
* commit
* vérification de la mise à jour du badge de couverture
* ajout de tous les tests nécessaires à la couverture complète du code, avec autant de commit que nécessaires

# TP 3

Le but de cette phase est de modifier le code des fichiers **GildedRose.java** et **Item.java** afin de rendre le code évolutif.  
On appelle cette phase le *refactoring*.  
Maintenant que les tests sont en place, aucun bug ne devrait survenir car toutes les règles métiers sont testées.

Vous devriez arriver dans un état où l'équipe est satifaite du code produit, code que vous pensez lisible par d'autres personnes, extérieures à l'équipe.

**Points de vigilance:**
* Cette dernière étant difficile à tester, il est déconseillé de placer plus d'une ligne de code dans la **méthode main**. Exemple :
```java
public static void main(String[] args) {
    new MyObject().myMethod();
}
```
* En Java la longueur (en ligne de code) des blocs est importante, on considère comme un mauvais design d'avoir:
  * des classes de plus de 100 lignes
  * des méthodes de plus de 10 lignes

# TP 4 (Optionel)

*Cette partie sera notée sur 4, si elle n'est pas réalisée, vous ne serez noté que sur 16.*  
Dans cette partie, vous aller mettre en place un ensemble de webservices permettant d'interragir avec le code métier de la taverne.  

Il s'agira de mettre en place, en respectant le principe d'architecture hexagonale vu en cours, l'adapteur nécessaire pour l'exposition de services HTTP/json.

Pour ce faire:
* ajouter les dépendances
  * org.springframework.boot:spring-boot-starter-web:1.5.10.RELEASE
  * com.fasterxml.jackson.core:jackson-databind:2.9.4
* En vous inspirant du projet [Exemple Spring-Web](../../samples/springweb), écrire les services suivants:
  * Consultation des objets pouvant être vendus (avec prix, qualité et quantité)
  * Achat d'un objet (va provoquer une baisse de la quantité, jusqu'à la disparition de l'objet dans le catalogue)
  * Approvisionnement d'un objet (nom, prix, qualité et quantité)
* La modification "journalière" (#updateQuality()) devra être appelée automatiquement toutes les 15 minutes.
  * Comme vu en cours, il faudra utiliser les threads, par ex:
  ```java
  import java.util.concurrent.Executors;
  import java.util.concurrent.ScheduledExecutorService;
  import java.util.concurrent.TimeUnit;

  ...
  
  Runnable updateQualityTask = ...;
  ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
  scheduledExecutorService.scheduleAtFixedRate(updateQualityTask, 0L, 15L, TimeUnit.MINUTES);
  ```
  
La documentation des différentes dépendences est disponible ici :
  * [Spring-Web](https://docs.spring.io/spring/docs/5.0.4.BUILD-SNAPSHOT/spring-framework-reference/web.html#spring-web)
  * [Spring-Boot](https://spring.io/guides/gs/spring-boot/)
  * [Jackson](https://github.com/FasterXML/jackson-docs/wiki)


