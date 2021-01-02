package br.com.brasilprev.api.service;

import br.com.brasilprev.api.model.Product;
import br.com.brasilprev.api.model.dto.ProductDTO;
import br.com.brasilprev.api.model.mapper.ProductMapper;
import br.com.brasilprev.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MessageSource messageSource;

    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()) throw new EmptyResultDataAccessException(1);
        return productList.stream().map(productMapper::convertModelToDto).collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        if(product == null) throw new EmptyResultDataAccessException(1);
        return productMapper.convertModelToDto(product);
    }

    public Product create(ProductDTO dto) {
        validateCreationScope(dto);
        return productRepository.save(productMapper.convertDtoToModel(dto));
    }

    public ProductDTO update(ProductDTO dto) {
        if(dto.getId() == null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("request.out.of.scope",
                            null, LocaleContextHolder.getLocale()));
        return productMapper.convertModelToDto(
                productRepository.save(
                        productMapper.convertDtoToModel(dto)));
    }

    public void delete(Long id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        productRepository.delete(p);
    }

    private void validateCreationScope(ProductDTO dto) {

        if(dto.getId() != null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("request.out.of.scope", null, LocaleContextHolder.getLocale()));


    }


}
