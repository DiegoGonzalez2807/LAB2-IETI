package edu.escuelaing.ieti.app.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.app.entities.User;
import edu.escuelaing.ieti.app.persistence.UserServicePersistenceException;
import edu.escuelaing.ieti.app.repository.UserRepository;
import edu.escuelaing.ieti.app.service.UserService;

@Service
   public class UserServiceMongoDB implements UserService
   {
   
       private final UserRepository userRepository;
   
       public UserServiceMongoDB( UserRepository userRepository )
       {
           this.userRepository = userRepository;
       }


    @Override
    public User create(User user) throws UserServicePersistenceException {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(String id) throws UserServicePersistenceException {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() throws UserServicePersistenceException {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) throws UserServicePersistenceException {
        userRepository.deleteById(id);
        
    }

    @Override
    public User update(User user, String userId) throws UserServicePersistenceException {
        userRepository.save(user);
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String name, String lastName) {
        return userRepository.findByNameOrLastName(name,lastName);
    }

    @Override
    public List<User> findUsersCreatedAfter(String startDate) {
        return userRepository.findByCreatedAtGreaterThan(startDate);
    }

	

}
