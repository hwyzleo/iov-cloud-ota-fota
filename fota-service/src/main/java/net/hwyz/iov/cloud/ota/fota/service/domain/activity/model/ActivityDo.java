package net.hwyz.iov.cloud.ota.fota.service.domain.activity.model;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.BaseDo;
import net.hwyz.iov.cloud.framework.common.domain.DomainObj;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.ActivityState;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityPo;

import java.util.Date;

/**
 * 升级活动领域对象
 *
 * @author hwyz_leo
 */
@Slf4j
@Getter
@SuperBuilder
public class ActivityDo extends BaseDo<Long> implements DomainObj<ActivityDo> {

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动版本
     */
    private String version;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动发布时间
     */
    private Date releaseTime;

    /**
     * 活动状态
     */
    private ActivityState activityState;

    /**
     * 是否基线活动
     */
    private Boolean baseline;

    /**
     * 基线代码
     */
    private String baselineCode;

    /**
     * 备注
     */
    private String description;

    /**
     * 初始化
     */
    public void init() {
        stateInit();
    }

    /**
     * 修改信息
     *
     * @param activityPo 升级活动
     */
    public void edit(ActivityPo activityPo) {
        if (StrUtil.isNotBlank(activityPo.getName()) && !activityPo.getName().equals(this.name)) {
            this.name = activityPo.getName();
            stateChange();
        }
        if (StrUtil.isNotBlank(activityPo.getVersion()) && !activityPo.getVersion().equals(this.version)) {
            this.version = activityPo.getVersion();
            stateChange();
        }
        if (activityPo.getStartTime() != null && !activityPo.getStartTime().equals(this.startTime)) {
            this.startTime = activityPo.getStartTime();
            stateChange();
        }
        if (activityPo.getEndTime() != null && !activityPo.getEndTime().equals(this.endTime)) {
            this.endTime = activityPo.getEndTime();
            stateChange();
        }
    }

    /**
     * 提交活动
     *
     * @param activityPo 升级活动
     */
    public int submit(ActivityPo activityPo) {
        if (this.activityState == ActivityState.PENDING) {
            edit(activityPo);
            this.activityState = ActivityState.SUBMITTED;
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 审核活动
     *
     * @param audit  审核结果
     * @param reason 拒绝原因
     * @return 1: 成功，0: 失败
     */
    public int audit(Boolean audit, String reason) {
        if (this.activityState == ActivityState.SUBMITTED) {
            if (audit) {
                this.activityState = ActivityState.APPROVED;
            } else {
                this.activityState = ActivityState.REJECTED;
                this.description = reason;
            }
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 发布活动
     *
     * @return 1: 成功，0: 失败
     */
    public int release() {
        if (this.activityState == ActivityState.APPROVED) {
            this.releaseTime = new Date();
            this.activityState = ActivityState.RELEASED;
            stateChange();
            return 1;
        }
        return 0;
    }

    /**
     * 取消活动
     *
     * @return 1: 成功，0: 失败
     */
    public int cancel() {
        if (this.activityState == ActivityState.RELEASED) {
            this.activityState = ActivityState.CANCELLED;
            stateChange();
            return 1;
        }
        return 0;
    }

}
