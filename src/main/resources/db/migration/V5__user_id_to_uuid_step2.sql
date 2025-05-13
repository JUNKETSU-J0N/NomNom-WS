-- 1. Neue UUID-ID-Spalte in app_user
ALTER TABLE app_user ADD COLUMN id UUID PRIMARY KEY DEFAULT uuid_generate_v4();

-- 3. Testnutzer einfügen
INSERT INTO app_user (id, preference)
VALUES (
           '8a5ec4fc-908a-4873-9ae1-cbc4655c33a0',
           'VEGETARIAN'
       );

-- Spalten löschen und neu anlegen (ohne Constraints)
ALTER TABLE user_allergen DROP CONSTRAINT IF EXISTS fk_userallergen_on_user;
ALTER TABLE user_allergen DROP COLUMN IF EXISTS user_id;
ALTER TABLE user_allergen ADD COLUMN user_id UUID;

-- Genauso für andere Tabellen
ALTER TABLE user_recipe DROP CONSTRAINT IF EXISTS fk_userrecipe_on_user;
ALTER TABLE user_recipe DROP COLUMN IF EXISTS user_id;
ALTER TABLE user_recipe ADD COLUMN user_id UUID;

ALTER TABLE shopping_list DROP CONSTRAINT IF EXISTS fk_shoppinglist_on_user;
ALTER TABLE shopping_list DROP COLUMN IF EXISTS user_id;
ALTER TABLE shopping_list ADD COLUMN user_id UUID;
