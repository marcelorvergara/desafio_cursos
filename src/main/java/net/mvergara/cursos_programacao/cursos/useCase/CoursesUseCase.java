package net.mvergara.cursos_programacao.cursos.useCase;

import net.mvergara.cursos_programacao.cursos.entities.CursoEntity;
import net.mvergara.cursos_programacao.cursos.repository.CoursesRepository;
import net.mvergara.cursos_programacao.exceptions.CursoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoursesUseCase {

    @Autowired
    private CoursesRepository coursesRepository;

    public CursoEntity execute(CursoEntity cursoEntity) {
        this.coursesRepository.findByName(cursoEntity.getName())
                .ifPresent(curso -> {
                    throw new CursoException();
                });
        return this.coursesRepository.save(cursoEntity);
    }

    public List<CursoEntity> get() {
        return this.coursesRepository.findAll();
    }

    public CursoEntity put(UUID id, CursoEntity cursoEntity) {
        Optional<CursoEntity> existingCourse =  this.coursesRepository.findById(id);
        if(existingCourse.isPresent()){
            CursoEntity cursoEntityToUpdate = existingCourse.get();
            cursoEntityToUpdate.setName(cursoEntity.getName());
            cursoEntityToUpdate.setCategory(cursoEntity.getCategory());
            cursoEntityToUpdate.setActive(cursoEntity.isActive());
            cursoEntityToUpdate.setUpdatedAt(LocalDateTime.now());
            return this.coursesRepository.save(cursoEntityToUpdate);
        } else {
            return null;
        }
    }

    public String delete(UUID id) {
        Optional<CursoEntity> existingCourde =  this.coursesRepository.findById(id);
        if(existingCourde.isEmpty()){
          return "Curso não encontrado";
        }
        this.coursesRepository.deleteById(id);
        return "Curso " + existingCourde.get().getName() + " removido da base de dados";
    }

    public CursoEntity toggleActive(UUID id) {
        Optional<CursoEntity> existingCourse =  this.coursesRepository.findById(id);
        if(existingCourse.isPresent()){
            CursoEntity cursoEntityToUpdate = existingCourse.get();
            cursoEntityToUpdate.setActive(!existingCourse.get().isActive());
            cursoEntityToUpdate.setUpdatedAt(LocalDateTime.now());
            return this.coursesRepository.save(cursoEntityToUpdate);
        } else {
            return null;
        }
    }
}
