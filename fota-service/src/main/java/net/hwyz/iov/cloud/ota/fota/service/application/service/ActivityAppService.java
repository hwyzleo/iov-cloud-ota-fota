package net.hwyz.iov.cloud.ota.fota.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.util.ParamHelper;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.ActivityState;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.ActivityDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.ActivitySoftwarePartVersionDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivitySoftwarePartVersionPo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 升级活动应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityAppService {

    private final ActivityDao activityDao;
    private final ActivitySoftwarePartVersionDao activitySoftwarePartVersionDao;

    /**
     * 查询升级活动
     *
     * @param name      升级活动名称
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 升级活动列表
     */
    public List<ActivityPo> search(String name, Date beginTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", ParamHelper.fuzzyQueryParam(name));
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return activityDao.selectPoByMap(map);
    }

    /**
     * 获取升级活动下的软件零件版本列表
     *
     * @param activityId 升级活动ID
     * @return 软件零件版本列表
     */
    public List<ActivitySoftwarePartVersionPo> listSoftwarePartVersion(Long activityId) {
        return activitySoftwarePartVersionDao.selectPoByExample(ActivitySoftwarePartVersionPo.builder().activityId(activityId).build());
    }

    /**
     * 根据主键ID获取升级活动
     *
     * @param id 主键ID
     * @return 升级活动
     */
    public ActivityPo getActivityById(Long id) {
        return activityDao.selectPoById(id);
    }

    /**
     * 新增升级活动
     *
     * @param activity 升级活动
     * @return 结果
     */
    public int createActivity(ActivityPo activity) {
        activity.setState(ActivityState.PENDING.value);
        return activityDao.insertPo(activity);
    }

    /**
     * 新增升级活动软件零件版本信息
     *
     * @param activityId             升级活动ID
     * @param softwarePartVersionIds 软件零件版本ID数组
     * @return 结果
     */
    public int createActivitySoftwarePartVersion(Long activityId, Long[] softwarePartVersionIds) {
        Set<Long> softwarePartVersionIdSet = listSoftwarePartVersion(activityId).stream()
                .map(ActivitySoftwarePartVersionPo::getSoftwarePartVersionId)
                .collect(Collectors.toSet());
        List<ActivitySoftwarePartVersionPo> list = new ArrayList<>();
        for (Long softwarePartVersionId : softwarePartVersionIds) {
            if (!softwarePartVersionIdSet.contains(softwarePartVersionId)) {
                list.add(ActivitySoftwarePartVersionPo.builder()
                        .activityId(activityId)
                        .softwarePartVersionId(softwarePartVersionId)
                        .sort(0)
                        .versionGroup(0)
                        .build());
            }
        }
        if (!list.isEmpty()) {
            return activitySoftwarePartVersionDao.batchInsertPo(list);
        }
        return 0;
    }

    /**
     * 修改升级活动
     *
     * @param activity 升级活动
     * @return 结果
     */
    public int modifyActivity(ActivityPo activity) {
        return activityDao.updatePo(activity);
    }

    /**
     * 修改升级活动软件零件版本信息
     *
     * @param activityId             升级活动ID
     * @param softwarePartVersionIds 软件零件版本ID数组
     * @param sorts                  排序数组
     * @param groups                 组数数组
     * @return 结果
     */
    public int modifyActivitySoftwarePartVersion(Long activityId, Long[] softwarePartVersionIds, Integer[] sorts, Integer[] groups) {
        listSoftwarePartVersion(activityId).forEach(po -> {
            for (int i = 0; i < softwarePartVersionIds.length; i++) {
                if (po.getSoftwarePartVersionId().longValue() == softwarePartVersionIds[i]) {
                    po.setSort(sorts[i]);
                    po.setVersionGroup(groups[i]);
                    activitySoftwarePartVersionDao.updatePo(po);
                }
            }
        });
        return 1;
    }

    /**
     * 批量删除升级活动
     *
     * @param ids 升级活动ID数组
     * @return 结果
     */
    public int deleteActivityByIds(Long[] ids) {
        return activityDao.batchPhysicalDeletePo(ids);
    }

    /**
     * 删除升级活动软件零件版本信息
     *
     * @param activityId             升级活动ID
     * @param softwarePartVersionIds 软件零件版本ID数组
     * @return 结果
     */
    public int deleteBaselineSoftwarePartVersion(Long activityId, Long[] softwarePartVersionIds) {
        return activitySoftwarePartVersionDao.batchPhysicalDeletePoByActivityIdAndSoftwarePartVersionIds(activityId, softwarePartVersionIds);
    }

    /**
     * 统计基线软件零件版本数量
     *
     * @param activityId 升级活动ID
     * @return 基线软件零件版本数量
     */
    public int countActivitySoftwarePartVersion(Long activityId) {
        return activitySoftwarePartVersionDao.countByActivityId(activityId);
    }

}
