package net.hwyz.iov.cloud.ota.fota.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台升级任务
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskMpt extends BaseRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型：1 普通任务，2 快速任务
     */
    private Integer type;

    /**
     * 任务阶段：1 验证，2 灰度，3 发布
     */
    private Integer stage;

    /**
     * 升级活动ID
     */
    private Long activityId;

    /**
     * 升级对象，普通任务时为文件代码，快速任务时为VIN
     */
    private String target;

    /**
     * 任务开始时间
     */
    private Date startTime;

    /**
     * 任务结束时间
     */
    private Date endTime;

    /**
     * 任务发布时间
     */
    private Date publishTime;

    /**
     * 限制条件（多选）：1 需保持驻车，2 非充电模式，3 非供电模式，4 车窗、天窗及尾门需关闭，5 电压需稳定在9V以上，6 需无高压，7 需锁车状态，8 需解锁状态
     */
    private String limitCondition;

    /**
     * 小电瓶电量限制
     */
    private Integer limitIbsSoc;

    /**
     * 高压电池电量限制
     */
    private Integer limitBmsSoc;

    /**
     * 通知类型（多选）：1 手机
     */
    private String noticeType;

    /**
     * 升级模式：1 普通，2 强制，3 预约静默，4 远程静默，5 工厂
     */
    private Integer upgradeMode;

    /**
     * 预约升级时间
     */
    private Date appointmentTime;

    /**
     * ECU尝试刷写次数
     */
    private Integer ecuTryLimit;

    /**
     * 刷写失败是否回滚
     */
    private Boolean failRollback;

    /**
     * 适配主体：1 软件零件号，2 软件版本，3 两者均适配，4 两者均不适配
     */
    private Integer adaptation;

    /**
     * 基线是否对齐
     */
    private Boolean baselineAlignment;

    /**
     * 升级前是否版本校验
     */
    private Boolean versionCheck;

    /**
     * 是否兼容零件总成号
     */
    private Boolean partNoCompatible;

    /**
     * 用车是否影响
     */
    private Boolean vehicleImpact;

    /**
     * 全量包是否优先
     */
    private Boolean fullPackageFirst;

    /**
     * 任务状态：1 待提交，2 待审核，3 已审核，4 未通过，5 已发布，6 已暂停，7 已结束，8 已取消
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

}
