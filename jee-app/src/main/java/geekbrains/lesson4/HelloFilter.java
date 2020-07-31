package geekbrains.lesson4;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "HelloFilter", urlPatterns = {"/hello"})
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Фильтр запущен");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Фильтр обрабатывает запрос");
        request.setAttribute("hello", "Привет от фильтра");
        System.out.println("Фильтр передает переменную hello, которая содержит строку - "+request.getAttribute("hello"));
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        System.out.println("Фильтр завершает работу");
    }

}
