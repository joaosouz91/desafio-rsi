package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.dto.CostumerDTO;
import br.com.brasilprev.api.model.Costumer;
import br.com.brasilprev.api.model.enumerator.ModelStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CostumerMapper implements Mapper<CostumerDTO, Costumer> {

    @Autowired
    private CostumerAddressMapper costumerAddressMapper;

    @Override
    public Costumer convertDtoToModel(CostumerDTO dto) {

        final Costumer costumer = new Costumer();
        costumer.setId(dto.getId());
        costumer.setName(dto.getName());
        costumer.setPhoneOne(dto.getPhoneOne());
        costumer.setPhoneTwo(dto.getPhoneTwo());
        costumer.setStatus(dto.isEnabled() ? ModelStatus.ENABLED : ModelStatus.DISABLED);

        costumer.setAdressList(
                dto.getAddressList()
                        .stream()
                        .map(address -> costumerAddressMapper.convertDtoToModel(address, costumer))
                        .collect(Collectors.toList()));

        return costumer;
    }

    @Override
    public CostumerDTO convertModelToDto(Costumer model) {
        return new CostumerDTO(
                model.getId(),
                model.getName(),
                model.getAdressList()
                        .stream()
                        .map(costumerAddressMapper::convertModelToDto)
                        .collect(Collectors.toList()),
                model.getPhoneOne(),
                model.getPhoneTwo(),
                model.getStatus() == ModelStatus.ENABLED
        );
    }


}
