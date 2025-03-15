package ru.sokolov.trainer.domain.repo;

import java.util.List;
import java.util.Optional;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;

public interface QuestionRepository {
    List<OpenQuestionCard> findAll();
    Optional<OpenQuestionCard> findById(Long id);
    void add(OpenQuestionCard task);
    void update(OpenQuestionCard task);
    void remove(Long id);
}
