package ERP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Faculty  {
    String fuser;
    String fpass;
    String collegeCode;

    public  void facultyLogin(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Username:");
        fuser=sc.next();
        System.out.println("Enter Password");
        fpass=sc.next();
        loginFetch();
    }
    void loginFetch(){
        boolean found =false;
        try {
            BufferedReader br=new BufferedReader(new FileReader("facultyData.txt"));
            String Line;
            while ((Line=br.readLine())!=null){
                String[] data=Line.split(",");
                if(data.length>=5 &&
                        data[1].equals(fuser)&&
                        data[4].equals(fpass)
                ){
                    found=true;
                    collegeCode=data[0];
                    break;
                }
            }
            br.close();
            if(found){
                System.out.println("Faculty Login Success...");
                facultyMenu();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void facultyMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("----- Faculty Panel -----");
        System.out.println("1 View Students (Section Wise)");
        System.out.println("2 Mark Attendance");
        System.out.println("0 Logout");

        String op = sc.next();

        switch (op) {

            case "1" -> viewStudents();
            case "2" -> markAttendance();
            case "0" -> {
                System.out.println("Faculty logout");
                System.exit(0);
            }  // logout
            default -> {
                System.out.println("Invalided Inputs..");
                facultyMenu();
            }
        }
    }
    void viewStudents(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter section student view student");
        String sec=sc.next();
        try{
            BufferedReader br=new BufferedReader(new FileReader("studentData.txt"));
            String Lines;
            while((Lines=br.readLine())!=null){
                String[]data=Lines.split(",");
                if(data.length>=4 &&
                        data[0].equals(collegeCode) &&
                        data[3].equalsIgnoreCase(sec)){

                    System.out.println(Lines);
                }
            }
            facultyMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    void markAttendance() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Section (ex: secA / secB): ");
        String sec = sc.next();

        try {
            BufferedReader br = new BufferedReader(new FileReader("studentData.txt"));
            java.io.BufferedWriter bw =
                    new java.io.BufferedWriter(new java.io.FileWriter("attendance.txt", true));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                // data[3] = section , data[2] = name
                if (data.length >= 4 &&
                        data[0].equals(collegeCode) &&
                        data[3].equalsIgnoreCase(sec)) {

                    System.out.println("Student Name: " + data[2]);
                    System.out.print("Enter Attendance (A/P): ");
                    String att = sc.next();

                    // save -> name,section,attendance
                    bw.write(data[2] + "," + sec + "," + att);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            System.out.println("Attendance Saved Successfully...");
            facultyMenu();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {

    }
}
