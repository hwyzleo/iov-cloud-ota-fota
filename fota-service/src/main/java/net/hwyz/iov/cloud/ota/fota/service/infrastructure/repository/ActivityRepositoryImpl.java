package net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.AbstractRepository;
import net.hwyz.iov.cloud.ota.fota.service.domain.activity.model.ActivityDo;
import net.hwyz.iov.cloud.ota.fota.service.domain.activity.repository.ActivityRepository;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.assembler.ActivityPoAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.dao.ActivityDao;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.repository.po.ActivityPo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 升级活动仓库接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class ActivityRepositoryImpl extends AbstractRepository<Long, ActivityDo> implements ActivityRepository {

    private final ActivityDao activityDao;

    @Override
    public Optional<ActivityDo> getById(Long id) {
        ActivityPo activityPo = activityDao.selectPoById(id);
        if (activityPo != null) {
            ActivityDo activityDo = ActivityPoAssembler.INSTANCE.toDo(activityPo);
            activityDo.stateLoad();
            return Optional.of(activityDo);
        }
        return Optional.empty();
    }

    @Override
    public boolean save(ActivityDo activityDo) {
        switch (activityDo.getState()) {
            case CHANGED -> {
                ActivityPo activityPo = ActivityPoAssembler.INSTANCE.fromDo(activityDo);
                activityDao.updatePo(activityPo);
            }
            default -> {
                return false;
            }
        }
        return true;
    }

}
