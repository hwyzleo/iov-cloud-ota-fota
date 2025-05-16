package net.hwyz.iov.cloud.ota.fota.service.application.service;

import cn.hutool.core.util.ObjUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.util.StrUtil;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.PartDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.PartLogDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.PartLogPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.PartPo;
import org.springframework.stereotype.Service;

/**
 * 零部件相关应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PartAppService {

    private final PartDao partDao;
    private final PartLogDao partLogDao;

    /**
     * 保存零部件信息
     *
     * @param part   零部件信息
     * @param remark 备注
     */
    public void savePart(PartPo part, String remark) {
        PartPo partPo = partDao.selectBySn(part.getSn());
        if (ObjUtil.isNull(partDao.selectBySn(part.getSn()))) {
            partDao.insertPo(part);
            recordLog(part, StrUtil.nullToEmpty(remark) + "新增");
        } else {
            StringBuilder changeRemark = new StringBuilder();
            if (StrUtil.nullToEmpty(partPo.getConfigWord()).equalsIgnoreCase(StrUtil.nullToEmpty(part.getConfigWord()))) {
                partPo.setConfigWord(part.getConfigWord());
                changeRemark.append("配置字：").append(partPo.getConfigWord()).append("->").append(part.getConfigWord()).append(";");
            }
            if (StrUtil.nullToEmpty(partPo.getHardwareVer()).equalsIgnoreCase(StrUtil.nullToEmpty(part.getHardwareVer()))) {
                partPo.setHardwareVer(part.getHardwareVer());
                changeRemark.append("硬件版本：").append(partPo.getHardwareVer()).append("->").append(part.getHardwareVer()).append(";");
            }
            if (StrUtil.nullToEmpty(partPo.getSoftwareVer()).equalsIgnoreCase(StrUtil.nullToEmpty(part.getSoftwareVer()))) {
                partPo.setSoftwareVer(part.getSoftwareVer());
                changeRemark.append("软件版本：").append(partPo.getSoftwareVer()).append("->").append(part.getSoftwareVer()).append(";");
            }
            if (StrUtil.nullToEmpty(partPo.getHardwareNo()).equalsIgnoreCase(StrUtil.nullToEmpty(part.getHardwareNo()))) {
                partPo.setHardwareNo(part.getHardwareNo());
                changeRemark.append("硬件零件号：").append(partPo.getHardwareNo()).append("->").append(part.getHardwareNo()).append(";");
            }
            if (StrUtil.nullToEmpty(partPo.getSoftwareNo()).equalsIgnoreCase(StrUtil.nullToEmpty(part.getSoftwareNo()))) {
                partPo.setSoftwareNo(part.getSoftwareNo());
                changeRemark.append("软件零件号：").append(partPo.getSoftwareNo()).append("->").append(part.getSoftwareNo()).append(";");
            }
            if (changeRemark.length() > 0) {
                partDao.updatePo(partPo);
                recordLog(partPo, StrUtil.nullToEmpty(remark) + changeRemark);
            }
        }
    }

    /**
     * 记录零部件信息变更日志
     *
     * @param partPo 零部件对象
     * @param remark 变更备注
     */
    private void recordLog(PartPo partPo, String remark) {
        partLogDao.insertPo(PartLogPo.builder()
                .sn(partPo.getSn())
                .configWord(partPo.getConfigWord())
                .hardwareVer(partPo.getHardwareVer())
                .softwareVer(partPo.getSoftwareVer())
                .hardwareNo(partPo.getHardwareNo())
                .softwareNo(partPo.getSoftwareNo())
                .description(remark)
                .build());
    }

}
