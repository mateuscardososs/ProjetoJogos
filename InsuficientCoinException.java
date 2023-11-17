public class InsuficientCoinException {
    private Exception exception;

    public InsuficientCoinException(String mensagem) {
        this.exception = new Exception(mensagem);
    }

    public String getMessage() {
        return exception.getMessage();
    }
}