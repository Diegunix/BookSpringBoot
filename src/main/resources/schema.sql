
CREATE SCHEMA IF NOT EXISTS BOOKS_PUBLIC;

CREATE TABLE BOOKS_PUBLIC.Book (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `price` double NULL,
  `description` varchar(2000) NULL,
  `nbofpage` int NULL,
  `illustrations` boolean NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
