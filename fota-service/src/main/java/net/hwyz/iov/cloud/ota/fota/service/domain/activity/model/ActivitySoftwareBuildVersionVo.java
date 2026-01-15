package net.hwyz.iov.cloud.ota.fota.service.domain.activity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 活动软件内部版本信息值对象
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySoftwareBuildVersionVo {

    /**
     * 分组
     */
    private Integer group;

    /**
     * 是否强制升级
     */
    private Boolean forceUpgrade;

    /**
     * 软件内部版本
     */
    private SoftwareBuildVersionVo softwareBuildVersion;

    /**
     * 软件包列表
     */
    private List<SoftwarePackageVo> softwarePackageList;

    /**
     * 软件内部版本依赖列表
     */
    private List<SoftwareBuildVersionDependencyVo> softwareBuildVersionDependencyList;

    /**
     * 创建时间
     */
    private Date createTime;

}
