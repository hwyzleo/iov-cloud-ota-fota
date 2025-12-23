package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.FixedConfigWordDetailMpt;
import net.hwyz.iov.cloud.ota.fota.api.contract.FixedConfigWordMpt;

/**
 * 固定配置字相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface FixedConfigWordMptApi {

    /**
     * 分页查询固定配置字
     *
     * @param fixedConfigWord 固定配置字
     * @return 固定配置字列表
     */
    TableDataInfo list(FixedConfigWordMpt fixedConfigWord);

    /**
     * 列出固定配置字明细列表
     *
     * @param fixedConfigWordId 固定配置字ID
     * @return 固定配置字明细列表
     */
    AjaxResult listDetail(Long fixedConfigWordId);

    /**
     * 导出固定配置字
     *
     * @param response        响应
     * @param fixedConfigWord 固定配置字
     */
    void export(HttpServletResponse response, FixedConfigWordMpt fixedConfigWord);

    /**
     * 根据固定配置字ID获取固定配置字
     *
     * @param fixedConfigWordId 固定配置字ID
     * @return 固定配置字
     */
    AjaxResult getInfo(Long fixedConfigWordId);

    /**
     * 根据固定配置字明细ID获取固定配置字明细
     *
     * @param fixedConfigWordId       固定配置字ID
     * @param fixedConfigWordDetailId 固定配置字明细ID
     * @return 固定配置字明细
     */
    AjaxResult getDetailInfo(Long fixedConfigWordId, Long fixedConfigWordDetailId);

    /**
     * 新增固定配置字
     *
     * @param fixedConfigWord 固定配置字
     * @return 结果
     */
    AjaxResult add(FixedConfigWordMpt fixedConfigWord);

    /**
     * 新增固定配置字明细
     *
     * @param fixedConfigWordId     固定配置字ID
     * @param fixedConfigWordDetail 固定配置字明细
     * @return 结果
     */
    AjaxResult addDetail(Long fixedConfigWordId, FixedConfigWordDetailMpt fixedConfigWordDetail);

    /**
     * 修改保存固定配置字
     *
     * @param fixedConfigWord 固定配置字
     * @return 结果
     */
    AjaxResult edit(FixedConfigWordMpt fixedConfigWord);

    /**
     * 修改保存固定配置字明细
     *
     * @param fixedConfigWordId     固定配置字ID
     * @param fixedConfigWordDetail 固定配置字明细
     * @return 结果
     */
    AjaxResult editDetail(Long fixedConfigWordId, FixedConfigWordDetailMpt fixedConfigWordDetail);

    /**
     * 删除固定配置字
     *
     * @param fixedConfigWordIds 固定配置字ID数组
     * @return 结果
     */
    AjaxResult remove(Long[] fixedConfigWordIds);

    /**
     * 删除固定配置字明细
     *
     * @param fixedConfigWordId        固定配置字ID
     * @param fixedConfigWordDetailIds 固定配置字明细ID数组
     * @return 结果
     */
    AjaxResult removeDetail(Long fixedConfigWordId, Long[] fixedConfigWordDetailIds);

}
