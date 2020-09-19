package br.com.brasilprev.api.controller;

import br.com.brasilprev.api.event.CreatedResourceEvent;
import br.com.brasilprev.api.event.UpdatedResourceEvent;
import br.com.brasilprev.api.model.Costumer;
import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/costumers")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/{id}")
    public ResponseEntity<Costumer> findById(@PathVariable("id") Long id) {
        Costumer costumer = costumerService.findById(id);
        return costumer != null ? ResponseEntity.ok(costumer) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Costumer> create(@Valid @RequestBody Costumer costumer, HttpServletResponse response) {
        Costumer created = costumerService.create(costumer);
        eventPublisher.publishEvent(new CreatedResourceEvent(this, response, created.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Costumer> update(@Valid @RequestBody Costumer costumer, HttpServletResponse response) {
        Costumer updated = costumerService.update(costumer);
        eventPublisher.publishEvent(new UpdatedResourceEvent(this, response, updated.getId()));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Long id) {
        costumerService.delete(id);
        return ResponseEntity.ok().build();
    }


}
