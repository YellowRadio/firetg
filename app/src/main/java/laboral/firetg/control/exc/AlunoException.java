package laboral.firetg.control.exc;

public class AlunoException extends Exception {
    private String msg;
    public AlunoException(String msg){
        this.msg=msg;
    }
    public AlunoException(int i){
        switch (i){
            case 1: this.msg="O preechimento do nome é obrigatório.";
        }
    }
    @Override
    public String getMessage() {
        return this.msg;
    }
}
