package ru.sokolov.trainer.db.jdbc.dao;

import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import ru.sokolov.trainer.domain.repository.QuestionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class QuestionJdbcDao implements QuestionRepository {
    private final String url;
    private final String user;
    private final String password;

    public QuestionJdbcDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCard> questions = new ArrayList<>();
        String sql = "SELECT id, question_text, expected_answer FROM questions";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                questions.add(new OpenQuestionCard(
                        rs.getLong("id"),
                        rs.getString("question_text"),
                        rs.getString("expected_answer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        String sql = "SELECT id, question_text, expected_answer FROM questions WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new OpenQuestionCard(
                        rs.getLong("id"),
                        rs.getString("question_text"),
                        rs.getString("expected_answer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void add(OpenQuestionCard question) {
        String sql = "INSERT INTO questions (question_text, expected_answer) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getExpectedAnswer());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OpenQuestionCard question) {
        String sql = "UPDATE questions SET question_text = ?, expected_answer = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getExpectedAnswer());
            stmt.setLong(3, question.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
