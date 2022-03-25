package JSQL.exception;

public class UnsupportedOperation extends Exception{
    public UnsupportedOperation(String message, int line) {
        super(message + " at line : ", line);
    }
}
