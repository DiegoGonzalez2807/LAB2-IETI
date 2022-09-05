package edu.escuelaing.ieti.app.service;

import java.util.List;

import edu.escuelaing.ieti.app.entities.User;
import edu.escuelaing.ieti.app.persistence.UserServicePersistenceException;

public interface UserService
{
    User create( User user ) throws UserServicePersistenceException;

    User findById( String id ) throws UserServicePersistenceException;
    
    List<User> getAll() throws UserServicePersistenceException;

    void deleteById( String id ) throws UserServicePersistenceException;

    User update( User user, String userId ) throws UserServicePersistenceException;
}  