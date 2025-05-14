-- 1. Referenzierte Testdaten einfügen

-- Allergene
INSERT INTO user_allergen (user_id, allergen_id)
VALUES ('8a5ec4fc-908a-4873-9ae1-cbc4655c33a0', 1);

-- Rezepte
INSERT INTO user_recipe (user_id, recipe_id, notes, evaluation)
VALUES
    ('8a5ec4fc-908a-4873-9ae1-cbc4655c33a0', 1, 'Super lecker', 'YES'),
    ('8a5ec4fc-908a-4873-9ae1-cbc4655c33a0', 4, 'Lieblingsrezept!', 'FAVORITE');

-- Einkaufsliste
INSERT INTO shopping_list (id, user_id)
VALUES (1, '8a5ec4fc-908a-4873-9ae1-cbc4655c33a0');

-- Einkaufsitems
INSERT INTO shopping_item (id, shopping_list_id, ingredient_id, amount, unit, added)
VALUES
    (1, 1, 1, 500, 'GRAM', FALSE),
    (2, 1, 2, 200, 'MILLILITER', TRUE);

-- 2. Foreign Keys wiederherstellen
ALTER TABLE user_allergen
    ADD CONSTRAINT fk_userallergen_on_user FOREIGN KEY (user_id) REFERENCES app_user (id);

ALTER TABLE user_recipe
    ADD CONSTRAINT fk_userrecipe_on_user FOREIGN KEY (user_id) REFERENCES app_user (id);

ALTER TABLE shopping_list
    ADD CONSTRAINT fk_shoppinglist_on_user FOREIGN KEY (user_id) REFERENCES app_user (id);

-- 3. NOT NULL Constraints
ALTER TABLE user_allergen ALTER COLUMN user_id SET NOT NULL;
ALTER TABLE user_recipe ALTER COLUMN user_id SET NOT NULL;
ALTER TABLE shopping_list ALTER COLUMN user_id SET NOT NULL;
ALTER TABLE app_user ALTER COLUMN id SET NOT NULL;

