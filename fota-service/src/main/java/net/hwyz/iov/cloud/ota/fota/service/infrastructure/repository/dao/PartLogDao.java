package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.PartLogPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 零部件信息变更日志表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-16
 */
@Mapper
public interface PartLogDao extends BaseDao<PartLogPo, Long> {

}
