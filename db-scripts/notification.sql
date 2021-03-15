CREATE DATABASE  IF NOT EXISTS `as_notification_ms`;
USE `as_notification_ms`;

CREATE TABLE IF NOT EXISTS `notifications` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(256),
	`message` TEXT,
   `created_at` DATETIME,
   `url` VARCHAR(256),
	`is_read` BOOLEAN,
   `id_user` INT(11),
   PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;