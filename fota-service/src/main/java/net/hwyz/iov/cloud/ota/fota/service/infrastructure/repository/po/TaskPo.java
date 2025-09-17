package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import net.hwyz.iov.cloud.framework.mysql.po.BasePo;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 升级任务表 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-09-17
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_task")
public class TaskPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务名称
     */
    @TableField("name")
    private String name;

    /**
     * 任务类型：1 普通任务，2 快速任务
     */
    @TableField("type")
    private Integer type;

    /**
     * 任务阶段：1 验证，2 灰度，3 发布
     */
    @TableField("stage")
    private Integer stage;

    /**
     * 升级活动ID
     */
    @TableField("activity_id")
    private Long activityId;

    /**
     * 升级对象，普通任务时为文件代码，快速任务时为VIN
     */
    @TableField("target")
    private String target;

    /**
     * 任务开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 任务结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 任务发布时间
     */
    @TableField("publish_time")
    private Date publishTime;

    /**
     * 限制条件（多选）：1 需保持驻车，2 非充电模式，3 非供电模式，4 车窗、天窗及尾门需关闭，5 电压需稳定在9V以上，6 需无高压，7 需锁车状态，8 需解锁状态
     */
    @TableField("limit_condition")
    private String limitCondition;

    /**
     * 小电瓶电量限制
     */
    @TableField("limit_ibs_soc")
    private Integer limitIbsSoc;

    /**
     * 高压电池电量限制
     */
    @TableField("limit_bms_soc")
    private Integer limitBmsSoc;

    /**
     * 通知类型（多选）：1 手机
     */
    @TableField("notice_type")
    private String noticeType;

    /**
     * 升级模式：1 普通，2 强制，3 预约静默，4 远程静默，5 工厂
     */
    @TableField("upgrade_mode")
    private Integer upgradeMode;

    /**
     * 预约升级时间
     */
    @TableField("appointment_time")
    private Date appointmentTime;

    /**
     * ECU尝试刷写次数
     */
    @TableField("ecu_try_limit")
    private Integer ecuTryLimit;

    /**
     * 刷写失败是否回滚
     */
    @TableField("fail_rollback")
    private Boolean failRollback;

    /**
     * 适配主体：1 软件零件号，2 软件版本，3 两者均适配，4 两者均不适配
     */
    @TableField("adaptation")
    private Integer adaptation;

    /**
     * 基线是否对齐
     */
    @TableField("baseline_alignment")
    private Boolean baselineAlignment;

    /**
     * 升级前是否版本校验
     */
    @TableField("version_check")
    private Boolean versionCheck;

    /**
     * 是否兼容零件总成号
     */
    @TableField("part_no_compatible")
    private Boolean partNoCompatible;

    /**
     * 用车是否影响
     */
    @TableField("vehicle_impact")
    private Boolean vehicleImpact;

    /**
     * 全量包是否优先
     */
    @TableField("full_package_first")
    private Boolean fullPackageFirst;

    /**
     * 任务状态：1 待提交，2 待审核，3 已审核，4 未通过，5 已发布，6 已暂停，7 已结束，8 已取消
     */
    @TableField("state")
    private Integer state;
}
