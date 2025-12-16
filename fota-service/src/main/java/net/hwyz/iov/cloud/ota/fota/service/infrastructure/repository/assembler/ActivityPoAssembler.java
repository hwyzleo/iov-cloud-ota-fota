package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.assembler;

import net.hwyz.iov.cloud.ota.fota.service.domain.activity.model.ActivityDo;
import net.hwyz.iov.cloud.ota.fota.service.domain.task.model.TaskDo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityPo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.TaskPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 升级活动数据对象转换类
 *
 * @author hwyz_leo
 */
@Mapper
public interface ActivityPoAssembler {

    ActivityPoAssembler INSTANCE = Mappers.getMapper(ActivityPoAssembler.class);

    /**
     * 数据对象转领域对象
     *
     * @param activityPo 数据对象
     * @return 领域对象
     */
    @Mappings({
            @Mapping(target = "activityState", expression = "java(net.hwyz.iov.cloud.ota.fota.api.contract.enums.ActivityState.valOf(activityPo.getState()))"),
            @Mapping(target = "state", ignore = true)
    })
    ActivityDo toDo(ActivityPo activityPo);

    /**
     * 领域对象转数据对象
     *
     * @param activityDo 领域对象
     * @return 数据对象
     */
    @Mappings({
            @Mapping(target = "state", source = "activityState.value"),
            @Mapping(target = "description", source = "description")
    })
    ActivityPo fromDo(ActivityDo activityDo);

}
