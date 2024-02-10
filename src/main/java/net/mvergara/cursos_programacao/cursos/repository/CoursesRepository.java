package net.mvergara.cursos_programacao.cursos.repository;

import net.mvergara.cursos_programacao.cursos.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CoursesRepository extends JpaRepository<CursoEntity, UUID> {

    Optional<CursoEntity> findByName(String name);
}
