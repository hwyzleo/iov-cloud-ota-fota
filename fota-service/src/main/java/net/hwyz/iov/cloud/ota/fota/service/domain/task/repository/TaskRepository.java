package net.hwyz.iov.cloud.ota.fota.service.domain.task.repository;

import net.hwyz.iov.cloud.framework.common.domain.BaseRepository;
import net.hwyz.iov.cloud.ota.fota.service.domain.task.model.TaskDo;

/**
 * 升级任务领域仓库接口
 *
 * @author hwyz_leo
 */
public interface TaskRepository extends BaseRepository<Long, TaskDo> {
}
