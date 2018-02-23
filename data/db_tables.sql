-- Создаем базу данных
CREATE DATABASE products_db CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Создаем таблицу, которая будет хранить категории продуктов.
CREATE TABLE `categories` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Создаем таблицу, которая будет хранить продукты.
CREATE TABLE `products` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(11) NOT NULL DEFAULT '',
  `category` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prod_to_cat` (`category`),
  CONSTRAINT `prod_to_cat` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Создаем таблицу, которая будет хранить свойства продуктов.
CREATE TABLE `attributes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `capacity` float DEFAULT NULL,
  `product` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `attr_to_prod` (`product`),
  CONSTRAINT `attr_to_prod` FOREIGN KEY (`product`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;