package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl  implements UserService {
    Connection connection = null;
    Statement statement = null;
    List<User> users = new ArrayList<>();
    String SQL = "SELECT * FROM sometable";
    UserDaoJDBCImpl one = new UserDaoJDBCImpl();

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/testbase?useSSL=false&serverTimezone=UTC";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "FlyingHome@112";


    public void createUsersTable() throws SQLException {
       one.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        one.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        one.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
       one.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        one.getAllUsers();
        return users;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        one.cleanUsersTable();
    }

}

