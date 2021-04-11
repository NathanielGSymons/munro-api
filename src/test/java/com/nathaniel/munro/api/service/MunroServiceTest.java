package com.nathaniel.munro.api.service;

import com.nathaniel.munro.api.file.CsvReader;
import com.nathaniel.munro.api.model.Munro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MunroServiceTest {
    @Mock
    private CsvReader csvReader;

    private MunroService munroService;

    @BeforeEach
    void setUp() {
        munroService = new MunroService(csvReader);
    }

    @Test
    void getMunros_returnsListOfMunros() throws IOException {
        Munro munro = new Munro(
                "name",
                100,
                "hillCategory",
                "gridReference"
        );
        List<Munro> munros = List.of(munro);

        when(csvReader.fetchMunroData()).thenReturn(munros);

        Assertions.assertThat(munroService.getMunros()).isEqualTo(munros);
    }
}