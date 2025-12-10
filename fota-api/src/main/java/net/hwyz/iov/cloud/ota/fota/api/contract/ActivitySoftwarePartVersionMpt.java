package net.hwyz.iov.cloud.ota.fota.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台升级活动下软件零件版本信息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ActivitySoftwarePartVersionMpt extends BaseRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 软件零件版本ID
     */
    private Long softwarePartVersionId;

    /**
     * ECU编码
     */
    private String ecuCode;

    /**
     * 软件零件号
     */
    private String softwarePn;

    /**
     * 软件零件名称
     */
    private String softwarePartName;

    /**
     * 软件零件版本
     */
    private String softwarePartVer;

    /**
     * 软件来源：1-BOM，2-OTA
     */
    private Integer softwareSource;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 软件版本组
     */
    private Integer versionGroup;

    /**
     * 创建时间
     */
    private Date createTime;

}
