package ru.sokolov.ui.controller;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sokolov.trainer.domain.service.QuestionService;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import ru.sokolov.ui.model.QuestionTableModel;

@Component
public class MainController {

    private final QuestionService questionService;
    private final QuestionTableModel tableModel;

    @Autowired
    public MainController(QuestionService questionService, QuestionTableModel tableModel) {
        this.questionService = questionService;
        this.tableModel = tableModel;
        this.tableModel.setQuestions(questionService.getAll());
    }

    public void refreshTable() {
        tableModel.fireTableDataChanged();
    }

    public void addQuestion(String text, String answer) {
        questionService.add(new OpenQuestionCard(null, text, answer));
        refreshTable();
    }

    public void removeQuestion(long id) {
        questionService.remove(id);
        refreshTable();
    }

    public QuestionTableModel getTableModel() {
        return tableModel;
    }
}
