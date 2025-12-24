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
 * 配置字表 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-24
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_config_word")
public class ConfigWordPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 固定配置字ID
     */
    @TableField("fixed_config_word_id")
    private Long fixedConfigWordId;

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
     * 软件零件版本
     */
    @TableField("software_part_ver")
    private String softwarePartVer;

    /**
     * 配置字版本
     */
    @TableField("config_word_version")
    private String configWordVersion;

    /**
     * 起始byte
     */
    @TableField("start_byte")
    private Integer startByte;

    /**
     * 起始bit
     */
    @TableField("start_bit")
    private Integer startBit;

    /**
     * 配置字值
     */
    @TableField("config_word_value")
    private String configWordValue;

    /**
     * 依赖零部件ECU
     */
    @TableField("depend_ecu")
    private String dependEcu;

    /**
     * 依赖ECU软件零件号
     */
    @TableField("depend_ecu_software_pn")
    private String dependEcuSoftwarePn;
}
