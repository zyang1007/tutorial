package com.example.java_core.p06_java8.optional;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mobile_v7 {
    private long id;
    private String brand;
    private String name;
    private VersionFeatures_v7 versionFeatures;
}
