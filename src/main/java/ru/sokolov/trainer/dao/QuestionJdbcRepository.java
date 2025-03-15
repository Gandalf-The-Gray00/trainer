package ru.sokolov.trainer.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import ru.sokolov.trainer.domain.repo.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuestionJdbcRepository implements QuestionRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<OpenQuestionCard> rowMapper = (rs, rowNum) ->
            new OpenQuestionCard(
                    rs.getLong("id"),
                    rs.getString("question"),
                    rs.getString("expectedAnswer")
            );

    @Override
    public List<OpenQuestionCard> findAll() {
        return jdbcTemplate.query("SELECT * FROM questions", rowMapper);
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM questions WHERE id = ?", rowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public void add(OpenQuestionCard task) {
        String sql = "INSERT INTO questions (id, question, expected_answer) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, task.getId(), task.getQuestion(), task.getExpectedAnswer());
    }

    @Override
    public void update(OpenQuestionCard task) {
        String sql = "UPDATE questions SET question = ?, expected_answer = ? WHERE id = ?";
        jdbcTemplate.update(sql, task.getQuestion(), task.getExpectedAnswer(), task.getId());
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

