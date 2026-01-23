package net.hwyz.iov.cloud.ota.fota.service.facade.ccp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.framework.common.web.controller.BaseController;
import net.hwyz.iov.cloud.ota.fota.api.contract.CloudFotaInfoCcp;
import net.hwyz.iov.cloud.ota.fota.api.contract.VehicleFotaInfoCcp;
import net.hwyz.iov.cloud.ota.fota.api.feign.ccp.FotaCcpApi;
import net.hwyz.iov.cloud.ota.fota.service.domain.activity.repository.ActivityRepository;
import net.hwyz.iov.cloud.ota.fota.service.domain.task.service.TaskService;
import net.hwyz.iov.cloud.ota.fota.service.domain.taskvehicle.repository.TaskVehicleRepository;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model.EcuInfoVo;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model.VehicleDo;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.repository.VehicleRepository;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.EcuInfoCcpAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.exception.VehicleNotExistException;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.util.FotaHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 在线固件升级相关中奖计算平台接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/ccp/fota")
public class FotaCcpController extends BaseController implements FotaCcpApi {

    private final TaskService taskService;
    private final VehicleRepository vehicleRepository;
    private final FotaHelper fotaHelper;
    private final ActivityRepository activityRepository;
    private final TaskVehicleRepository taskVehicleRepository;

    /**
     * 检查车辆升级信息
     *
     * @param vin             车架号
     * @param vehicleFotaInfo 车辆升级信息
     * @return 云端升级信息
     */
    @Override
    @PostMapping("/check")
    public Response<CloudFotaInfoCcp> check(@RequestHeader String vin, @Validated @RequestBody VehicleFotaInfoCcp vehicleFotaInfo) {
        logger.info("车辆[{}]检查车辆升级信息", vin);
        // 处理车辆信息
        VehicleDo vehicle = vehicleRepository.getById(vin).orElseThrow(() -> new VehicleNotExistException(vin));
        List<EcuInfoVo> ecuInfoList = EcuInfoCcpAssembler.INSTANCE.toVoList(vehicleFotaInfo.getEcuInfoList());
        if (vehicle.checkBaseline(vehicleFotaInfo.getBaseline()) || vehicle.checkEcus(ecuInfoList)) {
            vehicle.markBaselineAlignment(fotaHelper.isBaselineAlignment(vehicleFotaInfo.getBaseline(), ecuInfoList));
        }
        // 处理任务信息
        final CloudFotaInfoCcp[] cloudFotaInfoCcp = {null};
        taskService.getVehicleTask(vehicle).ifPresent(task -> {
            if (!task.checkPreconditions(vehicle)) {
                return;
            }
            activityRepository.getById(task.getActivityId()).ifPresent(activity -> {
                if (!activity.checkPreconditions(vehicle)) {
                    return;
                }
                taskVehicleRepository.getByTaskIdAndVin(task.getId(), vin).ifPresent(taskVehicle -> {
                    taskVehicle.loadBaseInfo(activity, task);
                    taskVehicle.loadStrategy(task);
                    taskVehicle.loadSoftwareBuildVersion(activity, task, vehicle, fotaHelper);
                    taskVehicle.loadArticle(activity);
                    cloudFotaInfoCcp[0] = taskVehicle.toCloudFotaInfoCcp();
                    taskVehicleRepository.save(taskVehicle);
                });
            });
        });
        vehicleRepository.save(vehicle);
        return new Response<>(cloudFotaInfoCcp[0]);
    }

}
