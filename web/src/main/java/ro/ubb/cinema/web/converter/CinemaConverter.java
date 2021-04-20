package ro.ubb.cinema.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.web.dto.CinemaDto;

@Component
public class CinemaConverter extends BaseConverter<Cinema, CinemaDto>{

    @Override
    public Cinema convertDtoToModel(CinemaDto dto) {
        var model = new Cinema();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setAddress(dto.getAddress());
        return model;
    }

    @Override
    public CinemaDto convertModelToDto(Cinema cinema) {
        CinemaDto dto = new CinemaDto(cinema.getName(), cinema.getAddress());
        dto.setId(cinema.getId());
        return dto;
    }
}

