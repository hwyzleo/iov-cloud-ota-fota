package net.hwyz.iov.cloud.ota.fota.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.FixedConfigWordDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.FixedConfigWordDetailDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.FixedConfigWordDetailPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.FixedConfigWordPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 固定配置字应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FixedConfigWordAppService {

    private final FixedConfigWordDao fixedConfigWordDao;
    private final FixedConfigWordDetailDao fixedConfigWordDetailDao;

    /**
     * 查询固定配置字
     *
     * @param ecu       ECU设备
     * @param type      分类
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 升级活动列表
     */
    public List<FixedConfigWordPo> search(String ecu, Integer type, Date beginTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("ecu", ecu);
        map.put("type", type);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return fixedConfigWordDao.selectPoByMap(map);
    }

    /**
     * 根据主键ID获取固定配置字
     *
     * @param id 主键ID
     * @return 固定配置字
     */
    public FixedConfigWordPo getFixedConfigWordById(Long id) {
        return fixedConfigWordDao.selectPoById(id);
    }

    /**
     * 根据主键ID获取固定配置字明细
     *
     * @param id 主键ID
     * @return 固定配置字明细
     */
    public FixedConfigWordDetailPo getFixedConfigWordDetailById(Long id) {
        return fixedConfigWordDetailDao.selectPoById(id);
    }

    /**
     * 根据主键ID获取固定配置字明细
     *
     * @param fixedConfigWordId 固定配置字ID
     * @return 固定配置字明细列表
     */
    public List<FixedConfigWordDetailPo> listDetailByFixedConfigWordId(Long fixedConfigWordId) {
        Map<String, Object> map = new HashMap<>();
        map.put("fixedConfigWordId", fixedConfigWordId);
        return fixedConfigWordDetailDao.selectPoByMap(map);
    }

    /**
     * 新增固定配置字
     *
     * @param fixedConfigWordPo 固定配置字
     * @return 结果
     */
    public int createFixedConfigWord(FixedConfigWordPo fixedConfigWordPo) {
        return fixedConfigWordDao.insertPo(fixedConfigWordPo);
    }

    /**
     * 新增固定配置字明细
     *
     * @param fixedConfigWordDetailPo 固定配置字明细
     * @return 结果
     */
    public int createFixedConfigWordDetail(FixedConfigWordDetailPo fixedConfigWordDetailPo) {
        return fixedConfigWordDetailDao.insertPo(fixedConfigWordDetailPo);
    }

    /**
     * 修改固定配置字
     *
     * @param fixedConfigWordPo 固定配置字
     * @return 结果
     */
    public int modifyFixedConfigWord(FixedConfigWordPo fixedConfigWordPo) {
        return fixedConfigWordDao.updatePo(fixedConfigWordPo);
    }

    /**
     * 修改固定配置字明细
     *
     * @param fixedConfigWordDetailPo 固定配置字明细
     * @return 结果
     */
    public int modifyFixedConfigWordDetail(FixedConfigWordDetailPo fixedConfigWordDetailPo) {
        return fixedConfigWordDetailDao.updatePo(fixedConfigWordDetailPo);
    }

    /**
     * 批量删除固定配置字
     *
     * @param ids 固定配置字ID数组
     * @return 结果
     */
    public int deleteFixedConfigWordByIds(Long[] ids) {
        return fixedConfigWordDao.batchPhysicalDeletePo(ids);
    }

    /**
     * 批量删除固定配置字
     *
     * @param ids 固定配置字ID数组
     * @return 结果
     */
    public int deleteFixedConfigWordDetailByIds(Long[] ids) {
        return fixedConfigWordDetailDao.batchPhysicalDeletePo(ids);
    }

}
