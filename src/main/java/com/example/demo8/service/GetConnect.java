package com.example.demo8.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnect {
       static String link = "jdbc:mysql://localhost:3306/module3";
       static String username = "root";
       static String password = "admin";

       public static Connection getConnection() throws Exception {
              try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                     Connection conn = DriverManager.getConnection(link, username, password);
                     return conn;
              }catch (SQLException e) {
                     return null;
              }
       }
}
