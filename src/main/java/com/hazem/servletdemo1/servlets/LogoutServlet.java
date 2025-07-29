package com.hazem.servletdemo1.servlets;

import com.hazem.servletdemo1.dao.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  private static final Logger LOGGER = Logger.getLogger(LogoutServlet.class.getName());
  private final UserDao userDao = new UserDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    Optional.ofNullable(req.getSession(false))
        .ifPresent(
            session -> {
              userDao.delete((Integer) session.getAttribute("userId"));
              session.invalidate();
              LOGGER.info("Session has been closed.");
            });

    try {
      resp.sendRedirect("index.html");
      LOGGER.info("Redirecting to the main page.");
    } catch (IOException e) {
      throw new RuntimeException("Couldn't redirect user to the main page");
    }
  }
}
