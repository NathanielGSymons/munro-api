package com.nathaniel.munro.api.controller;

import com.nathaniel.munro.api.model.Munro;
import com.nathaniel.munro.api.service.MunroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class MunroControllerTest {
    @MockBean
    private MunroService munroService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new MunroController(munroService))
                .build();
    }

    @Test
    void getMunros_returnsAllMunro() throws Exception {
        Munro munro = new Munro(
                "name",
                100.0,
                "hillCategory",
                "gridReference"
        );
        List<Munro> munros = List.of(munro);

        when(munroService.fetchMunros()).thenReturn(munros);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/munros")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertThat(content).isEqualTo("[{\"Name\":\"name\",\"Height (m)\":100.0,\"Post 1997\":\"hillCategory\",\"Grid Ref\":\"gridReference\"}]");
    }

    @Test
    void getMunrosDoesNotExist_throwsNotFoundException() throws Exception {
        when(munroService.fetchMunros()).thenThrow(new RuntimeException());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/munros")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String errorMessage = mvcResult.getResponse().getErrorMessage();

        assertThat(errorMessage).isEqualTo("Munros not found");
    }
}