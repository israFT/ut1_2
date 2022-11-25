package com.ift.ut2;

import java.sql.*;

public class ej1 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String sql;
        try(
            Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
            Statement stmt = conn.createStatement();
         ){
            System.out.println("creando bases de datos 'israelft'");
            sql="DROP DATABASE IF EXISTS israelft; CREATE DATABASE israelFT";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //conectarse a la base de datos nueva
        jdbcUrl="jdbc:postgresql://localhost:5432/israelft";
        try(
            Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
            Statement stmt = conn.createStatement();
        ){
            System.out.println("conectarse a la base de datos israelft");
            sql="CREATE TABLE empleados("+
                    "EMPLEADOID int PRIMARY KEY, "+
                    "NOMBRE char(40) NOT NULL, "+
                    "SALARIO numeric NOT NULL);";
            stmt.executeUpdate(sql);
            System.out.println("tabla empleados creada");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
