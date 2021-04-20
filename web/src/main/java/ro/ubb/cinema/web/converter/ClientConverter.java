package ro.ubb.cinema.web.converter;
import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto>{
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        var model = new Client();
        model.setId(dto.getId());
        model.setClientFirstName(dto.getFirstName());
        model.setClientLastName(dto.getLastName());
        model.setClientEmail(dto.getEmail());
        model.setClientAge(dto.getAge());
        return model;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = new ClientDto(client.getClientFirstName(), client.getClientFirstName(), client.getClientEmail(), client.getClientAge());
        dto.setId(client.getId());
        return dto;
    }
}
