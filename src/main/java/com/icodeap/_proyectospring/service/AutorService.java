package com.icodeap._proyectospring.service;

import com.icodeap._proyectospring.model.Autor;

import java.util.List;

public interface AutorService {

    Autor save(Autor autor);
    Autor findById(Long id);
    void deleteById(Long id);
    Autor update(Long id,Autor autor);
    List<Autor> findAll();
}
