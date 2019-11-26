CREATE TABLE post
(
    id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50),
    content text,
    author int,
    comment_count int,
    view_count int,
    like_count int,
    tag varchar(256),
    create_time bigint,
    modified_time bigint
);