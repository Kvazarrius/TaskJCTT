package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;
    Util util =new Util();
    List<User> users = new ArrayList<>();

    public UserDaoJDBCImpl() {
        connection = util.getConnection();
    }

    public void createUsersTable() throws SQLException {
        connection.setAutoCommit(false);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE sometable " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(30), " +
                    " lastName VARCHAR (30), " +
                    " Age VARCHAR (3), " +
                    " PRIMARY KEY (id))");
            System.out.println("Таблица создана!");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void dropUsersTable() throws SQLException {
        connection.setAutoCommit(false);
            try {
                Statement statement = connection.createStatement();
                System.out.println("Удаляю таблицу...");
                statement.executeUpdate("DROP TABLE sometable");
                System.out.println("Таблица удалена (или её не было вовсе)");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица удалена (или её не было вовсе)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        connection.setAutoCommit(true);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        connection.setAutoCommit(false);
            try {
               PreparedStatement ps = null;
               ps = connection.prepareStatement(("INSERT INTO sometable(name, lastName, Age) VALUES (?,?,?)"));
               ps.setString(1, name);
               ps.setString(2, lastName);
               ps.setInt(3, age);
               ps.executeUpdate();
               connection.commit();
               System.out.println("User с именем " + name + " добавлен в базу данных");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
    }

    public void removeUserById(long id) throws SQLException{
        connection.setAutoCommit(false);
        try {
            Statement statement = connection.createStatement();
            System.out.println("Удаляю запись с id = " + id);
            String SQL = "DELETE FROM sometable WHERE id = " + id;
            statement.executeUpdate(SQL);
            System.out.println("Удалено");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        connection.setAutoCommit(false);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sometable");
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

    public void cleanUsersTable() throws SQLException {
        connection.setAutoCommit(false);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE sometable");
            System.out.println("Таблица очищена)");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица удалена (или её не было вовсе)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
