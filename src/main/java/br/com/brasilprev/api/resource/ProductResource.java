package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.model.dto.ProductDTO;
import br.com.brasilprev.api.event.CreatedResourceEvent;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.service.ProductService;
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
@RequestMapping("/api/v1/products")
public class ProductResource {

    @Autowired
    ProductService productService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the Product (dto) list"),
            @ApiResponse(code = 404, message = "No Product found to display")
    })
    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PRODUCT') and #oauth2.hasScope('read')")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> dtoList = productService.findAll();
        return dtoList != null ? ResponseEntity.ok(dtoList) : ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the Product (dto) from the pointed Id"),
            @ApiResponse(code = 404, message = "No Product found to display")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PRODUCT') and #oauth2.hasScope('read')")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) {
        ProductDTO dto = productService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Product successfully created")
    })
    @PostMapping(consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_CREATE_PRODUCT') and #oauth2.hasScope('read')")
    public ResponseEntity<Void> create(@Valid @RequestBody ProductDTO dto, HttpServletResponse response) {
        Product created = productService.create(dto);
        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, created.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product successfully updated"),
            @ApiResponse(code = 404, message = "No Product found to update")
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PRODUCT') and #oauth2.hasScope('read')")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO dto) {
        dto.setId(id);
        ProductDTO updated = productService.update(dto); //todo retornar dto
        return ResponseEntity.ok(updated);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product successfully deleted"),
            @ApiResponse(code = 404, message = "No Product found to delete")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVE_PRODUCT') and #oauth2.hasScope('read')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
