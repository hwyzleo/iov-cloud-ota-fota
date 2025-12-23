package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.FixedConfigWordDetailPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 固定配置字明细表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-17
 */
@Mapper
public interface FixedConfigWordDetailDao extends BaseDao<FixedConfigWordDetailPo, Long> {

}
