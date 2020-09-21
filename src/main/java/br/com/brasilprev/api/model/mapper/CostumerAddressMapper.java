package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.dto.CostumerAddressDTO;
import br.com.brasilprev.api.model.Costumer;
import br.com.brasilprev.api.model.CostumerAddress;
import org.springframework.stereotype.Component;

@Component
public class CostumerAddressMapper implements Mapper<CostumerAddressDTO, CostumerAddress> {

    @Override
    @Deprecated
    public CostumerAddress convertDtoToModel(CostumerAddressDTO dto) {
        return null;
    }

    public CostumerAddress convertDtoToModel(CostumerAddressDTO dto, Costumer costumer) {
        return new CostumerAddress(
                dto.getId(),
                costumer,
                dto.getStreet(),
                dto.getNumber(),
                dto.getCep(),
                dto.getDistrict(),
                dto.getCity(),
                dto.getState(),
                dto.getCountry(),
                dto.getAddressType()
        );
    }

    @Override
    public CostumerAddressDTO convertModelToDto(CostumerAddress model) {
        return new CostumerAddressDTO(
                model.getId(),
                model.getStreet(),
                model.getNumber(),
                model.getCep(),
                model.getDistrict(),
                model.getCity(),
                model.getState(),
                model.getCountry(),
                model.getAddressType()
        );
    }
}
