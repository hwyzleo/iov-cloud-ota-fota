package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.PartPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.VehPartPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车辆零部件关系表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-16
 */
@Mapper
public interface VehPartDao extends BaseDao<VehPartPo, Long> {

    /**
     * 根据车架号和ECU查询车辆零部件信息
     *
     * @param vin 车架号
     * @param ecu ECU
     * @return 车辆零部件信息
     */
    VehPartPo selectByVinAndEcu(String vin, String ecu);

}
