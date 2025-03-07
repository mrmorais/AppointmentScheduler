CREATE DATABASE  IF NOT EXISTS `as_user_ms`;
USE `as_user_ms`;

CREATE TABLE IF NOT EXISTS `roles` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) DEFAULT NULL,
	PRIMARY KEY (`id`)
)
	ENGINE=InnoDB
	AUTO_INCREMENT=1
	DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `users` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(50) NOT NULL,
	`password` char(80) NOT NULL,
	`first_name` varchar(50),
	`last_name` varchar(50),
	`email` varchar(50),
	`mobile` varchar(50),
	`street` varchar(50),
	`city` varchar(50),
	`postcode` varchar(50),
  PRIMARY KEY (`id`)
)
	ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `users_roles` (
	`user_id` int(11) NOT NULL,
	`role_id` int(11) NOT NULL,
	PRIMARY KEY (`user_id`,`role_id`),
	KEY `FK_ROLE_idx` (`role_id`),

	CONSTRAINT `FK_users_user` FOREIGN KEY (`user_id`)
	REFERENCES `users` (`id`)
	ON DELETE NO ACTION ON UPDATE NO ACTION,

	CONSTRAINT `FK_roles_role` FOREIGN KEY (`role_id`)
	REFERENCES `roles` (`id`)
	ON DELETE NO ACTION ON UPDATE NO ACTION
)
	ENGINE=InnoDB
  DEFAULT CHARSET=utf8;
	SET FOREIGN_KEY_CHECKS = 1;
    
CREATE TABLE IF NOT EXISTS `works_providers` (
  `id_user` INT(11) NOT NULL,
  `id_work` INT(11) NOT NULL,
  PRIMARY KEY (`id_user`, `id_work`),
  KEY `id_work` (`id_work`),
  CONSTRAINT `works_providers_users_provider` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `working_plans` (
	`id_provider` int(11) NOT NULL,
  `monday` TEXT,
	`tuesday` TEXT,
	`wednesday` TEXT,
	`thursday` TEXT,
	`friday` TEXT,
	`saturday` TEXT,
	`sunday` TEXT,

  PRIMARY KEY (`id_provider`),
	KEY `id_provider` (`id_provider`),

	CONSTRAINT `FK_appointments_provider` FOREIGN KEY (`id_provider`)
	REFERENCES `users` (`id`)

	ON DELETE NO ACTION
  ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS `corporate_customers` (
	`id_customer` int(11) NOT NULL,
	`vat_number` VARCHAR(256),
	`company_name` VARCHAR(256),
  PRIMARY KEY (`id_customer`),
	KEY `id_customer` (`id_customer`),
	CONSTRAINT `FK_corporate_customer_user` FOREIGN KEY (`id_customer`)
	REFERENCES `users` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `providers` (
	`id_provider` int(11) NOT NULL,
  PRIMARY KEY (`id_provider`),
	KEY `id_provider` (`id_provider`),
	CONSTRAINT `FK_provider_user` FOREIGN KEY (`id_provider`)
	REFERENCES `users` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE IF NOT EXISTS  `retail_customers` (
	`id_customer` int(11) NOT NULL,
  PRIMARY KEY (`id_customer`),
	KEY `id_customer` (`id_customer`),
	CONSTRAINT `FK_retail_customer_user` FOREIGN KEY (`id_customer`)
	REFERENCES `users` (`id`)
)
	ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `customers` (
	`id_customer` int(11) NOT NULL,
  PRIMARY KEY (`id_customer`),
	KEY `id_customer` (`id_customer`),

	CONSTRAINT `FK_customer_user` FOREIGN KEY (`id_customer`)
	REFERENCES `users` (`id`)
)
	ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- INSERT available roles
INSERT INTO `roles` (id,name) VALUES
  (1,'ROLE_ADMIN'),
  (2,'ROLE_PROVIDER'),
  (3,'ROLE_CUSTOMER'),
  (4,'ROLE_CUSTOMER_CORPORATE'),
  (5,'ROLE_CUSTOMER_RETAIL');

-- INSERT admin account with username: 'admin' and password 'qwerty123'
INSERT INTO `users` (id, username, password)
VALUES (1, 'admin', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO `users_roles` (user_id, role_id)
VALUES (1, 1);

-- INSERT provider account with username: 'provider' and password 'qwerty123'
INSERT INTO `users` (id, username, password)
VALUES (2, 'provider', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO `providers` (id_provider)
VALUES (2);
INSERT INTO `users_roles` (user_id, role_id)
VALUES (2, 2);


-- INSERT retail customer account with username: 'customer_r' and password 'qwerty123'
INSERT INTO `users` (id, username, password)
VALUES (3, 'customer_r', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO `customers` (id_customer)
VALUES (3);
INSERT INTO `retail_customers` (id_customer)
VALUES (3);
INSERT INTO `users_roles` (user_id, role_id)
VALUES (3, 3);
INSERT INTO `users_roles` (user_id, role_id)
VALUES (3, 5);

-- INSERT corporate customer account with username: 'customer_c' and password 'qwerty123'
INSERT INTO `users` (id, username, password)
VALUES (4, 'customer_c', '$2a$10$EqKcp1WFKVQISheBxkQJoOqFbsWDzGJXRz/tjkGq85IZKJJ1IipYi');
INSERT INTO `customers` (id_customer)
VALUES (4);
INSERT INTO `corporate_customers` (id_customer, vat_number, company_name)
VALUES (4, '123456789', 'Company name');
INSERT INTO `users_roles` (user_id, role_id)
VALUES (4, 3);
INSERT INTO `users_roles` (user_id, role_id)
VALUES (4, 4);

INSERT INTO works_providers
VALUES (2, 1);
INSERT INTO working_plans
VALUES (2,
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeroidsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeroidsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeroidsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeroidsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeroidsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeroidsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}',
        '{"workingHours":{"start":[6,0],"end":[18,0]},"breaks":[],"timePeroidsWithBreaksExcluded":[{"start":[6,0],"end":[18,0]}]}');

