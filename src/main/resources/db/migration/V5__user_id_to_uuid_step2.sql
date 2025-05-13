-- 1. Neue UUID-ID-Spalte in app_user
ALTER TABLE app_user ADD COLUMN id UUID PRIMARY KEY DEFAULT uuid_generate_v4();

-- 3. Testnutzer einfügen
INSERT INTO app_user (id, name, email, preference)
VALUES (
           '8a5ec4fc-908a-4873-9ae1-cbc4655c33a0',
           'nomnom test',
           'nomnom@test.de',
           'VEGETARIAN'
       );
