package edu.escuelaing.ieti.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.ieti.app.entities.User;
  
public interface UserRepository extends MongoRepository<User, String>
{
    List<User> findByNameOrLastName(String name, String lastName);
    
    List<User> findByCreatedAtGreaterThan(String startDate);
}