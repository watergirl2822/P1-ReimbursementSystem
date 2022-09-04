package com.revature.P1.services;

import com.revature.P1.daos.UserDAO;
import com.revature.P1.utils.database.custom_exceptions.InvalidRequestException;

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

        return true;
    }

    public boolean isDuplicateUsername(String username){

        return false;
    }

    public boolean isSamePassword(String password){

        return false;
    }




}
