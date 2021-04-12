package com.nathaniel.munro.api.controller;

import com.nathaniel.munro.api.model.Munro;
import com.nathaniel.munro.api.service.MunroService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class MunroController {
    private final MunroService munroService;

    public MunroController(MunroService munroService) {
        this.munroService = munroService;
    }

    @GetMapping(value = "/munros", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Munro>> getMunros(
            @RequestParam(required = false) Map<String, String> parameters
    ) {
        if (parameters.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(munroService.fetchMunros());
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(munroService.fetchMunros(parameters));
        }
    }
}
