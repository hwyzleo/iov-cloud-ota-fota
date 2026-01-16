package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskRestrictionPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 升级任务限制条件表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-25
 */
@Mapper
public interface TaskRestrictionDao extends BaseDao<TaskRestrictionPo, Long> {

}
