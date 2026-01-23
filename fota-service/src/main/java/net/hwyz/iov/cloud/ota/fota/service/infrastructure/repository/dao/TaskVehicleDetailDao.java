package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskVehicleDetailPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 升级任务车辆详情表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2026-01-21
 */
@Mapper
public interface TaskVehicleDetailDao extends BaseDao<TaskVehicleDetailPo, Long> {

}
