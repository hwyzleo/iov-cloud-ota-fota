package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.VehicleMpt;

/**
 * 车辆相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface VehicleMptApi {

    /**
     * 分页查询车辆信息
     *
     * @param vehicle 车辆信息
     * @return 车辆信息列表
     */
    TableDataInfo list(VehicleMpt vehicle);

    /**
     * 导出车辆信息
     *
     * @param response 响应
     * @param vehicle  车辆信息
     */
    void export(HttpServletResponse response, VehicleMpt vehicle);

    /**
     * 根据车架号获取车辆信息
     *
     * @param vin 车架号
     * @return 车辆信息
     */
    AjaxResult getInfo(String vin);

}
