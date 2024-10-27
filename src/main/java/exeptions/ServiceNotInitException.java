package exeptions;

public class ServiceNotInitException extends RuntimeException{

    public ServiceNotInitException(String msg){
        super(msg);
    }
}
