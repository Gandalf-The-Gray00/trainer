package ru.sokolov.trainer.ui.console.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.sokolov.trainer.db.spring.jdbc.config.DbConfig;


@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = "ru.sokolov")
@PropertySource("jdbc.properties")
public class SpringConfig {

}
