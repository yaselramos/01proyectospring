package com.icodeap._proyectospring.controller;

import com.icodeap._proyectospring.model.Autor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private List<Autor> listaAutores=new ArrayList<>(Arrays.asList(
            new Autor(1L, "Gabriel", "García Márquez", "123456789"),
            new Autor(2L, "Isabel", "Allende", "987654321"),
            new Autor(3L, "Mario", "Vargas Llosa", "456789123")));

    @GetMapping
    public List<Autor> ListarAutores() {
        return listaAutores;
    }
    @GetMapping("/{id}")//variable de uri
    public ResponseEntity<?> obtenerAutorByID(@PathVariable Long id){
       Optional<Autor>  autor=listaAutores.stream().filter(autor1->autor1.getId().equals(id)).findFirst();
        if(autor.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el autor con id: "+id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(autor.orElseThrow());
    }
    @GetMapping("/buscar")//variable de consulta o query parameter
    public ResponseEntity<?> obtenerAutorByNombre(@RequestParam(required = false)String nombre){
        Optional<Autor>  autor=listaAutores.stream().filter(autor1->autor1.getNombre().equals(nombre)).findFirst();
        if(autor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el autor con nombre: "+nombre);
        }
        return ResponseEntity.status(HttpStatus.OK).body(autor.orElseThrow());
    }

    @PostMapping
    public ResponseEntity<?> crearAutor(@RequestBody Autor autor){
        if(listaAutores.isEmpty()){
            autor.setId(listaAutores.stream().mapToLong(Autor::getId).max().orElse(1L));
        }else{
            autor.setId(listaAutores.stream().mapToLong(Autor::getId).max().orElse(0L)+1);
        }

        listaAutores.add(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAutor(@PathVariable Long id,@RequestBody Autor autor){
        Optional<Autor> autor1=listaAutores.stream().filter(a->a.getId().equals(id)).findFirst();
        if(autor1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el autor con id: "+id);
        }else {
            autor1.orElseThrow().setNombre(autor.getNombre());
            autor1.orElseThrow().setApellido(autor.getApellido());
            autor1.orElseThrow().setPhone(autor.getPhone());
            return ResponseEntity.status(HttpStatus.OK).body(autor1.orElseThrow());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAutor(@PathVariable Long id){
        Optional<Autor> autor=listaAutores.stream().filter(a->a.getId().equals(id)).findFirst();
        if(autor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el autor con id: "+id);
        }else {
            listaAutores.remove(autor.orElseThrow());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
