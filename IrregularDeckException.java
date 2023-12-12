public class IrregularDeckException {
    private Exception exception;

    public IrregularDeckException(String mensagem) {
        this.exception = new Exception(mensagem);
    }

    public String getMessage() {
        return exception.getMessage();
    }
}