package com.revature.P1.services;

import com.revature.P1.daos.UserDAO;
import com.revature.P1.dtos.request.NewUserRequest;
import com.revature.P1.models.User;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;

import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public boolean isUsernameValid(String username){
        if(!username.matches("^[a-zA-Z0-9]([._](?![._])|[a-zA-Z0-9]){4,19}")){
            throw new InvalidRequestException("\nUsername not valid.\nUsername can only contain alphanumerics" +
                    "\nMust Start with alphanumerics\nCan only contain _ and . and they can't be next to eachother\n" +
                    "Must have 5-20 characters");
        }
        return true;
    }

    public boolean isValidPassword(String password){
        if(!password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"))
        {throw new InvalidRequestException("Password must contain at least 8 chars\nContain at least one digit\n" +
                "Contain at least one lower alpha char and one upper alpha char\nContain at least one special char\n" +
                "Does NOT contain spaces or tabs etc");
        }

        return true;
    }

    public boolean isDuplicateUsername(String username){
        if (userDAO.getByUsername(username) == null) {
            return false;
        }
        return true;
    }

    public boolean isSamePassword(String password, String password1){
        if(!password.equals(password1)) throw new InvalidRequestException("\nPasswords do not match");
            return true;
    }

    public User register(NewUserRequest request){
        User user = null;

        if(isUsernameValid(request.getUsername())){
            if (!isDuplicateUsername(request.getUsername())){
                if (isValidPassword(request.getPassword())){
                    if(isSamePassword(request.getPassword(), request.getPassword2())){
                       user = new User(UUID.randomUUID().toString(), request.getUsername(), request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), true, request.getRoleid());
                       userDAO.save(user);
                    }
                }
            }
        }
        return user;
    }

    public List<User> getAllUsers(){
        return userDAO.getAll();
    }

    public String getUserByUsername(String username){
        return userDAO.getByUsername(username);
    }




}
