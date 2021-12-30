CREATE DATABASE IF NOT EXISTS forum;

USE forum;

CREATE TABLE forum.roles (
role_id int NOT NULL AUTO_INCREMENT,
role_name varchar(50) DEFAULT NULL,
PRIMARY KEY (role_id)
);

INSERT INTO forum.roles(role_name)
VALUES('Guest'),
VALUES('User'),
VALUES('Admin'),
VALUES('Moderator');

CREATE TABLE forum.users (
user_id int NOT NULL AUTO_INCREMENT,
user_name varchar(50) DEFAULT NULL,
user_email varchar(50) DEFAULT NULL,
user_login varchar(50) NOT NULL,
user_password varchar(50) NOT NULL,
role_id int NOT NULL,
PRIMARY KEY (user_id),
FOREIGN KEY (role_id) REFERENCES forum.roles(role_id)
);

CREATE TABLE forum.sections (
section_id int NOT NULL AUTO_INCREMENT,
section_name varchar(50) DEFAULT NULL,
PRIMARY KEY (section_id)
);

CREATE TABLE forum.forums (
forum_id int NOT NULL AUTO_INCREMENT,
forum_name varchar(50) DEFAULT NULL,
section_id int NOT NULL,
PRIMARY KEY (forum_id),
FOREIGN KEY (section_id) REFERENCES forum.sections(section_id)
);

CREATE TABLE forum.topics (
topic_id int NOT NULL AUTO_INCREMENT,
topic_name varchar(50) DEFAULT NULL,
start_msg text DEFAULT NULL,
start_msg_user int NOT NULL,
forum_id int NOT NULL,
PRIMARY KEY (topic_id),
FOREIGN KEY (start_msg_user) REFERENCES forum.users(user_id),
FOREIGN KEY (forum_id) REFERENCES forum.forums(forum_id)
);

CREATE TABLE forum.tags (
tag_id int NOT NULL AUTO_INCREMENT,
tag_name varchar(50) DEFAULT NULL,
PRIMARY KEY (tag_id)
);

CREATE TABLE forum.posts (
post_id int NOT NULL AUTO_INCREMENT,
message text DEFAULT NULL,
tag_id int DEFAULT NULL,
topic_id int DEFAULT NULL,
post_time timestamp DEFAULT CURRENT_TIMESTAMP,
user_id int NOT NULL,
PRIMARY KEY (post_id),
FOREIGN KEY (tag_id) REFERENCES forum.tags(tag_id),
FOREIGN KEY (topic_id) REFERENCES forum.topics(topic_id),
FOREIGN KEY (user_id) REFERENCES forum.users(user_id)
);

CREATE TABLE forum.pics (
pic_id int NOT NULL AUTO_INCREMENT,
caption varchar(50) DEFAULT NULL,
img longblob DEFAULT NULL,
post_id int NOT NULL,
PRIMARY KEY (pic_id),
FOREIGN KEY (post_id) REFERENCES forum.posts(post_id)
);

CREATE TABLE forum.comments (
comment_id int NOT NULL AUTO_INCREMENT,
comment_name varchar(50) DEFAULT NULL,
user_id int NOT NULL,
post_id int NOT NULL,
PRIMARY KEY (comment_id),
FOREIGN KEY (user_id) REFERENCES forum.users(user_id),
FOREIGN KEY (post_id) REFERENCES forum.posts(post_id)
);