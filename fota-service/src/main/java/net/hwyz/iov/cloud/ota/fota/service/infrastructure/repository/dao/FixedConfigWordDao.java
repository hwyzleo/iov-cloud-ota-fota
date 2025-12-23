package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.FixedConfigWordPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 固定配置字表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-17
 */
@Mapper
public interface FixedConfigWordDao extends BaseDao<FixedConfigWordPo, Long> {

}
