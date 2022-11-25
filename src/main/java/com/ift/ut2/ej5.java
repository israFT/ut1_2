package com.ift.ut2;

import java.sql.*;

public class ej5 {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/israelft";
        String sql="INSERT INTO EMPLEADOS VALUES (100, 'fer', 200), (101, 'tony', 450), (102, 'javi', 2048)";
        Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
        PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try{
            conn.setAutoCommit(false);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                System.out.println("rollback");
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
