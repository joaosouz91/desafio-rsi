package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.event.CreatedResourceEvent;
import br.com.brasilprev.api.model.Order;
import br.com.brasilprev.api.model.dto.OrderDTO;
import br.com.brasilprev.api.service.OrderService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized access. Authentication needed"),
        @ApiResponse(code = 403, message = "Permission to access the resource denied"),
        @ApiResponse(code = 500, message = "Internal Server Error [response specifies whether it's related to the request]")
})
@RestController
@RequestMapping("/api/v1/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the Order (dto) list"),
            @ApiResponse(code = 404, message = "No Order found to display")
    })
    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_ORDER') and #oauth2.hasScope('read')")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> dtoList = orderService.findAll();
        return dtoList != null ? ResponseEntity.ok(dtoList) : ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the Order (dto) from the pointed Id"),
            @ApiResponse(code = 404, message = "No Order found to display")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_ORDER') and #oauth2.hasScope('read')")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id) {
        OrderDTO dto = orderService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Order successfully created")
    })
    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_CREATE_ORDER') and #oauth2.hasScope('read')")
    public ResponseEntity<Void> create(@Valid @RequestBody OrderDTO orderDTO, HttpServletResponse response) {
        Order created = orderService.create(orderDTO);
        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, created.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order successfully updated"),
            @ApiResponse(code = 404, message = "No Order found to update")
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_ORDER') and #oauth2.hasScope('read')")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable("id") Long id) {
         OrderDTO updated = orderService.updateStatus(id);
        return ResponseEntity.ok(updated);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order successfully canceled"),
            @ApiResponse(code = 404, message = "No Order found to cancel")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVE_ORDER') and #oauth2.hasScope('read')")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable("id") Long id) {
        OrderDTO updated = orderService.cancelOrder(id);
        return ResponseEntity.ok(updated);
    }

}
