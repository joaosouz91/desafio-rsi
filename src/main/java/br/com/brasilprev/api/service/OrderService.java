package br.com.brasilprev.api.service;

import br.com.brasilprev.api.exception.AddressUnavailableException;
import br.com.brasilprev.api.exception.CustomerUnavailableException;
import br.com.brasilprev.api.exception.OrderUnavailableException;
import br.com.brasilprev.api.exception.ProductUnavailableException;
import br.com.brasilprev.api.model.Customer;
import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.model.dto.OrderDTO;
import br.com.brasilprev.api.model.enumerator.OrderStatus;
import br.com.brasilprev.api.model.mapper.OrderMapper;
import br.com.brasilprev.api.repository.ProductRepository;
import br.com.brasilprev.api.repository.CustomerRepository;
import br.com.brasilprev.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MessageSource messageSource;

    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findAll();
        if(orderList == null || orderList.isEmpty()) throw new EmptyResultDataAccessException(1);
        return orderList.stream().map(orderMapper::convertModelToDto).collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return orderMapper.convertModelToDto(order);
    }

    public Order create(OrderDTO dto) {

        validateCreationScope(dto);

        Customer customer = customerRepository.findById(dto.getIdCustomer()).orElseThrow(CustomerUnavailableException::new);

        Set<Product> productSet = dto.getProductList().stream()
                                    .map(p -> productRepository.findById(p.getIdProduct()).orElseThrow(ProductUnavailableException::new))
                                    .collect(Collectors.toSet());

        return orderRepository.save(orderMapper.convertDtoToModel(dto, productSet, customer));
    }

    public OrderDTO updateStatus(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderUnavailableException::new);
        validateStatusUpdate(order);
        order.setStatus(order.getStatus().getNext());
        if (order.getStatus().equals(OrderStatus.DELIVERED)) order.setEndDate(LocalDate.now());
        return orderMapper.convertModelToDto(orderRepository.save(order));
    }

    public OrderDTO cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderUnavailableException::new);
        // call chargeback treatments
        validateStatusUpdate(order);
        order.setEndDate(LocalDate.now());
        order.setStatus(OrderStatus.CANCELED);
        return orderMapper.convertModelToDto(orderRepository.save(order));
    }

    private void validateStatusUpdate(Order order) {
        if(order.getStatus().equals(OrderStatus.CANCELED)
                || order.getStatus().equals(OrderStatus.DELIVERED))
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("orderstatus.update-not-allowed", null, LocaleContextHolder.getLocale()));
    }

    private void validateCreationScope(OrderDTO dto) {

        if(dto.getId() != null) {
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("resource.id-not-allowed", null, LocaleContextHolder.getLocale()));
        }

        Optional<Customer> customer = customerRepository.findById(dto.getIdCustomer());
        if(!customer.isPresent() || !customer.get().getStatus().getBoolean()) {
            throw new CustomerUnavailableException();
        }

        customer.get().getAdressList()
                .stream()
                .filter(address -> address.getId().equals(dto.getIdDeliveryAddress()))
                .findFirst()
                .orElseThrow(AddressUnavailableException::new);

        dto.getProductList().forEach(p -> {
            Optional<Product> product = productRepository.findById(p.getIdProduct());
            if(!product.isPresent() || !product.get().getStatus().getBoolean()) {
                throw new ProductUnavailableException();
            }
        });
    }


}
