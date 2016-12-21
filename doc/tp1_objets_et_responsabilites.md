# TP 1: Objets et responsabilités

L'intérêt de la programmation orientée objet est de modéliser en éléments interactifs un système. Ce découpage permet une maintenance du code plus simple, une facilité pour le test ou encore une meilleure lisibilité.
SOLID résume les grands principes du développement orienté objet :
* Single responsibility: une seule responsabilité par classe
* Open/Close: ouvert à la composition, fermé à la modification
* Lyskov substitution: substitution par un sous-type sans modification de la cohérence
* Interface segregation: une interface (contrat) différente par client
* Dependency inversion: travailler avec la forme la plus abstraite d’un objet (une interface en général)


L’objectif de ce TP sera de mettre en pratique 4 de ces grands principes.
L'utilisation de plusieurs interfaces ne sera pas couverte par ce TP.

Tout au long de ce TP des concepts génériques de programmation seront manipulés.
Afin d’éviter de correspondre à la majorité des concepts sans faire d’anglicismes, les packages, classes, interfaces, méthodes, arguments et variables seront en Anglais.

## EX 1: Création d'un Logger
Ce qu'on appelle couramment **Logger** est un objet qui a la responsabilité de produire le journal applicatif.
Ce journal, qu'il soit dans la console ou dans un fichier permet de comprendre ce que fait le programme au travers de messages, qu'ils soient :
* critiques (ex: le serveur distant n'est plus joignable)
* informatifs (ex: tel utilisateur a fait telle opération)
* de "debug" (ex: la requête au serveur distant a prit 39ms)

Pour commencer, créer un package spécifique : `org.tp.logger`
Dans ce package, créer une interface `Logger` avec une seule méthode abstraite :
```java
void log(String message);
```
Créer ensuite une classe `ConsoleLogger` implémentant `Logger` et affichant le `message` passé en paramètre dans la console en utilisant `System.out`.

Créer enfin une classe `LoggerFactory` ayant une méthode statique `getLogger(String name)` retournant une instance de `Logger`.

## EX 2: Modélisation d'un jeu : l'age du capitaine
Le jeu ici sera de deviner un nombre que l'ordinateur aura choisi.
Le joueur aura un retour après chaque tentative: plus grand ou plus petit.

Dans un package `org.tp.guessgame` créer l'interface `Player`.
Cette dernière aura les méthodes suivantes
```java
long askNextGuess();

void respond(boolean lowerOrGreater);
```
Créer une première implémentation qui utilisera votre interaction `HumanPlayer`.
Cette classe utilisera
* d'une part une instance de `Logger` donnée par `LoggerFactory` avec l'argument `"player"`
* d'autre part la classe `java.util.Scanner` de Java permettant de récupérer les entrées de l’utilisateur dans la console

Créer une classe `Simulation` telle que:
```java
public class Simulation {

  private final Logger logger = LoggerFactory.getLogger("simulation");
  private final ??? player;  //TODO add variable type
  private ??? numberToGuess; //TODO add variable type

  public Simulation(Player player) {
    //TODO implement me
  }

  public void initialize(long numberToGuess) {
    //TODO implement me
  }

  /**
   * @return true if the player have guessed the right number
   */ 
  private boolean nextRound() {
    //TODO implement me
    return false;
  }

  public void loopUntilPlayerSucceed() {
    //TODO implement me
  }
}
```

Le constructeur permettra de renseigner les champs `private` qui seront utilisés à chaque tour de jeu.
La méthode `nextRound` devra:
* demander un nombre au joueur
* vérifier s'il est égal, plus grand ou plus petit
  * s'il est égal, retourner `true`
  * sinon, donner l'indice (plus grand ou plus petit) au joueur et retourner `false`
* dans tous les cas, afficher via `logger` les informations permettant de suivre l'évolution de la partie

Enfin, la méthode `loopUntilPlayerSucceed` devra utiliser une boucle afin d'appeler `nextRound` jusqu'à ce que la partie soit finie.

Créer enfin une classe `Launcher` avec une méthode statique `main` qui
* créera une nouvelle instance de `Simulation` avec un joueur `HumanPlayer`
* initialisera cette instance avec un nombre aléatoire, généré par la classe `java.security.SecureRandom`
```java
SecureRandom random = new SecureRandom();
long randomNumber = random.nextLong();
```
* lancera une partie en appelant la méthode `loopUntilPlayerSucceed`

## EX 3: Lancement du programme (optionnel)
A la racine du projet, créer un script `buildAndLaunch.sh` qui devra:
* compiler tous les fichiers *.java en utilisant la commande **javac**
* lancer le programme en utilisant la commande **java**

## EX 4: Création d'un utilisateur robot
Le but de cet exercice est de créer une seconde implémentation de `Player`: `ComputerPlayer`.
Cette nouvelle classe aura la même *fonction* que `HumanPlayer`, mais sans demander à l'utilisateur quoi que ce soit.

L'algorithme de recherche dichotomique pouvant ne pas converger du premier coup, nous allons ajouter une sécurité.
Modifier dans la classe `Simulation` la méthode `loopUntilPlayerSucceed` afin que celle-ci prenne en paramètre un nombre qui sera le  maximum d'itérations de la boucle.
Cette même méthode devra également afficher à la fin de la partie le temps que celle-ci a pris au format `mm:ss.SSS` et si oui ou non le joueur a trouvé la solution avant la limite d'itération.

Récupérer un timestamp se fait avec le code `System.currentTimeMillis()`.
La valeur retournée correspond au nombre de millisecondes entre le 1er Janvier 1970 et le moment où la fonction est appelée.

Modifier la classe `Launcher` afin que celle-ci gère 3 cas par rapport aux paramètres passés en ligne de commande (`String[] args`):
* si le premier argument vaut `-interactive`, alors utiliser la précédente façon de lancer le programme avec un `HumanPlayer` avec une limite d'itérations valant `Long.MAX_VALUE`
* si le premier argument vaut `-auto` et le second argument est numérique, alors
  * créer une nouvelle instance de `Simulation` avec un joueur `ComputerPlayer`
  * initialiser cette instance avec le nombre donné comme second argument
  * lancer une partie en appelant la méthode `loopUntilPlayerSucceed` et avec comme limite d'itération 10000
* sinon afficher les deux "façons" de lancer le programme décrite ci-dessus afin de guider l'utilisateur

Enfin, implémenter les méthodes de la classe `ComputerPlayer` afin que que la recherche de l'age du capitaine converge vers la solution.

## EX 5: Simplification des messages de log
A ce stade, des messages de logs provenant des classes `Launcher`, `Simulation`, `HumanPlayer` et `ComputerPlayer` se mélangent dans la console sans moyen de les distinguer.

Nous allons donc créer, dans le package `org.tp.logger`, une nouvelle classe `ContextualLogger` implémentant `Logger`, qui prendra le nom d'une classe, ainsi qu'un autre `Logger` en paramètres de constructeur.
Le but de ce `Logger` sera d'enrichir le message avec la date courante et le nom de la classe appelante.

Il est nécessaire pour cela d'utiliser la classe  `java.text.SimpleDateFormat` avec un pattern tel que `"YYYY-MM-dd HH:mm:ss.SSS"`.
La méthode `log` de cette implémentation devra elle-même appeler la méthode `log` de l'objet `Logger` passé par construction.
```java
public void log(String message) {
  delegateLogger.log(DATE_FORMAT.format(new Date()) + " " + callerClass + " " + message);
}
```

Enfin, modifier la classe `LoggerFactory` pour qu'elle produise une instance de `Logger` qui produira des messages enrichis dans la Console.

Lancer le programme et vérifier que les messages apparaissent bien datés et avec la classe d'origine.

En procédant ainsi on **compose** les objets `Logger` sans modifier leur comportement interne.
Il est alors plus simple de remplacer, `ConsoleLogger` par un objet de type `FileLogger` qui ajouterai les messages dans un fichier tout en gardant le même enrichissement de message.

Ecrire la classe `FileLogger` en utilisant le code ci-dessous
```java
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLogger implements Logger {
    private final Path path;

    public FileLogger(String pathAsString) {
        path = Paths.get(pathAsString).toAbsolutePath();
    }

    public void log(String message) {
        try {
            Files.write(path, (message + "\n").getBytes(), APPEND, CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write log message to file [" + path + "]", e);
        }
    }
}
```

Modifier le code de `LoggerFactory` afin que les messages soient produits dans un fichier sur le disque.

Lancer le programme et vérifier que les messages apparaissent bien datés et avec la classe d'origine dans le fichier spécifié dans la classe `LoggerFactory`.

# EX 6: Composition de plusieurs Loggers
Ajouter les messages dans un fichier est pratique pour comprendre ce qui s'est passé à posteriori, cependant ce n'est pas pratique pour le développement.
Nous allons donc combiner les deux loggers précédents en un seul.

Pour cela, créer une nouvelle classe `CompositeLogger` implémentant `Logger`.
Cette classe aura un constructeur prenant deux `Logger` en paramètres.
La méthode `log` appellera successivement `log` sur les deux `Logger` renseignés par construction.

Modifier la classe `LoggerFactory` pour qu'elle renvoie un seul `Logger` écrivant les messages à la fois dans la Console et dans un fichier.

# EX 7: Encore plus de composition
Afin d'y voir plus clair dans le diagnostic d'un comportement au travers d'un fichier de log, il peut être utile de filtrer certains messages afin de ne garder que ceux qui ont de l'intérêt.
Nous allons donc filtrer les messages provenant des classes implémentant `Player` pour le `FileLogger`.

Pour cela, créer une classe `FilteredLogger` implémentant `Logger` qui aura un constructeur avec deux paramètres:
```java
public FilteredLogger(Logger delegate, Predicate<String> condition) {
  //TODO assign arguments to fields
}
```
Implémenter la méthode log en testant si la condition valide le message donné en paramètre.
Si la condition est vérifiée, appeler le `Logger` delegate avec le même paramètre. 

L'interface `java.util.function.Predicate` modélise une condition sur un objet dont le type est spécifié entre chevron (ici `String`).
Il est possible de l'implémenter de deux façon
* avec une classe implémentant l'interface `Predicate`
* avec une lambda, ex: `Predicate<String> condition = message -> !message.contains("player");`.
Tous les messages qui ne contiennent pas le mot `"player"` valident cette condition.

Modifier la classe `LoggerFactory` pour qu'elle produise un `Logger` qui affichera tous les messages dans la console et n'affichera que les messages de la classe `Simulation` dans un fichier.
Les messages doivent tous être horodatés et indiquer de quelle classe ils proviennent.

