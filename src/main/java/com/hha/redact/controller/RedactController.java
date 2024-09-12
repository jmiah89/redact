package com.hha.redact.controller;

import com.hha.redact.service.RedactService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class RedactController {

    @Autowired
    private RedactService redactService;

    private static final Logger LOG = LoggerFactory.getLogger(RedactController.class);

    @PostMapping("/redact")
    public String redact(@RequestBody @NotNull String body) {
        LOG.info(body);
        return redactService.redact(body);
    }

    @GetMapping("/redact")
    public String info() {
        return RedactService.SERVICE_INFO;
    }
}
