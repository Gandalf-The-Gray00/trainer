package ru.sokolov.trainer.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpenQuestionCardTest {

    @Test
    void testConstructor_validData() {
        OpenQuestionCard card = new OpenQuestionCard(1L, "What is 2 + 2?", "4");
        assertEquals(1L, card.getId());
        assertEquals("What is 2 + 2?", card.getQuestion());
    }

    @Test
    void testConstructor_invalidId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new OpenQuestionCard(0L, "What is 2 + 2?", "4");
        });
        assertEquals("ID must be a positive number", exception.getMessage());
    }

    @Test
    void testConstructor_invalidQuestion() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new OpenQuestionCard(1L, "", "4");
        });
        assertEquals("Question cannot be null or empty", exception.getMessage());
    }

    @Test
    void testConstructor_invalidAnswer() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new OpenQuestionCard(1L, "What is 2 + 2?", "");
        });
        assertEquals("Expected answer cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCheckAnswer_correct() {
        OpenQuestionCard card = new OpenQuestionCard(1L, "What is 2 + 2?", "4");
        assertTrue(card.checkAnswer("4"));
    }

    @Test
    void testCheckAnswer_incorrect() {
        OpenQuestionCard card = new OpenQuestionCard(1L, "What is 2 + 2?", "4");
        assertFalse(card.checkAnswer("5"));
    }

    @Test
    void testCheckAnswer_caseInsensitiveAndTrim() {
        OpenQuestionCard card = new OpenQuestionCard(1L, "What is the capital of France?", "Paris");
        assertTrue(card.checkAnswer(" paris "));
        assertTrue(card.checkAnswer("PARIS"));
        assertTrue(card.checkAnswer("paris"));
        assertFalse(card.checkAnswer("London"));
    }
}
