package com.nathaniel.munro.api.file;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.nathaniel.munro.api.MunroProperties;
import com.nathaniel.munro.api.model.Munro;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class CsvReader {
    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;
    private final String fileLocation;

    private final List<Munro> munros;

    public CsvReader(CsvMapper csvMapper,
                     CsvSchema csvSchema,
                     MunroProperties munroProperties) throws IOException {
        this.csvMapper = csvMapper;
        this.csvSchema = csvSchema;
        this.fileLocation = munroProperties.getFileLocation();

        munros = fetchMunroDataFromCsv();
    }

    public List<Munro> fetchMunroData() {
        return munros;
    }

    private List<Munro> fetchMunroDataFromCsv() throws IOException {
        MappingIterator<Munro> munros = csvMapper
                .readerFor(Munro.class)
                .with(csvSchema)
                .readValues(new File(fileLocation));

        return munros.readAll().stream()
                .filter(removeIncorrectlyGeneratedMunros())
                .collect(Collectors.toList());
    }

    private Predicate<Munro> removeIncorrectlyGeneratedMunros() {
        return munro -> !munro.getName().equals("");
    }
}
