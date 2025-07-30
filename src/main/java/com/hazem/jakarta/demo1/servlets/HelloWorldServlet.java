package com.hazem.jakarta.demo1.servlets;

import com.hazem.jakarta.demo1.dao.UserDao;
import com.hazem.jakarta.demo1.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

  private static final Logger LOGGER = Logger.getLogger(HelloWorldServlet.class.getName());
  private final UserDao userDao = new UserDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    out.println("<html><body>");
    out.println("<h1>Hello, World! 👋</h1>");
    out.println("<p>Welcome to your first Servlet application!</p>");
    out.println("</body></html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    String username = req.getParameter("username");
    User user = userDao.save(new User(-1, username));
    if (user.id() != -1) {
      HttpSession userSession = req.getSession(true);
      userSession.setAttribute("userId", user.id());
    }

    resp.sendRedirect("profile");
  }

//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    String username = req.getParameter("username");
//
//    resp.setContentType("text/html");
//    PrintWriter out = resp.getWriter();
//
//    out.println("<html><body>");
//    out.println("<h1>Hello, " + username + "! 👋</h1>");
//    out.println("<p>Welcome to your first Servlet application!</p>");
//    out.println("</body></html>");
//  }
}
