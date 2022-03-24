package com.jingang.lifechange.utils;

import android.os.Build;

import java.util.Locale;

/**
 * @Description: 设备信息帮助类
 * @Author: jinGang.Li
 * @CreateTime: 2020/9/20 AM
 */
public class DeviceInfoHelper {
    public static final boolean IS_EMULATOR = isEmulator();

    private static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.toLowerCase(Locale.ROOT).contains("droid4x")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MODEL.contains("Lenovo TB-X606F")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.HARDWARE.contains("vbox86")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator")
                || Build.BOARD.toLowerCase(Locale.ROOT).contains("nox")
                || Build.BOOTLOADER.toLowerCase(Locale.ROOT).contains("nox")
                || Build.HARDWARE.toLowerCase(Locale.ROOT).contains("nox")
                || Build.PRODUCT.toLowerCase(Locale.ROOT).contains("nox")
                || Build.SERIAL.toLowerCase(Locale.ROOT).contains("nox")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
    }
}

