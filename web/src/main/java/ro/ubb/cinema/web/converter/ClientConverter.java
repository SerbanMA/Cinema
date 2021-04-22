package ro.ubb.cinema.web.converter;
import org.springframework.stereotype.Component;
import ro.ubb.cinema.core.domain.entities.Client;
import ro.ubb.cinema.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto>{
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        var model = new Client();
        model.setId(dto.getId());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setEmail(dto.getEmail());
        model.setAge(dto.getAge());
        return model;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = new ClientDto(client.getFirstName(), client.getLastName(), client.getEmail(), client.getAge());
        dto.setId(client.getId());
        return dto;
    }
}
