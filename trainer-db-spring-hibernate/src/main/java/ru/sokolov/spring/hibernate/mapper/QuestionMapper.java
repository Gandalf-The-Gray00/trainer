package ru.sokolov.spring.hibernate.mapper;

import org.mapstruct.Mapper;
import ru.sokolov.spring.hibernate.entity.OpenQuestionCardEntity;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;


import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    OpenQuestionCard mapToModel(OpenQuestionCardEntity entity);
    OpenQuestionCardEntity mapToEntity(OpenQuestionCard question);
    List<OpenQuestionCard> mapToModel(List<OpenQuestionCardEntity> entities);
    List<OpenQuestionCardEntity> mapToEntity(List<OpenQuestionCard> questions);
}
