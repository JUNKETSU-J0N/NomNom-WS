-- 1. UUID-Erweiterung aktivieren
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 2. Abhängige Daten entfernen
DELETE FROM shopping_item;
DELETE FROM shopping_list;
DELETE FROM user_recipe;
DELETE FROM user_allergen;

-- 3. Foreign Key Constraints entfernen
ALTER TABLE user_allergen DROP CONSTRAINT IF EXISTS fk_userallergen_on_user;
ALTER TABLE user_recipe DROP CONSTRAINT IF EXISTS fk_userrecipe_on_user;
ALTER TABLE shopping_list DROP CONSTRAINT IF EXISTS fk_shoppinglist_on_user;

-- 4. app_user vorbereiten
ALTER TABLE app_user DROP CONSTRAINT IF EXISTS app_user_pkey;
ALTER TABLE app_user DROP COLUMN IF EXISTS id;
ALTER TABLE app_user DROP COLUMN IF EXISTS password;
ALTER TABLE app_user DROP COLUMN IF EXISTS email;
ALTER TABLE app_user DROP COLUMN IF EXISTS name;

