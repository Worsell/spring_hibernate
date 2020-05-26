package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User u = new User("User1", "Lastname1", "user1@mail.ru");
      Car c1 = new Car(1, "car1", u);
      u.setC(c1);
      userService.add(u);
      User u2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car c2 = new Car(2, "car2", u2);
      u2.setC(c2);
      userService.add(u2);
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println(userService.getUserByNNCar(c1.getSeries(), c1.getName()));
      System.out.println(userService.getUserByNNCar(c2.getSeries(), c2.getName()));

      context.close();
   }
}
