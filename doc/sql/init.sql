DROP TABLE IF EXISTS `db_fota`.`tb_part`;
CREATE TABLE `db_fota`.`tb_part`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sn`            VARCHAR(255) NOT NULL COMMENT '序列号',
    `no`            VARCHAR(255)          DEFAULT NULL COMMENT '零部件编号',
    `ecu`           VARCHAR(20)  NOT NULL COMMENT '零部件ECU',
    `config_word`   VARCHAR(255)          DEFAULT NULL COMMENT '配置字',
    `supplier_code` VARCHAR(255)          DEFAULT NULL COMMENT '供应商编码',
    `hardware_ver`  VARCHAR(255)          DEFAULT NULL COMMENT '硬件版本号',
    `software_ver`  VARCHAR(255)          DEFAULT NULL COMMENT '软件版本号',
    `hardware_no`   VARCHAR(255)          DEFAULT NULL COMMENT '硬件零件号',
    `software_no`   VARCHAR(255)          DEFAULT NULL COMMENT '软件零件号',
    `description`   VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`     VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`     VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`   INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`     TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`sn`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='零部件信息表';

DROP TABLE IF EXISTS `db_fota`.`tb_part_log`;
CREATE TABLE `db_fota`.`tb_part_log`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sn`           VARCHAR(255) NOT NULL COMMENT '序列号',
    `config_word`  VARCHAR(255)          DEFAULT NULL COMMENT '配置字',
    `hardware_ver` VARCHAR(255)          DEFAULT NULL COMMENT '硬件版本号',
    `software_ver` VARCHAR(255)          DEFAULT NULL COMMENT '软件版本号',
    `hardware_no`  VARCHAR(255)          DEFAULT NULL COMMENT '硬件零件号',
    `software_no`  VARCHAR(255)          DEFAULT NULL COMMENT '软件零件号',
    `description`  VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`    VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`    VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`  INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`    TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    INDEX `idx_sn` (`sn`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='零部件信息变更日志表';

DROP TABLE IF EXISTS `db_fota`.`tb_veh_part`;
CREATE TABLE `db_fota`.`tb_veh_part`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `vin`         VARCHAR(20)  NOT NULL COMMENT '车架号',
    `ecu`         VARCHAR(20)  NOT NULL COMMENT '零部件ECU',
    `part_sn`     VARCHAR(255) NOT NULL COMMENT '零部件序列号',
    `description` VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`   VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version` INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`   TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    INDEX `idx_vin` (`vin`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='车辆零部件关系表';

DROP TABLE IF EXISTS `db_fota`.`tb_veh_part_log`;
CREATE TABLE `db_fota`.`tb_veh_part_log`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `vin`         VARCHAR(20)  NOT NULL COMMENT '车架号',
    `ecu`         VARCHAR(20)  NOT NULL COMMENT '零部件ECU',
    `part_sn`     VARCHAR(255) NOT NULL COMMENT '零部件序列号',
    `description` VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`   VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version` INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`   TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`),
    INDEX `idx_vin` (`vin`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='车辆零部件关系变更日志表';

DROP TABLE IF EXISTS `db_fota`.`tb_activity`;
CREATE TABLE `db_fota`.`tb_activity`
(
    `id`                           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`                         VARCHAR(255) NOT NULL COMMENT '活动名称',
    `version`                      VARCHAR(255) NOT NULL COMMENT '活动版本',
    `upgrade_notice_article_id`    BIGINT                DEFAULT NULL COMMENT '升级须知文章ID',
    `activity_term_article_id`     BIGINT                DEFAULT NULL COMMENT '活动条款文章ID',
    `privacy_agreement_article_id` BIGINT                DEFAULT NULL COMMENT '隐私协议文章ID',
    `start_time`                   DATETIME              DEFAULT NULL COMMENT '活动开始时间',
    `end_time`                     DATETIME              DEFAULT NULL COMMENT '活动结束时间',
    `publish_time`                 DATETIME              DEFAULT NULL COMMENT '活动发布时间',
    `upgrade_purpose`              TEXT                  DEFAULT NULL COMMENT '升级目的',
    `upgrade_function`             TEXT                  DEFAULT NULL COMMENT '升级功能项',
    `statement`                    LONGTEXT              DEFAULT NULL COMMENT '活动说明',
    `state`                        SMALLINT     NOT NULL COMMENT '活动状态：1 待提交，2 待审核，3 已审核，4 未通过，5 已发布，6 已结束，7 已取消',
    `total_file_size`              BIGINT       NOT NULL DEFAULT 1 COMMENT '总文件大小（MB）',
    `baseline`                     TINYINT      NOT NULL DEFAULT 0 COMMENT '是否基线活动',
    `baseline_code`                VARCHAR(255)          DEFAULT NULL COMMENT '基线代码',
    `description`                  VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`                  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`                    VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`                  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`                    VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`                  INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`                    TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '升级活动表';
