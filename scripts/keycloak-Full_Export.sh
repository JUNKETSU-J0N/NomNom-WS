#!/bin/bash

# Konfiguration
DOCKER_CONTAINER_NAME="keycloak"
EXPORT_DIR="/opt/keycloak/data/import"
HOST_DIR="./keycloak-full-exports"  # Zielordner auf dem Host

# Sicherstellen, dass der Zielordner existiert
mkdir -p "$HOST_DIR"

# Full Export starten (alle Realms und Einstellungen)
echo "Starte Full Export (alle Realms und Einstellungen)..."
docker exec -it $DOCKER_CONTAINER_NAME /opt/keycloak/bin/kc.sh export --dir $EXPORT_DIR --users=same_file

# Sicherstellen, dass der Export erfolgreich war
if [ $? -ne 0 ]; then
  echo "Fehler: Full Export konnte nicht durchgeführt werden."
  exit 1
fi

# Alle exportierten Dateien aus dem Container kopieren
echo "Kopiere exportierte Dateien aus Keycloak Container..."
docker cp $DOCKER_CONTAINER_NAME:$EXPORT_DIR/. $HOST_DIR

# Sicherstellen, dass die exportierten Dateien korrekt kopiert wurden
if [ "$(ls -A $HOST_DIR)" ]; then
  echo "Full Export erfolgreich kopiert nach $HOST_DIR"
else
  echo "Fehler: Exportierte Dateien konnten nicht kopiert werden."
  exit 1
fi

# Optional: Full Import durchführen (wiederherstellen)
echo "Starte Full Import..."
docker exec -it $DOCKER_CONTAINER_NAME /opt/keycloak/bin/kc.sh import --dir $EXPORT_DIR

echo "Full Import abgeschlossen!"
