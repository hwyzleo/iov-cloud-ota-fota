package net.hwyz.iov.cloud.ota.fota.service.facade.assembler;

import net.hwyz.iov.cloud.ota.fota.api.contract.ActivitySoftwarePartVersionMpt;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivitySoftwarePartVersionPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 管理后台升级活动下软件零件版本转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface ActivitySoftwarePartVersionMptAssembler {

    ActivitySoftwarePartVersionMptAssembler INSTANCE = Mappers.getMapper(ActivitySoftwarePartVersionMptAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param activitySoftwarePartVersionPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({})
    ActivitySoftwarePartVersionMpt fromPo(ActivitySoftwarePartVersionPo activitySoftwarePartVersionPo);

    /**
     * 数据传输对象转数据对象
     *
     * @param activitySoftwarePartVersionMpt 数据传输对象
     * @return 数据对象
     */
    @Mappings({})
    ActivitySoftwarePartVersionPo toPo(ActivitySoftwarePartVersionMpt activitySoftwarePartVersionMpt);

    /**
     * 数据对象列表转数据传输对象列表
     *
     * @param activitySoftwarePartVersionPoList 数据对象列表
     * @return 数据传输对象列表
     */
    List<ActivitySoftwarePartVersionMpt> fromPoList(List<ActivitySoftwarePartVersionPo> activitySoftwarePartVersionPoList);

}
