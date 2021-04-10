package com.nathaniel.munro.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "munro")
@ConstructorBinding
public class MunroProperties {
    private final String fileLocation;

    public MunroProperties(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
