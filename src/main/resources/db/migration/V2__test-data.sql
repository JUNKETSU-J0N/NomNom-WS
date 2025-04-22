-- === USERS ===
INSERT INTO "user" (id, name, email, password, preference)
VALUES
    (1, 'Anna Beispiel', 'anna@example.com', 'pass123', 'VEGETARIAN'),
    (2, 'Bob Test', 'bob@example.com', 'pass456', 'VEGAN');

-- === ALLERGENE ===
INSERT INTO allergen (id, name)
VALUES
    (1, 'Gluten'),
    (2, 'Laktose'),
    (3, 'Erdnüsse');

-- === USER-ALLERGENE ===
INSERT INTO user_allergen (user_id, allergen_id)
VALUES
    (1, 1),
    (2, 3);

-- === ZUTATEN ===
INSERT INTO ingredient (id, name, type, unit)
VALUES
    (1, 'Hackfleisch', 'MEAT', 'GRAM'),
    (2, 'Tomatensauce', 'VEGETABLE', 'MILLILITER'),
    (3, 'Pasta', 'GRAIN_PRODUCTS', 'GRAM'),
    (4, 'Käse', 'DAIRY', 'GRAM'),
    (5, 'Kartoffeln', 'VEGETABLE', 'GRAM'),
    (6, 'Béchamelsauce', 'DAIRY', 'MILLILITER'),
    (7, 'Pizza-Teig', 'GRAIN_PRODUCTS', 'GRAM'),
    (8, 'Mozzarella', 'DAIRY', 'GRAM'),
    (9, 'Spaghetti', 'GRAIN_PRODUCTS', 'GRAM'),
    (10, 'Sahne', 'DAIRY', 'MILLILITER'),
    (11, 'Champignons', 'MUSHROOMS', 'GRAM'),
    (12, 'Paprika', 'VEGETABLE', 'GRAM'),
    (13, 'Gnocchi', 'GRAIN_PRODUCTS', 'GRAM'),
    (14, 'Basilikum-Pesto', 'HERBS', 'GRAM');

-- === REZEPTE ===
INSERT INTO recipe (id, name, description)
VALUES
    (1, 'Burger', 'Klassischer Burger mit Rindfleischpatty.'),
    (2, 'Pizza Margherita', 'Einfach und lecker mit Tomate und Mozzarella.'),
    (3, 'Spaghetti Carbonara', 'Pasta mit Sahne und Speck.'),
    (4, 'Canneloni mit Hack', 'Gefüllte Canneloni mit Hack und Käse überbacken.'),
    (5, 'Kartoffelgratin', 'Herzhaftes Gratin mit Béchamelsauce.'),
    (6, 'Pizza Funghi', 'Pizza mit Tomatensauce, Mozzarella und Champignons.'),
    (7, 'Veggie-Burger', 'Burger mit pflanzlichem Patty und Gemüse.'),
    (8, 'Lasagne', 'Schichtweise mit Hack, Béchamelsauce und Käse.'),
    (9, 'Pasta mit Paprika-Sahne-Sauce', 'Würzige Paprikasauce mit Sahne.'),
    (10, 'Gnocchi mit Pesto', 'Kartoffel-Gnocchi mit Basilikum-Pesto.');

-- === REZEPT-ZUTATEN ===
INSERT INTO recipe_ingredient (recipe_id, ingredient_id, amount, unit)
VALUES
    (1, 1, 150, 'GRAM'),
    (1, 4, 30, 'GRAM'),
    (2, 7, 200, 'GRAM'),
    (2, 2, 80, 'MILLILITER'),
    (2, 8, 100, 'GRAM'),
    (3, 9, 200, 'GRAM'),
    (3, 10, 50, 'MILLILITER'),
    (4, 1, 200, 'GRAM'),
    (4, 2, 100, 'MILLILITER'),
    (4, 4, 50, 'GRAM'),
    (5, 5, 300, 'GRAM'),
    (5, 6, 100, 'MILLILITER'),
    (6, 7, 200, 'GRAM'),
    (6, 11, 100, 'GRAM'),
    (7, 12, 50, 'GRAM'),
    (7, 4, 30, 'GRAM'),
    (8, 1, 200, 'GRAM'),
    (8, 6, 100, 'MILLILITER'),
    (9, 3, 200, 'GRAM'),
    (9, 12, 80, 'GRAM'),
    (10, 13, 200, 'GRAM'),
    (10, 14, 50, 'GRAM');


-- === USER-REZEPTE ===
INSERT INTO user_recipe (user_id, recipe_id, notes, evaluation)
VALUES
    (1, 1, 'Super lecker', '5 Sterne'),
    (2, 2, 'Bisschen zu käsig', '3 Sterne'),
    (1, 4, 'Lieblingsrezept!', '5 Sterne');

-- === EINKAUFSLISTEN UND ITEMS ===
INSERT INTO shopping_list (id, user_id)
VALUES
    (1, 1),
    (2, 2);

INSERT INTO shopping_item (id, shopping_list_id, ingredient_id, amount, unit, added)
VALUES
    (1, 1, 1, 500, 'g', FALSE),
    (2, 1, 2, 200, 'ml', TRUE),
    (3, 2, 5, 300, 'g', FALSE);
