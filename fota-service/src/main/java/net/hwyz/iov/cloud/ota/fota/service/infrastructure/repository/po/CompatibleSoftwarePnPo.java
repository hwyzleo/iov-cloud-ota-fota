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
 * 兼容软件零件号表 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-23
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_compatible_software_pn")
public class CompatibleSoftwarePnPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 零部件ECU
     */
    @TableField("ecu")
    private String ecu;

    /**
     * 软件零件号
     */
    @TableField("software_pn")
    private String softwarePn;

    /**
     * 兼容软件零件号
     */
    @TableField("compatible_software_pn")
    private String compatibleSoftwarePn;

    /**
     * 分类
     */
    @TableField("type")
    private Integer type;
}
