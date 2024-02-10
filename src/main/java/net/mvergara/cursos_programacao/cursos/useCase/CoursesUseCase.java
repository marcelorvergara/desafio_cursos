package net.mvergara.cursos_programacao.cursos.useCase;

import net.mvergara.cursos_programacao.cursos.entities.CursoEntity;
import net.mvergara.cursos_programacao.cursos.repository.CoursesRepository;
import net.mvergara.cursos_programacao.exceptions.CursoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesUseCase {

    @Autowired
    private CoursesRepository coursesRepository;

    public CursoEntity execute(CursoEntity cursoEntity) {
        this.coursesRepository.findByName(cursoEntity.getName())
                .ifPresent(curso -> {
                    throw new CursoNotFoundException();
                });
        return this.coursesRepository.save(cursoEntity);
    }

    public List<CursoEntity> get() {
        return this.coursesRepository.findAll();
    }
}
