package ru.sokolov.trainer.db.spring.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import ru.sokolov.trainer.domain.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

public class QuestionJdbcTemplateDao implements QuestionRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        String sql = "SELECT id, question_text, expected_answer FROM questions";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new OpenQuestionCard(
                        rs.getLong("id"),
                        rs.getString("question_text"),
                        rs.getString("expected_answer")
                )
        );
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        String sql = "SELECT id, question_text, expected_answer FROM questions WHERE id = ?";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return Optional.of(new OpenQuestionCard(
                        rs.getLong("id"),
                        rs.getString("question_text"),
                        rs.getString("expected_answer")
                ));
            }
            return Optional.empty();
        }, id);
    }

    @Override
    public void add(OpenQuestionCard question) {
        String sql = "INSERT INTO questions (question_text, expected_answer) VALUES (?, ?)";
        jdbcTemplate.update(sql, question.getQuestionText(), question.getExpectedAnswer());
    }

    @Override
    public void update(OpenQuestionCard question) {  // <-- Добавляем метод update
        String sql = "UPDATE questions SET question_text = ?, expected_answer = ? WHERE id = ?";
        jdbcTemplate.update(sql, question.getQuestionText(), question.getExpectedAnswer(), question.getId());
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
