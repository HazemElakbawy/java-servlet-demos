package com.hazem.servletdemo1.dao;

import com.hazem.servletdemo1.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class UserDao {

  private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());

  public User save(User user) {
    try (PreparedStatement statement = DataSource.getConnection().prepareStatement(
        "INSERT INTO users (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
    ) {
      statement.setString(1, user.name());
      int affectedRows = statement.executeUpdate();
      if (affectedRows > 0) {
        LOGGER.info("user has been added successfully, affected rows " + affectedRows);
      }

      ResultSet resultSet = statement.getGeneratedKeys();
      int userId = -1;

      if (resultSet != null && resultSet.next()) {
        userId = resultSet.getInt(1);
      }

      return new User(userId, user.name());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public User findById(int id) {
    try (
        PreparedStatement statement = DataSource.getConnection()
            .prepareStatement("SELECT id, name FROM users WHERE id = ?")
    ) {
      statement.setInt(1, id);
      statement.executeQuery();
      LOGGER.info("query statement has completed successfully");

      ResultSet resultSet = statement.getResultSet();
      int userId = -1;
      String username = "";

      if (resultSet.next()) {
        userId = resultSet.getInt("id");
        username = resultSet.getString("name");
      }

      return new User(userId, username);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(Integer userId) {
    try (PreparedStatement statement = DataSource.getConnection()
        .prepareStatement("DELETE FROM users WHERE id = ?")) {
      statement.setInt(1, userId);
      int affectedRows = statement.executeUpdate();
      if (affectedRows > 0) {
        LOGGER.info("user has been deleted successfully, affected rows " + affectedRows);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
