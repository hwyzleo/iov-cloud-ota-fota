package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.ActivityAuditMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.ActivityMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.ActivitySoftwareBuildVersionMpt;

import java.util.List;

/**
 * 升级活动相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface ActivityMptApi {

    /**
     * 分页查询升级活动
     *
     * @param activity 升级活动
     * @return 升级活动列表
     */
    TableDataInfo list(ActivityMpt activity);

    /**
     * 获取所有升级活动状态
     *
     * @return 升级活动状态列表
     */
    AjaxResult listAllActivityState();

    /**
     * 列出升级活动下软件内部版本
     *
     * @param activityId 升级活动ID
     * @param group      组
     * @return 软件内部版本列表
     */
    AjaxResult listSoftwareBuildVersion(Long activityId, Integer group);

    /**
     * 列出升级活动下兼容零件号
     *
     * @param activityId 升级活动ID
     * @return 兼容零件号列表
     */
    AjaxResult listCompatiblePn(Long activityId);

    /**
     * 导出升级活动
     *
     * @param response 响应
     * @param activity 升级活动
     */
    void export(HttpServletResponse response, ActivityMpt activity);

    /**
     * 根据升级活动ID获取升级活动
     *
     * @param activityId 升级活动ID
     * @return 升级活动
     */
    AjaxResult getInfo(Long activityId);

    /**
     * 新增升级活动
     *
     * @param activity 升级活动
     * @return 结果
     */
    AjaxResult add(ActivityMpt activity);

    /**
     * 新增关联的软件内部版本
     *
     * @param activityId              升级活动ID
     * @param softwareBuildVersionIds 软件内部版本ID数组
     * @return 结果
     */
    AjaxResult addSoftwareBuildVersion(Long activityId, Long[] softwareBuildVersionIds);

    /**
     * 新增关联的兼容零件号
     *
     * @param activityId      升级活动ID
     * @param compatiblePnIds 兼容零件号ID数组
     * @return 结果
     */
    AjaxResult addCompatiblePn(Long activityId, Long[] compatiblePnIds);

    /**
     * 修改保存升级活动
     *
     * @param activity 升级活动
     * @return 结果
     */
    AjaxResult edit(ActivityMpt activity);

    /**
     * 修改关联的软件内部版本
     *
     * @param activityId              升级活动ID
     * @param softwareBuildVersionIds 软件内部版本ID数组
     * @param sorts                   排序数组
     * @param groups                  组数组
     * @return 结果
     */
    AjaxResult editSoftwareBuildVersion(Long activityId, Long[] softwareBuildVersionIds, Integer[] sorts, Integer[] groups);

    /**
     * 提交升级活动
     *
     * @param activityId 升级活动ID
     * @param activity   升级活动
     * @return 结果
     */
    AjaxResult submit(Long activityId, ActivityMpt activity);

    /**
     * 审核升级活动
     *
     * @param activityId    升级活动ID
     * @param activityAudit 升级活动审核
     * @return 结果
     */
    AjaxResult audit(Long activityId, ActivityAuditMpt activityAudit);

    /**
     * 发布升级活动
     *
     * @param activityId 升级活动ID
     * @return 结果
     */
    AjaxResult release(Long activityId);

    /**
     * 取消升级活动
     *
     * @param activityId 升级活动ID
     * @return 结果
     */
    AjaxResult cancel(Long activityId);

    /**
     * 删除升级活动
     *
     * @param activityIds 升级活动ID数组
     * @return 结果
     */
    AjaxResult remove(Long[] activityIds);

    /**
     * 删除关联的软件内部版本
     *
     * @param activityId              升级活动ID
     * @param softwareBuildVersionIds 软件内部版本关联ID数组
     * @return 结果
     */
    AjaxResult removeSoftwareBuildVersion(Long activityId, Long[] softwareBuildVersionIds);

    /**
     * 删除关联的兼容零件号
     *
     * @param activityId      升级活动ID
     * @param compatiblePnIds 兼容零件号ID数组
     * @return 结果
     */
    AjaxResult removeCompatiblePn(Long activityId, Long[] compatiblePnIds);

    /**
     * 调整关联的软件内部版本组
     *
     * @param activityId 升级活动ID
     * @param list       升级活动软件内部版本列表
     * @return 结果
     */
    AjaxResult regroupSoftwareBuildVersion(Long activityId, List<ActivitySoftwareBuildVersionMpt> list);

    /**
     * 重排序关联的软件内部版本
     *
     * @param activityId 升级活动ID
     * @param list       升级活动软件内部版本列表
     * @return 结果
     */
    AjaxResult resortSoftwareBuildVersion(Long activityId, List<ActivitySoftwareBuildVersionMpt> list);

}
