package pl.coderslab.entity;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Hello! Your options are listed below: ");
        System.out.println("Update\nDelete\nfindAll\nread\nCreate\nExit");
        Scanner scan = new Scanner(System.in);
        UserDao dao = new UserDao();
        while(!scan.nextLine().toLowerCase().equals("exit")){
            switch(scan.nextLine()){
                case "Update":
                    System.out.println("Enter id of the user you wish to update: ");
                    User user = UserDao.read(scan.nextLong());
                    System.out.println("New username: ");
                    user.setUserName(scan.nextLine());
                    System.out.println("New email: ");
                    user.setEmail(scan.nextLine());
                    System.out.println("New password: ");
                    user.setPassword(scan.nextLine());
                    UserDao.update(user);
                case "findAll":
                    UserDao.findAll();
                case "read":
                    System.out.println("Enter id of the user you wish to see details of");
                    UserDao.read(scan.nextLong());
            }
        }

    }
}
