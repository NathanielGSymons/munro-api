package com.nathaniel.munro.api.service;

import com.nathaniel.munro.api.file.CsvReader;
import com.nathaniel.munro.api.model.Munro;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

@Component
public class MunroService {
    private final CsvReader csvReader;

    public MunroService(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public List<Munro> fetchMunros() {
            return csvReader.fetchMunroData();
    }

    public List<Munro> fetchMunros(Map<String, String> parameters) {
        if (parameters.containsKey("limit")) {
            return formatMunros(parameters).stream()
                    .limit(parseLong(parameters.get("limit")))
                    .collect(Collectors.toList());
        } else {
            return formatMunros(parameters);
        }
    }

    private List<Munro> formatMunros(Map<String, String> parameters) {
        List<Munro> munros = csvReader.fetchMunroData().stream()
                .filter(munro -> filterByHillCategory(munro, parameters))
                .filter(munro -> filterByMinHeight(munro, parameters))
                .filter(munro -> filterByMaxHeight(munro, parameters))
                .collect(Collectors.toList());

        if (sortParameterIsPresent(parameters)) {
            return sortByName(munros, parameters.get("sort"));
        }

        return munros;
    }

    private boolean filterByHillCategory(Munro munro, Map<String, String> parameters) {
        if (parameters.containsKey("category")) {
            return munro.getHillCategory().equals(parameters.get("category"));
        }
        return true;
    }

    private boolean filterByMinHeight(Munro munro, Map<String, String> parameters) {
        if (parameters.containsKey("minHeight")) {
            return munro.getHeight() >= parseDouble(parameters.get("minHeight"));
        }
        return true;
    }

    private boolean filterByMaxHeight(Munro munro, Map<String, String> parameters) {
        if (parameters.containsKey("maxHeight")) {
            return munro.getHeight() <= parseDouble(parameters.get("maxHeight"));
        }
        return true;
    }

    private boolean sortParameterIsPresent(Map<String, String> parameters) {
        return parameters.get("sort") != null;
    }

    private List<Munro> sortByName(List<Munro> munros, String sort) {
        switch (sort) {
            case "nameAscending" -> munros.sort(Comparator.comparing(Munro::getName));
            case "nameDescending" -> munros.sort(Comparator.comparing(Munro::getName).reversed());
            case "heightAscending" -> munros.sort(Comparator.comparing(Munro::getHeight));
            case "heightDescending" -> munros.sort(Comparator.comparing(Munro::getHeight).reversed());
        }
        return munros;
    }
}
