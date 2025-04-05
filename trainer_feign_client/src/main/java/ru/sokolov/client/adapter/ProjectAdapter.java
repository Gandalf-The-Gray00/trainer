package ru.sokolov.client.adapter;

import org.springframework.stereotype.Component;
import ru.sokolov.api.dto.OpenQuestionCardDto;
import ru.sokolov.api.mapper.QuestionDtoMapper;
import ru.sokolov.client.client.ProjectFeignClient;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;
import ru.sokolov.trainer.domain.repository.QuestionRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectAdapter implements QuestionRepository {
    private final ProjectFeignClient feignClient;
    private final QuestionDtoMapper mapper;

    public ProjectAdapter(ProjectFeignClient feignClient, QuestionDtoMapper mapper) {
        this.feignClient = feignClient;
        this.mapper = mapper;
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCardDto> projects = feignClient.list();
        return projects.stream()
                .map(mapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCardDto> projects = feignClient.list();
        return projects.stream()
                .map(mapper::mapToModel)
                .filter(project -> project.getId().equals(id))
                .findAny();
    }

    @Override
    public void add(OpenQuestionCard project) {
        feignClient.add(mapper.mapToDto(project));
    }

    @Override
    public void update(OpenQuestionCard project) {
        feignClient.update(mapper.mapToDto(project));
    }

    @Override
    public void remove(Long id) {
        feignClient.remove(id);
    }
}
