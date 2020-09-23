package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.dto.CustomerAddressDTO;
import br.com.brasilprev.api.model.Customer;
import br.com.brasilprev.api.model.CustomerAddress;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressMapper implements Mapper<CustomerAddressDTO, CustomerAddress> {

    @Override
    @Deprecated
    public CustomerAddress convertDtoToModel(CustomerAddressDTO dto) {
        return null;
    }

    public CustomerAddress convertDtoToModel(CustomerAddressDTO dto, Customer customer) {
        return new CustomerAddress(
                dto.getId(),
                customer,
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
    public CustomerAddressDTO convertModelToDto(CustomerAddress model) {
        return new CustomerAddressDTO(
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
