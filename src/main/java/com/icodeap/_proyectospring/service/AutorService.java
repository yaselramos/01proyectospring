package com.icodeap._proyectospring.service;

import com.icodeap._proyectospring.dto.AutorDTO;
import com.icodeap._proyectospring.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {

    Autor save(Autor autor);
    Optional<Autor> findById(Long id);
    void deleteById(Long id);
    Optional<Autor> update(Long id,Autor autor);
    List<Autor> findAll();
}
