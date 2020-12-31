package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        final UserService userService = new UserServiceImpl();
        String testName = "Igor";
        String testLastName = "Perov";
        Byte testAge = 22;

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);

        User user = userService.getAllUsers().get(0);


        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        System.out.println(testName.equals(testName));




    }
}
/*
    в методе main  userService.createUsersTable(); в Userservicetest тоже создаётся, но выводит ошибку()
*/
