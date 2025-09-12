package com.icodeap._proyectospring.service;

import com.icodeap._proyectospring.dto.AutorDTO;
import com.icodeap._proyectospring.model.Autor;
import com.icodeap._proyectospring.repository.AutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public AutorDTO save(AutorDTO autor) {
    Autor autor1=modelMapper.map(autor,Autor.class);
        return modelMapper.map(autorRepository.save(autor1),AutorDTO.class);
    }

    @Override
    public AutorDTO findById(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return modelMapper.map(autorRepository.findById(id).orElseThrow(), AutorDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public AutorDTO update(Long id, AutorDTO autor) {
       AutorDTO autorDTO=findById(id);

        autorDTO.setApellido(autor.getApellido());
        autorDTO.setNombre(autor.getNombre());
        autorDTO.setPhone(autor.getPhone());
        Autor autor1=modelMapper.map(autorDTO,Autor.class);
        return modelMapper.map(autorRepository.save(autor1),AutorDTO.class);


    }

    @Override
    public List<AutorDTO> findAll() {
        return autorRepository.findAll().stream().map(autor -> {
            return modelMapper.map(autor, AutorDTO.class);
        }).collect(Collectors.toList());

    }
}
