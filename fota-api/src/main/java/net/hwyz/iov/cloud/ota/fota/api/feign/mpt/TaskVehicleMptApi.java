package net.hwyz.iov.cloud.ota.fota.api.feign.mpt;

import jakarta.servlet.http.HttpServletResponse;
import net.hwyz.iov.cloud.framework.common.web.domain.AjaxResult;
import net.hwyz.iov.cloud.framework.common.web.page.TableDataInfo;
import net.hwyz.iov.cloud.ota.fota.api.contract.TaskVehicleMpt;

/**
 * 车辆升级任务相关管理后台接口
 *
 * @author hwyz_leo
 */
public interface TaskVehicleMptApi {

    /**
     * 分页查询车辆升级任务
     *
     * @param taskVehicle 车辆升级任务
     * @return 车辆升级任务列表
     */
    TableDataInfo list(TaskVehicleMpt taskVehicle);

    /**
     * 导出车辆升级任务
     *
     * @param response    响应
     * @param taskVehicle 车辆升级任务
     */
    void export(HttpServletResponse response, TaskVehicleMpt taskVehicle);

    /**
     * 根据车辆升级任务ID获取车辆升级任务
     *
     * @param taskVehicleId 车辆升级任务ID
     * @return 车辆升级任务
     */
    AjaxResult getInfo(Long taskVehicleId);

    /**
     * 获取车辆升级任务过程
     *
     * @param taskVehicleId 升级任务车辆ID
     * @return 升级任务过程列表
     */
    AjaxResult listProcess(Long taskVehicleId);

}
