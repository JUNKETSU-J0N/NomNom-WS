## 5. Bausteinsicht

### 5.1 Grobstruktur
```plaintext
Frontend (Angular)
│
├── Komponente: Rezept-Swipe
├── Komponente: Suche
├── Komponente: Wochenplan
├── Komponente: Profil/Einstellungen
├── Komponente: Einkaufsliste
└── Komponente: Favoriten & Notizen

Backend (Spring Boot)
│
├── REST-Controller / API Layer
├── Authentifizierungsservice (Keycloak)
├── Rezept- und Swipe-Verwaltung
├── Nutzerprofilverwaltung
├── Einkaufslistenservice
└── Inventar-/Wochenplan-Service

Datenbank (PostgreSQL)
│
└── Tabellen: Nutzer, Rezepte, Präferenzen, Inventar, Einkaufslisten, Swipe-Tracking, Zutaten, Unverträglichkeiten
```
