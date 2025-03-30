package ru.sokolov.trainer.domain.model;

public class OpenQuestionCard {
    private Long id;
    private String questionText;
    private String expectedAnswer;

    public OpenQuestionCard(Long id, String questionText, String expectedAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.expectedAnswer = expectedAnswer;
    }

    public Long getId() { return id; }
    public String getQuestionText() { return questionText; }
    public String getExpectedAnswer() { return expectedAnswer; }

    public String getQuestion() {
        return questionText;
    }
}
