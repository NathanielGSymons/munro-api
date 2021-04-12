package com.nathaniel.munro.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = Application.class,
        properties = {"munro.fileLocation=src/test/resources/data/inputMunroData.csv"})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MunroIntegrationTest {
    public static final String FILE_PATH = "src/test/resources/expectedResponse.json";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void GetMunros_returnsMunrosCorrectly() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/munros")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();

        String expectedResponse = Files.readString(Paths.get(FILE_PATH));

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(expectedResponse);
    }
}
