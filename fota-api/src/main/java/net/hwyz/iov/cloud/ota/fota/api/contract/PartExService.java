package net.hwyz.iov.cloud.ota.fota.api.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对外服务零部件信息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartExService {

    /**
     * 序列号
     */
    private String sn;

    /**
     * 零件编号
     */
    private String no;

    /**
     * 零部件ECU
     */
    private String ecu;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 配置字
     */
    private String configWord;

    /**
     * 硬件版本号
     */
    private String hardwareVer;

    /**
     * 软件版本号
     */
    private String softwareVer;

    /**
     * 硬件零件号
     */
    private String hardwareNo;

    /**
     * 软件零件号
     */
    private String softwareNo;

}
