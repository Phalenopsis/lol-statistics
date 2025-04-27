# Statistiques pour LoL

Le but de ce programme est de pouvoir estimer les chances pour une équipe de finir à la Nième place en comparant toutes
les possibilités des matchs restant à réaliser.

## Exemple :
Soit un championnat de 4 équipes, A, B, C, et D :
Les matchs à réaliser jusqu'à la fin du championnat sont:
- AB => 2 - 1 
- CD => 2 - 1
- AC => 2 - 1
- BD => 2 - 0
- AD
- BC

Les deux derniers matchs restent à jouer.

A ce niveau, en prenant en compte toutes les possibilités des matchs restant :
- L'équipe A a 56.25% de chance de finir première et 43.75% de finir 2ème.
- L'équipe B a 25% de chances de finir 1ère, 25% de finir 2ème et 50% de finir 3ème.
- L'équipe C a 18.75% de chance de finir 1ème, 31.25 de finir 2ème, 43.75 de finir 3ème et 6.25 de finir 4ème.
- L'équipe D a 6.25% de chance de finir 3ème et 93.75% de finir 4ème.

## Règles de classement :
Les matchs sont en deux manches gagnantes. Pas d'égalité possible.
Pour déterminer le classement des équipes, on regarde en premier lieu leur nombre de match gagnés. Ensuite, c'est le 
goalaverage (différence entre manches gagnées et perdues), puis le nombre de manches gagnées et enfin le resultat de la
confrontation directe entre les deux équipes.

## Fonctionnement
Les prévisions sont basées sur un arbre de possibilités.
Chaque match restant à jouer est simulé avec les résultats possibles (2-0, 2-1, 1-2, 0-2).
Pour chaque résultats, les matchs suivants sont simulés de la même manière.

                                                        Match AD
                                                            |
                ________________________________________________________________________________________
                |                               |                           |                           |
               2-0                             2-1                         1-2                         0-2
                |                               |                           |                           |
            Match BC                        Match BC                    Match BC                     Match BC
                |                               |                           |                           |
     ______________________           ______________________      ______________________      ______________________
     |      |      |      |           |      |      |      |      |      |      |      |      |      |      |      |
    2-0    2-1    1-2    0-2         2-0    2-1    1-2    0-2    2-0    2-1    1-2    0-2    2-0    2-1    1-2    0-2

Une fois les matchs simulés, on calcule tous les classements et on regarde à quelle position fini l'équipe.

## Problème :
Ce fonctionnement est extrèmement gourmand. Pour un championnat de 8 équipes, il n'est possible de générer les 
statistiques de prévisions que pour les deux dernières journées, sinon cela provoque :
```
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space: failed reallocation of scalar replaced objects
```