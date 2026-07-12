CREATE DATABASE IF NOT EXISTS conference_db;
USE conference_db;

CREATE TABLE tbl_user (
                          user_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_name VARCHAR(255) NOT NULL,
                          user_email VARCHAR(255) NOT NULL
);


CREATE TABLE tbl_conference (
                                conference_id INT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,
                                address VARCHAR(255) NOT NULL
);


CREATE TABLE tbl_session (
                             session_id INT AUTO_INCREMENT PRIMARY KEY,
                             title VARCHAR(255) NOT NULL,
                             start_date DATE NOT NULL,
                             start_time TIME NOT NULL,
                             tbl_conference_id_conference INT NOT NULL,

                             CONSTRAINT fk_session_conference
                                 FOREIGN KEY (tbl_conference_id_conference)
                                     REFERENCES tbl_conference(conference_id)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE
);

CREATE TABLE tbl_subscription (
                                  subscribed_user_id INT NOT NULL,
                                  session_id INT NOT NULL,
                                  created_at timestamp NOT NULL ,
                                  level INT NOT NULL,
                                  unique_id VARCHAR(45) NOT NULL UNIQUE,

                                  PRIMARY KEY (subscribed_user_id, session_id),

                                  CONSTRAINT fk_subscription_user
                                      FOREIGN KEY (subscribed_user_id)
                                          REFERENCES tbl_user(user_id)
                                          ON DELETE CASCADE
                                          ON UPDATE CASCADE,

                                  CONSTRAINT fk_subscription_session
                                      FOREIGN KEY (session_id)
                                          REFERENCES tbl_session(session_id)
                                          ON DELETE CASCADE
                                          ON UPDATE CASCADE
);