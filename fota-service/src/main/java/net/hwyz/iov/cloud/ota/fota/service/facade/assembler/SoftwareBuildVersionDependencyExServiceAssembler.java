package net.hwyz.iov.cloud.ota.fota.service.facade.assembler;

import net.hwyz.iov.cloud.ota.baseline.api.contract.SoftwareBuildVersionDependencyExService;
import net.hwyz.iov.cloud.ota.fota.service.domain.activity.model.SoftwareBuildVersionDependencyVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 对外服务软件内部版本依赖信息转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface SoftwareBuildVersionDependencyExServiceAssembler {

    SoftwareBuildVersionDependencyExServiceAssembler INSTANCE = Mappers.getMapper(SoftwareBuildVersionDependencyExServiceAssembler.class);

    /**
     * 数据传输对象转值对象
     *
     * @param softwareBuildVersionDependencyExService 数据传输对象
     * @return 值对象
     */
    @Mappings({
            @Mapping(target = "adaptiveLevel", expression = "java(net.hwyz.iov.cloud.ota.fota.api.contract.enums.AdaptiveLevel.valOf(softwareBuildVersionDependencyExService.getAdaptiveLevel()))")
    })
    SoftwareBuildVersionDependencyVo toVo(SoftwareBuildVersionDependencyExService softwareBuildVersionDependencyExService);

    /**
     * 数据传输对象列表转值对象列表
     *
     * @param softwareBuildVersionDependencyExServiceList 数据传输对象列表
     * @return 值对象列表
     */
    List<SoftwareBuildVersionDependencyVo> toVoList(List<SoftwareBuildVersionDependencyExService> softwareBuildVersionDependencyExServiceList);

}
