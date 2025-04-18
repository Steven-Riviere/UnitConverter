# Converteur d'unités

Ce projet Java propose une interface (swing) simple pour convertir des unités courantes : distance, poids, quantité, température, etc.  
L'utilisateur peut choisir un type d’unité, entrer une valeur, et obtenir automatiquement la conversion.

Il est également possible d’ajouter un libellé personnalisé pour chaque conversion. L'information est conservée dans l’historique, jusqu’à suppression manuelle ou réinitialisation complète.  

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
  <img src="https://img.shields.io/badge/IntelliJIDEA-000000?style=for-the-badge&logo=java&logoColor=white" alt="IntelliJ"/>
</p>
