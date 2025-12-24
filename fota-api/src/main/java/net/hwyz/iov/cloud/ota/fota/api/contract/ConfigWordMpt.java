package net.hwyz.iov.cloud.ota.fota.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台配置字
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigWordMpt extends BaseRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 固定配置字ID
     */
    private Long fixedConfigWordId;

    /**
     * 零部件ECU
     */
    private String ecu;

    /**
     * 软件零件号
     */
    private String softwarePn;

    /**
     * 软件零件版本
     */
    private String softwarePartVer;

    /**
     * 配置字版本
     */
    private String configWordVersion;

    /**
     * 起始byte
     */
    private Integer startByte;

    /**
     * 起始bit
     */
    private Integer startBit;

    /**
     * 配置字值
     */
    private String configWordValue;

    /**
     * 依赖零部件ECU
     */
    private String dependEcu;

    /**
     * 依赖ECU软件零件号
     */
    private String dependEcuSoftwarePn;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}
