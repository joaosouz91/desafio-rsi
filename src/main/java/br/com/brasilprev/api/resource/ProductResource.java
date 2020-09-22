package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.model.dto.ProductDTO;
import br.com.brasilprev.api.event.CreatedResourceEvent;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

    @Autowired
    ProductService productService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> dtoList = productService.findAll();
        return dtoList != null ? ResponseEntity.ok(dtoList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) {
        ProductDTO dto = productService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO dto, HttpServletResponse response) {
        Product created = productService.create(dto);
        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, created.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO dto) {
        dto.setId(id);
        Product updated = productService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}