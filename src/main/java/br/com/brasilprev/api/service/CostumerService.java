package br.com.brasilprev.api.service;

import br.com.brasilprev.api.model.dto.CostumerDTO;
import br.com.brasilprev.api.model.Costumer;
import br.com.brasilprev.api.model.mapper.CostumerMapper;
import br.com.brasilprev.api.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private CostumerMapper costumerMapper;

    @Autowired
    private MessageSource messageSource;

    public List<CostumerDTO> findAll() {
        List<Costumer> costumerList = costumerRepository.findAll();
        if(costumerList == null && costumerList.isEmpty()) throw new EmptyResultDataAccessException(1);
        return costumerList.stream().map(costumerMapper::convertModelToDto).collect(Collectors.toList());
    }

    public CostumerDTO findById(Long id) {
        Costumer costumer = costumerRepository.findOne(id);
        if(costumer == null) throw new EmptyResultDataAccessException(1);
        return costumerMapper.convertModelToDto(costumer);
    }

    public Costumer create(CostumerDTO dto) {
        if(dto.getId() != null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("resource.id-not-allowed",
                            null, LocaleContextHolder.getLocale()));
        return costumerRepository.save(costumerMapper.convertDtoToModel(dto));
    }

    public Costumer update(Costumer costumer) {
        if(costumer.getId() == null)
            throw new HttpMessageNotReadableException(
                    messageSource.getMessage("resource.id-missing",
                            null, LocaleContextHolder.getLocale()));
        return costumerRepository.save(costumer);
    }

    public void delete(Long id) {
        costumerRepository.delete(id);
    }


}
