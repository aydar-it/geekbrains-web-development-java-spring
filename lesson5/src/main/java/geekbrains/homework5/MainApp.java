package geekbrains.homework5;

import geekbrains.homework5.entity.Product;
import geekbrains.homework5.entity.User;
import geekbrains.homework5.services.ProductService;
import geekbrains.homework5.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        PrepareData.forcePrepareData();
        UserService userService = ctx.getBean("userService", UserService.class);
        ProductService productService = ctx.getBean("productService", ProductService.class);
        System.out.println(userService.save(new User("A", 1)));
        System.out.println(userService.save(new User("B", 2)));
        System.out.println(userService.find(1L));
        System.out.println(userService.getAll());

        System.out.println(productService.save(new Product("A", 1)));
        System.out.println(productService.save(new Product("B", 2)));
        System.out.println(productService.find(1L));
        System.out.println(productService.getAll());
    }
}
