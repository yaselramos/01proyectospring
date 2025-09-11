package com.icodeap._proyectospring.controller;

import com.icodeap._proyectospring.dto.AutorDTO;
import com.icodeap._proyectospring.model.Autor;
import com.icodeap._proyectospring.service.AutorService;
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

    @GetMapping
    public ResponseEntity<List<AutorDTO>> getAllAutores() {
        List<AutorDTO> list = new ArrayList<>();
        for (int i = 0; i < autorService.findAll().size(); i++) {
            Autor a = autorService.findAll().get(i);
            AutorDTO autorDTO = new AutorDTO(a.getId(), a.getNombre(), a.getApellido(), a.getPhone());
            list.add(autorDTO);
        }
        list.stream().map(autor -> {
            AutorDTO a = new AutorDTO(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getPhone());
            return a;
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(list);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getAutorById(@PathVariable Long id) {
        Optional<Autor> autor = autorService.findById(id);
        if (autor.isPresent()) {
            AutorDTO a = new AutorDTO(autor.get().getId(), autor.get().getNombre(), autor.get().getApellido(), autor.get().getPhone());
            return ResponseEntity.status(HttpStatus.OK).body(a);
        } else {
            return ResponseEntity.notFound().build();
        }
        //   return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AutorDTO> createAutor(@RequestBody AutorDTO autor) {
        Autor objAutor = new Autor();
        objAutor.setNombre(autor.getNombre());
        objAutor.setApellido(autor.getApellido());
        objAutor.setPhone(autor.getPhone());
        Autor obj = autorService.save(objAutor);
        AutorDTO a = new AutorDTO(obj.getId(), obj.getNombre(), obj.getApellido(), obj.getPhone());
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> updateAutor(@PathVariable Long id, @RequestBody AutorDTO autor) {
        Autor objAutor = new Autor();
        objAutor.setNombre(autor.getNombre());
        objAutor.setApellido(autor.getApellido());
        objAutor.setPhone(autor.getPhone());
        Optional<Autor> updatedAutor = autorService.update(id, objAutor);
        if (updatedAutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AutorDTO a = new AutorDTO(updatedAutor.get().getId(), updatedAutor.get().getNombre(), updatedAutor.get().getApellido(), updatedAutor.get().getPhone());
        return ResponseEntity.ok(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
