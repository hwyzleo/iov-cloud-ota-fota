package net.hwyz.iov.cloud.ota.fota.api.contract.enums;

import lombok.AllArgsConstructor;

/**
 * 升级任务策略类型枚举类
 *
 * @author hwyz_leo
 */
@AllArgsConstructor
public enum TaskStrategyType {

    ROLLBACK("是否回滚");

    /**
     * 名称
     */
    public final String label;

}
