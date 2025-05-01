# Piste :
## Logs
### Etape 1 :
Avec 5 semaines de jouées :
- Deepth : 8
- KB: 391686.8515625
- computed in : 610 ms

En commentant ```prepareMatchResults()``` dans les contructeurs de Team,
on passe à :
- Deepth : 8
- KB: 177802.359375
- computed in : 351 ms

### Etape 2
En retirant l'ArrayList dans FinalRanking et en alimentant la HashMap à la volée :
- Deepth : 8
- KB: 27632.5703125
- computed in : 264 ms

La mémoire utilisée baisse. Ce qui était recherché. Problème : on perd en fiabilité à cause des 
```prepareMatchResults()``` commentés. (Les tests ne passent plus tous)

L'avantage est que l'on peut désormais calculer une journée de plus :
- Deepth : 12
- KB: 76971.3125
- computed in : 21857 ms

ça prend du temps, mais l'erreur outOfMemory disparait : la consommation en mémoire diminue (le garbage collector ne
devait pas avoir le temps de travailler auparavant).

### Etape 3
En remettant les ```prepareMatchResults()``` :
- Deepth : 8
- KB: 148433.9453125
- computed in : 387 ms

La consommation en mémoire remonte.
Mais... on regagne en précision. (les tests repassent)

On peut désormais calculer 3 journées, avec la fiabilité.
- Deepth : 12
- KB: 164069.78125
- computed in : 54625 ms




## Essai :
J'ai essayé d'obliger le garbage collector à détruire les branches déjà calculées en faisant s'autodétruire les objets 
en leur passant null et en faisant de même avec les objets qui les composent, mais cela n'a rien donné.
On passe même de 17****.*** KB à 18****.*** KB. 