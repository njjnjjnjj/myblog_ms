create schema myblog collate utf8mb4_bin;

use myblog;

create table if not exists t_blog_content
(
    content_id   varchar(32) charset utf8mb4   not null
    primary key,
    order_number decimal                       not null,
    content      varchar(4000) charset utf8mb4 not null,
    metadata_id  varchar(32) charset utf8mb4   null
    );

create table if not exists t_blog_metadata
(
    metadata_id          varchar(32) charset utf8mb4  not null
    primary key,
    publish_datetime     timestamp                    null,
    update_datetime      timestamp                    null,
    author_id            varchar(32) charset utf8mb4  null,
    title                varchar(500) charset utf8mb4 null,
    content_summary      varchar(500) charset utf8mb4 null,
    publish_mode         varchar(100) charset utf8mb4 null,
    visibility           varchar(100) charset utf8mb4 null,
    access_password      varchar(100) charset utf8mb4 null,
    pre_publish_datetime timestamp                    null
    );

create table if not exists t_blog_subscriber
(
    subscriber_id      varchar(32) charset utf8mb4  not null
    primary key,
    subscribe_datetime timestamp                    null,
    subscribe_email    varchar(200) charset utf8mb4 null
    );