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
import net.hwyz.iov.cloud.ota.fota.api.contract.FixedConfigWordDetailMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.FixedConfigWordMpt;
import net.hwyz.iov.cloud.ota.fota.api.feign.mpt.FixedConfigWordMptApi;
import net.hwyz.iov.cloud.ota.fota.service.application.service.FixedConfigWordAppService;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.FixedConfigWordDetailMptAssembler;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.FixedConfigWordMptAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.FixedConfigWordDetailPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.FixedConfigWordPo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 固定配置字相关管理接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mpt/fixedConfigWord")
public class FixedConfigWordMptController extends BaseController implements FixedConfigWordMptApi {

    private final FixedConfigWordAppService fixedConfigWordAppService;

    /**
     * 分页查询固定配置字
     *
     * @param fixedConfigWord 固定配置字
     * @return 固定配置字列表
     */
    @RequiresPermissions("ota:fota:fixedConfigWord:list")
    @Override
    @GetMapping(value = "/list")
    public TableDataInfo list(FixedConfigWordMpt fixedConfigWord) {
        logger.info("管理后台用户[{}]分页查询固定配置字", SecurityUtils.getUsername());
        startPage();
        List<FixedConfigWordPo> fixedConfigWordPoList = fixedConfigWordAppService.search(fixedConfigWord.getEcu(),
                fixedConfigWord.getType(), getBeginTime(fixedConfigWord), getEndTime(fixedConfigWord));
        List<FixedConfigWordMpt> fixedConfigWordMptList = FixedConfigWordMptAssembler.INSTANCE.fromPoList(fixedConfigWordPoList);
        return getDataTable(fixedConfigWordPoList, fixedConfigWordMptList);
    }

    /**
     * 列出固定配置字明细列表
     *
     * @param fixedConfigWordId 固定配置字ID
     * @return 固定配置字明细列表
     */
    @RequiresPermissions("ota:fota:fixedConfigWord:query")
    @Override
    @GetMapping(value = "/{fixedConfigWordId}/detail")
    public AjaxResult listDetail(@PathVariable Long fixedConfigWordId) {
        logger.info("管理后台用户[{}]列出固定配置字[{}]明细列表", SecurityUtils.getUsername(), fixedConfigWordId);
        List<FixedConfigWordDetailPo> fixedConfigWordDetailPoList = fixedConfigWordAppService.listDetailByFixedConfigWordId(fixedConfigWordId);
        return success(FixedConfigWordDetailMptAssembler.INSTANCE.fromPoList(fixedConfigWordDetailPoList));
    }

    /**
     * 导出固定配置字
     *
     * @param response        响应
     * @param fixedConfigWord 固定配置字
     */
    @Log(title = "固定配置字管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("ota:fota:fixedConfigWord:export")
    @Override
    @PostMapping("/export")
    public void export(HttpServletResponse response, FixedConfigWordMpt fixedConfigWord) {
        logger.info("管理后台用户[{}]导出固定配置字", SecurityUtils.getUsername());
    }

    /**
     * 根据固定配置字ID获取固定配置字
     *
     * @param fixedConfigWordId 固定配置字ID
     * @return 固定配置字
     */
    @RequiresPermissions("ota:fota:fixedConfigWord:query")
    @Override
    @GetMapping(value = "/{fixedConfigWordId}")
    public AjaxResult getInfo(@PathVariable Long fixedConfigWordId) {
        logger.info("管理后台用户[{}]根据固定配置字ID[{}]获取固定配置字", SecurityUtils.getUsername(), fixedConfigWordId);
        FixedConfigWordPo fixedConfigWordPo = fixedConfigWordAppService.getFixedConfigWordById(fixedConfigWordId);
        return success(FixedConfigWordMptAssembler.INSTANCE.fromPo(fixedConfigWordPo));
    }

    /**
     * 根据固定配置字明细ID获取固定配置字明细
     *
     * @param fixedConfigWordId       固定配置字ID
     * @param fixedConfigWordDetailId 固定配置字明细ID
     * @return 固定配置字明细
     */
    @RequiresPermissions("ota:fota:fixedConfigWord:query")
    @Override
    @GetMapping(value = "/{fixedConfigWordId}/detail/{fixedConfigWordDetailId}")
    public AjaxResult getDetailInfo(@PathVariable Long fixedConfigWordId, @PathVariable Long fixedConfigWordDetailId) {
        logger.info("管理后台用户[{}]根据固定配置字明细ID[{}]获取固定配置字明细", SecurityUtils.getUsername(), fixedConfigWordDetailId);
        FixedConfigWordDetailPo fixedConfigWordDetailPo = fixedConfigWordAppService.getFixedConfigWordDetailById(fixedConfigWordDetailId);
        return success(FixedConfigWordDetailMptAssembler.INSTANCE.fromPo(fixedConfigWordDetailPo));
    }

    /**
     * 新增固定配置字
     *
     * @param fixedConfigWord 固定配置字
     * @return 结果
     */
    @Log(title = "固定配置字管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("ota:fota:fixedConfigWord:add")
    @Override
    @PostMapping
    public AjaxResult add(@Validated @RequestBody FixedConfigWordMpt fixedConfigWord) {
        logger.info("管理后台用户[{}]新增固定配置字[{}]", SecurityUtils.getUsername(), fixedConfigWord.getEcu());
        FixedConfigWordPo fixedConfigWordPo = FixedConfigWordMptAssembler.INSTANCE.toPo(fixedConfigWord);
        fixedConfigWordPo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(fixedConfigWordAppService.createFixedConfigWord(fixedConfigWordPo));
    }

    /**
     * 新增固定配置字明细
     *
     * @param fixedConfigWordId     固定配置字ID
     * @param fixedConfigWordDetail 固定配置字明细
     * @return 结果
     */
    @Log(title = "固定配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:fixedConfigWord:edit")
    @Override
    @PostMapping("/{fixedConfigWordId}/detail")
    public AjaxResult addDetail(@PathVariable Long fixedConfigWordId, @Validated @RequestBody FixedConfigWordDetailMpt fixedConfigWordDetail) {
        logger.info("管理后台用户[{}]新增固定配置字[{}]明细", SecurityUtils.getUsername(), fixedConfigWordId);
        FixedConfigWordDetailPo fixedConfigWordDetailPo = FixedConfigWordDetailMptAssembler.INSTANCE.toPo(fixedConfigWordDetail);
        fixedConfigWordDetailPo.setCreateBy(SecurityUtils.getUserId().toString());
        return toAjax(fixedConfigWordAppService.createFixedConfigWordDetail(fixedConfigWordDetailPo));
    }

    /**
     * 修改保存固定配置字
     *
     * @param fixedConfigWord 固定配置字
     * @return 结果
     */
    @Log(title = "固定配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:fixedConfigWord:edit")
    @Override
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody FixedConfigWordMpt fixedConfigWord) {
        logger.info("管理后台用户[{}]修改保存固定配置字[{}]", SecurityUtils.getUsername(), fixedConfigWord.getEcu());
        FixedConfigWordPo fixedConfigWordPo = FixedConfigWordMptAssembler.INSTANCE.toPo(fixedConfigWord);
        fixedConfigWordPo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(fixedConfigWordAppService.modifyFixedConfigWord(fixedConfigWordPo));
    }

    /**
     * 修改保存固定配置字明细
     *
     * @param fixedConfigWordId     固定配置字ID
     * @param fixedConfigWordDetail 固定配置字明细
     * @return 结果
     */
    @Log(title = "固定配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:fixedConfigWord:edit")
    @Override
    @PutMapping("/{fixedConfigWordId}/detail")
    public AjaxResult editDetail(@PathVariable Long fixedConfigWordId, @Validated @RequestBody FixedConfigWordDetailMpt fixedConfigWordDetail) {
        logger.info("管理后台用户[{}]修改保存固定配置字[{}]明细", SecurityUtils.getUsername(), fixedConfigWordId);
        FixedConfigWordDetailPo fixedConfigWordDetailPo = FixedConfigWordDetailMptAssembler.INSTANCE.toPo(fixedConfigWordDetail);
        fixedConfigWordDetailPo.setModifyBy(SecurityUtils.getUserId().toString());
        return toAjax(fixedConfigWordAppService.modifyFixedConfigWordDetail(fixedConfigWordDetailPo));
    }

    /**
     * 删除固定配置字
     *
     * @param fixedConfigWordIds 固定配置字ID数组
     * @return 结果
     */
    @Log(title = "固定配置字管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("ota:fota:fixedConfigWord:remove")
    @Override
    @DeleteMapping("/{fixedConfigWordIds}")
    public AjaxResult remove(@PathVariable Long[] fixedConfigWordIds) {
        logger.info("管理后台用户[{}]删除固定配置字[{}]", SecurityUtils.getUsername(), fixedConfigWordIds);
        return toAjax(fixedConfigWordAppService.deleteFixedConfigWordByIds(fixedConfigWordIds));
    }

    /**
     * 删除固定配置字明细
     *
     * @param fixedConfigWordId        固定配置字ID
     * @param fixedConfigWordDetailIds 固定配置字明细ID数组
     * @return 结果
     */
    @Log(title = "固定配置字管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("ota:fota:fixedConfigWord:edit")
    @Override
    @DeleteMapping("/{fixedConfigWordId}/detail/{fixedConfigWordDetailIds}")
    public AjaxResult removeDetail(@PathVariable Long fixedConfigWordId, @PathVariable Long[] fixedConfigWordDetailIds) {
        logger.info("管理后台用户[{}]删除固定配置字[{}]明细[{}]", SecurityUtils.getUsername(), fixedConfigWordId, fixedConfigWordDetailIds);
        return toAjax(fixedConfigWordAppService.deleteFixedConfigWordDetailByIds(fixedConfigWordDetailIds));
    }
}
