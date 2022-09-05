package edu.escuelaing.ieti.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.app.entities.User;
import edu.escuelaing.ieti.app.persistence.UserServicePersistenceException;
import edu.escuelaing.ieti.app.service.UserService;

@Service
public class UserServiceHashMap  implements UserService{

    //Estructura Hashmap para usuarios
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>(); 

    /**
     * Funcion generada para guardar en el hashmap un usuario siempre y cuando el identificador de este
     * no exista previamente.
     */
    @Override
    public User create(User user) throws UserServicePersistenceException {
        if(users.contains(user.getId())){
            throw new UserServicePersistenceException(UserServicePersistenceException.PROHIBIDO);
        }
        users.put(user.getId(), user);
        return user;
    }

    /**
     * Funcion generada para buscar un usuario de acuerdo con el identificador que de el cliente
     */
    @Override
    public User findById(String id) throws UserServicePersistenceException {
        if(!users.keySet().contains(id)){
            throw new UserServicePersistenceException(UserServicePersistenceException.EXCEPTION_NO_ENCONTRADO);
        }
        return users.get(id);
    }

    /**
     * Funcion generada para retornar una lista con todos los usuarios
     * guardados en el hashmap.
     */
    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<User>();
        for(String key: users.keySet()){
            allUsers.add(users.get(key));
        }
        return allUsers;
    }

    /**
     * Funcion generada para eliminar un usuario de acuerdo con su identificador
     */
    @Override
    public void deleteById(String id) throws UserServicePersistenceException {
        if(users.containsKey(id)){
            users.remove(id);
        }
        else{
            throw new UserServicePersistenceException(UserServicePersistenceException.PROHIBIDO);
        }
        
    }

    /**
     * Funcion generada para actualizar la informacion de un usuario buscandolo por identificador
     */
    @Override
    public User update(User user, String userId) throws UserServicePersistenceException {
        if(!users.keySet().contains(userId)){
            throw new UserServicePersistenceException(UserServicePersistenceException.EXCEPTION_NO_ENCONTRADO);
        }
        return users.replace(userId, user);
    }
    
}
