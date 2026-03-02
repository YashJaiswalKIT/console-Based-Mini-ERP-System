package ERP;
import java.io.*;

import java.util.Scanner;

public class admin {
    String username;
    String password;
    String collegeName,collegeId,collegeAddress,collegeEmail,collegePhone,collegePassword;

    //admin Login
    public void AdminLogin(){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter Username:");
         username=input.next();
        System.out.println("Enter Password:");
        password=input.next();
        datafetching();
    }
    //login fetching
    void datafetching(){
        try {

            BufferedReader fr=new BufferedReader (new FileReader("admindata.txt"));
            String fileUsername=fr.readLine();
            String filePassword=fr.readLine();

            String u="Username:"+username;
            String p="Password:"+password;

            if(u.equals(fileUsername)&& p.equals(filePassword)){
                System.out.println("Login Successfully...");
                msgbox();
            }else{
                System.out.println("Invalid Credentials ,Re-run the program");
                System.exit(0);
            }

            fr.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //take college detail
    void collegedata(){
        System.out.println("Adding function called..(Enter Credentials without space..)");
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter College Name:");
        collegeName=sc.next();
        System.out.println("Enter College Id:");
        collegeId=sc.next();
        System.out.println("Enter College Address:");
        collegeAddress=sc.next();
        System.out.println("Enter College Email:");
        collegeEmail=sc.next();
        System.out.println("Enter College Phone:");
        collegePhone=sc.next();
        System.out.println("Enter College Password:");
        collegePassword=sc.next();

        if(collegeName.isEmpty() || collegeId.isEmpty()||collegeAddress.isEmpty() || collegeEmail.isEmpty() ||collegePhone.isEmpty() || collegePassword.isEmpty()){
            System.out.println("All filed Are Required..");
        }
        else {
            addcollege();
        }

    }
    void addcollege(){

        try{
           FileWriter br=new FileWriter("collegeData.txt",true);
            br.write(collegeName +"," + collegeId + "," + collegeAddress + "," + collegeEmail + "," + collegePhone + "," + collegePassword + "\n");
            br.close();
            System.out.println("College Added successfully..");
            msgbox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //college List
    void collegeList(){
        try{
            String lines;
            BufferedReader fr= new BufferedReader (new FileReader("collegeData.txt"));
            while ((lines=fr.readLine()) != null){
                System.out.println(lines);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    //delete college
    void deleteCollege() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the collegeName:");
        String CollegeName = sc.next();

        System.out.println("Enter the CollegeId:");
        String CollegeId = sc.next();

        boolean found = false;

        File inputFile = new File("collegeData.txt");
        File tempFile = new File("temp.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");


                if (data[0].equals(CollegeName) && data[1].equals(CollegeId)) {
                    found = true;
                    continue;
                }


                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();


            if (inputFile.delete()) {

                tempFile.renameTo(inputFile);
            }

            if (found) {
                System.out.println("College-Record deleted successfully.");
            } else {
                System.out.println("college-Record not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateCollege() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter College Id to Update:");
        String searchId = sc.next();

        boolean found = false;

        File inputFile = new File("collegeData.txt");
        File tempFile = new File("temp.txt");

        try {

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 6 && data[1].equals(searchId)) {

                    found = true;

                    System.out.println("Record Found. Enter New Details:");

                    System.out.println("Enter College Name:");
                    String collegeName = sc.next();

                    System.out.println("Enter College Address:");
                    String collegeAddress = sc.next();

                    System.out.println("Enter College Email:");
                    String collegeEmail = sc.next();

                    System.out.println("Enter College Phone:");
                    String collegePhone = sc.next();

                    System.out.println("Enter College Password:");
                    String collegePassword = sc.next();

                    // updated record write
                    bw.write(collegeName + "," + searchId + "," + collegeAddress + "," +
                            collegeEmail + "," + collegePhone + "," + collegePassword);
                    bw.newLine();

                } else {

                    // old line copy
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            }

            if (found) {
                System.out.println("College Updated Successfully...");
            } else {
                System.out.println("College ID not found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //msg content
    public void msgbox(){
        System.out.println("Add colleges press 1 or add");
        System.out.println("See total List Of college press 2 or list");
        System.out.println("if you want to delete Any college press 3 or delete");
        System.out.println("if you want to update Any college press 4 or update");
        System.out.println("if you want to exit type exit or cancel or close");

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Operation Key:");
        String option=sc.next();
        switch (option) {
            case "1", "add" -> {
                System.out.println("Here Is college Adding Form....");
                collegedata();
                msgbox();
            }
            case "2", "list" -> {
                System.out.println("here is college List...");
                collegeList();
                msgbox();
            }
            case "3", "delete" -> {
                System.out.println("Enter Credentials and perform delete operation");
                deleteCollege();
                msgbox();
            }
            case "4", "update" ->{
                System.out.println("verify credentials for updation ...");
                updateCollege();
                msgbox();
            }
            case "close","exit","cancel"-> {
                System.out.println("Admin Logout..");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalided Input re try....");
                msgbox();
            }
        }
    }



    public static void main(String[] args) {

    }
}
