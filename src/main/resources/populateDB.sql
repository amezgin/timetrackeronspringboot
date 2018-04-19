DELETE FROM user_roles;
DELETE FROM users;

INSERT INTO user_roles (name) VALUES
  ('USER'),
  ('ADMIN');

INSERT INTO users (name, password, role_id, enabled) VALUES
  ('user', 'user', '1', '1'),
  ('root', 'root', '2', '1');