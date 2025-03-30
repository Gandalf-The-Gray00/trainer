package ru.sokolov.trainer.ui.console;

import ru.sokolov.trainer.domain.service.QuestionService;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;

import java.util.Scanner;

public class MainConsole {
    private final QuestionService questionService;
    private final Scanner scanner;

    public MainConsole(QuestionService questionService) {
        this.questionService = questionService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать в консольный тренажер!");
        while (true) {
            System.out.println("1. Показать все вопросы");
            System.out.println("2. Добавить вопрос");
            System.out.println("3. Удалить вопрос");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                case 2:
                    showQuestions();
                    break;
                case 3:
                    addQuestion();
                    break;
                case 4:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private void showQuestions() {
        questionService.getAll().forEach(q ->
                System.out.println(q.getId() + ". " + q.getQuestionText())
        );
    }

    private void addQuestion() {
        System.out.print("Введите текст вопроса: ");
        String text = scanner.nextLine();
        System.out.print("Введите правильный ответ: ");
        String answer = scanner.nextLine();
        questionService.add(new OpenQuestionCard(null, text, answer));
        System.out.println("Вопрос добавлен.");
    }

    private void removeQuestion() {
        System.out.print("Введите ID вопроса для удаления: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        questionService.remove(id);
        System.out.println("Вопрос удален.");
    }
}
