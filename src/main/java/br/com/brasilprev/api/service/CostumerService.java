package br.com.brasilprev.api.service;

import br.com.brasilprev.api.model.Costumer;
import br.com.brasilprev.api.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

@Service
public class CostumerService implements AbstractCrudService<Costumer> {

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private MessageSource messageSource;

    public Costumer findById(Long id) {
        Costumer costumer = costumerRepository.findOne(id);
        if(costumer == null) throw new EmptyResultDataAccessException(1);
        return costumer;
    }

    public Costumer create(Costumer order) {
        if(order.getId() != null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("codigo.fora.escopo.requisicao",
                            null, LocaleContextHolder.getLocale()));
        return costumerRepository.save(order);
    }

    public Costumer update(Costumer costumer) {
        if(costumer.getId() == null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("codigo.fora.escopo.requisicao",
                            null, LocaleContextHolder.getLocale()));
        return costumerRepository.save(costumer);
    }

    public void delete(Long id) {
        costumerRepository.delete(id);
    }



}
