package br.com.brasilprev.api.model.mapper;

public interface Mapper<D, M> {

    M convertDtoToModel(D dto);

    D convertModelToDto(M model);

}
