package com.hha.redact.service;

import com.hha.redact.configuration.RedactConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedactServiceTest {

    private RedactService redactService;

    @BeforeAll
    public void setUp() {

        RedactConfig redactConfig = new RedactConfig();
        redactConfig.setWords(Arrays.asList("Dog", "Cat", "Snake", "Dolphin", "Mammal"));

        redactService = new RedactService(redactConfig);
    }

    @Test
    void redact_basicSentence() {
        String result = redactService.redact("A dog, a monkey or a dolphin are all mammals. A snake, however, is not a mammal, it is a reptile. Who can say what a DogSnake is?");
        assertEquals("A REDACTED, a monkey or a REDACTED are all mammals. A REDACTED, however, is not a REDACTED, it is a reptile. Who can say what a DogSnake is?", result);
    }

    @Test
    void redact_randomSentence() {

        String result = redactService.redact("dogDogSnake isSnake? SNaKE-- Cats Cat DOG DOGDOLPHIN");
        assertEquals("dogDogSnake isSnake? REDACTED-- Cats REDACTED REDACTED DOGDOLPHIN", result);
    }

    @Test
    void redact_emptySentence() {
        String result = redactService.redact(" ");
        assertEquals(" ", result);
    }

    @Test
    void redact_whenNoWordsConfigured() {
        RedactService redactServiceWithNoWords = new RedactService(new RedactConfig());

        String result = redactServiceWithNoWords.redact("A dog, a monkey or a dolphin are all mammals. A snake, however, is not a mammal, it is a reptile. Who can say what a DogSnake is?");
        assertEquals("A dog, a monkey or a dolphin are all mammals. A snake, however, is not a mammal, it is a reptile. Who can say what a DogSnake is?", result);
    }
}