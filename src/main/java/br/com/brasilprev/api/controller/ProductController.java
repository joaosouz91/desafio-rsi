package br.com.brasilprev.api.controller;

import br.com.brasilprev.api.event.CreatedResourceEvent;
import br.com.brasilprev.api.event.UpdatedResourceEvent;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product, HttpServletResponse response) {
        Product created = productService.create(product);
        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, created.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Product> update(@Valid @RequestBody Product product, HttpServletResponse response) {
        Product updated = productService.update(product);
        eventPublisher.publishEvent(new UpdatedResourceEvent(this, response, updated.getId()));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
