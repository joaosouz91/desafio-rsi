package br.com.brasilprev.api.service;

import br.com.brasilprev.api.model.Model;

public interface AbstractCrudService<T extends Model> {

    T findById(Long id);

    T create(T model);

    T update(T model);

    void delete(Long id);

}
