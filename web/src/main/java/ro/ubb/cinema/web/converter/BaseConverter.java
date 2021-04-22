package ro.ubb.cinema.web.converter;

import ro.ubb.cinema.core.domain.entities.BaseEntity;
import ro.ubb.cinema.web.dto.BaseDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<Long>, Dto extends BaseDto>
        implements Converter<Model, Dto> {

    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }
}