## 3. Kontextabgrenzung

### 3.1 Kontext-Diagramm

```plaintext
+------------------+        +--------------------+
|     Nutzer       |<-----> |     NomNom App     |
+------------------+        +--------------------+
        ^                           ^
        |                           |
        v                           v
+------------------+       +---------------------+
| Drittanbieter    | <---->| Rezeptdatenbank/API |
| (z. B. Chefkoch) |       +---------------------+
+------------------+
        ^
        | (evtl.)
        v
+------------------+
| Restaurant-API   |
+------------------+
```

### 3.2 Externe Schnittstellen
- Rezept-APIs (Chefkoch, ggf. Spoonacular)
- Authentifizierungsdienste (Spring Security / Keycloak)
- Maps API für Restaurantsuche
- Optional: Rabattanbieter / Händlerangebote