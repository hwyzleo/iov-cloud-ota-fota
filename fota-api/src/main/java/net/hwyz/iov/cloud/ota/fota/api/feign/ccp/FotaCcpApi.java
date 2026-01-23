package net.hwyz.iov.cloud.ota.fota.api.feign.ccp;

import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.ota.fota.api.contract.CloudFotaInfoCcp;
import net.hwyz.iov.cloud.ota.fota.api.contract.VehicleFotaInfoCcp;

/**
 * 在线固件升级中央计算平台接口
 *
 * @author hwyz_leo
 */
public interface FotaCcpApi {

    /**
     * 检查车辆升级信息
     *
     * @param vin             车架号
     * @param vehicleFotaInfo 车辆升级信息
     * @return 云端升级信息
     */
    Response<CloudFotaInfoCcp> check(String vin, VehicleFotaInfoCcp vehicleFotaInfo);

}
