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
 * 固定配置字明细表 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-17
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_fixed_config_word_detail")
public class FixedConfigWordDetailPo extends BasePo {

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
