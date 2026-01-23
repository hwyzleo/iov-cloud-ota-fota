package net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

/**
 * ECU设备信息值对象
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EcuInfoVo {

    /**
     * ECU零部件
     */
    private String ecu;

    /**
     * 零件序列号
     */
    private String partSn;

    /**
     * 零件号（总成号）
     */
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
    private String softwarePn;

    /**
     * 软件零件版本号
     */
    private String softwarePartVer;

    /**
     * 软件内部版本
     */
    private String softwareBuildVer;

    /**
     * 配置字
     */
    private String configWord;

    /**
     * 额外信息
     */
    private Map<String, String> extra;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcuInfoVo ecuInfoVo = (EcuInfoVo) o;
        return Objects.equals(ecu, ecuInfoVo.ecu) && Objects.equals(partSn, ecuInfoVo.partSn) && Objects.equals(partNo, ecuInfoVo.partNo) && Objects.equals(hardwarePn, ecuInfoVo.hardwarePn) && Objects.equals(hardwarePartVer, ecuInfoVo.hardwarePartVer) && Objects.equals(softwarePn, ecuInfoVo.softwarePn) && Objects.equals(softwarePartVer, ecuInfoVo.softwarePartVer) && Objects.equals(configWord, ecuInfoVo.configWord) && Objects.equals(extra, ecuInfoVo.extra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ecu, partSn, partNo, hardwarePn, hardwarePartVer, softwarePn, softwarePartVer, configWord, extra);
    }
}
