package com.hazem.jakarta.demo1.listener;

import com.hazem.jakarta.demo1.dao.DataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

  private static final Logger LOGGER = Logger.getLogger(DatabaseInitializer.class.getName());

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    try (Statement statement = DataSource.getConnection().createStatement()) {
      statement.execute("CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))");
      LOGGER.info("Database has been initialized successfully and users table has been created.");

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    LOGGER.info("Application is shutting down.");
  }
}
