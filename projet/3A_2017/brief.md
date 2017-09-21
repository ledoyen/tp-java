# Sujet de Projet POO 3A 2017

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

Le projet devra être envoyé par mail avant la date du ??? à ledoyen.loic@gmail.com
Le projet devra être envoyé sous la forme d'une archive `tar.gz`.
L'archive ne contiendra que des :
 * répertoires
 * fichiers `.java`
 * fichiers `.sh`

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
  * Le couplage entre les objets doit être minimal, à cette fin des interfaces devront être utilisées
  * Le framework de test utilise un `logger` pour afficher le résultats d'exécution des test (détails + résumé)
  * Les tests valident le fonctionnement des méthodes
  * Un usage intelligent du `logger` dans le projet `banking` afin de logger :
    * dans la console, uniquement les retours du programme sans mise en forme
    * dans un fichier
      * l'intégralité des évènements (entrées utilisateur et sortie du programme)
      * Au format suivant : <DATE(YYYY-MM-DD HH:SS:sss)> <CATEGORY(input / output)> <LEVEL(DEBUG / INFO / ERROR)> <MESSAGE>
  * Le projet `banking` fonctionne sans bug

## Détail des attendus pour chaque projet

### Logger

// TODO