package ERP;
import java.util.*;
import java.lang.*;


public class Main {


    public static void main(String[] args) {


        Scanner input =new Scanner(System.in);
        System.out.println("Enter your Role:");
        String role=input.next();
        switch (role) {
            case "admin" ->{
                System.out.println("Here Is Admin Login Form...");
                admin ad=new admin();
                ad.AdminLogin();
            }

            //admin login function called
            case "subAdmin" ->{
                System.out.println("Here Is SubAdmin Login Form...");
                CollegeSubadmin cs=new CollegeSubadmin();
                cs.subAdminLogin();
            }

            //subAdmin Function called
            case "faculty" -> {
                System.out.println("Here Is Faculty Login Form...");
                Faculty fc=new Faculty();
                fc.facultyLogin();
            }

            //Faculty Function called
            case "student" ->{
                System.out.println("Here Is Student Login Form...");
                Student st = new Student();
                st.studentLogin();
            }
            default -> System.out.println("Invailed Input.....");
        }

    }
}
