package com.example.java_core.p06_java8.optional;

import lombok.*;

import java.util.Optional;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mobile_v8 {
    private long id;
    private String brand;
    private String name;
    private Optional<VersionFeatures_v8> versionFeaturesOptional;
}
