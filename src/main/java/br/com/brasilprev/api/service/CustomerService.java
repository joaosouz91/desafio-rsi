package br.com.brasilprev.api.service;

import br.com.brasilprev.api.model.dto.CustomerDTO;
import br.com.brasilprev.api.model.Customer;
import br.com.brasilprev.api.model.mapper.CustomerMapper;
import br.com.brasilprev.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private MessageSource messageSource;

    public List<CustomerDTO> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        if(customerList == null && customerList.isEmpty()) throw new EmptyResultDataAccessException(1);
        return customerList.stream().map(customerMapper::convertModelToDto).collect(Collectors.toList());
    }

    public CustomerDTO findById(Long id) {
        Customer customer = customerRepository.findOne(id);
        if(customer == null) throw new EmptyResultDataAccessException(1);
        return customerMapper.convertModelToDto(customer);
    }

    public Customer create(CustomerDTO dto) {
        if(dto.getId() != null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("resource.id-not-allowed", null, LocaleContextHolder.getLocale()));
        return customerRepository.save(customerMapper.convertDtoToModel(dto));
    }

    public CustomerDTO update(CustomerDTO dto) {
        return customerMapper.convertModelToDto(
                customerRepository.save(customerMapper.convertDtoToModel(dto)));
    }

    public void delete(Long id) {
        customerRepository.delete(id);
    }


}
