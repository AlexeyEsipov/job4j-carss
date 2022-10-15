
INSERT INTO category(name) VALUES ('Легковые');
INSERT INTO category(name) VALUES ('Коммерческие');
INSERT INTO category(name) VALUES ('Спец. техника');

INSERT INTO body(type, category_id) VALUES ('Седан', 1);
INSERT INTO body(type, category_id) VALUES ('Внедорожник', 1);
INSERT INTO body(type, category_id) VALUES ('Хэтчбек', 1);
INSERT INTO body(type, category_id) VALUES ('Лифтбек', 1);
INSERT INTO body(type, category_id) VALUES ('Универсал', 1);
INSERT INTO body(type, category_id) VALUES ('Минивэн', 1);
INSERT INTO body(type, category_id) VALUES ('Купе', 1);
INSERT INTO body(type, category_id) VALUES ('Пикап', 1);
INSERT INTO body(type, category_id) VALUES ('Кабриолет', 1);
INSERT INTO body(type, category_id) VALUES ('Фургон', 1);
INSERT INTO body(type, category_id) VALUES ('Легкие коммерческие', 2);
INSERT INTO body(type, category_id) VALUES ('Грузовики', 2);
INSERT INTO body(type, category_id) VALUES ('Седельные тягачи', 2);
INSERT INTO body(type, category_id) VALUES ('Автобусы', 2);
INSERT INTO body(type, category_id) VALUES ('Коммунальная', 3);
INSERT INTO body(type, category_id) VALUES ('Бульдозеры', 3);
INSERT INTO body(type, category_id) VALUES ('Экскаваторы', 3);

INSERT INTO model(name, brand_id) VALUES ('M3', 0);
INSERT INTO model(name, brand_id) VALUES ('X5', 0);
INSERT INTO model(name, brand_id) VALUES ('7 Series', 0);
INSERT INTO model(name, brand_id) VALUES ('X6', 0);
INSERT INTO model(name, brand_id) VALUES ('80', 1);
INSERT INTO model(name, brand_id) VALUES ('100', 1);
INSERT INTO model(name, brand_id) VALUES ('A6', 1);
INSERT INTO model(name, brand_id) VALUES ('A8', 1);
INSERT INTO model(name, brand_id) VALUES ('Mustang', 2);
INSERT INTO model(name, brand_id) VALUES ('Transit', 2);
INSERT INTO model(name, brand_id) VALUES ('R160LC', 3);
INSERT INTO model(name, brand_id) VALUES ('R210LC', 3);
