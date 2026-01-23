package net.hwyz.iov.cloud.ota.fota.service.infrastructure.util;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.HexUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.ota.baseline.api.contract.BaselineSoftwareBuildVersionExService;
import net.hwyz.iov.cloud.ota.baseline.api.contract.SoftwareBuildVersionExService;
import net.hwyz.iov.cloud.ota.baseline.api.feign.service.ExBaselineService;
import net.hwyz.iov.cloud.ota.baseline.api.feign.service.ExSoftwareBuildVersionService;
import net.hwyz.iov.cloud.ota.baseline.api.feign.service.ExSoftwarePartService;
import net.hwyz.iov.cloud.ota.fota.service.domain.activity.model.ConfigWordVo;
import net.hwyz.iov.cloud.ota.fota.service.domain.activity.model.SoftwarePackageVo;
import net.hwyz.iov.cloud.ota.fota.service.domain.vehicle.model.EcuInfoVo;
import net.hwyz.iov.cloud.ota.fota.service.facade.assembler.SoftwarePackageExServiceAssembler;
import net.hwyz.iov.cloud.ota.fota.service.infrastructure.exception.BaselineNotExistException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * FOTA升级辅助类
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FotaHelper {

    private final ExBaselineService exBaselineService;
    private final ExSoftwarePartService exSoftwarePartService;
    private final ExSoftwareBuildVersionService exSoftwareBuildVersionService;

    /**
     * ECU软件零件映射
     */
    private static final Map<String, Map<String, Set<String>>> ecuSoftwarePartMap = new ConcurrentHashMap<>();
    /**
     * 基线软件内部版本映射
     */
    private static final Map<String, Map<String, BaselineSoftwareBuildVersionExService>> baselineSoftwareBuildVersionMap = new ConcurrentHashMap<>();

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        logger.info("初始化软件零件工具类");
        loadSoftwarePart();
    }

    /**
     * 判断是否是升级软件零件
     *
     * @param ecuCode         ECU编号
     * @param softwarePn      软件零件号
     * @param softwarePartVer 软件零件版本
     * @return 是否是升级软件零件
     */
    public boolean isOtaSoftwarePart(String ecuCode, String softwarePn, String softwarePartVer) {
        Map<String, Set<String>> ecuSoftwarePart = ecuSoftwarePartMap.get(ecuCode);
        if (ecuSoftwarePart == null) {
            return false;
        }
        Set<String> ecuSoftwarePartVersionRange = ecuSoftwarePart.get(softwarePn);
        if (ecuSoftwarePartVersionRange == null) {
            return false;
        }
        return ecuSoftwarePartVersionRange.contains(softwarePartVer);
    }

    /**
     * 判断ECU设备是否与基线对齐
     *
     * @param baselineCode 基线编号
     * @param ecuInfoList  ECU设备信息列表
     * @return 是否与基线对齐
     */
    public boolean isBaselineAlignment(String baselineCode, List<EcuInfoVo> ecuInfoList) {
        Map<String, BaselineSoftwareBuildVersionExService> softwarePartVersionMap = baselineSoftwareBuildVersionMap.get(baselineCode);
        if (softwarePartVersionMap == null) {
            List<BaselineSoftwareBuildVersionExService> baselineSoftwareBuildVersionList = exBaselineService.listSoftwareBuildVersion(baselineCode);
            if (baselineSoftwareBuildVersionList == null) {
                throw new BaselineNotExistException(baselineCode);
            }
            softwarePartVersionMap = baselineSoftwareBuildVersionList.stream()
                    .collect(Collectors.toMap(BaselineSoftwareBuildVersionExService::getEcuCode, softwarePartVersionEx -> softwarePartVersionEx));
            baselineSoftwareBuildVersionMap.put(baselineCode, softwarePartVersionMap);
        }
        for (EcuInfoVo ecuInfoVo : ecuInfoList) {
            if (!isOtaSoftwarePart(ecuInfoVo.getEcu(), ecuInfoVo.getSoftwarePn(), ecuInfoVo.getSoftwarePartVer())) {
                continue;
            }
            BaselineSoftwareBuildVersionExService ecuSoftwareBuildVersion = softwarePartVersionMap.get(ecuInfoVo.getEcu());
            if (ecuSoftwareBuildVersion == null || !ecuInfoVo.getSoftwarePn().equals(ecuSoftwareBuildVersion.getSoftwarePn())
                    || !ecuInfoVo.getSoftwarePartVer().equals(ecuSoftwareBuildVersion.getSoftwarePartVer())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取软件内部版本包
     *
     * @param ecuCode          ECU代码
     * @param softwarePn       软件零件号
     * @param softwareBuildVer 软件内部版本号
     * @return 软件内部版本包
     */
    public List<SoftwarePackageVo> getSoftwareBuildVersionPackages(String ecuCode, String softwarePn, String softwareBuildVer) {
        SoftwareBuildVersionExService softwareBuildVersion = exSoftwareBuildVersionService.getInfo(ecuCode, softwarePn, softwareBuildVer);
        return SoftwarePackageExServiceAssembler.INSTANCE.toVoList(softwareBuildVersion.getSoftwarePackageList());
    }

    /**
     * 配置字转换成字符串
     *
     * @param originConfigWord 原始配置字
     * @param configWordList   配置字列表
     * @return 转换后的字符串
     */
    public String configWordToStr(String originConfigWord, List<ConfigWordVo> configWordList) {
        byte[] origin = HexUtil.decodeHex(originConfigWord);
        for (ConfigWordVo configWord : configWordList) {
            String binaryValue = configWord.getConfigWordValue();
            int startByte = configWord.getStartByte();
            int startBit = configWord.getStartBit();
            if (binaryValue == null || binaryValue.isEmpty()) {
                continue;
            }
            if (startByte < 0 || startByte >= origin.length) {
                logger.warn("起始字节索引超出范围：" + startByte + "，数组长度：" + origin.length);
                continue;
            }
            if (startBit < 0 || startBit > 7) {
                logger.warn("起始位偏移非法（需0-7）：" + startBit);
                continue;
            }
            for (int i = 0; i < binaryValue.length(); i++) {
                int targetByteIndex = startByte + (startBit + i) / 8;
                int bitOffset = (startBit + i) % 8;
                char bitChar = binaryValue.charAt(i);
                int bitValue = (bitChar == '1') ? 1 : 0;

                if (targetByteIndex < 0 || targetByteIndex >= origin.length) {
                    logger.warn("替换位超出字节数组范围：字节索引=" + targetByteIndex + "，数组长度=" + origin.length);
                    continue;
                }
                origin[targetByteIndex] = (byte) (origin[targetByteIndex] & ~(1 << bitOffset));
                origin[targetByteIndex] = (byte) (origin[targetByteIndex] | (bitValue << bitOffset));
            }
        }
        return HexUtil.encodeHexStr(origin);
    }

    /**
     * 加载所有软件零件
     */
    private void loadSoftwarePart() {
        logger.info("加载所有软件零件");
        exSoftwarePartService.listAll().forEach(softwarePartEx -> {
            if (!ecuSoftwarePartMap.containsKey(softwarePartEx.getEcuCode())) {
                ecuSoftwarePartMap.put(softwarePartEx.getEcuCode(), new ConcurrentHashMap<>());
            }
            Map<String, Set<String>> ecuSoftwarePart = ecuSoftwarePartMap.get(softwarePartEx.getEcuCode());
            if (!ecuSoftwarePart.containsKey(softwarePartEx.getSoftwarePn())) {
                ecuSoftwarePart.put(softwarePartEx.getSoftwarePn(), new ConcurrentHashSet<>());
            }
            Set<String> ecuSoftwarePartVersionRange = ecuSoftwarePart.get(softwarePartEx.getSoftwarePn());
            for (String version : softwarePartEx.getSoftwarePartVerRange().split(",")) {
                ecuSoftwarePartVersionRange.add(version.trim());
            }
        });
    }

}
