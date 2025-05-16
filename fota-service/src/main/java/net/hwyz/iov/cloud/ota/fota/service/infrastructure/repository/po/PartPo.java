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
 * 零部件信息表 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-16
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_part")
public class PartPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 序列号
     */
    @TableField("sn")
    private String sn;

    /**
     * 零部件编号
     */
    @TableField("no")
    private String no;

    /**
     * 零部件ECU
     */
    @TableField("ecu")
    private String ecu;

    /**
     * 配置字
     */
    @TableField("config_word")
    private String configWord;

    /**
     * 供应商编码
     */
    @TableField("supplier_code")
    private String supplierCode;

    /**
     * 硬件版本号
     */
    @TableField("hardware_ver")
    private String hardwareVer;

    /**
     * 软件版本号
     */
    @TableField("software_ver")
    private String softwareVer;

    /**
     * 硬件零件号
     */
    @TableField("hardware_no")
    private String hardwareNo;

    /**
     * 软件零件号
     */
    @TableField("software_no")
    private String softwareNo;
}
