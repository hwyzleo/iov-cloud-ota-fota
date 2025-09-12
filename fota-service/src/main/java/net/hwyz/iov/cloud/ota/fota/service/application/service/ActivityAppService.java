package net.hwyz.iov.cloud.ota.fota.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.util.ParamHelper;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.ActivityDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return activityDao.insertPo(activity);
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
     * 批量删除升级活动
     *
     * @param ids 升级活动ID数组
     * @return 结果
     */
    public int deleteActivityByIds(Long[] ids) {
        return activityDao.batchPhysicalDeletePo(ids);
    }

}
