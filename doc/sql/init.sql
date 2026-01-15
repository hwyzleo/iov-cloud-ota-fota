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

DROP TABLE IF EXISTS `db_fota`.`tb_article`;
CREATE TABLE `db_fota`.`tb_article`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`       VARCHAR(255) NOT NULL COMMENT '文章标题',
    `content`     LONGTEXT              DEFAULT NULL COMMENT '文章内容',
    `type`        SMALLINT     NOT NULL COMMENT '文章类型：1-活动条款，2-升级须知，3-隐私协议',
    `description` VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`   VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version` INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`   TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '文章表';

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
    `release_time`                 DATETIME              DEFAULT NULL COMMENT '活动发布时间',
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

DROP TABLE IF EXISTS `db_fota`.`tb_activity_software_build_version`;
CREATE TABLE `db_fota`.`tb_activity_software_build_version`
(
    `id`                        BIGINT    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `activity_id`               BIGINT    NOT NULL COMMENT '活动ID',
    `software_build_version_id` BIGINT    NOT NULL COMMENT '软件内部版本ID',
    `sort`                      SMALLINT  NOT NULL COMMENT '排序',
    `version_group`             SMALLINT  NOT NULL COMMENT '软件版本组',
    `force_upgrade`             TINYINT            DEFAULT 0 COMMENT '是否强制升级',
    `description`               VARCHAR(255)       DEFAULT NULL COMMENT '备注',
    `create_time`               TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`                 VARCHAR(64)        DEFAULT NULL COMMENT '创建者',
    `modify_time`               TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`                 VARCHAR(64)        DEFAULT NULL COMMENT '修改者',
    `row_version`               INT                DEFAULT 1 COMMENT '记录版本',
    `row_valid`                 TINYINT            DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_activity` (`activity_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '升级活动软件内部版本关系表';

DROP TABLE IF EXISTS `db_fota`.`tb_task`;
CREATE TABLE `db_fota`.`tb_task`
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`             VARCHAR(255) NOT NULL COMMENT '任务名称',
    `type`             SMALLINT     NOT NULL DEFAULT 1 COMMENT '任务类型：1=普通任务，2=快速任务',
    `phase`            SMALLINT     NOT NULL DEFAULT 1 COMMENT '任务阶段：1=验证，2=灰度，3=发布',
    `activity_id`      BIGINT       NOT NULL COMMENT '升级活动ID',
    `target`           VARCHAR(255) NOT NULL COMMENT '升级对象，普通任务时为文件代码，快速任务时为VIN',
    `start_time`       DATETIME              DEFAULT NULL COMMENT '任务开始时间',
    `end_time`         DATETIME              DEFAULT NULL COMMENT '任务结束时间',
    `release_time`     DATETIME              DEFAULT NULL COMMENT '任务发布时间',
    `notice_type`      VARCHAR(255)          DEFAULT NULL COMMENT '通知类型（多选）：1 手机',
    `upgrade_mode`     SMALLINT              DEFAULT NULL COMMENT '升级模式：1=普通，2=强制，3=预约静默，4=远程静默，5=工厂',
    `upgrade_mode_arg` VARCHAR(255)          DEFAULT NULL COMMENT '升级模式参数',
    `state`            SMALLINT     NOT NULL COMMENT '任务状态：1=待提交，2=待审核，3=已审核，4=未通过，5=已发布，6=已暂停，7=已结束，8=已取消',
    `description`      VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`        VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`      INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`        TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '升级任务表';

DROP TABLE IF EXISTS `db_fota`.`tb_task_vehicle`;
CREATE TABLE `db_fota`.`tb_task_vehicle`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `activity_id` BIGINT      NOT NULL COMMENT '升级活动ID',
    `task_id`     BIGINT      NOT NULL COMMENT '升级任务ID',
    `vin`         VARCHAR(20) NOT NULL COMMENT '车架号',
    `state`       SMALLINT    NOT NULL COMMENT '车辆任务状态：0=等待下载，1=开始下载，3=继续下载，5=结束下载，7=预约升级，9=自动升级，10=安装检测，11=开始安装，15=结束安装，17=开始回滚，19=结束回滚，21=升级立即重启，22=升级用户重启，23=回滚立即重启，24=回滚用户重启，25=写配置字，26=回滚配置字，90=升级失败，91=升级超时',
    `result_code` VARCHAR(20)          DEFAULT NULL COMMENT '结果代码',
    `description` VARCHAR(255)         DEFAULT NULL COMMENT '备注',
    `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   VARCHAR(64)          DEFAULT NULL COMMENT '创建者',
    `modify_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`   VARCHAR(64)          DEFAULT NULL COMMENT '修改者',
    `row_version` INT                  DEFAULT 1 COMMENT '记录版本',
    `row_valid`   TINYINT              DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_activity` (`activity_id`),
    INDEX `idx_task` (`task_id`),
    INDEX `idx_vin` (`vin`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '升级任务车辆表';

DROP TABLE IF EXISTS `db_fota`.`tb_veh_status`;
CREATE TABLE `db_fota`.`tb_veh_status`
(
    `id`                 BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `vin`                VARCHAR(20) NOT NULL COMMENT '车架号',
    `report_time`        TIMESTAMP   NULL     DEFAULT NULL COMMENT '最后上报时间',
    `baseline_code`      VARCHAR(255)         DEFAULT NULL COMMENT '最后基线代码',
    `baseline_alignment` TINYINT              DEFAULT NULL COMMENT '最后基线是否对齐',
    `ecu_info`           TEXT                 DEFAULT NULL COMMENT '最后ECU设备信息',
    `activity_id`        BIGINT               DEFAULT NULL COMMENT '最后升级活动ID',
    `task_id`            BIGINT               DEFAULT NULL COMMENT '最后升级任务ID',
    `config_word`        VARCHAR(2000)        DEFAULT NULL COMMENT '最后升级配置字',
    `master_version`     VARCHAR(255)         DEFAULT NULL COMMENT '最后OTA Master版本',
    `description`        VARCHAR(255)         DEFAULT NULL COMMENT '备注',
    `create_time`        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`          VARCHAR(64)          DEFAULT NULL COMMENT '创建者',
    `modify_time`        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`          VARCHAR(64)          DEFAULT NULL COMMENT '修改者',
    `row_version`        INT                  DEFAULT 1 COMMENT '记录版本',
    `row_valid`          TINYINT              DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_vin` (`vin`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '车辆状态表';

DROP TABLE IF EXISTS `db_fota`.`tb_activity_fixed_config_word`;
CREATE TABLE `db_fota`.`tb_activity_fixed_config_word`
(
    `id`                   BIGINT    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `activity_id`          BIGINT    NOT NULL COMMENT '升级活动ID',
    `fixed_config_word_id` BIGINT    NOT NULL COMMENT '固定配置字ID',
    `description`          VARCHAR(255)       DEFAULT NULL COMMENT '备注',
    `create_time`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`            VARCHAR(64)        DEFAULT NULL COMMENT '创建者',
    `modify_time`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`            VARCHAR(64)        DEFAULT NULL COMMENT '修改者',
    `row_version`          INT                DEFAULT 1 COMMENT '记录版本',
    `row_valid`            TINYINT            DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_activity` (`activity_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '升级活动固定配置字表';

DROP TABLE IF EXISTS `db_fota`.`tb_compatible_software_pn`;
CREATE TABLE `db_fota`.`tb_compatible_software_pn`
(
    `id`                     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `ecu`                    VARCHAR(20)  NOT NULL COMMENT '零部件ECU',
    `software_pn`            VARCHAR(50)  NOT NULL COMMENT '软件零件号',
    `compatible_software_pn` VARCHAR(255) NOT NULL COMMENT '兼容软件零件号',
    `type`                   SMALLINT              DEFAULT NULL COMMENT '分类',
    `description`            VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`              VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`              VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`            INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`              TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '兼容软件零件号表';

DROP TABLE IF EXISTS `db_fota`.`tb_task_restriction`;
CREATE TABLE `db_fota`.`tb_task_restriction`
(
    `id`                     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `task_id`                BIGINT       NOT NULL COMMENT '升级任务ID',
    `restriction_type`       VARCHAR(255) NOT NULL COMMENT '限制条件类型',
    `restriction_expression` VARCHAR(255) NOT NULL COMMENT '限制条件表达式',
    `description`            VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`              VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`              VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`            INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`              TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_task` (`task_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '升级任务限制条件表';
