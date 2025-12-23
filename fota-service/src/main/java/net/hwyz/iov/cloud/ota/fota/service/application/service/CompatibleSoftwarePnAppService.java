package net.hwyz.iov.cloud.ota.fota.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.CompatibleSoftwarePnDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.CompatibleSoftwarePnPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 兼容软件零件号应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompatibleSoftwarePnAppService {

    private final CompatibleSoftwarePnDao compatibleSoftwarePnDao;

    /**
     * 查询兼容软件零件号
     *
     * @param ecu       ECU设备
     * @param type      分类
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 升级活动列表
     */
    public List<CompatibleSoftwarePnPo> search(String ecu, Integer type, Date beginTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("ecu", ecu);
        map.put("type", type);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return compatibleSoftwarePnDao.selectPoByMap(map);
    }

    /**
     * 根据主键ID获取兼容软件零件号
     *
     * @param id 主键ID
     * @return 兼容软件零件号
     */
    public CompatibleSoftwarePnPo getCompatibleSoftwarePnById(Long id) {
        return compatibleSoftwarePnDao.selectPoById(id);
    }

    /**
     * 新增兼容软件零件号
     *
     * @param compatibleSoftwarePnPo 兼容软件零件号
     * @return 结果
     */
    public int createCompatibleSoftwarePn(CompatibleSoftwarePnPo compatibleSoftwarePnPo) {
        return compatibleSoftwarePnDao.insertPo(compatibleSoftwarePnPo);
    }

    /**
     * 修改兼容软件零件号
     *
     * @param compatibleSoftwarePnPo 兼容软件零件号
     * @return 结果
     */
    public int modifyCompatibleSoftwarePn(CompatibleSoftwarePnPo compatibleSoftwarePnPo) {
        return compatibleSoftwarePnDao.updatePo(compatibleSoftwarePnPo);
    }

    /**
     * 批量删除兼容软件零件号
     *
     * @param ids 兼容软件零件号ID数组
     * @return 结果
     */
    public int deleteCompatibleSoftwarePnByIds(Long[] ids) {
        return compatibleSoftwarePnDao.batchPhysicalDeletePo(ids);
    }

}
