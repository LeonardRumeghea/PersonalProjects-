package exceptions;

public class NoCityFoundException extends Exception {
    public NoCityFoundException() {
        super("City with specified id does not exist in the database.");
    }
}