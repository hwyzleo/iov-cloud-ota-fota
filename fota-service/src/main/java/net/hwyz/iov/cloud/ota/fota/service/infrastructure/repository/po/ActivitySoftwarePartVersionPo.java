package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.hwyz.iov.cloud.framework.mysql.po.BasePo;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 升级活动软件零件版本关系表 数据对象
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
@TableName("tb_activity_software_part_version")
public class ActivitySoftwarePartVersionPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动ID
     */
    @TableField("activity_id")
    private Long activityId;

    /**
     * 软件零件版本ID
     */
    @TableField("software_part_version_id")
    private Long softwarePartVersionId;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 软件版本组
     */
    @TableField("group")
    private Integer group;
}
