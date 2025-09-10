package com.icodeap._proyectospring.service;

import com.icodeap._proyectospring.model.Autor;
import com.icodeap._proyectospring.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AutorServiceImpl implements AutorService{
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor findById(Long id) {
        return autorRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public Autor update(Long id, Autor autor) {
        Optional<Autor> autorbd=autorRepository.findById(id);
        if(autorbd.isPresent()){
            autorbd.orElseThrow().setApellido(autor.getApellido());
            autorbd.orElseThrow().setNombre(autor.getNombre());
            autorbd.orElseThrow().setPhone(autor.getPhone());
            return autorRepository.save(autor);
        }
        return null;
    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }
}
