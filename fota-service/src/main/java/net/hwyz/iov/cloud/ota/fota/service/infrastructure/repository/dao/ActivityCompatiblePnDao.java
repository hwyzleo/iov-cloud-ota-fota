package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityCompatiblePnPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 升级活动兼容零件号关系表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2026-01-16
 */
@Mapper
public interface ActivityCompatiblePnDao extends BaseDao<ActivityCompatiblePnPo, Long> {

    /**
     * 批量物理删除指定活动ID下兼容零件号关系
     *
     * @param activityId      升级活动ID
     * @param compatiblePnIds 兼容零件号ID列表
     * @return 删除数量
     */
    int batchPhysicalDeletePoByActivityIdAndCompatiblePnIds(Long activityId, Long[] compatiblePnIds);

}
