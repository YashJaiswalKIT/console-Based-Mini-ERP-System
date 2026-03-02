package ERP;
import java.io.*;
import java.util.Scanner;

public class CollegeSubadmin{

    String collegeName;
    String collegeId;
    String password;

    // login
    void subAdminLogin() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter College Name:");
        collegeName = sc.next();

        System.out.println("Enter College Code:");
        collegeId = sc.next();

        System.out.println("Enter Password:");
        password = sc.next();

        loginCheck();
    }
    void subAdminMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("----- SubAdmin Panel -----");
        System.out.println("1 Add Faculty");
        System.out.println("2 Delete Faculty");
        System.out.println("3 Update Faculty");
        System.out.println("4 Add Student");
        System.out.println("5 Delete Student");
        System.out.println("6 Update Student");
        System.out.println("7 Total Faculty Data");
        System.out.println("8 Total Students Data");
        System.out.println("0 Exit");

        String op = sc.next();

        switch (op) {

            case "1" -> addFaculty();
            case "2" -> deleteFaculty();
            case "3" -> updateFaculty();
            case "4" -> addStudent();
            case "5" -> deleteStudent();
            case "6" -> updateStudent();
            case "7" ->getTotalFaculty();
            case "8" -> getTotalStudent();
            case "0" -> System.exit(0);
            default -> subAdminMenu();
        }
    }
    void deleteFaculty(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Faculty Id to Delete:");
        String searchId = sc.next();

        boolean found = false;

        File inputFile = new File("facultyData.txt");
        File tempFile = new File("temp.txt");

        try{

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length >= 2 && data[0].equals(collegeId) && data[1].equals(searchId)){
                    found = true;
                    continue;   // skip -> delete
                }

                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if(inputFile.delete()){
                tempFile.renameTo(inputFile);
            }

            if(found){
                System.out.println("Faculty Deleted Successfully...");
            }else{
                System.out.println("Faculty Not Found...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        subAdminMenu();
    }
    void updateFaculty(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Faculty Id to Update:");
        String searchId = sc.next();

        boolean found = false;

        File inputFile = new File("facultyData.txt");
        File tempFile = new File("temp.txt");

        try{

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length >= 4 && data[0].equals(collegeId) && data[1].equals(searchId)){

                    found = true;

                    System.out.println("Record Found Enter New Details");

                    System.out.println("Enter Faculty Name:");
                    String name = sc.next();

                    System.out.println("Enter Subject:");
                    String subject = sc.next();

                    bw.write(collegeId + "," + searchId + "," + name + "," + subject);
                    bw.newLine();

                }else{
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            if(inputFile.delete()){
                tempFile.renameTo(inputFile);
            }

            if(found){
                System.out.println("Faculty Updated Successfully...");
            }else{
                System.out.println("Faculty Not Found...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        subAdminMenu();
    }
    void deleteStudent(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student Id to Delete:");
        String searchId = sc.next();

        boolean found = false;

        File inputFile = new File("studentData.txt");
        File tempFile = new File("temp.txt");

        try{

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length >= 2 && data[0].equals(collegeId) && data[1].equals(searchId)){
                    found = true;
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if(inputFile.delete()){
                tempFile.renameTo(inputFile);
            }

            if(found){
                System.out.println("Student Deleted Successfully...");
            }else{
                System.out.println("Student Not Found...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        subAdminMenu();
    }
    void updateStudent(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student Id to Update:");
        String searchId = sc.next();

        boolean found = false;

        File inputFile = new File("studentData.txt");
        File tempFile = new File("temp.txt");

        try{

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while((line = br.readLine()) != null){

                String[] data = line.split(",");

                if(data.length >= 4 && data[0].equals(collegeId) && data[1].equals(searchId)){

                    found = true;

                    System.out.println("Record Found Enter New Details");

                    System.out.println("Enter Student Name:");
                    String name = sc.next();

                    System.out.println("Enter Section (SecA/SecB):");
                    String section = sc.next();

                    bw.write(collegeId + "," + searchId + "," + name + "," + section);
                    bw.newLine();

                }else{
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            if(inputFile.delete()){
                tempFile.renameTo(inputFile);
            }

            if(found){
                System.out.println("Student Updated Successfully...");
            }else{
                System.out.println("Student Not Found...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        subAdminMenu();
    }
    void addFaculty() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Faculty Id:");
        String fid = sc.next();
        System.out.println("Enter Faculty Password:");
        String pass=sc.next();
        System.out.println("Enter Faculty Name:");
        String fname = sc.next();

        System.out.println("Enter Subject:");
        String subject = sc.next();

        try {

            FileWriter fw = new FileWriter("facultyData.txt", true);

            fw.write(collegeId + "," + fid + "," + fname + "," + subject + "," + pass + "\n");
            fw.close();

            System.out.println("Faculty Added...");

        } catch (Exception e) {
            e.printStackTrace();
        }

        subAdminMenu();
    }
    void addStudent(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student Id:");
        String sid = sc.next();
        System.out.println("Enter Student Password:");
        String spass=sc.next();
        System.out.println("Enter Student Name:");
        String sname = sc.next();

        System.out.println("Enter Section (SecA / SecB):");
        String section = sc.next();

        try{

            FileWriter fw = new FileWriter("studentData.txt", true);

            fw.write(collegeId + "," + sid + "," + sname + "," + section + ","+ spass + "\n");
            fw.close();

            System.out.println("Student Added...");

        }catch(Exception e){
            e.printStackTrace();
        }

        subAdminMenu();
    }
    void loginCheck() {

        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("collegeData.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 6 &&
                        data[0].equals(collegeName) &&
                        data[1].equals(collegeId) &&
                        data[5].equals(password)) {

                    found = true;
                    break;
                }
            }

            br.close();

            if (found) {
                System.out.println("SubAdmin Login Success...");
                subAdminMenu();
            } else {
                System.out.println("Invalid College Credentials...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void getTotalFaculty(){
        try{
            String lines;
            BufferedReader br=new BufferedReader(new FileReader("facultyData.txt"));
            while((lines =br.readLine())!=null){
                String[]data=lines.split(",");
                if(data[0].equals(collegeId)) {
                    System.out.println(lines);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        subAdminMenu();
    }
    void getTotalStudent(){
        try {
            String Lins;
            BufferedReader bf=new BufferedReader(new FileReader("studentData.txt"));
            while ((Lins=bf.readLine())!=null) {
                String[]data=Lins.split(",");

                if(data[0].equals(collegeId)){
                    System.out.println(Lins);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        subAdminMenu();
    }

    public static void main(String[] args) {

    }
}
