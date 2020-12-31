package jm.task.core.jdbc.service;

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

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/testbase?useSSL=false&serverTimezone=UTC";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "FlyingHome@112";


    public void createUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Пытаюсь создать таблицу в этой БД...");
            statement = connection.createStatement();
            String SQL = "CREATE TABLE sometable " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(30), " +
                    " lastName VARCHAR (30), " +
                    " Age VARCHAR (3), " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
            System.out.println("Таблица создана! (Или уже была)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица создана! (Или уже была)");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Удаляю таблицу...");
            statement = connection.createStatement();
            String sql = "DROP TABLE sometable ";
            statement.executeUpdate(sql);
            System.out.println("Таблица удалена (или её не было вовсе)");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица удалена (или её не было вовсе)");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(("INSERT INTO sometable(name, lastName, Age) VALUES (?,?,?)"));
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM sometable";
            ResultSet resultSet = statement.executeQuery(SQL);
            System.out.println("Удаляю запись с id = " + id);
            SQL = "DELETE FROM sometable WHERE id = " + id;
            statement.executeUpdate(SQL);
            System.out.println("Удалено");
            SQL = "SELECT * FROM sometable";
            resultSet = statement.executeQuery(SQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() {

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                User user = new User(name);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Connection connection1 = null;
        Statement statement1 = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection1 = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Удаляю таблицу...");
            statement1 = connection1.createStatement();
            String sql = "TRUNCATE TABLE sometable ";
            statement1.executeUpdate(sql);
            System.out.println("Таблица удалена (или её не было вовсе)");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица удалена (или её не было вовсе)");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement1 != null)
                    connection1.close();
            } catch (SQLException se) {
            }
            try {
                if (statement1 != null)
                    connection1.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}

