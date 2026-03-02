package Hospital_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    private Connection conn;

    private Scanner sc;

    public Patient(Connection conn, Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }


    public void addPatient(){
        System.out.println("Enter Patient Name: ");
        String name = sc.next();
        System.out.println("Enter Patient Age: ");
        int age = sc.nextInt();
        System.out.println("Enter Patient Gender: ");
        String gender = sc.next();

        try{
            String sql = "insert into patients(name, age, gender) values(?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);

            int affected = pstmt.executeUpdate();
            if(affected > 0){
                System.out.println("Patient Added Successfully!");
            }else{
                System.out.println("Failed To Add Patient");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void viewPatients(){
        String sql = "select * from patients";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+-------------------+----------+-----------+");
            System.out.println("| Patient Id | Name              | Age      | Gender    |");
            System.out.println("+------------+-------------------+----------+-----------+");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-17s | %-8s | %-9s |\n", id, name, age, gender);
                System.out.println("+------------+-------------------+----------+-----------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public boolean getPatientById(int id){

        String sql = "select * from patients where id = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
