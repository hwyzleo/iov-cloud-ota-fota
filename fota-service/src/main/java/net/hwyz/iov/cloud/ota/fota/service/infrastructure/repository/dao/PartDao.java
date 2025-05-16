package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.PartPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 零部件信息表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-16
 */
@Mapper
public interface PartDao extends BaseDao<PartPo, Long> {

    /**
     * 根据序列号查询零部件信息
     *
     * @param sn 序列号
     * @return 零部件信息
     */
    PartPo selectBySn(String sn);

}
