package ru.sokolov.api.mapper;

import org.mapstruct.Mapper;
import ru.sokolov.api.dto.OpenQuestionCardDto;
import ru.sokolov.trainer.domain.model.OpenQuestionCard;

import java.util.List;
@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {
    OpenQuestionCard mapToModel(OpenQuestionCardDto entity);
    OpenQuestionCardDto mapToDto(OpenQuestionCard question);
    List<OpenQuestionCard> mapToModel(List<OpenQuestionCardDto> entities);
    List<OpenQuestionCardDto> mapToDto(List<OpenQuestionCard> questions);
}
