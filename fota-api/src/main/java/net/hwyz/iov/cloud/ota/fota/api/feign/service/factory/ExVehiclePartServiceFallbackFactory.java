package net.hwyz.iov.cloud.ota.fota.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.ota.fota.api.contract.request.UpdateVehiclePartsRequest;
import net.hwyz.iov.cloud.ota.fota.api.feign.service.ExVehiclePartService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 车辆零部件相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExVehiclePartServiceFallbackFactory implements FallbackFactory<ExVehiclePartService> {

    @Override
    public ExVehiclePartService create(Throwable throwable) {
        return new ExVehiclePartService() {
            @Override
            public void saveVehicleParts(String vin, UpdateVehiclePartsRequest request) {
                logger.error("车辆零部件相关服务保存车辆[{}]零部件信息调用异常", vin, throwable);
            }
        };
    }
}
