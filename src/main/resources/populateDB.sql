DELETE FROM user_roles;
DELETE FROM users;

INSERT INTO user_roles (name) VALUES
  ('ROLE_USER'),
  ('ROLE_ADMIN');

INSERT INTO users (name, password, role_id, enabled) VALUES
  ('user', '$2a$10$ezChhbNTENDc8tnGtEtYYOrJg78Kg7pR6U5jgS955CG4cp4oURxza', '1', '1'),
  ('root', '$2a$10$CSzMkFMXSUlXNnDGAWRuW.C4S7vEDpys6peLGuxkI92IvJkeTrymu', '2', '1');