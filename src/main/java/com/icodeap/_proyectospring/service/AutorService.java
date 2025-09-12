package com.icodeap._proyectospring.service;

import com.icodeap._proyectospring.dto.AutorDTO;
import com.icodeap._proyectospring.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {

    AutorDTO save(AutorDTO autor);
    AutorDTO findById(Long id);
    void deleteById(Long id);
    AutorDTO update(Long id,AutorDTO autor);
    List<AutorDTO> findAll();
}
