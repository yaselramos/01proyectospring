package com.icodeap._proyectospring.controller;


import com.icodeap._proyectospring.dto.AutorDTO;
import com.icodeap._proyectospring.exception.ErrorResponse;
import com.icodeap._proyectospring.exception.NotFoundException;
import com.icodeap._proyectospring.model.Autor;
import com.icodeap._proyectospring.service.AutorService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/autores")
public class AutorControllerBD {

    @Autowired
    private AutorService autorService;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<AutorDTO>> getAllAutores() {
        List<AutorDTO> list = autorService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(list);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getAutorById(@PathVariable Long id) {
        try {
            autorService.findById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("El autor con id " + id + " no fue encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(autorService.findById(id));

        //   return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AutorDTO> createAutor(@RequestBody AutorDTO autor) {

        return new ResponseEntity<>(autorService.save(autor), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> updateAutor(@PathVariable Long id, @RequestBody AutorDTO autor) {
try
{
    autorService.update(id, autor);
} catch (NoSuchElementException e) {
    throw new NotFoundException("El autor con id " + id + " no fue encontrado.");
}

        return ResponseEntity.ok(autorService.update(id, autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
