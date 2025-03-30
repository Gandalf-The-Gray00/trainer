package ru.sokolov.ui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sokolov.ui.config.SpringConfig;
import ru.sokolov.ui.controller.MainController;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        MainController controller = context.getBean(MainController.class);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Question Trainer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            JTable table = new JTable(controller.getTableModel());
            frame.add(new JScrollPane(table));
            frame.setVisible(true);
        });
    }
}
