package com.nathaniel.munro.api.file;

import com.fasterxml.jackson.databind.MappingIterator;
import com.nathaniel.munro.api.MunroProperties;
import com.nathaniel.munro.api.model.Munro;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FileHandler {
    private final CsvReader csvReader;
    private final String fileLocation;

    public FileHandler(CsvReader csvReader, MunroProperties munroProperties) {
        this.csvReader = csvReader;
        this.fileLocation = munroProperties.getFileLocation();
    }

    public MappingIterator<Munro> fetchMunros() throws IOException {
        return csvReader.fetchContent(fileLocation);
    }
}
