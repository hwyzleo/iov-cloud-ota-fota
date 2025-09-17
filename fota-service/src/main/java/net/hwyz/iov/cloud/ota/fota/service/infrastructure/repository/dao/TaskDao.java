package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 升级任务表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-09-17
 */
@Mapper
public interface TaskDao extends BaseDao<TaskPo, Long> {

}
