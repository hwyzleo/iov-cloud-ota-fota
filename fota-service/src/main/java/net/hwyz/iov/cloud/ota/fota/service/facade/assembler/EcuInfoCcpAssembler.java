package net.hwyz.iov.cloud.ota.fota.service.facade.assembler;

import net.hwyz.iov.cloud.ota.fota.api.contract.EcuInfoCcp;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model.EcuInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 中央计算平台ECU设备信息转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface EcuInfoCcpAssembler {

    EcuInfoCcpAssembler INSTANCE = Mappers.getMapper(EcuInfoCcpAssembler.class);

    /**
     * 值对象转数据传输对象
     *
     * @param ecuInfoVo 值对象
     * @return 数据传输对象
     */
    @Mappings({})
    EcuInfoCcp fromVo(EcuInfoVo ecuInfoVo);

    /**
     * 数据传输对象转值对象
     *
     * @param ecuInfoCcp 数据传输对象
     * @return 值对象
     */
    @Mappings({})
    EcuInfoVo toVo(EcuInfoCcp ecuInfoCcp);

    /**
     * 值对象列表转数据传输对象列表
     *
     * @param ecuInfoVoList 值对象列表
     * @return 数据传输对象列表
     */
    List<EcuInfoCcp> fromVoList(List<EcuInfoVo> ecuInfoVoList);

    /**
     * 数据传输对象列表转值对象列表
     *
     * @param ecuInfoCcpList 数据传输对象列表
     * @return 值对象列表
     */
    List<EcuInfoVo> toVoList(List<EcuInfoCcp> ecuInfoCcpList);

}
