-- Добавляем категории
INSERT INTO `categories` (`id`, `name`) VALUES (1, 'Vegetables');

-- Добавляем продукты
INSERT INTO `products` (`id`, `name`, `category`) VALUES (1, 'Tomato', 1);

-- Добавляем свойства
INSERT INTO `attributes` (`id`, `name`, `capacity`, `product`)
VALUES (1, 'A', 0.25, 1), (2, 'B3', 0.6, 1), (3, 'В5', 0.1, 1), (4, 'В6', 0.25, 1), (5, 'С', 12, 1), (6, 'Е', 0.5, 1),
	(7, 'Potassium', 237, 1), (8, 'Phosphorus', 24, 1), (9, 'Magnesium', 11, 1), (10, 'Calcium', 10, 1), (11, 'Sodium', 5, 1);
