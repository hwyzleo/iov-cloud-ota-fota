package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityFixedConfigWordPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 升级活动固定配置字表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-23
 */
@Mapper
public interface ActivityFixedConfigWordDao extends BaseDao<ActivityFixedConfigWordPo, Long> {

}
