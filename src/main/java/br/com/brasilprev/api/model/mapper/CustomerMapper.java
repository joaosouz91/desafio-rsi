package br.com.brasilprev.api.model.mapper;

import br.com.brasilprev.api.model.dto.CustomerDTO;
import br.com.brasilprev.api.model.Customer;
import br.com.brasilprev.api.model.enumerator.ModelStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper implements Mapper<CustomerDTO, Customer> {

    @Autowired
    private CustomerAddressMapper customerAddressMapper;

    @Override
    public Customer convertDtoToModel(CustomerDTO dto) {

        final Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhoneOne(dto.getPhoneOne());
        customer.setPhoneTwo(dto.getPhoneTwo());
        customer.setStatus(dto.isEnabled() ? ModelStatus.ENABLED : ModelStatus.DISABLED);

        customer.setAdressList(
                dto.getAddressList()
                        .stream()
                        .map(address -> customerAddressMapper.convertDtoToModel(address, customer))
                        .collect(Collectors.toList()));

        return customer;
    }

    @Override
    public CustomerDTO convertModelToDto(Customer model) {
        return new CustomerDTO(
                model.getId(),
                model.getName(),
                model.getAdressList()
                        .stream()
                        .map(customerAddressMapper::convertModelToDto)
                        .collect(Collectors.toList()),
                model.getPhoneOne(),
                model.getPhoneTwo(),
                model.getStatus() == ModelStatus.ENABLED
        );
    }


}