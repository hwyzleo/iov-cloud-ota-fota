package net.hwyz.iov.cloud.ota.fota.api.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 中央计算平台云端在线固件升级信息
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudFotaInfoCcp {

    /**
     * 基线
     */
    private String baseline;

    /**
     * ECU设备信息列表
     */
    private List<EcuInfoCcp> ecuInfoCcpList;

}
