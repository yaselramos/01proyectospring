package com.icodeap._proyectospring.controller;


import com.icodeap._proyectospring.dto.AutorDTO;
import com.icodeap._proyectospring.model.Autor;
import com.icodeap._proyectospring.service.AutorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

        if (autorService.findById(id)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
        //   return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AutorDTO> createAutor(@RequestBody AutorDTO autor) {

        return new ResponseEntity<>(autorService.save(autor), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> updateAutor(@PathVariable Long id, @RequestBody AutorDTO autor) {

        if (autorService.update(id, autor) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autorService.update(id, autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
