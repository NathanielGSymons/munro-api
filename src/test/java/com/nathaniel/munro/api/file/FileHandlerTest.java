package com.nathaniel.munro.api.file;

import com.fasterxml.jackson.databind.MappingIterator;
import com.nathaniel.munro.api.MunroProperties;
import com.nathaniel.munro.api.model.Munro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileHandlerTest {
    public static final String FILE_LOCATION = "fileLocation";

    @Mock
    private CsvReader csvReader;

    private FileHandler fileHandler;

    @BeforeEach
    void setUp() {
        MunroProperties munroProperties = new MunroProperties(FILE_LOCATION);
        fileHandler = new FileHandler(csvReader, munroProperties);
    }

    @Test
    void fetchContent_returnsMunros() throws IOException {
        MappingIterator<Munro> munros = MappingIterator.emptyIterator();

        when(csvReader.fetchContent(any())).thenReturn(munros);

        Assertions.assertThat(fileHandler.fetchMunros()).isEqualTo(munros);

        verify(csvReader).fetchContent(FILE_LOCATION);
    }
}