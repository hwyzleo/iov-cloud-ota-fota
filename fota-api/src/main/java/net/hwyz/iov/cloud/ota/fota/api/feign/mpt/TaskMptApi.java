package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.TaskAuditMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.TaskMpt;

/**
 * 升级任务相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface TaskMptApi {

    /**
     * 分页查询升级任务
     *
     * @param task 升级任务
     * @return 升级任务列表
     */
    TableDataInfo list(TaskMpt task);

    /**
     * 获取所有升级任务状态
     *
     * @return 升级任务状态列表
     */
    AjaxResult listAllTaskState();

    /**
     * 导出升级任务
     *
     * @param response 响应
     * @param task     升级任务
     */
    void export(HttpServletResponse response, TaskMpt task);

    /**
     * 根据升级任务ID获取升级任务
     *
     * @param taskId 升级任务ID
     * @return 升级任务
     */
    AjaxResult getInfo(Long taskId);

    /**
     * 新增升级任务
     *
     * @param task 升级任务
     * @return 结果
     */
    AjaxResult add(TaskMpt task);

    /**
     * 修改保存升级任务
     *
     * @param task 升级任务
     * @return 结果
     */
    AjaxResult edit(TaskMpt task);

    /**
     * 提交升级任务
     *
     * @param taskId 升级任务ID
     * @param task   升级任务
     * @return 结果
     */
    AjaxResult submit(Long taskId, TaskMpt task);

    /**
     * 审核升级任务
     *
     * @param taskId    升级任务ID
     * @param taskAudit 升级任务审核
     * @return 结果
     */
    AjaxResult audit(Long taskId, TaskAuditMpt taskAudit);

    /**
     * 删除升级任务
     *
     * @param taskIds 升级任务ID数组
     * @return 结果
     */
    AjaxResult remove(Long[] taskIds);

}
