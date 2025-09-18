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
import net.hwyz.iov.cloud.ota.baseline.api.contract.SoftwarePartVersionExService;
import net.hwyz.iov.cloud.ota.baseline.api.feign.service.ExSoftwarePartVersionService;
import net.hwyz.iov.cloud.ota.fota.api.contract.ActivityMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.ActivitySoftwarePartVersionMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.ActivityState;
import net.hwyz.iov.cloud.ota.fota.api.feign.mpt.ActivityMptApi;
import net.hwyz.iov.cloud.ota.fota.service.application.service.ActivityAppService;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.ActivityMptAssembler;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.ActivitySoftwarePartVersionMptAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivitySoftwarePartVersionPo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 升级活动相关管理接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/activity")
public class ActivityMptController extends BaseController implements ActivityMptApi {

    private final ActivityAppService activityAppService;
    private final ExSoftwarePartVersionService expSoftwarePartVersionService;

    /**
     * 分页查询升级活动
     *
     * @param activity 升级活动
     * @return 升级活动列表
     */
    @RequiresPermissions("ota:fota:activity:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(ActivityMpt activity) {
        logger.info("管理后台用户[{}]分页查询升级活动", SecurityUtils.getUsername());
        startPage();
        List<ActivityPo> activityPoList = activityAppService.search(activity.getName(), getBeginTime(activity), getEndTime(activity));
        List<ActivityMpt> activityMptList = ActivityMptAssembler.INSTANCE.fromPoList(activityPoList);
        activityMptList.forEach(activityMpt -> activityMpt.setSoftwarePartVersionCount(activityAppService.countActivitySoftwarePartVersion(activityMpt.getId())));
        return getDataTable(activityPoList, activityMptList);
    }

    /**
     * 获取所有升级活动状态
     *
     * @return 升级活动状态列表
     */
    @RequiresPermissions("ota:fota:activity:list")
    @Override
    @GetMapping(value = "/listAllActivityState")
    public AjaxResult listAllActivityState() {
        logger.info("管理后台用户[{}]获取所有升级活动状态", SecurityUtils.getUsername());
        List<Map<String, Object>> list = new ArrayList<>();
        for (ActivityState activityState : ActivityState.values()) {
            list.add(Map.of("value", activityState.value, "label", activityState.label));
        }
        return success(list);
    }

    /**
     * 列出升级活动下软件零件版本
     *
     * @param activityId 升级活动ID
     * @return 软件零件版本列表
     */
    @RequiresPermissions("ota:fota:activity:list")
    @Override
    @GetMapping(value = "/{activityId}/listSoftwarePartVersion")
    public AjaxResult listSoftwarePartVersion(@PathVariable Long activityId) {
        logger.info("管理后台用户[{}]列出升级活动[{}]下软件零件版本", SecurityUtils.getUsername(), activityId);
        List<ActivitySoftwarePartVersionPo> activitySoftwarePartVersionPoList = activityAppService.listSoftwarePartVersion(activityId);
        List<ActivitySoftwarePartVersionMpt> activitySoftwarePartVersionMptList = ActivitySoftwarePartVersionMptAssembler.INSTANCE.fromPoList(activitySoftwarePartVersionPoList);
        for (ActivitySoftwarePartVersionMpt activitySoftwarePartVersionMpt : activitySoftwarePartVersionMptList) {
            SoftwarePartVersionExService softwarePartVersion = expSoftwarePartVersionService.getInfo(activitySoftwarePartVersionMpt.getSoftwarePartVersionId());
            activitySoftwarePartVersionMpt.setEcuCode(softwarePartVersion.getEcuCode());
            activitySoftwarePartVersionMpt.setSoftwarePn(softwarePartVersion.getSoftwarePn());
            activitySoftwarePartVersionMpt.setSoftwarePartName(softwarePartVersion.getSoftwarePartName());
            activitySoftwarePartVersionMpt.setSoftwarePartVer(softwarePartVersion.getSoftwarePartVer());
            activitySoftwarePartVersionMpt.setSoftwareSource(softwarePartVersion.getSoftwareSource());
        }
        return success(activitySoftwarePartVersionMptList);
    }

    /**
     * 导出升级活动
     *
     * @param response 响应
     * @param activity 升级活动
     */
    @Log(title = "升级活动管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("ota:fota:activity:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityMpt activity) {
        logger.info("管理后台用户[{}]导出升级活动", SecurityUtils.getUsername());
    }

    /**
     * 根据升级活动ID获取升级活动
     *
     * @param activityId 升级活动ID
     * @return 升级活动
     */
    @RequiresPermissions("ota:fota:activity:query")
    @Override
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable Long activityId) {
        logger.info("管理后台用户[{}]根据升级活动ID[{}]获取升级活动", SecurityUtils.getUsername(), activityId);
        ActivityPo activityPo = activityAppService.getActivityById(activityId);
        return success(ActivityMptAssembler.INSTANCE.fromPo(activityPo));
    }

    /**
     * 新增升级活动
     *
     * @param activity 升级活动
     * @return 结果
     */
    @Log(title = "升级活动管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("ota:fota:activity:add")
    @Override
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ActivityMpt activity) {
        logger.info("管理后台用户[{}]新增升级活动[{}]", SecurityUtils.getUsername(), activity.getName());
        ActivityPo activityPo = ActivityMptAssembler.INSTANCE.toPo(activity);
        activityPo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(activityAppService.createActivity(activityPo));
    }

    /**
     * 新增关联的软件零件版本
     *
     * @param activityId             升级活动ID
     * @param softwarePartVersionIds 软件零件版本ID数组
     * @return 结果
     */
    @Log(title = "升级活动管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:activity:edit")
    @Override
    @PostMapping(value = "/{activityId}/action/addSoftwarePartVersion/{softwarePartVersionIds}")
    public AjaxResult addSoftwarePartVersion(@PathVariable Long activityId, @PathVariable Long[] softwarePartVersionIds) {
        logger.info("管理后台用户[{}]新增升级活动[{}]关联的软件零件版本[{}]", SecurityUtils.getUsername(), activityId, softwarePartVersionIds);
        return toAjax(activityAppService.createActivitySoftwarePartVersion(activityId, softwarePartVersionIds));
    }

    /**
     * 修改保存升级活动
     *
     * @param activity 升级活动
     * @return 结果
     */
    @Log(title = "升级活动管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:activity:edit")
    @Override
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ActivityMpt activity) {
        logger.info("管理后台用户[{}]修改保存升级活动[{}]", SecurityUtils.getUsername(), activity.getName());
        ActivityPo activityPo = ActivityMptAssembler.INSTANCE.toPo(activity);
        activityPo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(activityAppService.modifyActivity(activityPo));
    }

    /**
     * 修改关联的软件零件版本
     *
     * @param activityId             升级活动ID
     * @param softwarePartVersionIds 软件零件版本ID数组
     * @param sorts                  排序数组
     * @param groups                 组数组
     * @return 结果
     */
    @Log(title = "升级活动管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:activity:edit")
    @Override
    @PostMapping(value = "/{activityId}/action/editSoftwarePartVersion/{softwarePartVersionIds}")
    public AjaxResult editSoftwarePartVersion(@PathVariable Long activityId, @PathVariable Long[] softwarePartVersionIds,
                                              @RequestParam Integer[] sorts, @RequestParam Integer[] groups) {
        logger.info("管理后台用户[{}]修改升级活动[{}]关联的软件零件版本[{}]", SecurityUtils.getUsername(), activityId, softwarePartVersionIds);
        return success(activityAppService.modifyActivitySoftwarePartVersion(activityId, softwarePartVersionIds, sorts, groups));
    }

    /**
     * 删除升级活动
     *
     * @param activityIds 升级活动ID数组
     * @return 结果
     */
    @Log(title = "升级活动管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("ota:fota:activity:remove")
    @Override
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds) {
        logger.info("管理后台用户[{}]删除升级活动[{}]", SecurityUtils.getUsername(), activityIds);
        return toAjax(activityAppService.deleteActivityByIds(activityIds));
    }

    /**
     * 删除关联的软件零件版本
     *
     * @param activityId             升级活动ID
     * @param softwarePartVersionIds 软件零件版本关联ID数组
     * @return 结果
     */
    @Log(title = "升级活动管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:activity:edit")
    @Override
    @PostMapping(value = "/{activityId}/action/removeSoftwarePartVersion/{softwarePartVersionIds}")
    public AjaxResult removeSoftwarePartVersion(@PathVariable Long activityId, @PathVariable Long[] softwarePartVersionIds) {
        logger.info("管理后台用户[{}]删除升级活动[{}]关联的软件零件版本[{}]", SecurityUtils.getUsername(), activityId, softwarePartVersionIds);
        return toAjax(activityAppService.deleteBaselineSoftwarePartVersion(activityId, softwarePartVersionIds));
    }
}
