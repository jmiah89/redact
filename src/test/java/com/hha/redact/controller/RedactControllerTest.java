package com.hha.redact.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hha.redact.service.RedactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RedactController.class)
class RedactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RedactService redactService;

    @Test
    void redactPOST() throws Exception {

        when(redactService.redact("my name is john")).thenReturn("my name is REDACTED");

        mockMvc.perform(MockMvcRequestBuilders.post("/redact")
                        .content("my name is john")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("my name is REDACTED"));
    }

    @Test
    void redactPOST_withNullBody() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/redact"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void redactGET() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/redact"))
                .andExpect(status().isOk())
                .andExpect(content().string("Redaction Service"));
    }
}