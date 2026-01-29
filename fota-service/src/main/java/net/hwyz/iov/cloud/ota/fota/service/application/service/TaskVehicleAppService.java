package net.hwyz.iov.cloud.ota.fota.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.TaskVehicleProcessDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskVehicleProcessPo;
import org.springframework.stereotype.Service;

/**
 * 升级任务车辆应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskVehicleAppService {

    private final TaskVehicleProcessDao taskVehicleProcessDao;

    /**
     * 新增升级任务车辆升级过程
     *
     * @param taskVehicleProcess 升级任务车辆升级过程
     */
    public void addTaskVehicleProcess(TaskVehicleProcessPo taskVehicleProcess) {
        taskVehicleProcessDao.insertPo(taskVehicleProcess);
    }

}
