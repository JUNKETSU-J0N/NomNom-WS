ALTER TABLE "user" RENAME TO app_user;

CREATE SEQUENCE IF NOT EXISTS app_user_seq START WITH 1 INCREMENT BY 50;

UPDATE shopping_item
SET unit = 'GRAM'
WHERE unit = 'g';

UPDATE shopping_item
SET unit = 'MILLILITER'
WHERE unit = 'ml';