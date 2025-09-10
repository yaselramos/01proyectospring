package com.icodeap._proyectospring.controller;

import com.icodeap._proyectospring.model.Autor;
import com.icodeap._proyectospring.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorControllerBD {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Autor>> getAllAutores() {
        return ResponseEntity.ok(autorService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        Autor autor = autorService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }
    @PostMapping
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
          autorService.save(autor);
        return new ResponseEntity<>(autor, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Autor updatedAutor = autorService.update(id, autor);
        if (updatedAutor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAutor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
