#!/usr/bin/env bash

# Variables
URL="http://localhost:8090/basic/validate?PLANNING=vacances"
FILE_PATH="/home/vertours/IdeaProjects/basicPost/CSVs/Vacances.csv" # Mettez à jour ce chemin vers votre fichier

# Vérification de l'existence du fichier
if [ ! -f "$FILE_PATH" ]; then
  echo "Erreur : Le fichier spécifié n'existe pas à l'emplacement : $FILE_PATH"
  exit 1
fi

# Vérification des permissions du fichier
if [ ! -r "$FILE_PATH" ]; then
  echo "Erreur : Le fichier spécifié n'a pas les permissions de lecture à l'emplacement $FILE_PATH"
  exit 1
fi

# Afficher les valeurs des variables pour vérification
echo "URL : $URL"
echo "Chemin du fichier : $FILE_PATH"

# Exécution de la requête HTTP et capture du code de retour
HTTP_RESPONSE=$(curl --write-out "HTTPSTATUS:%{http_code}" -X POST "$URL" \
    -H "Content-Type: application/octet-stream" \
    -H "Accept: application/octet-stream" \
    --data-binary "@$FILE_PATH")

# Code de retour
HTTP_STATUS=$(echo $HTTP_RESPONSE | tr -d '\n' | sed -e 's/.*HTTPSTATUS://')
echo "Code de retour HTTP : $HTTP_STATUS"

# Sortir avec le code de retour de la requête
exit $HTTP_STATUS