package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivitySoftwarePartVersionPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 升级活动软件零件版本关系表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-12-09
 */
@Mapper
public interface ActivitySoftwarePartVersionDao extends BaseDao<ActivitySoftwarePartVersionPo, Long> {

    /**
     * 批量物理删除指定活动ID下软件零件版本关系
     *
     * @param activityId             升级活动ID
     * @param softwarePartVersionIds 软件零件版本ID列表
     * @return 删除数量
     */
    int batchPhysicalDeletePoByActivityIdAndSoftwarePartVersionIds(Long activityId, Long[] softwarePartVersionIds);

    /**
     * 根据升级活动ID统计数量
     *
     * @param activityId 升级活动ID
     * @return 数量
     */
    int countByActivityId(Long activityId);

}
