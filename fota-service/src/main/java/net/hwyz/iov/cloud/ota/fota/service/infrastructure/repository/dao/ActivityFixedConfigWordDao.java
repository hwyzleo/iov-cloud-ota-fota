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

    /**
     * 批量物理删除指定活动ID下固定配置字关系
     *
     * @param activityId         升级活动ID
     * @param fixedConfigWordIds 固定配置字ID列表
     * @return 删除数量
     */
    int batchPhysicalDeletePoByActivityIdAndFixedConfigWordIds(Long activityId, Long[] fixedConfigWordIds);

}
