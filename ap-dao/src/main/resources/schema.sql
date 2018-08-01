DROP DATABASE IF EXISTS ap;

CREATE DATABASE ap
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE ap;

-- ----------------------------
--  Table structure for tb_article
-- ----------------------------
DROP TABLE
IF EXISTS tb_article;

CREATE TABLE tb_article
(
  id               BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  title            VARCHAR(128)                          NOT NULL
  COMMENT '文章标题',
  content          LONGTEXT                              NOT NULL
  COMMENT '文章内容',
  created_username VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '创建人',
  apply_status     VARCHAR(32)                           NOT NULL                    DEFAULT ''
  COMMENT '申请状态',
  reply_msg        VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '审批意见',
  status           TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '状态:{0:可用, 1:禁用}',
  created_time     TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time     TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '文章表';

