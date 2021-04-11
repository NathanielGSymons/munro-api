package com.nathaniel.munro.api.file;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CsvReaderTest {
    @Mock
    private CsvMapper csvMapper;
    @Mock
    private CsvSchema csvSchema;

    private CsvReader csvReader;

    @BeforeEach
    void setUp() {
    }

    @Test
    void fetchMunroData_returnListOfMunros() {
        // TODO refactor class to allow appropriate unit tests.
    }
}