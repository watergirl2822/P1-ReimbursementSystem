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




}
