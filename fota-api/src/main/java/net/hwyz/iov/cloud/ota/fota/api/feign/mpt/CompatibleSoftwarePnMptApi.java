package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.CompatibleSoftwarePnMpt;

/**
 * 兼容软件零件号相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface CompatibleSoftwarePnMptApi {

    /**
     * 分页查询兼容软件零件号
     *
     * @param compatibleSoftwarePn 兼容软件零件号
     * @return 兼容软件零件号列表
     */
    TableDataInfo list(CompatibleSoftwarePnMpt compatibleSoftwarePn);

    /**
     * 导出兼容软件零件号
     *
     * @param response             响应
     * @param compatibleSoftwarePn 兼容软件零件号
     */
    void export(HttpServletResponse response, CompatibleSoftwarePnMpt compatibleSoftwarePn);

    /**
     * 根据兼容软件零件号ID获取兼容软件零件号
     *
     * @param compatibleSoftwarePnId 兼容软件零件号ID
     * @return 兼容软件零件号
     */
    AjaxResult getInfo(Long compatibleSoftwarePnId);

    /**
     * 新增兼容软件零件号
     *
     * @param compatibleSoftwarePn 兼容软件零件号
     * @return 结果
     */
    AjaxResult add(CompatibleSoftwarePnMpt compatibleSoftwarePn);

    /**
     * 修改保存兼容软件零件号
     *
     * @param compatibleSoftwarePn 兼容软件零件号
     * @return 结果
     */
    AjaxResult edit(CompatibleSoftwarePnMpt compatibleSoftwarePn);

    /**
     * 删除兼容软件零件号
     *
     * @param compatibleSoftwarePnIds 兼容软件零件号ID数组
     * @return 结果
     */
    AjaxResult remove(Long[] compatibleSoftwarePnIds);

}
