package net.hwyz.iov.cloud.ota.fota.service.facade.mpt;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.audit.annotation.Log;
import net.hwyz.iov.cloud.framework.audit.enums.BusinessType;
import net.hwyz.iov.cloud.framework.common.web.controller.BaseController;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.framework.security.annotation.RequiresPermissions;
import net.hwyz.iov.cloud.framework.security.util.SecurityUtils;
import net.hwyz.iov.cloud.ota.fota.api.contract.TaskMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.TaskState;
import net.hwyz.iov.cloud.ota.fota.api.feign.mpt.TaskMptApi;
import net.hwyz.iov.cloud.ota.fota.service.application.service.TaskAppService;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.TaskMptAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskPo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 升级任务相关管理接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/task")
public class TaskMptController extends BaseController implements TaskMptApi {

    private final TaskAppService taskAppService;

    /**
     * 分页查询升级任务
     *
     * @param task 升级任务
     * @return 升级任务列表
     */
    @RequiresPermissions("ota:fota:task:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(TaskMpt task) {
        logger.info("管理后台用户[{}]分页查询升级任务", SecurityUtils.getUsername());
        startPage();
        List<TaskPo> taskPoList = taskAppService.search(task.getName(), getBeginTime(task), getEndTime(task));
        List<TaskMpt> taskMptList = TaskMptAssembler.INSTANCE.fromPoList(taskPoList);
        return getDataTable(taskPoList, taskMptList);
    }

    /**
     * 获取所有升级任务状态
     *
     * @return 升级任务状态列表
     */
    @RequiresPermissions("ota:fota:task:list")
    @Override
    @GetMapping(value = "/listAllTaskState")
    public AjaxResult listAllTaskState() {
        logger.info("管理后台用户[{}]获取所有升级任务状态", SecurityUtils.getUsername());
        List<Map<String, Object>> list = new ArrayList<>();
        for (TaskState taskState : TaskState.values()) {
            list.add(Map.of("value", taskState.value, "label", taskState.label));
        }
        return success(list);
    }

    /**
     * 导出升级任务
     *
     * @param response 响应
     * @param task     升级任务
     */
    @Log(title = "升级任务管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("ota:fota:task:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskMpt task) {
        logger.info("管理后台用户[{}]导出升级任务", SecurityUtils.getUsername());
    }

    /**
     * 根据升级任务ID获取升级任务
     *
     * @param taskId 升级任务ID
     * @return 升级任务
     */
    @RequiresPermissions("ota:fota:task:query")
    @Override
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable Long taskId) {
        logger.info("管理后台用户[{}]根据升级任务ID[{}]获取升级任务", SecurityUtils.getUsername(), taskId);
        TaskPo taskPo = taskAppService.getTaskById(taskId);
        return success(TaskMptAssembler.INSTANCE.fromPo(taskPo));
    }

    /**
     * 新增升级任务
     *
     * @param task 升级任务
     * @return 结果
     */
    @Log(title = "升级任务管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("ota:fota:task:add")
    @Override
    @PostMapping
    public AjaxResult add(@Validated @RequestBody TaskMpt task) {
        logger.info("管理后台用户[{}]新增升级任务[{}]", SecurityUtils.getUsername(), task.getName());
        TaskPo taskPo = TaskMptAssembler.INSTANCE.toPo(task);
        taskPo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(taskAppService.createTask(taskPo));
    }

    /**
     * 修改保存升级任务
     *
     * @param task 升级任务
     * @return 结果
     */
    @Log(title = "升级任务管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:task:edit")
    @Override
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody TaskMpt task) {
        logger.info("管理后台用户[{}]修改保存升级活动[{}]", SecurityUtils.getUsername(), task.getName());
        TaskPo taskPo = TaskMptAssembler.INSTANCE.toPo(task);
        taskPo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(taskAppService.modifyTask(taskPo));
    }

    /**
     * 删除升级任务
     *
     * @param taskIds 升级任务ID数组
     * @return 结果
     */
    @Log(title = "升级任务管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("ota:fota:task:remove")
    @Override
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds) {
        logger.info("管理后台用户[{}]删除升级任务[{}]", SecurityUtils.getUsername(), taskIds);
        return toAjax(taskAppService.deleteTaskByIds(taskIds));
    }
}
