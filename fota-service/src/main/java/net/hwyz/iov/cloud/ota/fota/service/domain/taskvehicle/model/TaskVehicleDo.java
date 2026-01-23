package net.hwyz.iov.cloud.ota.fota.service.domain.taskvehicle.model;

import cn.hutool.core.comparator.VersionComparator;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.domain.BaseDo;
import net.hwyz.iov.cloud.framework.common.domain.DomainObj;
import net.hwyz.iov.cloud.ota.baseline.api.contract.enums.SoftwarePackageType;
import net.hwyz.iov.cloud.ota.fota.api.contract.CloudFotaInfoCcp;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.AdaptiveLevel;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.AdaptiveSubject;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.TaskRestrictionType;
import net.hwyz.iov.cloud.ota.fota.api.contract.enums.TaskStrategyType;
import net.hwyz.iov.cloud.ota.fota.service.domain.activity.model.*;
import net.hwyz.iov.cloud.ota.fota.service.domain.task.model.TaskDo;
import net.hwyz.iov.cloud.ota.fota.service.domain.task.model.TaskRestrictionVo;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model.EcuInfoVo;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model.VehicleDo;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.util.FotaHelper;

import java.util.*;

/**
 * 升级任务车辆领域对象
 *
 * @author hwyz_leo
 */
@Slf4j
@Getter
@SuperBuilder
public class TaskVehicleDo extends BaseDo<Long> implements DomainObj<TaskVehicleDo> {

    /**
     * 策略列表
     */
    private Map<TaskStrategyType, String> strategyMap;

    /**
     * 软件内部版本列表
     */
    private List<TaskVehicleSoftwareBuildVersionVo> softwareBuildVersionList;

    /**
     * 升级须知文章ID
     */
    private Long upgradeNoticeArticleId;

    /**
     * 活动条款文章ID
     */
    private Long activityTermArticleId;

    /**
     * 隐私协议文章ID
     */
    private Long privacyAgreementArticleId;

    /**
     * 初始化
     */
    public void init() {
        stateInit();
    }

    /**
     * 加载策略
     *
     * @param task 升级任务
     */
    public void loadStrategy(TaskDo task) {
        this.strategyMap = new HashMap<>();
        if (task.getTaskStrategyList() != null) {
            task.getTaskStrategyList().forEach(taskStrategy -> this.strategyMap.put(taskStrategy.getStrategyType(), taskStrategy.getStrategyExpression()));
        }
    }

    /**
     * 加载软件内部版本
     *
     * @param activity   升级活动
     * @param task       升级任务
     * @param vehicle    车辆
     * @param fotaHelper 升级辅助类
     */
    public void loadSoftwareBuildVersion(ActivityDo activity, TaskDo task, VehicleDo vehicle, FotaHelper fotaHelper) {
        this.softwareBuildVersionList = new ArrayList<>();
        Map<Integer, List<ActivitySoftwareBuildVersionVo>> groupSoftwareBuildVersionMap = activity.getGroupSoftwareBuildVersionMap();
        TaskVehicleSoftwareBuildVersionVo taskVehicleSoftwareBuildVersion;
        for (Integer group : groupSoftwareBuildVersionMap.keySet()) {
            List<ActivitySoftwareBuildVersionVo> groupSoftwareBuildVersionList = groupSoftwareBuildVersionMap.get(group);
            if (groupAdaptationMatch(groupSoftwareBuildVersionList, vehicle, activity, task)) {
                for (ActivitySoftwareBuildVersionVo activitySoftwareBuildVersion : groupSoftwareBuildVersionList) {
                    if (!activitySoftwareBuildVersion.getOta()) {
                        logger.info("车辆[{}]ECU[{}]版本[{}]不支持OTA升级，忽略", activitySoftwareBuildVersion.getSoftwareBuildVersion().getEcuCode(),
                                activitySoftwareBuildVersion.getSoftwareBuildVersion().getSoftwareBuildVer(), vehicle.getId());
                        continue;
                    }
                    List<ConfigWordVo> softwareConfigWordList = activitySoftwareBuildVersion.getConfigWordList();
                    List<ConfigWordVo> fixedConfigWordList = activity.getFixedConfigWordList();
                    taskVehicleSoftwareBuildVersion = new TaskVehicleSoftwareBuildVersionVo();
                    taskVehicleSoftwareBuildVersion.setGroup(group);
                    taskVehicleSoftwareBuildVersion.setForceUpgrade(activitySoftwareBuildVersion.getForceUpgrade());
                    taskVehicleSoftwareBuildVersion.setSoftwareBuildVersion(activitySoftwareBuildVersion.getSoftwareBuildVersion());
                    taskVehicleSoftwareBuildVersion.setSoftwarePackageList(activitySoftwareBuildVersion.getSoftwarePackageList());
                    taskVehicleSoftwareBuildVersion.setSoftwareBuildVersionDependencyList(activitySoftwareBuildVersion.getSoftwareBuildVersionDependencyList());
                    taskVehicleSoftwareBuildVersion.setConfigWordList(softwareConfigWordList);
                    EcuInfoVo vehicleEcuInfo = vehicle.getEcuMap().get(activitySoftwareBuildVersion.getSoftwareBuildVersion().getEcuCode());
                    if (Boolean.parseBoolean(this.strategyMap.get(TaskStrategyType.ROLLBACK))) {
                        List<SoftwarePackageVo> rollbackSoftwarePackage = fotaHelper.getSoftwareBuildVersionPackages(vehicleEcuInfo.getEcu(),
                                vehicleEcuInfo.getSoftwarePn() + vehicleEcuInfo.getSoftwarePartVer(),
                                vehicleEcuInfo.getSoftwareBuildVer());
                        taskVehicleSoftwareBuildVersion.setRollbackSoftwarePackageList(rollbackSoftwarePackage);
                    }
                    if (!softwareConfigWordList.isEmpty()) {
                        taskVehicleSoftwareBuildVersion.setOriginConfigWord(vehicleEcuInfo.getConfigWord());
                        taskVehicleSoftwareBuildVersion.setTargetConfigWord(fotaHelper.configWordToStr(vehicleEcuInfo.getConfigWord(), softwareConfigWordList));
                    }
                    if (!fixedConfigWordList.isEmpty()) {
                        taskVehicleSoftwareBuildVersion.setOriginConfigWord(vehicleEcuInfo.getConfigWord());
                        taskVehicleSoftwareBuildVersion.setTargetConfigWord(fotaHelper.configWordToStr(vehicleEcuInfo.getConfigWord(), fixedConfigWordList));
                    }
                    this.softwareBuildVersionList.add(taskVehicleSoftwareBuildVersion);
                }
            }
        }
    }

    /**
     * 加载文章
     *
     * @param activity 升级活动
     */
    public void loadArticle(ActivityDo activity) {
        this.upgradeNoticeArticleId = activity.getUpgradeNoticeArticleId();
        this.activityTermArticleId = activity.getActivityTermArticleId();
        this.privacyAgreementArticleId = activity.getPrivacyAgreementArticleId();
    }

    /**
     * 转换为云端升级信息
     *
     * @return 云端升级信息
     */
    public CloudFotaInfoCcp toCloudFotaInfoCcp() {
        CloudFotaInfoCcp cloudFotaInfoCcp = new CloudFotaInfoCcp();
        return cloudFotaInfoCcp;
    }

    /**
     * 软件组适配比对
     * 同组内软件有任何一个不适配，不影响其他软件适配，直到适配结束
     * 非基线OTA软件适配，同组内软件有任何一个不适配，不进行升级
     *
     * @param groupSoftwareBuildVersionList 组软件内部版本列表
     * @param vehicle                       车辆
     * @param activity                      升级活动
     * @param task                          升级任务
     * @return true: 适配成功，false: 适配失败
     */
    private boolean groupAdaptationMatch(List<ActivitySoftwareBuildVersionVo> groupSoftwareBuildVersionList, VehicleDo vehicle,
                                         ActivityDo activity, TaskDo task) {
        for (int i = groupSoftwareBuildVersionList.size() - 1; i > 0; i--) {
            ActivitySoftwareBuildVersionVo activitySoftwareBuildVersion = groupSoftwareBuildVersionList.get(i);
            SoftwareBuildVersionVo softwareBuildVersion = activitySoftwareBuildVersion.getSoftwareBuildVersion();
            EcuInfoVo ecuInfo = vehicle.getEcuMap().get(softwareBuildVersion.getEcuCode());
            boolean adaptiveResult = false;
            if (ecuInfo == null) {
                logger.warn("车辆[{}]待升级ECU[{}]没有上报，跳过", vehicle.getId(), softwareBuildVersion.getEcuCode());
            } else {
                adaptiveResult = ecuAdaptationMatch(ecuInfo, activitySoftwareBuildVersion, activity, task, vehicle);
            }
            if (!adaptiveResult) {
                if (!activity.getBaseline()) {
                    return false;
                }
                groupSoftwareBuildVersionList.remove(i);
            }
        }
        return true;
    }

    /**
     * ECU适配比对
     *
     * @param ecuInfo                      ECU信息
     * @param activitySoftwareBuildVersion 活动软件内部版本
     * @param activity                     升级活动
     * @param task                         升级任务
     * @param vehicle                      车辆
     * @return true: 适配成功，false: 适配失败
     */
    private boolean ecuAdaptationMatch(EcuInfoVo ecuInfo, ActivitySoftwareBuildVersionVo activitySoftwareBuildVersion,
                                       ActivityDo activity, TaskDo task, VehicleDo vehicle) {
        TaskRestrictionVo comparisonCriteriaVo = task.getTaskRestrictionMap().get(TaskRestrictionType.COMPARISON_CRITERIA);
        boolean comparisonCriteria = Boolean.parseBoolean(comparisonCriteriaVo.getRestrictionExpression());
        SoftwareBuildVersionVo softwareBuildVersion = activitySoftwareBuildVersion.getSoftwareBuildVersion();
        Map<String, Set<String>> compatiblePnMap = activity.getCompatiblePnMap();
        boolean softwarePnMatch = softwareBuildVersion.getSoftwarePn().equals(ecuInfo.getSoftwarePn());
        if (!softwarePnMatch && comparisonCriteria) {
            softwarePnMatch = compatiblePnMap.get(ecuInfo.getEcu()).contains(softwareBuildVersion.getSoftwarePn());
        }
        if (!softwarePnMatch) {
            logger.info("车辆[{}]ECU[{}]软件零件号[{}]与软件内部版本软件零件号[{}]不匹配，忽略", vehicle.getId(),
                    softwareBuildVersion.getEcuCode(), ecuInfo.getSoftwarePn(), softwareBuildVersion.getSoftwarePn());
            return false;
        }
        TaskRestrictionVo adaptationSubjectVo = task.getTaskRestrictionMap().get(TaskRestrictionType.ADAPTATION_SUBJECT);
        AdaptiveSubject adaptationSubject = AdaptiveSubject.valOf(Integer.parseInt(adaptationSubjectVo.getRestrictionExpression()));
        for (SoftwarePackageVo softwarePackage : activitySoftwareBuildVersion.getSoftwarePackageList()) {
            if (adaptationSubject == AdaptiveSubject.NONE) {
                if (softwarePackage.getPackageType() == SoftwarePackageType.DELTA) {
                    // 即使适配主体无需比对，差分包仍然需要适配基础版本
                    boolean deltaPackageMatch = versionMatch(ecuInfo.getSoftwareBuildVer(), softwarePackage.getBaseSoftwareVer(), softwarePackage.getPackageAdaptiveLevel());
                    if (deltaPackageMatch) {
                        softwarePackage.setMatch(true);
                    } else {
                        logger.warn("车辆[{}]ECU[{}]软件包[{}]与软件内部版本差分软件包[{}]不匹配，忽略", vehicle.getId(),
                                softwareBuildVersion.getEcuCode(), softwarePackage.getPackageName(), softwarePackage.getBaseSoftwarePn());
                    }
                    continue;
                }
                if (!isSoftwarePnLatest(softwareBuildVersion, ecuInfo) || !isSoftwareBuildVerLatest(softwareBuildVersion, ecuInfo)) {
                    softwarePackage.setMatch(true);
                    continue;
                } else {
                    // 当前ECU已是最新版
                    return false;
                }
            }
            String adaptiveHardwarePn = softwareBuildVersion.getAdaptiveHardwarePn();
            if (!adaptiveHardwarePn.contains(ecuInfo.getHardwarePn()) && !adaptiveHardwarePn.contains(ecuInfo.getPartNo())) {
                logger.warn("车辆[{}]ECU[{}]硬件零件号[{}:{}]与软件内部版本硬件零件号[{}]不匹配", vehicle.getId(), ecuInfo.getEcu(),
                        ecuInfo.getHardwarePn(), ecuInfo.getPartNo(), adaptiveHardwarePn);
                return false;
            }
            if (activitySoftwareBuildVersion.getForceUpgrade()) {
                logger.info("车辆[{}]ECU[{}]软件包[{}]强制升级，跳过校验", vehicle.getId(), softwareBuildVersion.getEcuCode(), softwarePackage.getPackageName());
                softwarePackage.setMatch(true);
                continue;
            }
            if (adaptationSubject == AdaptiveSubject.BOTH) {
                if (isSoftwarePnLatest(softwareBuildVersion, ecuInfo) && isSoftwareBuildVerLatest(softwareBuildVersion, ecuInfo)) {
                    logger.info("车辆[{}]ECU[{}]软件零件号[{}]内部版本[{}]已是最新版本，忽略", vehicle.getId(), ecuInfo.getEcu(),
                            ecuInfo.getSoftwarePn(), ecuInfo.getSoftwareBuildVer());
                    return false;
                }
            }
            if (adaptationSubject == AdaptiveSubject.SOFTWARE_PN) {
                if (isSoftwarePnLatest(softwareBuildVersion, ecuInfo)) {
                    logger.info("车辆[{}]ECU[{}]软件零件号[{}]已是最新版，忽略", vehicle.getId(), ecuInfo.getEcu(), ecuInfo.getSoftwarePn());
                    return false;
                }
            }
            if (adaptationSubject == AdaptiveSubject.SOFTWARE_BUILD_VERSION) {
                if (isSoftwareBuildVerLatest(softwareBuildVersion, ecuInfo)) {
                    logger.info("车辆[{}]ECU[{}]内部版本[{}]已是最新版，忽略", vehicle.getId(), ecuInfo.getEcu(), ecuInfo.getSoftwareBuildVer());
                    return false;
                }
            }
            if (adaptationSubject == AdaptiveSubject.BOTH || adaptationSubject == AdaptiveSubject.SOFTWARE_PN) {
                if (!versionMatch(ecuInfo.getSoftwarePn() + ecuInfo.getSoftwarePartVer(),
                        softwarePackage.getSoftwarePn() + softwarePackage.getSoftwarePartVer(), softwarePackage.getPackageAdaptiveLevel())) {
                    logger.warn("车辆[{}]ECU[{}]软件零件版本[{}:{}]与软件内部版本软件零件版本[{}:{}]不匹配，忽略", vehicle.getId(),
                            softwareBuildVersion.getEcuCode(), ecuInfo.getSoftwarePn(), ecuInfo.getSoftwarePartVer(),
                            softwarePackage.getSoftwarePn(), softwarePackage.getSoftwarePartVer());
                    continue;
                }
            }
            if (adaptationSubject == AdaptiveSubject.BOTH || adaptationSubject == AdaptiveSubject.SOFTWARE_BUILD_VERSION) {
                if (!versionMatch(ecuInfo.getSoftwareBuildVer(), softwarePackage.getBaseSoftwareVer(), softwarePackage.getPackageAdaptiveLevel())) {
                    logger.warn("车辆[{}]ECU[{}]软件内部版本[{}:{}]与软件内部版本软件内部版本[{}:{}]不匹配，忽略", vehicle.getId(),
                            softwareBuildVersion.getEcuCode(), ecuInfo.getSoftwarePn(), ecuInfo.getSoftwareBuildVer(),
                            softwarePackage.getSoftwarePn(), softwarePackage.getBaseSoftwareVer());
                    continue;
                }
            }
            if (!activity.getBaseline()) {
                // 非基线需要进行依赖项校验
                if (!dependencyMatch(activitySoftwareBuildVersion.getSoftwareBuildVersionDependencyList(), vehicle,
                        comparisonCriteria, compatiblePnMap, adaptationSubject)) {
                    logger.warn("车辆[{}]ECU[{}]软件包[{}]依赖项不匹配，忽略", vehicle.getId(), softwareBuildVersion.getEcuCode(), softwarePackage.getPackageName());
                    continue;
                }
            }
            softwarePackage.setMatch(true);
        }
        return true;
    }

    /**
     * 版本适配
     *
     * @param originVersion   原版本
     * @param targetVersion   目标版本
     * @param adaptationLevel 适配级别
     * @return true: 适配成功，false: 适配失败
     */
    private boolean versionMatch(String originVersion, String targetVersion, AdaptiveLevel adaptationLevel) {
        int compareResult = VersionComparator.INSTANCE.compare(originVersion, targetVersion);
        return switch (adaptationLevel) {
            case LE -> compareResult <= 0;
            case GE -> compareResult >= 0;
            case EQ -> compareResult == 0;
            default -> false;
        };
    }

    /**
     * 软件包依赖项适配
     *
     * @param softwareBuildVersionDependencyList 软件内部版本依赖项列表
     * @param vehicle                            车辆
     * @param comparisonCriteria                 比对基准是否兼容
     * @param compatiblePnMap                    兼容的零件号
     * @param adaptationSubject                  适配主体
     * @return true: 适配成功，false: 适配失败
     */
    private boolean dependencyMatch(List<SoftwareBuildVersionDependencyVo> softwareBuildVersionDependencyList, VehicleDo vehicle,
                                    boolean comparisonCriteria, Map<String, Set<String>> compatiblePnMap, AdaptiveSubject adaptationSubject) {
        if (softwareBuildVersionDependencyList != null) {
            for (SoftwareBuildVersionDependencyVo dependency : softwareBuildVersionDependencyList) {
                EcuInfoVo ecuInfo = vehicle.getEcuMap().get(dependency.getEcuCode());
                if (ecuInfo == null) {
                    logger.warn("车辆[{}]软件内部版本[{}]依赖ECU[{}]不存在", vehicle.getId(), dependency.getSoftwareBuildVersionId(),
                            dependency.getEcuCode());
                    return false;
                }
                boolean softwarePnMatch = dependency.getSoftwarePn().equals(ecuInfo.getSoftwarePn());
                if (!softwarePnMatch && comparisonCriteria) {
                    softwarePnMatch = compatiblePnMap.get(ecuInfo.getEcu()).contains(dependency.getSoftwarePn());
                }
                if (!softwarePnMatch) {
                    logger.info("车辆[{}]软件内部版本[{}]依赖ECU[{}]软件零件号[{}]与软件内部版本软件零件号[{}]不匹配，忽略", vehicle.getId(),
                            dependency.getSoftwareBuildVersionId(), dependency.getEcuCode(), ecuInfo.getSoftwarePn(), dependency.getSoftwarePn());
                    return false;
                }
                if (adaptationSubject == AdaptiveSubject.NONE) {
                    continue;
                }
                if (adaptationSubject == AdaptiveSubject.SOFTWARE_PN || adaptationSubject == AdaptiveSubject.BOTH) {
                    if (!versionMatch(ecuInfo.getSoftwarePn() + ecuInfo.getSoftwarePartVer(),
                            dependency.getSoftwarePn() + dependency.getSoftwarePartVer(), dependency.getAdaptiveLevel())) {
                        logger.warn("车辆[{}]ECU[{}]软件零件号[{}:{}]与软件内部版本软件零件号[{}:{}]不匹配，忽略", vehicle.getId(),
                                dependency.getEcuCode(), ecuInfo.getSoftwarePn(), ecuInfo.getSoftwarePartVer(),
                                dependency.getSoftwarePn(), dependency.getSoftwarePartVer());
                        return false;
                    }
                }
                if (adaptationSubject == AdaptiveSubject.SOFTWARE_BUILD_VERSION || adaptationSubject == AdaptiveSubject.BOTH) {
                    if (!versionMatch(ecuInfo.getSoftwareBuildVer(), dependency.getSoftwareBuildVer(), dependency.getAdaptiveLevel())) {
                        logger.warn("车辆[{}]ECU[{}]软件内部版本[{}]与软件内部版本软件内部版本[{}]不匹配，忽略", vehicle.getId(),
                                dependency.getEcuCode(), ecuInfo.getSoftwareBuildVer(), dependency.getSoftwareBuildVer());
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 软件零件号是否最新
     *
     * @param softwareBuildVersion 软件内部版本
     * @param ecuInfo              ECU信息
     * @return true: 是，false: 否
     */
    private boolean isSoftwarePnLatest(SoftwareBuildVersionVo softwareBuildVersion, EcuInfoVo ecuInfo) {
        return softwareBuildVersion.getSoftwarePn().equals(ecuInfo.getSoftwarePn()) &&
                softwareBuildVersion.getSoftwarePartVer().equals(ecuInfo.getSoftwarePartVer());
    }

    /**
     * 软件内部版本是否最新
     *
     * @param softwareBuildVersion 软件内部版本
     * @param ecuInfo              ECU信息
     * @return true: 是，false: 否
     */
    private boolean isSoftwareBuildVerLatest(SoftwareBuildVersionVo softwareBuildVersion, EcuInfoVo ecuInfo) {
        return softwareBuildVersion.getSoftwareBuildVer().equals(ecuInfo.getSoftwareBuildVer());
    }

}
