package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.CompatibleSoftwarePnPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 兼容软件零件号表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-23
 */
@Mapper
public interface CompatibleSoftwarePnDao extends BaseDao<CompatibleSoftwarePnPo, Long> {

}
