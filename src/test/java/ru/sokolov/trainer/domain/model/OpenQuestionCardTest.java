package ru.sokolov.trainer.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpenQuestionCardTest {

    @Test
    void testConstructor_validData() {
        OpenQuestionCard card = new OpenQuestionCard("What is 2 + 2?", "4");
        assertEquals("What is 2 + 2?", card.getQuestion());
    }

    @Test
    void testConstructor_invalidQuestion() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new OpenQuestionCard("", "4");
        });
        assertEquals("Question cannot be null or empty", exception.getMessage());
    }

    @Test
    void testConstructor_invalidAnswer() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new OpenQuestionCard("What is 2 + 2?", "");
        });
        assertEquals("Expected answer cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCheckAnswer_correct() {
        OpenQuestionCard card = new OpenQuestionCard("What is 2 + 2?", "4");
        assertTrue(card.checkAnswer("4"));
    }

    @Test
    void testCheckAnswer_incorrect() {
        OpenQuestionCard card = new OpenQuestionCard("What is 2 + 2?", "4");
        assertFalse(card.checkAnswer("5"));
    }
}
