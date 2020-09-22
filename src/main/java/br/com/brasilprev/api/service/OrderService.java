package br.com.brasilprev.api.service;

import br.com.brasilprev.api.exception.AddressUnavailableException;
import br.com.brasilprev.api.exception.CostumerUnavailableException;
import br.com.brasilprev.api.exception.ProductUnavailableException;
import br.com.brasilprev.api.model.Costumer;
import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.model.dto.OrderDTO;
import br.com.brasilprev.api.model.enumerator.OrderStatus;
import br.com.brasilprev.api.model.mapper.OrderMapper;
import br.com.brasilprev.api.repository.CostumerRepository;
import br.com.brasilprev.api.repository.OrderRepository;
import br.com.brasilprev.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CostumerRepository costumerRepository;

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
        Order order = orderRepository.findOne(id);
        if(order == null) throw new EmptyResultDataAccessException(1);
        return orderMapper.convertModelToDto(order);
    }

    public Order create(OrderDTO dto) {
        validateCreationScope(dto);
        Costumer costumer = costumerRepository.findOne(dto.getIdCostumer());
        Set<Product> productSet = dto.getProductList().stream().map(p -> productRepository.findOne(p.getIdProduct())).collect(Collectors.toSet());
        return orderRepository.save(orderMapper.convertDtoToModel(dto, productSet, costumer));
    }

    public Order updateStatus(Long id) {
        Order order = orderRepository.findOne(id);
        validateStatusUpdate(order);
        order.setStatus(order.getStatus().getNext());
        if (order.getStatus().equals(OrderStatus.DELIVERED)) order.setEndDate(LocalDate.now());
        return orderRepository.save(order);
    }

    public Order cancelOrder(Long id) {
        Order order = orderRepository.findOne(id);
        // call chargeback treatments
        validateStatusUpdate(order);
        order.setEndDate(LocalDate.now());
        order.setStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
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

        Costumer costumer = costumerRepository.findOne(dto.getIdCostumer());
        if(costumer == null || !costumer.getStatus().getBoolean()) {
            throw new CostumerUnavailableException();
        }

        costumer.getAdressList()
                .stream()
                .filter(address -> address.getId().equals(dto.getIdDeliveryAddress()))
                .findFirst()
                .orElseThrow(AddressUnavailableException::new);

        dto.getProductList().forEach(p -> {
            Product product = productRepository.findOne(p.getIdProduct());
            if(product == null || !product.getStatus().getBoolean()) {
                throw new ProductUnavailableException();
            }
        });
    }


}
