package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.model.Customer;
import br.com.brasilprev.api.model.dto.CustomerDTO;
import br.com.brasilprev.api.service.CustomerService;
import br.com.brasilprev.api.event.CreatedResourceEvent;
import io.swagger.annotations.*;
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
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the Customer list"),
            @ApiResponse(code = 404, message = "No Customer found to display")
    })
    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CUSTOMER') and #oauth2.hasScope('read')")
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<CustomerDTO> dtoList = customerService.findAll();
        return dtoList != null ? ResponseEntity.ok(dtoList) : ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the Customer from the pointed Id"),
            @ApiResponse(code = 404, message = "No Customer found to display")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CUSTOMER') and #oauth2.hasScope('read')")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Long id) {
        CustomerDTO dto = customerService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Customer successfully created"),
            @ApiResponse(code = 400, message = "Missing required field or invalid filed value")
    })
    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_CREATE_CUSTOMER') and #oauth2.hasScope('read')")
    public ResponseEntity<Void> create(@Valid @RequestBody CustomerDTO costumerDTO, HttpServletResponse response) {
        Customer created = customerService.create(costumerDTO);
        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, created.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully updated"),
            @ApiResponse(code = 400, message = "Missing required field or invalid field value"),
            @ApiResponse(code = 404, message = "No Customer found to update")
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_CUSTOMER') and #oauth2.hasScope('read')")
    public ResponseEntity<CustomerDTO> update(@PathVariable("id") Long id, @Valid @RequestBody CustomerDTO dto) {
        dto.setId(id);
        CustomerDTO updated = customerService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully deleted"),
            @ApiResponse(code = 400, message = "Operation not allowed. Costumer is in use by other entity registry"),
            @ApiResponse(code = 404, message = "No Customer found to delete")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVE_CUSTOMER') and #oauth2.hasScope('read')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

}
