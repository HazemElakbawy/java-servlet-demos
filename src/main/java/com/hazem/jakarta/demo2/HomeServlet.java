package com.hazem.jakarta.demo2;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    out.println("<html><body>");
    out.println("<h1>Welcome to the Home Page! 🎉</h1>");
    out.println("<p>This content was served by HomeServlet.</p>");
    out.println("<p>Check your console for filter messages!</p>");
    out.println("</body></html>");
  }
}
