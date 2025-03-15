package ru.sokolov.trainer.controller;

import java.util.Scanner;
import org.springframework.stereotype.Controller;
import ru.sokolov.trainer.domain.service.QuestionService;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;

@Controller
public class ConsoleController {
    private final QuestionService service;

    public ConsoleController(QuestionService service) {
        this.service = service;
    }

    public void interactWithUser() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду (add/list/exit): ");
            String command = scanner.nextLine();
            if ("exit".equalsIgnoreCase(command)) {
                break;
            } else if ("list".equalsIgnoreCase(command)) {
                service.getAll().forEach(q -> System.out.println(q.getQuestion()));
            } else if ("add".equalsIgnoreCase(command)) {
                System.out.println("Введите ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                System.out.println("Введите вопрос: ");
                String question = scanner.nextLine();
                System.out.println("Введите ответ: ");
                String answer = scanner.nextLine();
                service.save(new OpenQuestionCard(id, question, answer));
            }
        }
        scanner.close();
    }
}

