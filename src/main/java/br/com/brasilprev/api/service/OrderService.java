package br.com.brasilprev.api.service;

import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements AbstractCrudService<Order> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageSource messageSource;

    public Order findById(Long id) {
        Order order = orderRepository.findOne(id);
        if(order == null) throw new EmptyResultDataAccessException(1);
        return order;
    }

    public Order create(Order order) {
        if(order.getId() != null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("request.out.of.scope",
                            null, LocaleContextHolder.getLocale()));
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        if(order.getId() == null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("request.out.of.scope",
                            null, LocaleContextHolder.getLocale()));
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }

}
