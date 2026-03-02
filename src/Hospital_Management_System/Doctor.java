package Hospital_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private Connection conn;

    public Doctor(Connection conn){
        this.conn = conn;
    }


    public void viewDoctors(){
        String sql = "select * from doctors";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+-------------------+----------+--------+");
            System.out.println("| Doctors Id | Name              | Specialization    |");
            System.out.println("+------------+-------------------+----------+--------+");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-17s | %-17s |\n", id, name, specialization);
                System.out.println("+------------+-------------------+----------+--------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public boolean getDoctorById(int id){

        String sql = "select * from doctors where id = ?";

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
