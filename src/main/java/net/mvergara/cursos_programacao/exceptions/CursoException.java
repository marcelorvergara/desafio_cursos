package net.mvergara.cursos_programacao.exceptions;

public class CursoException extends RuntimeException{

    public CursoException() {
        super("O curso já cadastrado");
    }

    public CursoException(String msg) {
        super(msg);
    }
}
