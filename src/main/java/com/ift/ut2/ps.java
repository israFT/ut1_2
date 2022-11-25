package com.ift.ut2;


import java.sql.*;
import java.util.Scanner;

public class ps
{
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/pedidos";
        try(
                Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
                PreparedStatement ps= conn.prepareStatement("SELECT * FROM CATEGORIAS WHERE CATEGORIAID <= ?");
        ){
            System.out.println("numero limite");
            int col=sc.nextInt();

            ps.setInt(1,col);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                System.out.println(rs.getInt("categoriaid")+" nombre "+rs.getString("nombrecat"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
