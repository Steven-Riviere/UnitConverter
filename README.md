# Converteur d'unités

Ce projet Java propose une interface (swing) simple pour convertir des unités courantes : distance, poids, quantité, température, etc.  
Il permet à l'utilisateur de choisir un type d’unité, d’entrer une valeur, et d'obtenir automatiquement la conversion.
Il est possible également de mettre un libellé pour l'unité que nous voulons convertir puisque l'information est stockée jusqu'à temps que l'utilisateur décide de la supprimer ou de supprimer complétement l'historique.

## Aperçu de l'application
![Aperçu de l'application](./assets/conversion-unites.jpg)

L'image ci-dessus montre l'interface du convertisseur, avec un exemple concret de conversion.

## Fonctionnalités
- Interface graphique intuitive en Java (Swing)
- Conversion entre différentes unités :
  - Températures (Celsius  <-> Fahrenheit)
  - Distance (kilomètres <-> miles)
  - Poids (kilogrammes <-> livres)
- Validation des données
- Résultat affiché en temps réel

## Défis rencontrés
- Créer un système de conversion flexible et extensible
- Gérer l'historique avec suppression individuelle ou totale
- Garantir une interface simple tout en intégrant plusieurs types de conversion

## Technologies utilisées
<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Swing-3DDC84?style=for-the-badge&logo=java&logoColor=white" alt="Swing" />
</p>
