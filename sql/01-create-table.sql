USE message_board;

CREATE TABLE users (
                       id  BIGINT AUTO_INCREMENT PRIMARY KEY ,
                       view_name VARCHAR(32) NOT NULL,
                       email VARCHAR(256) UNIQUE NOT NULL ,
                       password VARCHAR(128) NOT NULL
);

CREATE TABLE threads (
                         id  BIGINT  AUTO_INCREMENT PRIMARY KEY ,
                         title VARCHAR(256) NOT NULL ,
                         user_id BIGINT REFERENCES users(id),
                         created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE messages (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY ,
                          thread_id BIGINT NOT NULL REFERENCES threads(id),
                          user_id BIGINT NOT NULL REFERENCES users(id),
                          message TEXT,
                          posted_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          deleted BOOLEAN NOT NULL DEFAULT FALSE
);