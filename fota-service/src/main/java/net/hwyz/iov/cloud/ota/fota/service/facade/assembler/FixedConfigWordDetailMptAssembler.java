package net.hwyz.iov.cloud.ota.fota.service.facade.assembler;

import net.hwyz.iov.cloud.ota.fota.api.contract.FixedConfigWordDetailMpt;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.FixedConfigWordDetailPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 管理后台固定配置字明细转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface FixedConfigWordDetailMptAssembler {

    FixedConfigWordDetailMptAssembler INSTANCE = Mappers.getMapper(FixedConfigWordDetailMptAssembler.class);

    /**
     * 数据对象转数据传输对象
     *
     * @param fixedConfigWordDetailPo 数据对象
     * @return 数据传输对象
     */
    @Mappings({
            @Mapping(target = "description", source = "description")
    })
    FixedConfigWordDetailMpt fromPo(FixedConfigWordDetailPo fixedConfigWordDetailPo);

    /**
     * 数据传输对象转数据对象
     *
     * @param fixedConfigWordDetailMpt 数据传输对象
     * @return 数据对象
     */
    @Mappings({
            @Mapping(target = "description", source = "description")
    })
    FixedConfigWordDetailPo toPo(FixedConfigWordDetailMpt fixedConfigWordDetailMpt);

    /**
     * 数据对象列表转数据传输对象列表
     *
     * @param fixedConfigWordDetailPoList 数据对象列表
     * @return 数据传输对象列表
     */
    List<FixedConfigWordDetailMpt> fromPoList(List<FixedConfigWordDetailPo> fixedConfigWordDetailPoList);

}
