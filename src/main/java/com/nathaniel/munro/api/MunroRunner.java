package com.nathaniel.munro.api;

import com.nathaniel.munro.api.file.FileHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MunroRunner implements CommandLineRunner {
    private final FileHandler fileHandler;

    public MunroRunner(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void run(String... args) throws Exception {
        fileHandler.fetchMunros();
    }
}
