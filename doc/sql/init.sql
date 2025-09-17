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

DROP TABLE IF EXISTS `db_fota`.`tb_task`;
CREATE TABLE `db_fota`.`tb_task`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`               VARCHAR(255) NOT NULL COMMENT '任务名称',
    `type`               SMALLINT     NOT NULL DEFAULT 1 COMMENT '任务类型：1 普通任务，2 快速任务',
    `stage`              SMALLINT     NOT NULL DEFAULT 1 COMMENT '任务阶段：1 验证，2 灰度，3 发布',
    `activity_id`        BIGINT       NOT NULL COMMENT '升级活动ID',
    `target`             VARCHAR(255) NOT NULL COMMENT '升级对象，普通任务时为文件代码，快速任务时为VIN',
    `start_time`         DATETIME              DEFAULT NULL COMMENT '任务开始时间',
    `end_time`           DATETIME              DEFAULT NULL COMMENT '任务结束时间',
    `publish_time`       DATETIME              DEFAULT NULL COMMENT '任务发布时间',
    `limit_condition`    VARCHAR(255)          DEFAULT NULL COMMENT '限制条件（多选）：1 需保持驻车，2 非充电模式，3 非供电模式，4 车窗、天窗及尾门需关闭，5 电压需稳定在9V以上，6 需无高压，7 需锁车状态，8 需解锁状态',
    `limit_ibs_soc`      SMALLINT              DEFAULT NULL COMMENT '小电瓶电量限制',
    `limit_bms_soc`      SMALLINT              DEFAULT NULL COMMENT '高压电池电量限制',
    `notice_type`        VARCHAR(255)          DEFAULT NULL COMMENT '通知类型（多选）：1 手机',
    `upgrade_mode`       SMALLINT              DEFAULT NULL COMMENT '升级模式：1 普通，2 强制，3 预约静默，4 远程静默，5 工厂',
    `appointment_time`   DATETIME              DEFAULT NULL COMMENT '预约升级时间',
    `ecu_try_limit`      SMALLINT              DEFAULT NULL COMMENT 'ECU尝试刷写次数',
    `fail_rollback`      TINYINT               DEFAULT NULL COMMENT '刷写失败是否回滚',
    `adaptation`         SMALLINT              DEFAULT NULL COMMENT '适配主体：1 软件零件号，2 软件版本，3 两者均适配，4 两者均不适配',
    `baseline_alignment` TINYINT               DEFAULT NULL COMMENT '基线是否对齐',
    `version_check`      TINYINT               DEFAULT NULL COMMENT '升级前是否版本校验',
    `part_no_compatible` TINYINT               DEFAULT NULL COMMENT '是否兼容零件总成号',
    `vehicle_impact`     TINYINT      NOT NULL DEFAULT 0 COMMENT '用车是否影响',
    `full_package_first` TINYINT      NOT NULL DEFAULT 0 COMMENT '全量包是否优先',
    `state`              SMALLINT     NOT NULL COMMENT '任务状态：1 待提交，2 待审核，3 已审核，4 未通过，5 已发布，6 已暂停，7 已结束，8 已取消',
    `description`        VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    `create_time`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`          VARCHAR(64)           DEFAULT NULL COMMENT '创建者',
    `modify_time`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `modify_by`          VARCHAR(64)           DEFAULT NULL COMMENT '修改者',
    `row_version`        INT                   DEFAULT 1 COMMENT '记录版本',
    `row_valid`          TINYINT               DEFAULT 1 COMMENT '记录是否有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '升级任务表';
