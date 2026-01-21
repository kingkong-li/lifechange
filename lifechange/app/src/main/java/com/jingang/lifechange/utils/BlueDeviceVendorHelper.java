package com.jingang.lifechange.utils;

import android.bluetooth.le.ScanRecord;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;

public class BlueDeviceVendorHelper {

    // 常见的蓝牙厂商 ID 映射表 (十进制)
    private static final Map<Integer, String> VENDOR_IDS = new HashMap<>();

    static {

        // 国际主流厂商
        VENDOR_IDS.put(911, "华为/荣耀 (Huawei)");       // 0x038F
        VENDOR_IDS.put(637, "华为-Legacy (Huawei-Legacy)"); // 0x027D
        VENDOR_IDS.put(76, "苹果 (Apple)");             // 0x004C
        VENDOR_IDS.put(89, "Nordic半导体");            // 0x0059 (手环/DIY设备)
        VENDOR_IDS.put(6, "微软 (Microsoft)");           // 0x0006
        VENDOR_IDS.put(75, "三星 (Samsung)");           // 0x004B
        VENDOR_IDS.put(892, "小米 (Xiaomi)");            // 0x037C
        VENDOR_IDS.put(196, "谷歌 (Google)");            // 0x00C4
        VENDOR_IDS.put(159, "思科 (Cisco)");            // 0x009F

        // 中国本土厂商
        VENDOR_IDS.put(93, "紫光展锐 (UNISOC)");        // 0x005D (5G芯片)
        VENDOR_IDS.put(110, "泰凌微电子 (Telink)");      // 0x006E (物联网芯片)
        VENDOR_IDS.put(127, "炬芯科技 (JL)");           // 0x007F (音频芯片)
        VENDOR_IDS.put(128, "恒玄科技 (BES)");          // 0x0080 (智能音频SoC)
        VENDOR_IDS.put(145, "中科蓝讯 (Bluetrum)");     // 0x0091 (高性价比方案)
        VENDOR_IDS.put(162, "杰理科技 (JL)");           // 0x00A2 (TWS耳机方案)
        VENDOR_IDS.put(179, "瑞昱半导体 (Realtek)");    // 0x00B3 (台湾地区方案)
        VENDOR_IDS.put(213, "汇顶科技 (Goodix)");       // 0x00D5 (指纹芯片跨界)
        VENDOR_IDS.put(230, "芯科科技 (SemiLink)");     // 0x00E6 (物联网方案)
        VENDOR_IDS.put(247, "百瑞互联 (BriLink)");      // 0x00F7 (模组方案商)
        VENDOR_IDS.put(264, "联发科 (MediaTek)");       // 0x0108 (台湾地区移动芯片)
        VENDOR_IDS.put(281, "矽力杰 (Silergy)");        // 0x0119 (电源管理芯片)
        VENDOR_IDS.put(298, "昂瑞微 (OnMicro)");        // 0x012A (射频芯片)

    }

    /**
     * 获取厂商名称，如果未知则暴露 ID
     */
    public static String getVendorName(ScanRecord record) {
        if (record == null) return "未知厂商设备";

        SparseArray<byte[]> manufacturerData = record.getManufacturerSpecificData();

        if (manufacturerData != null && manufacturerData.size() > 0) {
            // 取第一个厂商数据 ID
            int id = manufacturerData.keyAt(0);

            // 1. 先从已知字典找
            if (VENDOR_IDS.containsKey(id)) {
                return VENDOR_IDS.get(id);
            }

            // 2. 找不到则直接暴露 ID (转换为 16 进制看起来更专业，0x038F 即 911)
            return String.format("厂商 ID: 0x%04X (%d)", id, id);
        }

        return "未知厂商设备";
    }

}