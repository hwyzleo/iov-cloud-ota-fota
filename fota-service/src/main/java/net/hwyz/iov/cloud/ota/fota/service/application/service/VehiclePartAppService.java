package net.hwyz.iov.cloud.ota.fota.service.application.service;

import cn.hutool.core.util.ObjUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.util.StrUtil;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.VehPartDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.VehPartLogDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.PartPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.VehPartLogPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.VehPartPo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆零部件相关应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VehiclePartAppService {

    private final VehPartDao vehPartDao;
    private final VehPartLogDao vehPartLogDao;
    private final PartAppService partAppService;

    /**
     * 保存车辆零部件信息
     *
     * @param vin      车架号
     * @param remark   备注
     * @param partList 零部件列表
     */
    public void saveVehicleParts(String vin, String remark, List<PartPo> partList) {
        for (PartPo partPo : partList) {
            partAppService.savePart(partPo, remark);
            VehPartPo vehPartPo = vehPartDao.selectByVinAndEcu(vin, partPo.getEcu());
            if (ObjUtil.isNull(vehPartPo)) {
                VehPartPo newVehPartPo = VehPartPo.builder()
                        .vin(vin)
                        .ecu(partPo.getEcu())
                        .partSn(partPo.getSn())
                        .build();
                vehPartDao.insertPo(newVehPartPo);
                recordLog(newVehPartPo, StrUtil.nullToEmpty(remark) + "新增");
            } else {
                if (!StrUtil.nullToEmpty(vehPartPo.getPartSn()).equalsIgnoreCase(partPo.getSn())) {
                    String changeRemark = "SN：" + vehPartPo.getPartSn() + "->" + partPo.getSn();
                    vehPartPo.setPartSn(partPo.getSn());
                    vehPartDao.updatePo(vehPartPo);
                    recordLog(vehPartPo, StrUtil.nullToEmpty(remark) + changeRemark);
                }
            }
        }
    }

    /**
     * 记录车辆零部件信息变更日志
     *
     * @param vehPartPo 车辆零部件对象
     * @param remark    变更备注
     */
    private void recordLog(VehPartPo vehPartPo, String remark) {
        vehPartLogDao.insertPo(VehPartLogPo.builder()
                .vin(vehPartPo.getVin())
                .ecu(vehPartPo.getEcu())
                .partSn(vehPartPo.getPartSn())
                .description(remark)
                .build());
    }

}
