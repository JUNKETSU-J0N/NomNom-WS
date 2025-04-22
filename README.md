# NomNom-WS

## Getting Started
Vorraussetzungen Docker oder Docker Desktop
https://www.docker.com/get-started/

### Projekt bauen und testen
   ```bash
  mvn clean install
   ```
### Projekt mit Docker starten
   ```bash
  docker-compose up
   ```

## Migration automatisch generieren mit Hibernate + Flyway

Wenn du Entities erstellt oder geändert hast, kannst du Hibernate das SQL-Schema generieren lassen und es automatisch als Flyway-Migration speichern:

### Schritte:

1. Erzeuge das SQL mit JPA Buddy