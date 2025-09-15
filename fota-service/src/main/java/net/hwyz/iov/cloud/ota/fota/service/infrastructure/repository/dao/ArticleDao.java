package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ArticlePo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-09-15
 */
@Mapper
public interface ArticleDao extends BaseDao<ArticlePo, Long> {

}
