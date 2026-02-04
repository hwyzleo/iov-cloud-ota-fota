package net.hwyz.iov.cloud.ota.fota.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台车辆升级任务过程
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskVehicleProcessMpt extends BaseRequest {

    /**
     * 升级任务车辆主键
     */
    private Long id;

    /**
     * 升级任务ID
     */
    private Long taskId;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 升级设备
     */
    private String device;

    /**
     * 设备序列号
     */
    private String deviceSn;

    /**
     * 执行操作
     */
    private Integer operation;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 操作结果
     */
    private Integer operationResult;

    /**
     * 重试次数
     */
    private Integer retryCount;

    /**
     * 异常代码
     */
    private Integer errorCode;

    /**
     * 异常消息
     */
    private String errorMsg;

    /**
     * 扩展内容
     */
    private String extra;

    /**
     * 创建时间
     */
    private Date createTime;

}
