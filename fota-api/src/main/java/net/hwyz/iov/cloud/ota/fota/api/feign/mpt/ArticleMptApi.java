package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.ArticleMpt;

/**
 * 文章相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface ArticleMptApi {

    /**
     * 分页查询文章
     *
     * @param article 文章
     * @return 文章列表
     */
    TableDataInfo list(ArticleMpt article);

    /**
     * 导出文章
     *
     * @param response 响应
     * @param article  文章
     */
    void export(HttpServletResponse response, ArticleMpt article);

    /**
     * 根据文章ID获取文章
     *
     * @param articleId 文章ID
     * @return 文章
     */
    AjaxResult getInfo(Long articleId);

    /**
     * 新增文章
     *
     * @param article 文章
     * @return 结果
     */
    AjaxResult add(ArticleMpt article);

    /**
     * 修改保存文章
     *
     * @param article 文章
     * @return 结果
     */
    AjaxResult edit(ArticleMpt article);

    /**
     * 删除文章
     *
     * @param articleIds 文章ID数组
     * @return 结果
     */
    AjaxResult remove(Long[] articleIds);

}
