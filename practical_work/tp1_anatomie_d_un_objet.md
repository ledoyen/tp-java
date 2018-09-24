# TP 1 : Anatomie d'un objet

L'approche orientée objet vise à structurer le code à l'image des concepts du monde réel.

On regroupe sous forme d'objet des propriétés et des comportements qui ont une cohérence.

## EX 1: Point d'entrée

Il y a un endroit unique dans un programme orienté objet qui n'est pas objet : son point d'entrée.
La première *ligne de code* à être exécutée doit être marquée comme telle.
Puisqu'on parle de *ligne* et non d'une capacité d'un objet à faire une opération, il s'agit à cet unique endroit de code procédural.

En Java on marque un point d'entrée avec une **méthode statique** particulière, c'est-à-dire une fonction qui existe en dehors d'une *instance* d'objet.
```java
package anatomy;

public class StartClass {

    public static void main(String[] args) {
        // here can be placed object-oriented code
    }
}
```

On appelle la **signature d'une méthode**, l'ensemble des paramètres suivants :
 * ses modificateurs (ici `public` et `static`)
 * son type de retour (ici `void`)
 * son nom (ici `main`)
 * ses paramètres (ici un seul de type `String[]`)

Dans le cas d'un point d'entrée pour un code Java, cette signature doit être respectée, pour que la JVM puisse trouver le code à exécuter.

Pour commencer, créer un répertoire **tp1** contenant un répertoire **src** contenant un répertoire **anatomy**.
Dans ce dernier répertoire, créer un fichier **StartClass.java** contenant le code ci-dessus.
Ensuite, dans le répertoire **tp1**, créer un script **compileAndStart.sh** qui va compiler puis lancer le programme.

> Pour compiler, on utilise la commande `javac` (se termine par un **c** pour **compilation**)

`javac -d bin src/anatomy/StartClass.java`

La compilation transforme le code lisible par un humain, en code lisible par une machine, en l'occurence un fichier **StartClass.class** compréhensible par la JVM.

> Pour lancer le programme, on utilise la commande `java` (sans le **c**)

`java -classpath bin anatomy.StartClass`

La machine vituelle Java pouvant lire un programme contenu dans plusieurs sources (fichiers, JARs, etc.), on ne spécifie pas un nom de fichier, mais un nom logique.
Ce nom *logique*, appelé **nom qualifié d'une classe** permetant d'identifier une classe quelque soit sa source, est composé du nom du **package** suivi du nom de la classe.
Pour éviter la confusion entre nom logique et nom physique, Java a choisi d'utiliser le `.` comme séparateur de chemin.

A ce stade, en lancant le script, vous ne devriez rien voir, même pas une erreur !

## EX 2 : Hello World !

Il est possible que notre programme fonctionne, mais pour en avoir le coeur net, nous allons ajouter l'afichage d'un message dans la console.

Pour cela créer un second fichier **Logger.java** à côté du premier fichier Java.
Ce fichier contiendra le code d'un objet responsable d'afficher du texte dans la console.

Dans ce fichier écrire le corps d'une classe **publique** appelée Logger, contenant une unique méthode :
```java
    public void log(String message) {
        System.out.println(message);
    }
```

Modifier la classe `StartClass` en ajoutant dans la fonction `main`, la création d'un objet de type `Logger` ainsi que l'appel de sa méthode `log` avec le paramètre `"Hello World !"`.
> Pour créer un nouvel objet, on utilise le mot-clé `new` suivi du constructeur de la classe, ici `new Logger()`

> Pour appeler une méthode d'un objet, on utilise le `.` comme séparateur, ici `logger.log("my message")`;
