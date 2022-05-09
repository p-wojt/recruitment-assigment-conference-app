CREATE TABLE IF NOT EXISTS `reservations` (
  `id`              LONG        AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `user_login`      VARCHAR(24)                NOT NULL,
  `user_email`      VARCHAR(255)               NOT NULL,
  `prelection_id`   LONG                       NOT NULL
);