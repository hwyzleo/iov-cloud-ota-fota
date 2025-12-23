package net.hwyz.iov.cloud.ota.fota.service.facade.assembler;

import net.hwyz.iov.cloud.ota.fota.api.contract.CompatibleSoftwarePnMpt;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.CompatibleSoftwarePnPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 管理后台兼容软件零件号转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface CompatibleSoftwarePnMptAssembler {

    CompatibleSoftwarePnMptAssembler INSTANCE = Mappers.getMapper(CompatibleSoftwarePnMptAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param compatibleSoftwarePnPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({
            @Mapping(target = "description", source = "description")
    })
    CompatibleSoftwarePnMpt fromPo(CompatibleSoftwarePnPo compatibleSoftwarePnPo);

    /**
     * 数据传输对象转数据对象
     *
     * @param compatibleSoftwarePnMpt 数据传输对象
     * @return 数据对象
     */
    @Mappings({
            @Mapping(target = "description", source = "description")
    })
    CompatibleSoftwarePnPo toPo(CompatibleSoftwarePnMpt compatibleSoftwarePnMpt);

    /**
     * 数据对象列表转数据传输对象列表
     *
     * @param compatibleSoftwarePnPoList 数据对象列表
     * @return 数据传输对象列表
     */
    List<CompatibleSoftwarePnMpt> fromPoList(List<CompatibleSoftwarePnPo> compatibleSoftwarePnPoList);

}
