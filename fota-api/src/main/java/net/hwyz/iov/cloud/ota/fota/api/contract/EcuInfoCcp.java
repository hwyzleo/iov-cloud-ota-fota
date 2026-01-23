package net.hwyz.iov.cloud.ota.fota.api.contract;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 中央计算平台ECU设备信息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcuInfoCcp {

    /**
     * ECU零部件
     */
    @NotBlank(message = "ECU不能为空")
    private String ecu;

    /**
     * 零件序列号
     */
    private String partSn;

    /**
     * 零件号（总成号）
     */
    @NotBlank(message = "零件号不能为空")
    private String partNo;

    /**
     * 硬件零件号
     */
    private String hardwarePn;

    /**
     * 硬件零件版本号
     */
    private String hardwarePartVer;

    /**
     * 软件零件号
     */
    @NotBlank(message = "软件零件号不能为空")
    private String softwarePn;

    /**
     * 软件零件版本号
     */
    @NotBlank(message = "软件零件版本号不能为空")
    private String softwarePartVer;

    /**
     * 配置字
     */
    private String configWord;

    /**
     * 额外信息
     */
    private Map<String, String> extra;

}
