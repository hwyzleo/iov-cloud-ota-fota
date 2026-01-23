package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.AbstractRepository;
import net.hwyz.iov.cloud.ota.fota.service.domain.taskvehicle.model.TaskVehicleDo;
import net.hwyz.iov.cloud.ota.fota.service.domain.taskvehicle.repository.TaskVehicleRepository;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.assembler.TaskVehiclePoAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.TaskVehicleDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskVehiclePo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 升级任务车辆仓库接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskVehicleRepositoryImpl extends AbstractRepository<Long, TaskVehicleDo> implements TaskVehicleRepository {

    private final TaskVehicleDao taskVehicleDao;

    @Override
    public Optional<TaskVehicleDo> getById(Long id) {
        TaskVehiclePo taskVehiclePo = taskVehicleDao.selectPoById(id);
        return Optional.ofNullable(TaskVehiclePoAssembler.INSTANCE.toDo(taskVehiclePo));
    }

    @Override
    public Optional<TaskVehicleDo> getByTaskIdAndVin(Long taskId, String vin) {
        TaskVehiclePo taskVehiclePo = taskVehicleDao.selectByTaskIdAndVin(taskId, vin);
        return Optional.ofNullable(TaskVehiclePoAssembler.INSTANCE.toDo(taskVehiclePo));
    }

    @Override
    public boolean save(TaskVehicleDo taskVehicleDo) {
        switch (taskVehicleDo.getState()) {
            case CHANGED -> {

            }
            default -> {
                return false;
            }
        }
        return true;
    }

}
