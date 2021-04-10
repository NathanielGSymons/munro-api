package com.nathaniel.munro.api.file;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.nathaniel.munro.api.model.Munro;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class CsvReader {
    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;

    public CsvReader(CsvMapper csvMapper, CsvSchema csvSchema) {
        this.csvMapper = csvMapper;
        this.csvSchema = csvSchema;
    }

    public MappingIterator<Munro> fetchContent(String fileLocation) throws IOException {
        return csvMapper.readerFor(Munro.class).with(csvSchema).readValues(new File(fileLocation));
    }
}
