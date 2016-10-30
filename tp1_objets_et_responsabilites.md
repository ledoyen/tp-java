 #TP 1 : Objets et responsabilités

L'intérêt de la programmation orientée objet est de modéliser en éléments interactifs un système. Ce découpage permet une maintenance du code plus simple, une facilité pour le test ou encore une meilleure lisibilité.
SOLID résume les grands principes du développement orienté objet :
* Single responsibility: une seule responsabilité par classe
* Open/Close: ouvert à la composition, fermé à la modification
* Lyskov substitution: substitution par un sous-type sans modification de la cohérence
* Interface segregation: une interface (contrat) différente par client
* Dependency inversion: travailler avec la forme la plus abstraite d’un objet (une interface en général)


L’objectif de ce TP sera de mettre en pratique ces 4 des ces grands principes.
La substitution de Lyskov ne sera volontairement pas couverte car il n’y aura pas besoin de faire de l’héritage.

Tout au long de ce TP des concepts génériques de programmation seront manipulés.
Afin d’éviter de correspondre à la majorité des concepts sans faire d’anglicismes, les packages, classes, interface, méthodes, arguments et variables seront en Anglais.

## Création d'un Logger
Pour commencer, créer un package spécifique : `org.tp.logger`
Dans ce package, créer une interface `Logger` avec une seule méthode abstraite :
```java
void log(String message);
```
Créer ensuite une classe `ConsoleLogger` implémentant `Logger` et affichant le `message` passé en paramètre dans la console en utilisant `System.out`.

Créer enfin une classe `ConsoleFactory` ayant une méthode statique `getLogger(String name)`.

## Modélisation d'un jeu : l'age du capitaine
Le jeu ici sera de deviner un nombre que l'ordinateur aura choisi.
Le joueur aura un retour après chaque tentative: plus grand ou plus petit.

Dans un package `org.tp.guessgame` créer la classe l'interface `Player`.
Cette dernière aura les méthodes suivantes
```java
long askNextGuess();

void respond(boolean lowerOrGreater);
```
Créer une première implémentation qui utilisera votre interaction `HumanPlayer`.
Cette classe utilisera
* d'une part une instance de `Logger` donnée par `LoggerFactory` avec l'argument `"player"`
* d'autre part la classe `java.lang.Scanner` de Java permettant de récupérer les entrées de l’utilisateur dans la console

Créer une classe `Simulation` telle que:
```java
public class Simulation {

  private final Logger logger = LoggerFactory.getLogger("simulation");
  private final ??? player;
  private ??? numberToGuess;

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
  * sinon, donner l'indice (plus grand ou plus petit) ou joueur et retourner `false`
* Dans tous les cas, affichera via `logger` les informations permettant de suivre l'évolution de la partie
Enfin, la méthode `loopUntilPlayerSucceed` devra utiliser une boucle afin d'appeler `nextRound` jusqu'à ce que la partie soit finie.

Créer enfin une classe `Launcher` avec une méthode statique `main` qui
* créera une nouvelle instance de `Simulation` avec un joueur `HumanPlayer`
* initialisera cette instance avec un nombre aléatoire, généré par la classe `java.security.SecureRandom`
```java
SecureRandom random = new SecureRandom();
long randomNumber = random.nextLong();
```
* lancera une partie en appelant la méthode `loopUntilPlayerSucceed`
