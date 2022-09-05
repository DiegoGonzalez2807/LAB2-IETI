package edu.escuelaing.ieti.app.persistence;

public class UserServicePersistenceException extends Exception{
    public static final String EXCEPTION_NO_ENCONTRADO = "El elemento que estas buscando no esta disponible";
    public static final String PROHIBIDO ="La transaccion que quieres hacer no esta permitida";

    public UserServicePersistenceException(String message){super(message);}

    public UserServicePersistenceException(){super();}

    public UserServicePersistenceException(String message, Throwable cause){super(message, cause);}
}
