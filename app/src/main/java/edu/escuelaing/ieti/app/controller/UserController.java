package edu.escuelaing.ieti.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.escuelaing.ieti.app.entities.User;
import edu.escuelaing.ieti.app.persistence.UserServicePersistenceException;
import edu.escuelaing.ieti.app.service.UserService;
import edu.escuelaing.ieti.app.dto.UserDto;


@RestController
@RequestMapping("/users")
public class UserController {
    //Conexion con userService
    private final UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Constructor generado para dar valor a la variable userService
     * @param userService
     */
    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    /**
     * Funcion generada para retornar la lista de usuarios que se tenga guardada
     * @return -> List<User>
     */
    @GetMapping
 public ResponseEntity<List<UserDto>> getAll() {
     try{
        List<User> users = userService.getAll();
        List<UserDto> usersConvert = new ArrayList<UserDto>();
        for(User user : users){
            usersConvert.add(modelMapper.map(user, UserDto.class));
        }
        return new ResponseEntity<>(usersConvert, HttpStatus.ACCEPTED);
     }
     catch(UserServicePersistenceException ex){
        ex.printStackTrace();
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.FORBIDDEN);
     }
 }

 /**
  * Funcion generada para retornar el usuario que requiera el cliente de acuerdo
  con el identificador
  * @param id
  * @return
  */
 @GetMapping( "/{id}" )
 public ResponseEntity<UserDto> findById( @PathVariable String id ) {
    try{
        return new ResponseEntity<>(modelMapper.map(userService.findById(id), UserDto.class),HttpStatus.ACCEPTED);
    }
    catch(UserServicePersistenceException ex){
        ex.printStackTrace();
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
 }

/**
 * Funcion generada para crear un usuario de acuerdo con los parametros
 * dados por el cliente 
 * @param user
 * @return
 */
 @PostMapping
 public ResponseEntity<UserDto> create( @RequestBody User user ) {
    try{
        return new ResponseEntity<>(modelMapper.map(userService.create(user), UserDto.class), HttpStatus.ACCEPTED);
    }
    catch(UserServicePersistenceException ex){
        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }
 }

 /**
  * Funcion generada para actualizar los datos de un usuario de acuerdon con su id
  * @param user
  * @param id
  * @return
  */
 @PutMapping( "/{id}" )
 public ResponseEntity<UserDto> update( @RequestBody User user, @PathVariable String id ) {
      try{
        return new ResponseEntity<>(modelMapper.map(userService.update(user,id), UserDto.class), HttpStatus.ACCEPTED);
      }
      catch(UserServicePersistenceException ex){
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
      }
 }

 /**
  * Funcion generada para eliminar a un usuario de acuerdo con su identificador
  * @param id
  * @return
  */
 @DeleteMapping( "/{id}" )
 public ResponseEntity<Boolean> delete( @PathVariable String id ) {
    try{
        userService.deleteById(id);
        return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
    }
    catch(UserServicePersistenceException ex){
        return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
    }
 }
}