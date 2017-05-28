package de.uniba.jsonApiSimulation.shared.exception;

/**
 * Custom Exception
 * Created by chandan on 02.05.17.
 */
public class CoursePersistenceException extends Exception{

    public CoursePersistenceException(){

    }

    public CoursePersistenceException(String message) {
        super(message);
    }

    public CoursePersistenceException(Throwable cause) {
        super(cause);
    }

    public CoursePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
