package com.example.java_core.p06_java8.optional;

import lombok.*;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VersionFeatures_v8 {
    private String size;
    private Optional<ScreenResolution> resolutionOptional;

}
