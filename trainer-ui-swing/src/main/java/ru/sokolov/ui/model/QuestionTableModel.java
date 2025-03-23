package ru.sokolov.ui.model;

import org.springframework.stereotype.Component;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@Component
public class QuestionTableModel extends AbstractTableModel {

    private List<OpenQuestionCard> questions;
    private final String[] columnNames = {"ID", "Текст вопроса", "Ответ"};

    public QuestionTableModel() {
    }

    public void setQuestions(List<OpenQuestionCard> questions) {
        this.questions = questions;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return (questions == null) ? 0 : questions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (questions == null || rowIndex >= questions.size()) {
            return null;
        }
        OpenQuestionCard question = questions.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return question.getId();
            case 1:
                return question.getQuestionText();
            case 2:
                return question.getExpectedAnswer();
            default:
                return null;
        }
    }
}
