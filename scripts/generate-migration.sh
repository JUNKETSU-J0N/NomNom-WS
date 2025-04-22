#!/bin/bash

GENERATED_SQL="target/generated-schema.sql"
MIGRATION_DIR="src/main/resources/db/migration"
THRESHOLD=${1:-90}  # Standard: 90%, kann via Argument überschrieben werden

if [ ! -f "$GENERATED_SQL" ]; then
  echo "Keine generierte SQL-Datei gefunden unter: $GENERATED_SQL"
  exit 1
fi

LAST_MIGRATION=$(ls -1 "$MIGRATION_DIR"/V*.sql 2>/dev/null | sort | tail -n 1)

if [ -f "$LAST_MIGRATION" ]; then
  COMMON_LINES=$(comm -12 <(sort "$GENERATED_SQL") <(sort "$LAST_MIGRATION") | wc -l)
  TOTAL_LINES=$(cat "$GENERATED_SQL" | wc -l)

  if [ "$TOTAL_LINES" -eq 0 ]; then
    echo "Die generierte Datei ist leer – Migration abgebrochen."
    exit 0
  fi

  SIMILARITY=$(( 100 * COMMON_LINES / TOTAL_LINES ))

  if [ "$SIMILARITY" -ge "$THRESHOLD" ]; then
    echo "Die neue Migration ist zu $SIMILARITY% identisch mit der letzten ($LAST_MIGRATION)."
    echo "→ Mindestabweichung von $((100 - THRESHOLD))% erforderlich."
    exit 0
  else
    echo "Unterschied erkannt – Ähnlichkeit: $SIMILARITY%"
  fi
fi

TIMESTAMP=$(date +"%Y%m%d%H%M%S")
MIGRATION_NAME="V${TIMESTAMP}__auto_generated.sql"
cp "$GENERATED_SQL" "$MIGRATION_DIR/$MIGRATION_NAME"

echo "Neue Migration erstellt: $MIGRATION_NAME"
