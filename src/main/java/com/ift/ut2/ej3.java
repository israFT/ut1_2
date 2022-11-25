package com.ift.ut2;

import java.sql.*;
import java.util.Scanner;

public class ej3 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/israelft";
        String sql;
        try(
            Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
            Statement stmt = conn.createStatement();
        ){
            Scanner scan= new Scanner(System.in);
            System.out.println("dime el nombre de un empleado para ver su salario");
            String nomEmp= scan.nextLine();

            sql ="select * from empleados where nombre = '"+nomEmp+"';";
            stmt.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
