package net.hwyz.iov.cloud.ota.fota.service.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.ota.fota.api.contract.request.SaveVehiclePartsRequest;
import net.hwyz.iov.cloud.ota.fota.service.application.service.VehiclePartAppService;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.PartExServiceAssembler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 车辆零部件相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/vehiclePart")
public class VehiclePartServiceController {

    private final VehiclePartAppService vehiclePartAppService;

    /**
     * 保存车辆零部件信息
     *
     * @param request 保存车辆零部件信息请求
     */
    @PutMapping("/{vin}/action/saveParts")
    void saveVehicleParts(@PathVariable("vin") String vin, @RequestBody @Validated SaveVehiclePartsRequest request) {
        logger.info("保存车辆[{}]零部件信息", vin);
        vehiclePartAppService.saveVehicleParts(vin, request.getRemark(), PartExServiceAssembler.INSTANCE.toPoList(request.getPartList()));
    }

}
