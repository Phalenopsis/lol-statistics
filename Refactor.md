# Piste :
## Logs
Avec 5 semaines de jouées :
- Deepth : 8
- KB: 391686.8515625
- computed in : 610 ms

En commentant ```prepareMatchResults()``` dans les contructeurs de Team,
on passe à :
- Deepth : 8
- KB: 177802.359375
- computed in : 351 ms

## Essai :
J'ai essayé d'obliger le garbage collector à détruire les branches déjà calculées en faisant s'autodétruire les objets 
en leur passant null et en faisant de même avec les objets qui les composent, mais cela n'a rien donné.
On passe même de 17****.*** KB à 18****.*** KB. 