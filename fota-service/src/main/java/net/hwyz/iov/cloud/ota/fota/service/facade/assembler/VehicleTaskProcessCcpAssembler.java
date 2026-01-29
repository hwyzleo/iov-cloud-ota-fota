package net.hwyz.iov.cloud.ota.fota.service.facade.assembler;

import net.hwyz.iov.cloud.ota.fota.api.contract.VehicleTaskProcessCcp;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskVehicleProcessPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 中央计算平台车辆升级任务升级过程转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface VehicleTaskProcessCcpAssembler {

    VehicleTaskProcessCcpAssembler INSTANCE = Mappers.getMapper(VehicleTaskProcessCcpAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param taskVehicleProcessPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({})
    VehicleTaskProcessCcp fromPo(TaskVehicleProcessPo taskVehicleProcessPo);

    /**
     * 数据传输对象转数据对象
     *
     * @param vehicleTaskProcessCcp 数据传输对象
     * @return 数据对象
     */
    @Mappings({})
    TaskVehicleProcessPo toPo(VehicleTaskProcessCcp vehicleTaskProcessCcp);

    /**
     * 数据对象列表转数据传输对象列表
     *
     * @param taskVehicleProcessPoList 数据对象列表
     * @return 数据传输对象列表
     */
    List<VehicleTaskProcessCcp> fromPoList(List<TaskVehicleProcessPo> taskVehicleProcessPoList);

}
