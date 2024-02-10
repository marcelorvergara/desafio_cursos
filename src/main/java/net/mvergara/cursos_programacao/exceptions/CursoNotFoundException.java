package net.mvergara.cursos_programacao.exceptions;

public class CursoNotFoundException extends RuntimeException{

    public CursoNotFoundException() {
        super("O curso já cadastrado");
    }
}
