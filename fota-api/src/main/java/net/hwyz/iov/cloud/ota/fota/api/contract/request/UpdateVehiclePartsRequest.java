package net.hwyz.iov.cloud.ota.fota.api.contract.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.hwyz.iov.cloud.ota.fota.api.contract.PartExService;

import java.util.List;

/**
 * 更新车辆零部件信息请求
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehiclePartsRequest {

    /**
     * 零部件列表
     */
    @NotEmpty(message = "零部件列表不能为空")
    private List<PartExService> partList;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 备注
     */
    private String remark;

}
