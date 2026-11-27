package org.jboss.as.quickstarts.servlet.filterlistener;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * A simple servlet that exists as a target for Servlet Filters and Servlet Listeners.
 *
 * @author Jonathan Fuerth <jfuerth@redhat.com>
 */
@SuppressWarnings("serial")
@WebServlet("/FilterExample")
public class FilterExampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession(true);
        Integer views = (Integer) session.getAttribute("views");
        views = (views == null) ? 1 : views + 1;
        session.setAttribute("views", views);

        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
        writer.println(" <head>");
        writer.println("  <title>servlet-filterlistener</title>");
        writer.println(" </head>");
        writer.println(" <body>");
        writer.println("  <h1>App Version: 3.1</h1>");
        writer.println("  <p>Session ID: " + session.getId() + "</p>");
        writer.println("  <p>Views in this session: " + views + "</p>");
        writer.println("  <hr>");
        writer.println("  <form>");
        writer.println("   <label for=userInput>Enter some text:</label> <input type=text name=userInput>");
        writer.println("   <button type=submit>Send</button>");
        writer.println("  </form>");

        if (req.getParameter("userInput") != null) {
            writer.println("  <h1>You Typed: " + req.getParameter("userInput") + "</h1>");
        }

        writer.println(" </body>");
        writer.println("</html>");
        writer.close();
    }

}
