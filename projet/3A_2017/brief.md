# Sujet de Projet POO 3A 2017

Date de rendu : 15 novembre 2017.

Le projet sera noté et reflètera les connaissances acquises durant le cours et les TPs.


## Objectifs

* Ecrire une librairie pour [historiser les évènements](https://fr.wikipedia.org/wiki/Historique_(informatique))
* Ecrire une librairie pour tester du code
* Utiliser ces deux librairies pour construire un projet simple de gestion de compte en banque
* Scripter le lancement (bash unix) :
  * de la compilation
  * du lancement des tests
  * du lancement du programme de gestion de compte en banque


## Structure du projet

Le projet devra être envoyé par mail avant la date du 15 novembre 2017 à ledoyen.loic@gmail.com
Le projet devra être envoyé sous la forme d'une archive `tar.gz` avec les noms complets des étudiants ayant participés.
L'archive ne contiendra que des :
 * répertoires
 * fichiers `.java`
 * fichiers `.sh`
 * fichiers `.md`

Le contenu de l'archive sera structuré comme suit :
.  
+--_logger  
|  +-- _src  
|  |   +-- _logger  
|  |       +-- (*.java)  
|  +-- _bin  
+-- _testframework  
|  +-- _src  
|  |   +-- _test  
|  |       +-- (*.java)  
|  +-- _bin  
+-- _banking  
|  +-- _src  
|  |   +-- _banking  
|  |       +-- (*.java)  
|  +-- _bin  
+-- compile.sh  
+-- test.sh  
+-- launch_banking.sh  
+-- README.md  

Le fichier **compile.sh** lancera la compilation de tous les fichiers java des différents projets.  
Le fichier **test.sh** lancera les tests du projet **banking**.  
Le fichier **launch_banking.sh** lancera l'application **banking**.

## Notation

La note du projet sera découpée suivant le respect des critères suivant :
* Critères absolus (dont le non respect entrainera la note de 0)
  * Le projet est rendu dans les temps, **aucune négociation ne sera possible**
  * Le projet respecte la structure de fichier demandée
  * Les projets de code compilent (les deux librairies + programme de gestion de compte bancaire)


* Autres critères
  * La documentation du projet (README.md) au format Markdown précise
    * l'utilisation des scripts (nom, fonction, paramètres)
    * les points d'entrée du code (classes main)
    * stratégie de résolution des problèmes rencontrés
  * Les noms des classes, méthodes et variables respecte les conventions vues en cours / TP et témoigne de la fonction de l'entité
    par ex: il est impossible de dire à quoi servent la variable `x` ou la classe `Ex6`, alors que `arrayIndex` dans un tableau ou `Logger`
  * Le couplage entre les objets doit être minimal, à cette fin des interfaces devront être utilisées
  * La librairie de test utilise un `logger` pour afficher le résultats d'exécution des test (détails + résumé)
  * Les tests valident le fonctionnement des méthodes
  * Un usage intelligent du `logger` dans le projet `banking` afin de logger :
    * dans la console, uniquement les retours du programme sans mise en forme
    * dans un fichier
      * l'intégralité des évènements (entrées utilisateur et sortie du programme)
      * Au format suivant : <DATE(YYYY-MM-DD HH:SS:sss)> <CATEGORY(INPUT / OUTPUT / PROGRAM)> <LEVEL(DEBUG / INFO / ERROR)> <MESSAGE>
  * Le projet `banking` fonctionne sans bug

## Détail des attendus pour chaque projet

### Logger

Le logger sera inspiré en majeure partie de ce qui aura été fait en TP (séance 3).

L'interface `Logger` devra avoir 3 méthodes abstraites :
```java
void debug(String category, String message);
void info(String category, String message);
void error(String category, String message);
```

L'argument `category` correspondra à la nature du message :
* INPUT pour les données entrées par l'utilisateur
* OUTPUT pour les messages émis par le programme à destination de l'utilisateur
* PROGRAM, pour les informations internes au programmes

Les instances d'objets respectant le contrat `Logger` seront créées par composition en utilisant la classe `LoggerFactory`.
Par example :
```java
Logger logger = LoggerFactory.dateAndTimeLogger(
    LoggerFactory.compositeLogger(
        LoggerFactory.withoutContextLogger(LoggerFactory.consoleLogger()),
        LoggerFactory.fileLogger("banking.log")
    )
)
```

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

### Application de gestion de compte bancaire

L'application utilisera le terminal comme interface homme-machine.
Aucune interface graphique n'est demandée.

Voici les opérations qui devront être disponibles :
* quitter le programme
* afficher la liste des opérations disponibles
* créer le compte d'un utilisateur (avec son nom et son age)
* quand au moins un compte existe
  * faire un dépot d'argent
    * erreur si le montant est négatif
  * faire un retrait
    * erreur quand le montant dépasse le solde du compte de l'utilisateur)
  * demander un crédit (avec montant durée et taux)
    * erreur si
      * le taux et plus petit que 1%
      * la durée amène le client à un age supérieur à 70 ans
      * le montant est négatif
      * l'utilisateur a déjà plus de 2 crédits
  * lister les crédits
* quand au moins deux comptes existent
  * faire un virement d'un compte à un autre
    * erreur si le montant du virement dépasse le solde su compte émetteur

les différentes règles métier (cas d'erreur) seront testées indépendemment les unes des autres en utilisant la librairie de test.
