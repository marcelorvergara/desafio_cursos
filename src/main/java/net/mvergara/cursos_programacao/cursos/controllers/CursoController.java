package net.mvergara.cursos_programacao.cursos.controllers;

import jakarta.validation.Valid;
import net.mvergara.cursos_programacao.cursos.CursoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity cursoEntity) {
        try {
            return ResponseEntity.ok().body("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
