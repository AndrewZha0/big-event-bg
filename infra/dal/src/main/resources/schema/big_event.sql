-- 创建数据库
CREATE DATABASE big_event;

-- 使用数据库
USE big_event;

-- 用户表
DROP TABLE IF EXISTS `bigevent_s_user`;
CREATE TABLE `bigevent_s_user`
(
    `id`          BIGINT            NOT NULL COMMENT '唯一id',
    `username`    varchar(20)       NOT NULL COMMENT '用户名',
    `password`    varchar(32)       NOT NULL COMMENT '密码',
    `nickname`    varchar(10)       NULL COMMENT '昵称',
    `email`       varchar(128)      NULL COMMENT '邮箱',
    `user_pic`    varchar(128)      NULL COMMENT '头像',
    `gmt_create`  DATETIME          NOT NULL COMMENT '创建时间',
    `created_by`  BIGINT            NOT NULL COMMENT '创建者',
    `gmt_modify`  DATETIME          NULL COMMENT '修改时间',
    `modified_by` BIGINT            NULL COMMENT '修改者',
    `deleted`     TINYINT DEFAULT 0 NOT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE user_unique_username (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '用户表';

-- 分类表
DROP TABLE IF EXISTS `bigevent_b_category`;
CREATE TABLE `bigevent_b_category`
(
    `id`             BIGINT            NOT NULL COMMENT '唯一id',
    `category_name`  varchar(32)       NOT NULL COMMENT '分类名称',
    `category_alias` varchar(32)       NOT NULL COMMENT '分类别名',
    `gmt_create`     DATETIME          NOT NULL COMMENT '创建时间',
    `created_by`     BIGINT            NOT NULL COMMENT '创建者',
    `gmt_modify`     DATETIME          NULL COMMENT '修改时间',
    `modified_by`    BIGINT            NULL COMMENT '修改者',
    `deleted`        TINYINT DEFAULT 0 NOT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '分类表';
-- 文章表
DROP TABLE IF EXISTS `bigevent_b_article`;
CREATE TABLE `bigevent_b_article`
(
    `id`          BIGINT                 NOT NULL COMMENT '唯一id',
    `title`       varchar(30)            NOT NULL COMMENT '文章标题',
    `content`     varchar(10000)         NOT NULL COMMENT '文章内容',
    `cover_img`   varchar(128)           NULL COMMENT '文章封面',
    `state`       varchar(128) DEFAULT '草稿' COMMENT '文章状态: 只能是[已发布] 或者 [草稿]',
    `category_id` BIGINT                 NULL COMMENT '文章分类ID',
    `gmt_create`  DATETIME               NOT NULL COMMENT '创建时间',
    `created_by`  BIGINT                 NOT NULL COMMENT '创建者',
    `gmt_modify`  DATETIME               NULL COMMENT '修改时间',
    `modified_by` BIGINT                 NULL COMMENT '修改者',
    `deleted`     TINYINT      DEFAULT 0 NOT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '文章表';