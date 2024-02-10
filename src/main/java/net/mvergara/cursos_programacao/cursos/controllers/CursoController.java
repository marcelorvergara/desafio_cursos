package net.mvergara.cursos_programacao.cursos.controllers;

import jakarta.validation.Valid;
import net.mvergara.cursos_programacao.cursos.entities.CursoEntity;
import net.mvergara.cursos_programacao.cursos.useCase.CoursesUseCase;
import net.mvergara.cursos_programacao.exceptions.CursoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CoursesUseCase coursesUseCase;
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity cursoEntity) {
        try {
            var result = this.coursesUseCase.execute(cursoEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> get() {
        try {
            var result = this.coursesUseCase.get();
            if(result.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@Valid @PathVariable UUID id, @RequestBody CursoEntity cursoEntity) {
        try{
            var result = this.coursesUseCase.put(id, cursoEntity);
            if (result == null) {
                return ResponseEntity.badRequest().body("Curso não encontrado");
            }
            return  ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
