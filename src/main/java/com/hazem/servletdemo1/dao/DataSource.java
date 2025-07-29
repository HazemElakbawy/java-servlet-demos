package com.hazem.servletdemo1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

  private static final String DB_URL = "jdbc:h2:mem:servlet-demo-db;DB_CLOSE_DELAY=-1";
  private static final String DB_USER = "sa";
  private static final String PASSWORD = "";

  public static Connection getConnection() {
    try {
      Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
      return connection;
    } catch (SQLException e) {
      throw new RuntimeException("Couldn't open a database connection.");
    }
  }
}
