package ro.ubb.cinema.web.converter;

import ro.ubb.cinema.core.domain.entities.IDCard;
import ro.ubb.cinema.web.dto.IDCardDto;

import java.time.LocalDate;

public class IDCardConverter{

    public IDCard convertDtoToModel(IDCardDto dto) {
        var model = new IDCard();
        model.setCnp(dto.getCnp());
        model.setSeries(dto.getSeries());
        model.setNumber(dto.getNumber());
        model.setAddress(dto.getAddress());

        model.setDateOfBirth(
                (dto.getDateOfBirth() != null) ?
                        LocalDate.of(
                                Integer.parseInt(dto.getDateOfBirth().split("/")[2]),
                                Integer.parseInt(dto.getDateOfBirth().split("/")[1]),
                                Integer.parseInt(dto.getDateOfBirth().split("/")[0])
                        ) :
                        null
        );

        return model;
    }


    public IDCardDto convertModelToDto(IDCard idCard) {

        if (idCard == null)
            return new IDCardDto();

        String dateOfBirth = idCard.getDateOfBirth() != null ?
                idCard.getDateOfBirth().getDayOfMonth() + " " + idCard.getDateOfBirth().getMonth() + " " + idCard.getDateOfBirth().getYear() :
                null;

        return new IDCardDto(idCard.getCnp(), idCard.getSeries(), idCard.getNumber(), idCard.getAddress(), dateOfBirth);
    }
}
