package ru.sokolov.trainer.domain.service;

import org.springframework.stereotype.Service;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import ru.sokolov.trainer.domain.repository.QuestionRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<OpenQuestionCard> getAll() {
        return repository.findAll();
    }

    public Optional<OpenQuestionCard> getById(Long id) { return repository.findById(id); }


    private boolean isTaskInvalid(OpenQuestionCard task) {
        return Objects.isNull(task) || Objects.isNull(task.getId());
    }

    public boolean contains(OpenQuestionCard task) {
        if (isTaskInvalid(task)) {
            return false;
        }
        return repository.findById(task.getId()).isPresent();
    }
    public void save(OpenQuestionCard task) {
        if (isTaskInvalid(task)) {
            return;
        }
        if (contains(task)) {
            repository.update(task);
        } else {
            repository.add(task);
        }
    }

    public void delete(Long id) { repository.remove(id); }

    public void add(OpenQuestionCard question) {
        repository.add(question);
    }

    public void remove(Long id) {
        repository.remove(id);
    }
}
