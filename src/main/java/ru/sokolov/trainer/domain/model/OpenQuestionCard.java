package ru.sokolov.trainer.domain.model;

public class OpenQuestionCard {
    private final Long id;
    private final String question;
    private final String expectedAnswer;

    public OpenQuestionCard(Long id, String question, String expectedAnswer) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (expectedAnswer == null || expectedAnswer.isEmpty()) {
            throw new IllegalArgumentException("Expected answer cannot be null or empty");
        }

        this.id = id;
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getExpectedAnswer() {  // ✅ Добавленный метод
        return expectedAnswer;
    }

    public boolean checkAnswer(String answer) {
        return answer != null && expectedAnswer.trim().equalsIgnoreCase(answer.trim());
    }
}
