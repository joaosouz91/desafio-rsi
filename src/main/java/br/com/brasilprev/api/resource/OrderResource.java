package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.model.dto.OrderDTO;
import br.com.brasilprev.api.event.CreatedResourceEvent;
import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> dtoList = orderService.findAll();
        return dtoList != null ? ResponseEntity.ok(dtoList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id) {
        OrderDTO dto = orderService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderDTO orderDTO, HttpServletResponse response) {
        Order created = orderService.create(orderDTO);
        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, created.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateStatus(@PathVariable("id") Long id) {
        Order updated = orderService.updateStatus(id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable("id") Long id) {
        Order updated = orderService.cancelOrder(id);
        return ResponseEntity.ok(updated);
    }

}
