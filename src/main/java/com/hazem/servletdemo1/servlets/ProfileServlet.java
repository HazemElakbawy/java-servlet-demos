package com.hazem.servletdemo1.servlets;

import com.hazem.servletdemo1.dao.UserDao;
import com.hazem.servletdemo1.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

  private static final Logger LOGGER = Logger.getLogger(ProfileServlet.class.getName());
  private final UserDao userDao = new UserDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    resp.setContentType("text/html");

    HttpSession userSession = req.getSession();
    Optional<Integer> userId = Optional.ofNullable((Integer) userSession.getAttribute("userId"));

    userId.ifPresentOrElse(
        id -> {
          User user = userDao.findById(id);
          if (!Objects.equals(user.name(), "")) {
            LOGGER.info("The username is " + user.name());
            req.setAttribute("username", user.name());
            RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");

            try {
              dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
              throw new RuntimeException(e);
            }
          }
        },
        () -> {
          try {
            resp.sendRedirect("index.html");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
    );
  }

//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    resp.setContentType("text/html");
//    PrintWriter out = resp.getWriter();
//
//    HttpSession userSession = req.getSession();
//    Optional<String> username = Optional.ofNullable(userSession.getAttribute("username").toString());
//
//    username.ifPresentOrElse(
//        name -> {
//          req.setAttribute("username", name);
//          RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp");
//          try {
//            dispatcher.forward(req, resp);
//          } catch (ServletException | IOException e) {
//            throw new RuntimeException(e);
//          }
//        },
//        () -> {
//          try {
//            resp.sendRedirect("index.html");
//          } catch (IOException e) {
//            throw new RuntimeException(e);
//          }
//        }
//    );
//  }

//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    resp.setContentType("text/html");
//    PrintWriter out = resp.getWriter();
//
//    HttpSession userSession = req.getSession();
//    Optional<String> username = Optional.ofNullable(userSession.getAttribute("username").toString());
//
//    out.println("<html><body>");
//
//    username.ifPresentOrElse(
//        name -> {
//          out.println("<h1>Welcome to your profile, " + name + "! 👋</h1>");
//          out.println("<p>The server remembered your name!</p>");
//          out.println("<br>");
//          out.println("<a href='logout'>Logout</a>");
//        },
//        () -> {
//          out.println("<h1>You are not logged in!</h1>");
//          out.println("<p>Please <a href='index.html'>go back</a> and enter your name.</p>");
//          throw new RuntimeException("username is not found!");
//        }
//    );
//
//    out.println("</body></html>");
//  }
}
