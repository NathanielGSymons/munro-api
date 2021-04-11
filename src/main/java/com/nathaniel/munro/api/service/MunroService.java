package com.nathaniel.munro.api.service;

import com.nathaniel.munro.api.file.CsvReader;
import com.nathaniel.munro.api.model.Munro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MunroService {
    private final CsvReader csvReader;

    public MunroService(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public List<Munro> getMunros() {
        return csvReader.fetchMunroData();
    }
}
