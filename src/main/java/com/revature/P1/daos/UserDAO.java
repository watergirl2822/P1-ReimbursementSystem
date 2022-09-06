package com.revature.P1.daos;

import com.revature.P1.models.User;
import com.revature.P1.utils.database.ConnectionFactory;
import com.revature.P1.utils.database.custom_exceptions.InvalidSQLException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    @Override
    public void save(User obj) throws IOException {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public String getByUsername(String username){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT (USERNAME) FROM ERS_USERS WHERE USERNAME = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("USERNAME");

        }catch (SQLException e) {
            throw new InvalidSQLException("Sorry an error occurred while trying to connect to the database");
        }
        return null;
    }

    public String getByPassword(String password){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT (PASSWORD) FROM ERS_USERS WHERE PASSWORD = ?");
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("PASSWORD");

        }catch (SQLException e){
            throw new InvalidSQLException("Sorry an error occurred while trying to connect to the database");
        }

        return null;
    }
}
