Java Console Quiz

Un quiz interactif en ligne de commande écrit en Java 17, utilisant Maven pour la gestion de projet. Les questions sont chargées depuis un fichier JSON, et chaque question est limitée dans le temps via un minuteur visible. Le quiz mélange les questions aléatoirement et offre une option pour rejouer ou quitter à la fin.

---

Fonctionnalités

* **Chargement JSON** : questions et choix stockés dans `src/main/resources/questions.json`.
* **Minuteur visible** : compte à rebours affiché en secondes pour chaque question.
* **Mélange aléatoire** : ordre des questions (et éventuellement des choix) aléatoire à chaque lancement.
* **Option Rejouer/ Quitter** : possibilité de relancer un nouveau quiz ou de quitter à la fin.
* **Coloration console** (optionnelle) : utilisation de codes ANSI pour enrichir l’affichage.

---

Prérequis

* **Java 17+** installé et configuré dans le PATH.
  Vérifier avec :

  ```bash
  java -version
  ```
* **Maven 3.6+** installé.
  Vérifier avec :

  ```bash
  mvn -v
  ```

---

Installation

1. **Cloner le dépôt** :

   ```bash
   git clone https://github.com/TON_UTILISATEUR/java-quiz.git
   cd java-quiz
   ```
2. **Compiler le projet** :

   ```bash
   mvn clean compile
   ```

---

Exécution

Lancer le quiz dans un terminal interactif :

```bash
mvn exec:java -Dexec.mainClass="com.example.quiz.Main"
```

Le quiz démarre : chaque question apparaît avec un compte à rebours. Tapez le numéro de votre réponse puis Entrée.

---

Personnalisation

* **Questions** : modifier `src/main/resources/questions.json` (format JSON) :

  ```json
  [
    {
      "question": "Quelle est la capitale de la France ?",
      "choices": ["Lyon", "Marseille", "Paris", "Nice"],
      "answerIndex": 2
    },
    ...
  ]
  ```
* **Durée du minuteur** : modifier la constante `TIME_LIMIT_SECONDS` dans `Quiz.java`.
* **Shuffle des choix** : décommenter ou ajouter `Collections.shuffle(q.getChoices());` dans la boucle.
* **Couleurs** : activer les codes ANSI dans `Quiz.java` pour colorer le texte.

---

Packaging

Pour générer un JAR exécutable :

1. Ajouter le plugin Maven Shade dans `pom.xml`.
2. Exécuter :

   ```bash
   mvn clean package
   ```
3. Lancer le JAR :

   ```bash
   java -jar target/java-quiz-1.0.0.jar
   ```

---

Contribution

Les contributions sont les bienvenues ! 

1. Forke ce dépôt.
2. Crée une branche (`git checkout -b feature/ma-fonctionnalite`).
3. Commit tes changements (`git commit -m 'Ajout : fonctionnalité X'`).
4. Push (`git push origin feature/ma-fonctionnalite`).
5. Ouvre une Pull Request.

---

Amuse-toi bien et bon quiz !
