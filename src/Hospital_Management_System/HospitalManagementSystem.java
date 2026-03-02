package Hospital_Management_System;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hospital";

    private static final String username = "root";

    private static final String password = "tiger";

    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            Patient patient = new Patient(conn, sc);
            Doctor doctor = new Doctor(conn);

            while (true){
                System.out.println(" HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice){
                    case 1:
                        // Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        // View Patient
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        // View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        // Book Appointment
                        bookAppointment(patient, doctor, conn, sc);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("Thank You For Using Hospital Management System!");
                       return;
                    default:
                        System.out.println("Enter valid choice!");
                        break;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static void bookAppointment(Patient patient, Doctor doctor, Connection conn, Scanner sc){

        System.out.print("Enter Patient Id:");
        int patientId = sc.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = sc.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = sc.next();

        if(patient.getPatientById(patientId)){
            if(checkDoctorAvailability(doctorId, appointmentDate, conn)){

                String query = "insert into appointments(patient_id, doctor_id, appointment_date) values(?, ?, ?)";

                try{
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, patientId);
                    pstmt.setInt(2, doctorId);
                    pstmt.setString(3, appointmentDate);

                    int rowsAffected = pstmt.executeUpdate();
                    if(rowsAffected > 0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor not Available on this date!");
            }
        }else{
            System.out.println("Either doctor or patient doesn't exist!");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection conn){

        String query = "select count(*) from appointments where doctor_id = ? AND appointment_date = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, doctorId);
            pstmt.setString(2, appointmentDate);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count == 0){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
