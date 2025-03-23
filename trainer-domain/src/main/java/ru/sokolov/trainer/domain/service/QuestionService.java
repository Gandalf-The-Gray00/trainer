package ru.sokolov.trainer.domain.service;

import org.springframework.stereotype.Service;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import ru.sokolov.trainer.domain.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<OpenQuestionCard> getAll() {
        return repository.findAll();
    }

    public void add(OpenQuestionCard question) {
        repository.add(question);
    }

    public void remove(Long id) {
        repository.remove(id);
    }
}
