package com.mkyong.jdbc;

import java.sql.*;

class Program {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/testbase?useSSL=false&serverTimezone=UTC";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "FlyingHome@112";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        int id = 1;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM sometable";
            ResultSet resultSet = statement.executeQuery(SQL);
            System.out.println("Удаляю запись с id = " + id);
            SQL = "DELETE FROM sometable WHERE id = " + id;
            statement.executeUpdate(SQL);
            System.out.println("Удалено");
            SQL = "SELECT * FROM sometable";
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                int id1 = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String specialty = resultSet.getString(3);
                int salary = resultSet.getInt(4);

                System.out.println("id: " + id1);
                System.out.println("name: " + name);
                System.out.println("lastName: " + specialty);
                System.out.println("Age: " + salary);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}