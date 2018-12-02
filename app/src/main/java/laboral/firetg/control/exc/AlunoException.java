package laboral.firetg.control.exc;

public class AlunoException extends Exception {
    private String msg;
    public AlunoException(String msg){
        this.msg=msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
