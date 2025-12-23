package net.hwyz.iov.cloud.ota.fota.api.contract;

import lombok.*;
import net.hwyz.iov.cloud.framework.common.web.domain.BaseRequest;

import java.util.Date;

/**
 * 管理后台固定配置字
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FixedConfigWordMpt extends BaseRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 零部件ECU
     */
    private String ecu;

    /**
     * 软件零件号
     */
    private String softwarePn;

    /**
     * 分类
     */
    private Integer type;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}
