package de.uniba.jsonApiSimulation.shared.exception;

/**
 * Custom Exception
 * Created by chandan on 02.05.17.
 */
public class FacultyGroupPersistenceException extends Exception {

    public FacultyGroupPersistenceException(){

    }

    public FacultyGroupPersistenceException(String message) {
        super(message);
    }

    public FacultyGroupPersistenceException(Throwable cause) {
        super(cause);
    }

    public FacultyGroupPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
