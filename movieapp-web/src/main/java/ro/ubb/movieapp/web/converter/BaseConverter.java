package ro.ubb.movieapp.web.converter;

import ro.ubb.movieapp.core.model.BaseEntity;
import ro.ubb.movieapp.web.dto.BaseDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class BaseConverter<Model extends BaseEntity<Long>, Dto extends BaseDto> implements
                                                                                         Converter<Model, Dto> {


    public Set<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toSet());
    }
}
