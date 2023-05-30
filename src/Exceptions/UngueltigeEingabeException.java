package Exceptions;

public class UngueltigeEingabeException extends Exception{

    public UngueltigeEingabeException(Throwable cause){
        super(cause); // wenn man statt Int String eingibt >InputMismatchException
    }
}
