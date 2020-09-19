package br.com.brasilprev.api.service;

import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements AbstractCrudService<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MessageSource messageSource;

    public Product findById(Long id) {
        Product product = productRepository.findOne(id);
        if(product == null) throw new EmptyResultDataAccessException(1);
        return product;
    }

    public Product create(Product product) {
        if(product.getId() != null)
            throw new HttpMessageNotReadableException(
                messageSource.getMessage("request.out.of.scope",
                        null, LocaleContextHolder.getLocale()));
        return productRepository.save(product);
    }

    public Product update(Product product) {
        if(product.getId() == null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("request.out.of.scope",
                            null, LocaleContextHolder.getLocale()));
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }





}
