# Architecture logicielle : TP 2020

## Objectifs
* mettre en place un projet en automatisant les tâches de base (intégration continue)
* travailler à plusieurs en utilisant GIT
* mettre en pratique les concepts objets appris en cours
* construire l'architecture d'un projet

## Notation
Le TP est à terminer pour 1 semaine (maximum) après la dernière séance de TP (soit le mercredi 4 Mars 23h59).  
Tout commit sur la branche `master` après cette date entrainera une pénalité de -3 points par commit.

La notation se découpera en plusieurs parties:
* **L'historique GIT** lisible et propre (pas de message de commit sans intention, pas de commit successifs avec le même message) (-0.5 point de pénalité par message
  * Voir cet article pour plus d'information https://chris.beams.io/posts/git-commit/
* **Le Style** doit permettre de lire le code facilement
  * les méthodes doivent faire moins de 7 lignes
  * les classes moins de 60 lignes (-1 point de pénalité pour les écarts)
  * tout champ mutable doit être justifié (tous les champs doivent être marqués `final`)
  * tout champ ou méthode statique doit être justifié (le mot-clé, `static` ne doit pas être utilisé)
* Les étapes du sujet ont été réalisées
* L'automatisation est fonctionelle

## 1ère séance

Attendu en fin de séance : l'automatisation est en place (2 badges sur le README).
> Tous les projets n'ayant pas atteint cette étape en fin de séance seront pénalisés (-2 points).

Outils nécessaires (à installer avant la séance):
* JDK 11 ou plus (à ne pas confondre avec Java ou JRE, qui ne contient pas de compilateur)
* un IDE (intelliJ, Eclipse, VS-code, etc.)
* un terminal POSIX (rien à faire sur Linux ou Mac. Sur Windows, utiliser GitBash)
* Maven (3.6.3) (`sudo apt-get install maven` pour **Ubuntu**)

### Création du dépot GitHub (20 minutes)
* Créer un compte GitHub **par personne** (si vous n'en possédez pas déjà un)
* Créer un nouveau dépot **public** **par groupe** en cochant **Initialize this repository with a README**
  * vous pouvez choisir n'importe quel nom pour ce dépôt (ex: TP-team1)
* Ajouter à ce dépot tous les membres du groupe afin qu'ils puissent y accéder en écriture
* Réduire les possibilités de merge à `Allow rebase merging ` et cocher `Automatically delete head branches`
* Protéger la branche `master` contre les push en ajoutant une règle, dans Settings > Branches > **Require status checks to pass before merging**

* Chaque membre du groupe peut maintenant cloner le dépot sur son ordinateur
  * pour cela générer une clé SSH **sans mot de passe** (si vous n'en possédez pas déjà une) mettre votre email dans la commande `ssh-keygen -t rsa -b 4096 -C "your_email@example.com"`
  * puis faire `git clone ` avec l'url SSH du dépôt nouvellement créé

* L'url SSH du dépot doit être communiquée à Mr LEDOYEN pour notation

### Initialisation du dépôt (15 min)
* Se placer sur une nouvelle branche `git checkout -b branch_name`
* Ajouter un fichier .editorconfig spécifiant (pour tous les fichiers)
  * caractère de fin de ligne : LF (unix)
  * encodage: UTF-8
  * fichiers doivent finir par une ligne vide
  * l'indentation est de 4 espaces
```
root = true

[*]
end_of_line = lf
insert_final_newline = true

charset = utf-8

indent_style = space
indent_size = 4

```
* Faire un commit **avec** cet unique fichier et le message "Setup project layout"
* Publier la branche sur le serveur GitHub `git push`
* Créer la Pull Request correspondante à cette branche sur l'interface GitHub
* Fusionner la Pull Request

### Création de la structure du projet Java (15 min)
* Se placer sur une nouvelle branche `git checkout -b branch_name`
* Ajouter Jitpack comme dépot dans le fichier de configuration de Maven
  * fichier **~/.m2/settings.xml**
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <profiles>
        <profile>
            <id>jitpack</id>
            <repositories>
                <repository>
                    <id>jitpack</id>
                    <url>https://jitpack.io</url>
                </repository>
            </repositories>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>jitpack</activeProfile>
    </activeProfiles>
</settings>
```
* Génerer le projet grâce à l'archetype `com.github.lernejo:java-maven-archetype:4A.2020.RC2` présent sur le dépot `https://jitpack.io`.
  * commande complète
```bash
mvn archetype:generate -B \
    -DarchetypeGroupId=com.github.lernejo \
    -DarchetypeArtifactId=java-maven-archetype \
    -DarchetypeVersion=4A.2020.RC2 \
    -DgroupId=com.esiea \
    -DartifactId=tp-4A-2020 \
    -Dversion=1.0.0-SNAPSHOT \
    -Dpackage=com.esiea.tp4A
```
* Déplacer tous les fichiers du dossier nouvellement créé `tp-4A-2020` dans le dossier parent (la racine du dépôt Git)
* A la racine du dépôt doivent se trouver les fichiers `pom.xml`, `.travis.yml` et `src` 
* Vérifier que tout fonctionne `mvn test`
* Faire un commit **avec** les fichiers `*.java`, `*.yml` et `*.xml` et le message "Initial Java project"
> Ne pas inclure les fichiers générés par Maven ou spécifique à votre IDE.  
> Pour celà, vous pouvez vous servir du fichier `.gitignore` afin de déclarer les fichiers que Git ne considérera pas
* Créer la Pull Request correspondante à cette branche sur l'interface GitHub
* Fusionner la Pull Request

### Brancher l'intégration continue (10 min)
* Se connecter sur https://travis-ci.com avec le compte github qui a créé le dépôt
* Activer le build travis pour le dépôt du TP
* Vérifier que le build est en succès
* Se placer sur une nouvelle branche
* Copier le badge au format Markdown fourni par Travis dans le fichier README.md à la racine du projet
* Publier ce changement avec une nouvelle Pull Request
* Fusionner la Pull Request

### Héberger la couverture des tests (15 min)
* Se connecter sur https://codecov.io/ avec le compte github qui a créé le dépôt
* Sélectionner le dépôt correspondant au TP
* Dans le cadre d'une nouvelle Pull Request, modifier le fichier `.travis.yml` en y ajoutant les lignes
```yml
after_success:
- bash <(curl -s https://codecov.io/bash)
```
* Fusionner la Pull Request

* Vérifier que le dashboard CodeCov affiche le détail des lignes de code couvertes par les tests

* Se placer sur une nouvelle branche
* Ajouter le badge au format Markdown fourni par CodeCov dans le fichier README.md à la racine du projet
* Publier ce changement avec une nouvelle Pull Request
* Fusionner la Pull Request

## Mars Rover (source https://kata-log.rocks/mars-rover-kata)

### Programme initial (1h 15)

Il est ici fortement conseillé de suivre la méthode TDD (Test Driven Development) afin de produire un code simple, modulaire et testé à 100%.
Petit rappel sur le TDD :
> - Commencer par coder un seul cas de test  
> - Coder le minimum possible pour faire passer ce test  
> - Améliorer le code de production sans toucher le test si nécessaire  
> - Recommencer

Petit rappel sur la structure d'un test :
> - zero ou plusieurs élèments de mise en **condition initiale**  
> - un unique élèment déclencheur  
> - une ou plusieurs vérifications sur l'état résultant attendu


Créer le programme de navigation du robot Mars Rover.

### API programmatique

Ajouter l'interface suivante, dans le package : com.esiea.tp4A.domain
```java
public interface MarsRover {

    default MarsRover initialize(Position position) {
        return this;
    }

    default MarsRover updateMap(PlanetMap map) {
        return this;
    }

    default MarsRover configureLaserRange(int range) {
        return this;
    }

    default Position move(String command) {
        return Position.of(0, 0, Direction.NORTH);
    }
}
```
Avec les contrats suivants :
```java
public interface Position {
    
    int getX();
    int getY();
    Direction getDirection();
    
    static Position of(int x, int y, Direction direction) {
        return new FixedPosition(x, y, direction);
    }
    
    final class FixedPosition implements Position {

        private final int x;
        private final int y;
        private final Direction direction;

        public FixedPosition(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }
        
        @Override
        public Direction getDirection() {
            return direction;
        }
    }
}
```
```java
public interface PlanetMap {
    
    Set<Position> obstaclePositions();
}
```
```java
public enum Direction {
    
    NORTH, EAST, SOUTH, WEST;
}
```


#### Implémenter le déplacement

Ce programme prendra en entrée la position initiale du rover, des coordonnées et la direction à laquelle il fait face (x, y, D).
Les directions sont encodées sur N (nord), S (sud), W (ouest) et E (est).

Le rover peut recevoir un `char[]` [c1, c2, ... cn] comme commande de déplacement :
* `f` : le rover avance dans la direction à laquelle il fait face
* `b` : le rover recule
* `l` : le rover pivote sur la gauche
* `r` : le rover pivote sur la droite

**Par exemple**
> avec un rover à la position initiale de `(0, 0, N)` si le rover reçoit la commande [f, f, l, b], sa nouvelle position est `(1, 2, W)`.

#### Implémenter le déplacement sur une grille sphérique

Considérant que la planète Mars fait 100x100, un rover à la position `(0, 50, N)` recevant la commande [f], se retrouvera à la position `(0, -49, N)`.

#### Implémenter la détection d'obstacle

Le rover doit être capable de recevoir une carte de la planète, contenant un ensemble de points (x, y) correspondant à des obstacles infranchissables.  
Dans le cas où le rover reçoit une commande lui demandant de franchir un obstacle, les déplacements impossibles ignorés.

**Par exemple**
> avec un rover à la position initiale de `(0, 0, N)` et un obstacle en (0, 1) si le rover reçoit la commande [f, f, l, b], sa nouvelle position est `(1, 0, W)`.

#### Implémenter le laser

Le rover est équipé d'un laser lui permettant de détruire n'importe quel obstable à une portée maximale définie à l'initialisation.

Ce laser lui permet entre autre de détruire les obstacles présents sur la carte, et ainsi de pouvoir passer là où ce n'était pas possible avant. 
Ce laser fonctionne par tirs, un tir s'arrête lorsqu'il détruit un obstacle.

Le rover doit pouvoir prendre en compte une nouvelle lettre comme commande : `s` tir avec le laser dans la direction dans laquelle est le rover. 

**Par exemple**
> avec un rover à la position initiale de `(0, 0, N)`, un laser de portée 2 et un obstacle en (0, 2) si le rover reçoit la commande [s, f, f], sa nouvelle position est `(0, 2, NORTH)`.


## 2nd séance

Nous allons transformer le programme de la dernière fois afin de créer un jeu en ligne.  
Le but de ce jeu sera d'être le dernier en vie, en se servant du laser pour éliminer les autres joueurs.
Un joueur touché une fois par un tir de laser meurt.

Le jeu génèrera en aléatoire les paramètres suivants :
* taille de la carte (petite 100x100, moyenne 300x300, grande 600x600)
* positions des obstacles sur la carte (15% du terrain doit etre constitué d'obstacles)
* positions des 50 joueurs
* portée du laser (courte 5, moyenne 30, infinie -> risque d'auto-destruction si le tir fait le tour de la planete)

### Concevoir une API (10 min)
Les joueurs pourront interagir avec le jeu à travers une interface web.  
Celle-ci leur devra leur permettre de :
* connaitre leur position
* connaitre la position des obstacles et joueurs adverses dans un carré de 30x30 (par défaut) autour du joueur (radar)
* connaitre la portée du laser
* se déplacer
* tirer avec le laser
* savoir le statut de son personnage (mort ou vivant)

Imaginer une API _programmatique_ (une interface en Java) qui permette d'échanger ces informations.  
Coder cette API dans un package différent ce celui utilisé dans la première séance.  

### Concevoir le système initial (30 min)
Dans un fichier Markdown, expliquer comment le système va fonctionner.  
Vous êtes encouragés à faire des diagrammes, différents outils existent, mais celui-ci est bien pratique : https://kroki.io/  

Voici des exemples de problèmes auxquels vous allez devoir faire face, et pour lesquels il faudra imaginer des solutions
* Est-ce que les joueurs intéragiront en temps réel, ou au tour par tour et pourquoi ?
* Comment sera stocké l'état (toutes les données permettant de représenter le jeu) ?
* Comment seront stockées les informations confidentielles des joueurs (emails, etc.)
* Comment gérer plusieurs parties en même temps ?
* Comment gérer plusieurs parties par joueurs en même temps ?
* Que se passera-t-il si un serveur plante ? (considérer la machine / vm / conteneur éteint(e) et inaccessible)
* Comment les joueurs s'authentifieront et sauvegardent leur progression ?
* Comment gérer une charge imprévue ? (100x plus de joueurs que prévu par ex)
* etc.

### Mise à jour du système (30 min)

Afin de garder la communauté des joueurs active, il est important de faire évoluer le jeu pour qu'il ne devienne pas lassant,
et inciter d'autres joueurs à venir.

Pour cela, nous allons ajouter au jeu des bonus.  
Tous les 15 jours, un nouveau bonus viendra enrichir le jeu (par ex: rayon du radar étendu pendant x min / tours).

Dans un nouveau document au format Markdown, décrir les étapes de la mise à jour.
* Comment faire pour éviter la coupure de service ?
* Que faire des parties en cours ?
* Comment changer l'API pour que les joueurs utilisant une vieille version du client web puissent toujours jouer ? (rétro-compatibilité)
* Comment avertir les joueurs de la nouveauté une unique fois ?


### Coder la première version (1h30)

Il s'agira uniquement de coder la partie "serveur".  
Pour simplifier
 * un joueur "s'authentifiera" juste avec son nom.
 * l'état est stocké en mémoire ou dans une base de donnée embarquée (H2)

Coder le serveur HTTP qui servira du JSON pour interagir avec les joueurs.
Vous pouvez vous aider de tests d'intégration pour vérifier le méchanisme de sérialisation.

Le serveur doit écouter sur le port 8080.

Le serveur devra répondre à ces ressources :
* `POST /api/player/{playerName}` pour créer un nouveau joueur, avec ces retours possibles :
  * code : 201  
    body schema :
    ```json
    {
        "$schema": "http://json-schema.org/schema#",
        "type": "object",
        "properties": {
            "player": {
                "type": "object",
                "properties": {
                    "name": { "type": "string" },
                    "status": { "type": "string", "enum": ["alive", "dead"]},
                    "position": {
                        "type": "object",
                        "properties": {
                            "x": { "type": "integer" },
                            "y": { "type": "integer" },
                            "direction": { "type": "string", "enum": ["NORTH", "EAST", "SOUTH", "WEST"]}
                        },
                        "additionalProperties": false,
                        "required": ["x", "y", "direction"]
                    },
                    "laser-range": { "type": "integer", "exclusiveMinimum": 0 }
                },
                "additionalProperties": false,
                "required": ["name", "status", "position", "laser-range"]
            },
            "local-map": {
                "type": "object",
                "properties": {
                    "obstacles": {
                        "type": "array",
                        "items": {
                            "type": "object",
                            "properties": {
                                "x": { "type": "integer" },
                                "y": { "type": "integer" }
                            },
                            "additionalProperties": false,
                            "required": ["x", "y"]
                        }
                    },
                    "players": {
                        "type": "array",
                        "items": {
                            "type": "object",
                            "properties": {
                                "name": { "type": "string" },
                                "x": { "type": "integer" },
                                "y": { "type": "integer" }
                            },
                            "additionalProperties": false,
                            "required": ["name", "x", "y"]
                        }
                    }
                },
                "additionalProperties": false,
                "required": ["obstacles", "players"]
            }
        },
        "additionalProperties": false,
        "required": ["player", "local-map"]
    }
    ```
  * code: 409 dans le cas où un joueur de ce nom existe déjà

* `GET /api/player/{playerName}` pour obtenir le statut d'un joueur, avec ces retours possibles
  * code 200
    même _body schema_ que le retour en succès de l'API de création
  * code 404 quand le joueur n'existe pas

* `PATCH /api/player/{playerName}/{command}` pour envoyer une commande au rover, avec les mêmes retours possibles que pour l'obtention du statut

**Par exemple**
> un retour possible est :
```json
{
    "player": {
        "name": "lol",
        "status": "alive",
        "position": {
            "x": 2,
            "y": 2,
            "direction": "EAST"
        },
        "laser-range": 3
    },
    "local-map": {
        "obstacles": [],
        "players": [
          {
              "name": "ptdl",
              "x": 4,
              "y": -3
          }
        ]
    }
}
```
