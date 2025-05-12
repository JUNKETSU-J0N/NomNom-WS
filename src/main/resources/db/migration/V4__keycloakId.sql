ALTER TABLE app_user ADD COLUMN keycloak_id VARCHAR(255);

UPDATE app_user
SET keycloak_id = '3efbc9d7-1896-43b7-bea9-e9d4dd8af006'
WHERE id = 1;