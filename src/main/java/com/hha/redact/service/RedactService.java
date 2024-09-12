package com.hha.redact.service;

import com.hha.redact.configuration.RedactConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.StringJoiner;

@Service
public class RedactService {

    private RedactConfig redactConfig;

    public static final String SERVICE_INFO = "Redaction Service";
    private static final String REDACTED = "REDACTED";

    public RedactService(RedactConfig redactConfig){
        this.redactConfig = redactConfig;
    }

    public String redact(String str){

        if (CollectionUtils.isEmpty(redactConfig.getWords())) {
            return str;
        }

        StringJoiner regex = new StringJoiner("|", "(?i)(", ")");

        redactConfig.getWords().stream().forEach(word -> regex.add("\\b" + word + "\\b"));

        return str.replaceAll(regex.toString(), REDACTED);
    }
}
