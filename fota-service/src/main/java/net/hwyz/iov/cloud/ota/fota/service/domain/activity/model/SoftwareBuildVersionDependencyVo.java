package net.hwyz.iov.cloud.ota.fota.service.domain.activity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 零件内部版本依赖值对象
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareBuildVersionDependencyVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 软件内部版本ID
     */
    private Long softwareBuildVersionId;

    /**
     * 依赖软件内部版本ID
     */
    private Long dependencySoftwareBuildVersionId;

    /**
     * 适配级别：1-版本及以下，2-版本及以上，3-与版本一致
     */
    private Integer adaptionLevel;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

}
