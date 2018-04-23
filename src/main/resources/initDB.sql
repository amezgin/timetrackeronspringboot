DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles
(
  id        BIGINT       NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users
(
  id       BIGINT                  NOT NULL AUTO_INCREMENT,
  name     VARCHAR(100)            NOT NULL,
  password VARCHAR(100)             NOT NULL,
  role_id  BIGINT                  NOT NULL,
  enabled  BOOL DEFAULT TRUE       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES user_roles (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_name_idx
  ON users (name);

CREATE TABLE statuses (
  id                 BIGINT                     NOT NULL AUTO_INCREMENT,
  name               VARCHAR(100)               NOT NULL,
  date_time          TIMESTAMP                  NOT NULL,
  user_id            BIGINT                     NOT NULL,
  start_new_work_day BOOL DEFAULT FALSE         NOT NULL,
  end_work_day       BOOL DEFAULT FALSE         NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE UNIQUE INDEX status_unique_user_datetime_idx
  ON statuses (user_id, date_time);