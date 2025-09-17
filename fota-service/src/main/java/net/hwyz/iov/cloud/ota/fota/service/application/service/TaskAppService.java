package net.hwyz.iov.cloud.ota.fota.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.util.ParamHelper;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.TaskState;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.TaskDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 升级任务应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskAppService {

    private final TaskDao taskDao;

    /**
     * 查询升级任务
     *
     * @param name      升级活动名称
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 升级活动列表
     */
    public List<TaskPo> search(String name, Date beginTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", ParamHelper.fuzzyQueryParam(name));
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return taskDao.selectPoByMap(map);
    }

    /**
     * 根据主键ID获取升级任务
     *
     * @param id 主键ID
     * @return 升级任务
     */
    public TaskPo getTaskById(Long id) {
        return taskDao.selectPoById(id);
    }

    /**
     * 新增升级任务
     *
     * @param task 升级任务
     * @return 结果
     */
    public int createTask(TaskPo task) {
        task.setState(TaskState.PENDING.value);
        return taskDao.insertPo(task);
    }

    /**
     * 修改升级任务
     *
     * @param task 升级任务
     * @return 结果
     */
    public int modifyTask(TaskPo task) {
        return taskDao.updatePo(task);
    }

    /**
     * 批量删除升级任务
     *
     * @param ids 升级任务ID数组
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids) {
        return taskDao.batchPhysicalDeletePo(ids);
    }

}
