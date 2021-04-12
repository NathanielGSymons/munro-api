package com.nathaniel.munro.api.service;

import com.nathaniel.munro.api.file.CsvReader;
import com.nathaniel.munro.api.model.Munro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

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
    void getMunros_returnsListOfMunros() {
        Map<String, String> parameters = Map.of(
                "category", "MUN",
                "minHeight", "900",
                "maxHeight", "1000",
                "sort", "nameDescending",
                "limit", "2"
        );

        Munro correctMunroA = new Munro("munroA", 950.0, "MUN", "gridReference");
        Munro correctMunroB = new Munro("munroB", 950.0, "MUN", "gridReference");
        Munro correctMunroC = new Munro("munroC", 950.0, "MUN", "gridReference");
        Munro incorrectHillCategoryMunro = new Munro("munroD", 950.0, "TOP", "gridReference");
        Munro incorrectHeightMunro = new Munro("munroE", 1100.0, "MUN", "gridReference");

        List<Munro> munros = List.of(
                correctMunroA,
                correctMunroB,
                correctMunroC,
                incorrectHillCategoryMunro,
                incorrectHeightMunro
        );

        when(csvReader.fetchMunroData()).thenReturn(munros);

        Assertions.assertThat(munroService.fetchMunros(parameters))
                .usingRecursiveComparison().isEqualTo(List.of(correctMunroC, correctMunroB));
    }
}