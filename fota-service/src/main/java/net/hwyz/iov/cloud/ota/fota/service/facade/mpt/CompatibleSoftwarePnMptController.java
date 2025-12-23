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
import net.hwyz.iov.cloud.ota.fota.api.contract.CompatibleSoftwarePnMpt;
import net.hwyz.iov.cloud.ota.fota.api.feign.mpt.CompatibleSoftwarePnMptApi;
import net.hwyz.iov.cloud.ota.fota.service.application.service.CompatibleSoftwarePnAppService;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.CompatibleSoftwarePnMptAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.CompatibleSoftwarePnPo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 兼容软件零件号相关管理接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/compatibleSoftwarePn")
public class CompatibleSoftwarePnMptController extends BaseController implements CompatibleSoftwarePnMptApi {

    private final CompatibleSoftwarePnAppService compatibleSoftwarePnAppService;

    /**
     * 分页查询兼容软件零件号
     *
     * @param compatibleSoftwarePn 兼容软件零件号
     * @return 兼容软件零件号列表
     */
    @RequiresPermissions("ota:fota:compatibleSoftwarePn:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(CompatibleSoftwarePnMpt compatibleSoftwarePn) {
        logger.info("管理后台用户[{}]分页查询兼容软件零件号", SecurityUtils.getUsername());
        startPage();
        List<CompatibleSoftwarePnPo> compatibleSoftwarePnPoList = compatibleSoftwarePnAppService.search(compatibleSoftwarePn.getEcu(),
                compatibleSoftwarePn.getType(), getBeginTime(compatibleSoftwarePn), getEndTime(compatibleSoftwarePn));
        List<CompatibleSoftwarePnMpt> compatibleSoftwarePnMptList = CompatibleSoftwarePnMptAssembler.INSTANCE.fromPoList(compatibleSoftwarePnPoList);
        return getDataTable(compatibleSoftwarePnPoList, compatibleSoftwarePnMptList);
    }

    /**
     * 导出兼容软件零件号
     *
     * @param response             响应
     * @param compatibleSoftwarePn 兼容软件零件号
     */
    @Log(title = "兼容软件零件号管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("ota:fota:compatibleSoftwarePn:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompatibleSoftwarePnMpt compatibleSoftwarePn) {
        logger.info("管理后台用户[{}]导出兼容软件零件号", SecurityUtils.getUsername());
    }

    /**
     * 根据兼容软件零件号ID获取兼容软件零件号
     *
     * @param compatibleSoftwarePnId 兼容软件零件号ID
     * @return 兼容软件零件号
     */
    @RequiresPermissions("ota:fota:compatibleSoftwarePn:query")
    @Override
    @GetMapping(value = "/{compatibleSoftwarePnId}")
    public AjaxResult getInfo(@PathVariable Long compatibleSoftwarePnId) {
        logger.info("管理后台用户[{}]根据固定配置字ID[{}]获取固定配置字", SecurityUtils.getUsername(), compatibleSoftwarePnId);
        CompatibleSoftwarePnPo compatibleSoftwarePnPo = compatibleSoftwarePnAppService.getCompatibleSoftwarePnById(compatibleSoftwarePnId);
        return success(CompatibleSoftwarePnMptAssembler.INSTANCE.fromPo(compatibleSoftwarePnPo));
    }

    /**
     * 新增兼容软件零件号
     *
     * @param compatibleSoftwarePn 兼容软件零件号
     * @return 结果
     */
    @Log(title = "兼容软件零件号管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("ota:fota:compatibleSoftwarePn:add")
    @Override
    @PostMapping
    public AjaxResult add(@Validated @RequestBody CompatibleSoftwarePnMpt compatibleSoftwarePn) {
        logger.info("管理后台用户[{}]新增兼容软件零件号[{}]", SecurityUtils.getUsername(), compatibleSoftwarePn.getEcu());
        CompatibleSoftwarePnPo compatibleSoftwarePnPo = CompatibleSoftwarePnMptAssembler.INSTANCE.toPo(compatibleSoftwarePn);
        compatibleSoftwarePnPo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(compatibleSoftwarePnAppService.createCompatibleSoftwarePn(compatibleSoftwarePnPo));
    }

    /**
     * 修改保存兼容软件零件号
     *
     * @param compatibleSoftwarePn 兼容软件零件号
     * @return 结果
     */
    @Log(title = "兼容软件零件号管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:compatibleSoftwarePn:edit")
    @Override
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody CompatibleSoftwarePnMpt compatibleSoftwarePn) {
        logger.info("管理后台用户[{}]修改保存兼容软件零件号[{}]", SecurityUtils.getUsername(), compatibleSoftwarePn.getEcu());
        CompatibleSoftwarePnPo compatibleSoftwarePnPo = CompatibleSoftwarePnMptAssembler.INSTANCE.toPo(compatibleSoftwarePn);
        compatibleSoftwarePnPo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(compatibleSoftwarePnAppService.modifyCompatibleSoftwarePn(compatibleSoftwarePnPo));
    }

    /**
     * 删除兼容软件零件号
     *
     * @param compatibleSoftwarePnIds 兼容软件零件号ID数组
     * @return 结果
     */
    @Log(title = "兼容软件零件号管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("ota:fota:compatibleSoftwarePn:remove")
    @Override
    @DeleteMapping("/{compatibleSoftwarePnIds}")
    public AjaxResult remove(@PathVariable Long[] compatibleSoftwarePnIds) {
        logger.info("管理后台用户[{}]删除兼容软件零件号[{}]", SecurityUtils.getUsername(), compatibleSoftwarePnIds);
        return toAjax(compatibleSoftwarePnAppService.deleteCompatibleSoftwarePnByIds(compatibleSoftwarePnIds));
    }
}
