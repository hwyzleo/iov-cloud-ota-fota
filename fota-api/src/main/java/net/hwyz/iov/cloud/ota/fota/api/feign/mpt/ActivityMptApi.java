package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.ActivityMpt;

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
     * 修改保存升级活动
     *
     * @param activity 升级活动
     * @return 结果
     */
    AjaxResult edit(ActivityMpt activity);

    /**
     * 删除升级活动
     *
     * @param activityIds 升级活动ID数组
     * @return 结果
     */
    AjaxResult remove(Long[] activityIds);

}
