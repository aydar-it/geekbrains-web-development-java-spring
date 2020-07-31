package geekbrains.lesson4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Hello", urlPatterns = "/hello")
public class Hello extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet");
        PrintWriter pw = resp.getWriter();
        pw.println("Hello, Moto");
        resp.getWriter().print(req.getParameter("name"));
    }
}
