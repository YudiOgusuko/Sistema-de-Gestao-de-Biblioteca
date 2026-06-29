package biblioteca.exception;

public class UsuarioComEmailDuplicadoException extends RuntimeException {
    public UsuarioComEmailDuplicadoException(String message) {
        super(message);
    }
}
