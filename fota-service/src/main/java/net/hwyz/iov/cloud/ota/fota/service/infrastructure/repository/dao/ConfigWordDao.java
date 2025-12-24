package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ConfigWordPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 配置字表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-24
 */
@Mapper
public interface ConfigWordDao extends BaseDao<ConfigWordPo, Long> {

}
