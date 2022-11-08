package com.example.java_core.p06_java8.optional;

import java.util.Optional;

public class MobileClient {

    public int getMobileScreenWidth(Mobile_v7 mobile_v7) {
        if (mobile_v7 != null) {
            VersionFeatures_v7 versionFeatures_v7 = mobile_v7.getVersionFeatures();
            if (versionFeatures_v7 != null) {
                ScreenResolution screenResolution = versionFeatures_v7.getScreenResolution();
                if (screenResolution != null) {
                    return screenResolution.getWidth();
                }
            }
        }

        return 0;  // null pointer occurred before
    }

    public int getMobileScreenWidth_withOptional(Mobile_v8 mobile_v8) {
        return Optional.ofNullable(mobile_v8)
                .flatMap(Mobile_v8::getVersionFeaturesOptional)
                .flatMap(VersionFeatures_v8::getResolutionOptional)
                .map(ScreenResolution::getWidth)
                .orElse(0);  // if null value occurs, return 0
    }
}
