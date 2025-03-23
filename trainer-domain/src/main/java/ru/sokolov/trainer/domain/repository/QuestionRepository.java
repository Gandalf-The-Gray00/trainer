package ru.sokolov.trainer.domain.repository;

import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    List<OpenQuestionCard> findAll();
    Optional<OpenQuestionCard> findById(Long id);
    void add(OpenQuestionCard question);
    void update(OpenQuestionCard question); // <-- Добавляем этот метод!
    void remove(Long id);
}

