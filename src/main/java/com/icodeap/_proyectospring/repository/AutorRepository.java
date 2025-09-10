package com.icodeap._proyectospring.repository;

import com.icodeap._proyectospring.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
