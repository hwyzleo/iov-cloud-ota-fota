package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.AbstractRepository;
import net.hwyz.iov.cloud.ota.fota.service.domain.task.model.TaskDo;
import net.hwyz.iov.cloud.ota.fota.service.domain.task.repository.TaskRepository;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.assembler.TaskPoAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.TaskDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.TaskVehicleDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskVehiclePo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 升级任务仓库接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl extends AbstractRepository<Long, TaskDo> implements TaskRepository {

    private final TaskDao taskDao;
    private final TaskVehicleDao taskVehicleDao;

    @Override
    public Optional<TaskDo> getById(Long id) {
        TaskPo taskPo = taskDao.selectPoById(id);
        if (taskPo != null) {
            TaskDo taskDo = TaskPoAssembler.INSTANCE.toDo(taskPo);
            taskDo.stateLoad();
            return Optional.of(taskDo);
        }
        return Optional.empty();
    }

    @Override
    public boolean save(TaskDo taskDo) {
        switch (taskDo.getState()) {
            case CHANGED -> {
                TaskPo taskPo = TaskPoAssembler.INSTANCE.fromDo(taskDo);
                taskDao.updatePo(taskPo);
                taskDo.getVehicles().forEach(vehicle -> {
                    TaskVehiclePo taskVehiclePo = taskVehicleDao.selectByTaskIdAndVin(taskPo.getId(), vehicle);
                    if (taskVehiclePo == null) {
                        taskVehicleDao.insertPo(TaskVehiclePo.builder()
                                .activityId(taskPo.getActivityId())
                                .taskId(taskPo.getId())
                                .vin(vehicle)
                                .state(1)
                                .build());
                    }
                });
            }
            default -> {
                return false;
            }
        }
        return true;
    }

}
