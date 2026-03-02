package ERP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Student {

    String username;
    String password;

    String collegeCode;
    String studentName;
    String section;

    public void studentLogin() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Username:");
        username = sc.next();

        System.out.println("Enter Password:");
        password = sc.next();

        loginFetch();
    }

    void loginFetch() {

        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("studentData.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");



                if (data.length >= 5 &&
                        data[1].equals(username) &&
                        data[4].equals(password)) {

                    found = true;

                    collegeCode = data[0];
                    studentName = data[2];
                    section = data[3];

                    break;
                }
            }

            br.close();

            if (found) {
                System.out.println("Student Login Success...");
                studentMenu();
            } else {
                System.out.println("Invalid Login");
                studentLogin();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void studentMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("----- Student Panel -----");
        System.out.println("1 View My Attendance");
        System.out.println("2 View Classmates");
        System.out.println("0 Logout");

        String op = sc.next();

        switch (op) {

            case "1" -> viewAttendance();
            case "2" -> viewClassmates();
            case "0" -> {
                System.out.println("Logout Success");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid Input");
                studentMenu();
            }
        }
    }

    // Attendance Count (Present / Absent)
    void viewAttendance() {

        int present = 0;
        int absent = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("attendance.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                // format -> name,section,A/P
                if (data.length >= 3 &&
                        data[0].equalsIgnoreCase(studentName) &&
                        data[1].equalsIgnoreCase(section)) {

                    if (data[2].equalsIgnoreCase("P")) {
                        present++;
                    } else if (data[2].equalsIgnoreCase("A")) {
                        absent++;
                    }
                }
            }

            br.close();

            System.out.println("------ My Attendance ------");
            System.out.println("Present : " + present);
            System.out.println("Absent  : " + absent);

            studentMenu();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Same class students show
    void viewClassmates() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("studentData.txt"));
            String line;

            System.out.println("---- Classmates ----");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 4 &&
                        data[0].equals(collegeCode) &&
                        data[3].equalsIgnoreCase(section)) {

                    System.out.println("Name: " + data[2] + "  Roll: " + data[1]);
                }
            }

            br.close();

            studentMenu();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

//        Student st = new Student();
//        st.studentLogin();
    }
}