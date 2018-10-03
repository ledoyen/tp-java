# Sujet de Projet POO 3A 2018

Date de rendu : 27 novembre 2018.

Le projet sera noté et reflètera les connaissances acquises durant le cours et les TPs (module INF3034).

## Objectifs

* Ecrire un programme de gestion d'un restaurant de Bagel utilisable en ligne de commande
* Ecrire une librairie pour [historiser les évènements](https://fr.wikipedia.org/wiki/Historique_(informatique))
* Ecrire une librairie pour tester du code
* Utiliser ces deux librairies pour produire un projet [maintenable](https://fr.wikipedia.org/wiki/Maintenabilité)

* Scripter le lancement (bash unix) :
  * de la compilation
  * du lancement des tests
  * du lancement du programme de gestion

## Structure du projet

Le projet devra être rendu sur Moodle avant la date 27 novembre 2018.
Le projet devra être envoyé sous la forme d'une archive `tar.gz` contenant un fichier **README.md** avec les noms complets des étudiants ayant participés.
L'archive ne contiendra que des :
 * répertoires
 * fichiers `.java`
 * fichiers `.sh`
 * fichiers `.md`

Tous fichier supplémentaire présent et non utilisé par le code (fichiers liés aux IDE, fichiers cachés notamment sur les Macs, classes compilées, etc.) entrainera une **baisse de la note finale**.

Le contenu de l'archive sera structuré comme suit :
```
.
+-- logger/
|  +-- src/
|  |   +-- logger/
|  |       +-- (*.java)
|  +-- bin/
+-- testframework/
|  +-- src/
|  |   +-- test/
|  |       +-- (*.java)
|  +-- bin/
+-- restaurant/
|  +-- src/
|  |   +-- restaurant/
|  |       +-- (*.java)
|  +-- bin/
+-- compile.sh
+-- test.sh
+-- launch.sh
+-- README.md
```

Le fichier **compile.sh** lancera la compilation de tous les fichiers java des différents projets.
Le fichier **test.sh** lancera les tests du projet **restaurant**.
Le fichier **launch.sh** lancera l'application **restaurant**.

## Notation

La note du projet sera découpée suivant le respect des critères suivant :
* Critères absolus (dont le non respect entrainera la note de 0)
  * Le projet est rendu dans les temps, **aucune négociation ne sera possible**
  * Le projet respecte la structure de fichier demandée
  * Les projets de code compilent (les deux librairies + programme de gestion)

* Autres critères
  * La documentation du projet (README.md) au format Markdown précise
    * les subtilités des programmes
    * les stratégies de résolution des problèmes rencontrés
    * la documentation doit être succinte et précise
      * par ex : répéter les noms des classes ou des méthodes déjà présentes dans le code (sans valeur ajoutée, pour meubler) entrainera une baisse de la note finale
  * Les noms des classes, méthodes et variables respecte les conventions vues en cours / TP et témoigne de la fonction de l'entité
      * par ex: il est impossible de dire à quoi servent la variable `x` ou la classe `Ex6`, contrairement à `arrayIndex` pour un tableau ou `Logger`
  * Les classes ne font pas plus de 100 lignes de code, et les méthodes pas plus de 15 lignes
  * Le couplage entre les objets doit être minimal, à cette fin des interfaces devront être utilisées intelligemment
  * Il ne doit y avoir aucun appel à `System.out.println` dans tout le code, sauf dans le projet **logger** (où il ne devrait y en avoir qu'un seul)
  * Un usage intelligent du `logger` dans le projet de gestion afin de logger :
    * dans la console, uniquement les retours à destination de l'utilisateur sans mise en forme
    * dans un fichier
      * l'intégralité des évènements (entrées utilisateur et sortie du programme)
      * Au format suivant : DATE(YYYY-MM-DD HH:SS:sss) CATEGORY(INPUT / OUTPUT / PROGRAM) LEVEL(INFO / ERROR) MESSAGE
  * Le projet de gestion fonctionne sans bug (par ex: entrer une lettre quand on demande un chiffre ne devrait pas provoquer l'arrêt du programme) et son usage est intuitif (on attend que l'utilisateur soit guidé dans ce qu'il est possible de faire, dans ses erreurs, etc.)
  * Les tests valident le fonctionnement correcte des fonctions métiers
      * c'est à dire qu'en changeant le code d'une fonction, au moins un test devrait échouer

Les détails comme la gestion des utilisateurs, l'affichage de banière, etc. ne seront pas pris en compte dans la note.

## Détail des attendus pour chaque projet

### Gestion d'un restaurant de Bagel

L'application utilisera le terminal comme interface homme-machine.
Aucune interface graphique n'est demandée.

Voici les opérations qui devront être disponibles :
* quitter le programme
* afficher la liste des opérations disponibles
* ajouter un produit à la vente (nom, prix, stock)
* afficher la liste des produits à la vente (ainsi que le nombre restant)
* ouvrir la note d'un client (il peut y avoir plusieurs clients dans le restaurant au même moment)
* enregistrer la vente d'un produit sur la note d'un client
* clôturer la note d'un client en affichant :
  * le prix de chaque produit HT (hors-taxe)
  * le prix total HT
  * la TVA (10%)
  * le prix TTC
* afficher les données comptables:
  * total des rentrées d'argent
  * total de la TVA facturée

Règles supplémentaires :
* quand un produit est en rupture de stock, il doit être retiré de la vente
* au moment de la clôture d'une note, le vendeur peut choisir d'offrir une remise de 10%
  * si la remise est appliquée, elle devra apparaître sur la note
* au lancement du programme la boutique contient :
  * 20 bagels
  * 10 burgers
  * 30 smoothies
  * du café (en quantité illimitée)

Conseils quand à la structure du code :
* penser à tester chaque fonction de manière individuelle
  * le code d'initialisation doit pouvoir être appelé indépendamment afin que les tests ne soient pas pollués par cette donnée arbitraire
  * il ne doit pas être nécessaire de lancer l'application complète pour vérifier que le total de la note d'un client ayant commandé un seul produit est le prix de ce produit
* utiliser une interface pour décrire un contrat partagé par plusieurs objets (par example le contrat `Operation` commun à toutes les opérations disponibles en ligne de commande)
* séparer les interactions avec l'utilisateur (input / output) du code métier qui réalise les opérations
  * il ne doit pas être nécessaire de changer le code métier pour l'utiliser dans une API HTTP pour un site web
* à quelques exceptions, toutes les méthodes devraient avoir un type de retour (cad autre que `void`)
* quand du code est ajouté, penser à ce à quoi il va servir
  * par ex : demander le prix ou le stock d'un produit pour l'enlever des produits en vente ne sert à rien, seul son nom va permettre de procéder à la suppression


Des tests seront écrits pour chaque opération métier afin de vérifier avec du code qu'il n'y a pas de bug.
Par exemple en considérant la classe ci-dessous :
```java
public class Calculator {

    public int remainder(int dividend, int divisor) {
        return dividend % divisor;
    }

}
```

Voici la classe de test qui pourrait être écrite :
```java
public class CalculatorTest {

    private Calculator calculator = new Calculator();

    public void zero_remainder() {
        int quotient = calculator.remainder(9, 3);
        if(quotient != 0) {
            throw new AssertionError("Quotient of 9 / 3 should be 0 but was " + quotient);
        }
    }

    public void non_zero_remainder() {
        int quotient = calculator.remainder(11, 3);
        if(quotient != 2) {
            throw new AssertionError("Quotient of 11 / 3 should be 2 but was " + quotient);
        }
    }

    public void division_by_zero() {
        try {
            calculator.remainder(10, 0);
            throw new AssertionError("Division by 0 should have thrown an ArithmeticException");
        } catch(ArithmeticException e) {
            // normal case
        }
    }
}
```


### Logger

Le logger sera inspiré en majeure partie de ce qui aura été fait en TP (séance 3).

L'interface `Logger` devra avoir 2 méthodes :
```java
void info(String category, String message);
void error(String category, String message);
```

L'argument `category` correspondra à la nature du message :
* INPUT pour les données entrées par l'utilisateur
* OUTPUT pour les messages émis par le programme à destination de l'utilisateur
* PROGRAM, pour les informations internes au programmes

Une instance d'objet respectant le contrat `Logger` sera créée par composition en utilisant la classe `LoggerFactory`.
Par example :
```java
Logger logger = LoggerFactory.dateAndTimeLogger(
    LoggerFactory.compositeLogger(
        LoggerFactory.withoutContextLogger(LoggerFactory.consoleLogger()),
        LoggerFactory.fileLogger("restaurant.log")
    )
)
```
Dans une même classe, si nécessaire, une seule instance de `Logger` devrait être utilisée.

### Librairie de tests

La librairie de test devra proposer une classe principale contenant une méthode `main` : **RunTest.java**
Les tests seront lancés en utilisant la commande :
`java -classpath <chemins vers les fichiers compilés, séparés par le caractère ':'> RunTest <chemins qualifiés des classes de test, séparés par des espaces>`

Le chemin qualifié d'une classe correspond à `<package>.<className>`.

Le programme devra afficher dans la console :
* Pour chaque test, l'erreur (si le test est en échec)
* un résumé :
  * une ligne par test contenant : nom de la classe . nom de la méthode : statut (OK ou KO) durée (en millisecondes)
  * une ligne résumant le tout: pourcentage de réussite (nombre de test OK / nombre total) durée totale (en millisecondes)

Le paramètre `args` de la méthode `main` prendra alors les valeurs des paramètres spécifiés en ligne de commande (ceux séparés par des espaces).

Dans ce projet, les objets manipulés vont représenter des notions du langage.
Ainsi :
* un objet de type `Class` représente une classe
* un objet de type `Method` représente une méthode

De cette manière le code de la librairie de test pourra lister les méthodes d'une classe sans connaître à l'avance la classe en question.

Voici un exemple de code pour transformer un objet de type `String` en objet de type `Class` :
```java
String className = "logger.SampleTest";
try {
    Class<?> clazz = Class.forName(className); // la variable ici ne peut s'appeler class, car c'est un mot clé du langage
} catch (ClassNotFoundException e) {
    // gestion de l'erreur
    ...
}
```

Voici un exemple de code listant les méthodes d'une classe :
```java
import java.lang.reflect.Method;

...

Class<?> clazz = ...

for(Method method : clazz.getDeclaredMethods()) {
    System.out.println(method.getName());
}
```

Enfin, voici un exemple de code pour exécuter le code pointé par un objet de type `Method` :
```java
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

...

Object instance = null;
try {
    instance = clazz.newInstance();
} catch (InstantiationException | IllegalAccessException e) {
    // gestion de l'erreur
    ...
}

Method method = ...

try {
    method.invoke(instance);
} catch (InvocationTargetException | IllegalAccessException e) {
    // gestion de l'erreur
    ...
}
```

