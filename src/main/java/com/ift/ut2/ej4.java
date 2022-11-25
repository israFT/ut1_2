package com.ift.ut2;

import java.sql.*;
import java.util.Scanner;

public class ej4 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/israelft";
        String sql="select * from empleados where nombre = ?;";
        try(
                Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
                PreparedStatement ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ){
            Scanner scan= new Scanner(System.in);
            System.out.println("dime el nombre de un empleado para ver su salario");
            String nomEmp= scan.nextLine();
            ps.setString(1,nomEmp);

            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println(""+rs.getDouble(3));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
