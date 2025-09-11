package com.icodeap._proyectospring.service;

import com.icodeap._proyectospring.dto.AutorDTO;
import com.icodeap._proyectospring.model.Autor;
import com.icodeap._proyectospring.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor save(Autor autor) {

        return autorRepository.save(autor);
    }

    @Override
    public Optional<Autor> findById(Long id) {
        return autorRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public Optional<Autor> update(Long id, Autor autor) {
        return autorRepository.findById(id).map(autor1 ->
                {
                    autor1.setApellido(autor.getApellido());
                    autor1.setNombre(autor.getNombre());
                    autor1.setPhone(autor.getPhone());
                    return autorRepository.save(autor1);
                }
        );


    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }
}
