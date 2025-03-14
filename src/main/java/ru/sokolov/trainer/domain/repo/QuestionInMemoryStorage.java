package ru.sokolov.trainer.domain.repo;

import java.util.*;
import org.springframework.stereotype.Repository;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;

@Repository
public class QuestionInMemoryStorage implements QuestionRepository {
    private final Map<Long, OpenQuestionCard> storage = new HashMap<>();

    @Override
    public List<OpenQuestionCard> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void add(OpenQuestionCard task) {
        storage.put(task.getId(), task);
    }

    @Override
    public void update(OpenQuestionCard task) {
        storage.put(task.getId(), task);
    }

    @Override
    public void remove(Long id) {
        storage.remove(id);
    }
}

