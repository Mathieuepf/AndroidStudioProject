# ProjetPays2 - README

## Description

**ProjetPays2** est une application Android permettant d'afficher des informations sur différents pays. L'application utilise une base de données Room pour stocker les informations des pays favoris et se connecte à une API REST pour récupérer des informations supplémentaires sur les pays.

## Fonctionnalités

- **Affichage de la liste des pays** : Affiche une liste de pays générée aléatoirement.
- **Recherche de pays** : Permet à l'utilisateur de rechercher des pays par leur nom.
- **Détails du pays** : Affiche les détails d'un pays sélectionné, y compris son drapeau, sa capitale et sa population.
- **Ajout aux favoris** : Permet à l'utilisateur d'ajouter des pays à sa liste de favoris.

## Structure du Projet

Le projet est structuré comme suit :

- **MainActivity** : Activité principale permettant à l'utilisateur de rechercher des pays.
- **CountryListActivity** : Activité affichant la liste des pays correspondant à la recherche.
- **CountryDetailsActivity** : Activité affichant les détails d'un pays sélectionné.
- **CountryAdapter** : Adapter pour le RecyclerView affichant les pays.
- **CountryViewHolder** : ViewHolder pour le RecyclerView.
- **CountryEntity** : Entité Room représentant un pays dans la base de données.
- **CountryDao** : Interface DAO pour accéder aux données des pays dans la base de données.
- **AppDatabase** : Classe abstraite représentant la base de données Room.
- **CountryRepository** et **CountryRepositoryImpl** : Classes pour gérer la récupération des données des pays depuis l'API REST.
- **CountryService** : Interface Retrofit pour les appels API.
- **CountryDTO** : Data Transfer Object pour les réponses de l'API.
- **Country** : Classe modèle représentant un pays.

## Dépendances

Le projet utilise les dépendances suivantes :

- **Room** : Pour la gestion de la base de données locale.
- **Retrofit** : Pour effectuer les appels API.
- **Gson** : Pour la sérialisation/desérialisation des données JSON.
- **Glide** : Pour le chargement et l'affichage des images (drapeaux des pays).
- **Kotlin Coroutines** : Pour la gestion asynchrone des tâches.
