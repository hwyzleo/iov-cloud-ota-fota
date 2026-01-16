package net.hwyz.iov.cloud.ota.fota.service.domain.task.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.BaseDo;
import net.hwyz.iov.cloud.framework.common.domain.DomainObj;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.TaskRestrictionType;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.TaskState;
import net.hwyz.iov.cloud.ota.fota.service.domain.contract.enums.TaskPhase;
import net.hwyz.iov.cloud.ota.fota.service.domain.contract.enums.TaskType;
import net.hwyz.iov.cloud.ota.fota.service.domain.contract.enums.UpgradeMode;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskPo;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model.VehicleDo;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 升级任务领域对象
 *
 * @author hwyz_leo
 */
@Slf4j
@Getter
@SuperBuilder
public class TaskDo extends BaseDo<Long> implements DomainObj<TaskDo> {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型
     */
    private TaskType type;

    /**
     * 任务阶段
     */
    private TaskPhase phase;

    /**
     * 升级活动ID
     */
    private Long activityId;

    /**
     * 升级对象，普通任务时为文件代码，快速任务时为VIN
     */
    private String target;

    /**
     * 升级车辆列表
     */
    private Set<String> vehicles;

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
    private Date releaseTime;

    /**
     * 通知类型（多选）：1 手机
     */
    private String noticeType;

    /**
     * 升级模式
     */
    private UpgradeMode upgradeMode;

    /**
     * 升级模式参数
     */
    private JSONObject upgradeModeArg;

    /**
     * 升级任务状态
     */
    private TaskState taskState;

    /**
     * 备注
     */
    private String description;

    /**
     * 升级任务限制条件列表
     */
    private List<TaskRestrictionVo> taskRestrictionList;

    /**
     * 初始化
     */
    public void init() {
        stateInit();
    }

    /**
     * 修改信息
     *
     * @param taskPo 升级任务
     */
    public void edit(TaskPo taskPo) {
        if (StrUtil.isNotBlank(taskPo.getName()) && !taskPo.getName().equals(this.name)) {
            this.name = taskPo.getName();
            stateChange();
        }
        if (taskPo.getType() != null) {
            TaskType taskType = TaskType.valOf(taskPo.getType());
            if (taskType != this.type) {
                this.type = taskType;
                stateChange();
            }
        }
        if (taskPo.getPhase() != null) {
            TaskPhase taskPhase = TaskPhase.valOf(taskPo.getPhase());
            if (taskPhase != this.phase) {
                this.phase = taskPhase;
                stateChange();
            }
        }
        if (taskPo.getActivityId() != null && !taskPo.getActivityId().equals(this.activityId)) {
            this.activityId = taskPo.getActivityId();
            stateChange();
        }
        if (taskPo.getStartTime() != null && !taskPo.getStartTime().equals(this.startTime)) {
            this.startTime = taskPo.getStartTime();
            stateChange();
        }
        if (taskPo.getEndTime() != null && !taskPo.getEndTime().equals(this.endTime)) {
            this.endTime = taskPo.getEndTime();
            stateChange();
        }
        if (taskPo.getNoticeType() != null && !taskPo.getNoticeType().equals(this.noticeType)) {
            this.noticeType = taskPo.getNoticeType();
            stateChange();
        }
        if (taskPo.getUpgradeMode() != null && UpgradeMode.valOf(taskPo.getUpgradeMode()) != this.upgradeMode) {
            UpgradeMode upgradeModeTmp = UpgradeMode.valOf(taskPo.getUpgradeMode());
            if (upgradeModeTmp != this.upgradeMode) {
                this.upgradeMode = upgradeModeTmp;
                stateChange();
            }
        }
        if (taskPo.getUpgradeModeArg() != null) {
            JSONObject upgradeModeArgJson = JSONUtil.parseObj(taskPo.getUpgradeModeArg());
            if (!upgradeModeArgJson.equals(this.upgradeModeArg)) {
                this.upgradeModeArg = upgradeModeArgJson;
                stateChange();
            }
        }
    }

    /**
     * 提交任务
     *
     * @param taskPo 升级任务
     */
    public int submit(TaskPo taskPo) {
        if (this.taskState == TaskState.PENDING) {
            edit(taskPo);
            this.taskState = TaskState.SUBMITTED;
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 审核任务
     *
     * @param audit  审核结果
     * @param reason 拒绝原因
     * @return 1: 成功，0: 失败
     */
    public int audit(Boolean audit, String reason) {
        if (this.taskState == TaskState.SUBMITTED) {
            if (audit) {
                this.taskState = TaskState.APPROVED;
            } else {
                this.taskState = TaskState.REJECTED;
                this.description = reason;
            }
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 发布任务
     *
     * @return 1: 成功，0: 失败
     */
    public int release() {
        if (this.taskState == TaskState.APPROVED) {
            this.vehicles = Set.of(this.target.split(","));
            this.releaseTime = new Date();
            this.taskState = TaskState.RELEASED;
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 暂停任务
     *
     * @return 1: 成功，0: 失败
     */
    public int pause() {
        if (this.taskState == TaskState.RELEASED) {
            this.taskState = TaskState.PAUSED;
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 恢复任务
     *
     * @return 1: 成功，0: 失败
     */
    public int resume() {
        if (this.taskState == TaskState.PAUSED) {
            this.taskState = TaskState.RELEASED;
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 取消任务
     *
     * @return 1: 成功，0: 失败
     */
    public int cancel() {
        if (this.taskState == TaskState.RELEASED || this.taskState == TaskState.PAUSED) {
            this.taskState = TaskState.CANCELLED;
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 检查任务下载前置条件
     *
     * @param vehicle 车辆
     * @return true: 满足条件，false: 不满足条件
     */
    public boolean checkPreconditions(VehicleDo vehicle) {
        if (!checkTaskTimeRange()) {
            return false;
        }
        if (!checkRestrictions(vehicle)) {
            return false;
        }
        // TODO 检查各类条件（是否基线拉齐、……）
        return true;
    }

    /**
     * 检查任务时间范围
     *
     * @return true: 满足条件，false: 不满足条件
     */
    private boolean checkTaskTimeRange() {
        return System.currentTimeMillis() >= this.startTime.getTime() && System.currentTimeMillis() <= this.endTime.getTime();
    }

    /**
     * 检查各项任务限制
     *
     * @param vehicle 车辆
     * @return true: 满足条件，false: 不满足条件
     */
    private boolean checkRestrictions(VehicleDo vehicle) {
        for (TaskRestrictionVo restriction : this.taskRestrictionList) {
            switch (TaskRestrictionType.valueOf(restriction.getRestrictionType())) {
                case BASELINE_EXCLUDE -> {
                    // 检查车辆基线是否在任务排除的基线范围内
                    for (String baseline : restriction.getRestrictionExpression().split(",")) {
                        if (baseline.equals(vehicle.getBaselineCode())) {
                            return false;
                        }
                    }
                }
                case BASELINE_UNIFICATION -> {
                    // 未对齐基线的车辆如果未打开强制拉齐则不满足条件
                    if (!Boolean.parseBoolean(restriction.getRestrictionExpression()) && !vehicle.getIsBaselineAlignment()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
