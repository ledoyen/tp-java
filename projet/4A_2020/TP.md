# Architecture logicielle : TP 2020

## Objectifs
* mettre en place un projet en automatisant les tâches de base
* travailler à plusieurs en utilisant GIT
* mettre en pratique les concepts objets apris en cours

## Notation
Le TP est à terminer pour 1 smemaine (maximum) après la dernière séance de TP (soit le mercredi 4 Mars 23h59).
Tous commit sur la branche `master` après cette date entrainera une pénalité de -3 points par commit.

La notation se découpera en plusieurs parties:
* **L'historique GIT** lisible et propre (pas de message de commit sans intention, pas de commit successifs avec le même message) (-0.5 point de pénalité par message
* **Le Style** doit permettre de lire le code facilement
  * les méthodes doivent faire moins de 7 lignes
  * les classes moins de 100 lignes (-1 point de pénalité pour les écarts)
  * tout champ mutable doit être justifié (tous les champs doivent être marqués `final`)
  * tout champ ou méthode statique doit être justifié (le mot-clé, `static` ne doit pas être utilisé)
* Les étapes du sujet ont été réalisées
* l'automatisation est fonctionelle

## 1ère séance

Outils nécessaires (à installer avant la séance):
* JDK 11 ou plus (à ne pas confondre avec Java ou JRE, qui ne contient pas de compilateur)
* un IDE (intelliJ, Eclipse, VS-code, etc.)
* un terminal POSIX (rien à faire sur Linux ou Mac. Sur Windows, utiliser GitBash)

### Création du dépot GitHub (20 minutes)
* Créer un compte GitHub **par personne** (si vous n'en possédez pas déjà un)
* Créer un nouveau dépot **publique** **par groupe** en cochant **Initialize this repository with a README**
  * vous pouvez choisir n'importe quel nom pour ce dépôt (ex: TP-team1)
* Ajouter à ce dépot tous les membres du groupe afin qu'ils puissent y accéder en écriture
* Réduire les possibilité de merge à `Allow rebase merging ` et cochez `Automatically delete head branches`
* Protéger la branche `master` contre les push en ajoutant une règle, dans Settings > Branches > **Require status checks to pass before merging**

* Chaque membre du groupe peut maintenant cloner le dépot sur son ordinateur
  * pour cela générer une clé SSH **sans mot de passe** (si vous n'en possédez pas déjà une) mettre votre email dans la commande `ssh-keygen -t rsa -b 4096 -C "your_email@example.com"`
  * puis faire `git clone ` avec l'url SSH du dépôt nouvellement créé

* L'url SSH du dépot doit être communiquée à Mr LEDOYEN pour notation

### Initialisation du dépôt (15 min)
* se placer sur une nouvelle branche `git checkout -b branch_name`
* ajouter un fichier .editorconfig spécifiant (pour tous les fichiers)
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
* faire un commit **avec** cet unique fichier et le message "Setup project layout"
* publier la branche sur le serveur GitHub `git push`
* créer la Pull Request correspondante à cette branche sur l'interface GitHub
* fusionner la Pull Request

### Création de la structure du projet Java (15 min)
* se placer sur une nouvelle branche `git checkout -b branch_name`
* ajouter Jitpack comme dépot dans le fichier de configuration de Maven
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
* génerer le projet grâce à l'archetype `com.github.lernejo:java-maven-archetype:4A.2020.RC2` présent sur le dépot `https://jitpack.io`.
  * commande complète
```bash
mvn archetype:generate -B \
    -DarchetypeGroupId=com.github.lernejo \
    -DarchetypeArtifactId=java-maven-archetype \
    -DarchetypeVersion=4A.2020.RC2 \
    -DgroupId=com.esiea \
    -DartifactId=tp-4A-2020 \
    -Dversion=1.0.0-SNAPSHOT \
    -Dpackage=com.esiea.tp.4A
```
* vérifier que tout fonctionne `mvn test`
* faire un commit **avec** les fichiers `*.java`, `*.yml` et `*.xml` et le message "Initial Java project"
* créer la Pull Request correspondante à cette branche sur l'interface GitHub
* fusionner la Pull Request

### Brancher l'intégration continue (10 min)
* se connecter sur https://travis-ci.com avec le compte github qui a créé le dépôt
* activer le build travis pour le dépôt du TP
* vérifier que le build est en succès
* se placer sur une nouvelle branche
* copier le badge au format Markdown fourni par Travis dans le fichier README.md à la racine du projet
* publier ce changement avec une nouvelle Pull Request
* fusionner la Pull Request

## Héberger la couverture des tests (15 min)
* se connecter sur https://codecov.io/ avec le compte github qui a créé le dépôt
* sélectionner le dépôt correspondant au TP
* dans le cadre d'une nouvelle Pull Request, modifier le fichier `.travis.yml` en y ajoutant les lignes
```yml
after_success:
- bash <(curl -s https://codecov.io/bash)
```
* fusionner la Pull Request

* vérifier que le dashboard CodeCov affiche le détail des lignes de code couvertes par les tests

* se placer sur une nouvelle branche
* ajouter le badge au format Markdown fourni par CodeCov dans le fichier README.md à la racine du projet
* publier ce changement avec une nouvelle Pull Request
* fusionner la Pull Request

## Mars Rover (source https://kata-log.rocks/mars-rover-kata)

Créer le logiciel de navigation du robot Mars Rover.
