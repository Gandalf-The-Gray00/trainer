package ru.sokolov.trainer.domain.model;

public class OpenQuestionCard {

    private final String question;
    private final String expectedAnswer;

    // Конструктор, инициализирующий поля
    public OpenQuestionCard(String question, String expectedAnswer) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (expectedAnswer == null || expectedAnswer.isEmpty()) {
            throw new IllegalArgumentException("Expected answer cannot be null or empty");
        }

        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    // Геттер для поля question
    public String getQuestion() {
        return question;
    }

    // Метод для проверки ответа
    public boolean checkAnswer(String answer) {
        return expectedAnswer.equals(answer);
    }
}
