public class TimeOutException {
    private Exception exception;

    public TimeOutException(String mensagem) {
        this.exception = new Exception(mensagem);
    }

    public String getMessage() {
        return exception.getMessage();
    }
}