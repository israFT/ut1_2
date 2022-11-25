package com.ift.ut2;

import java.sql.*;

public class ej2 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/israelft";
        String sql;
        try(
            Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
            PreparedStatement ps = conn.prepareStatement("select * FROM empleados",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //Statement stmt = conn.createStatement();
            ResultSet rs = ps.executeQuery()
        ) {
            rs.moveToInsertRow();
            rs.updateInt("empleadoid",1);
            rs.updateString("nombre","paco");
            rs.updateDouble("salario", 500);
            rs.insertRow();
            rs.last();
            System.out.println("meto a "+rs.getString(2)+" con id "+rs.getInt(1)+" y salario "+rs.getDouble(3));

            rs.moveToInsertRow();
            rs.updateInt(1,2);
            rs.updateString(2,"pepe");
            rs.updateDouble(3, 1200);
            rs.insertRow();
            rs.last();
            System.out.println("meto a "+rs.getString(2)+" con id "+rs.getInt(1)+" y salario "+rs.getDouble(3));

            rs.moveToInsertRow();
            rs.updateInt(1,3);
            rs.updateString(2,"roberto");
            rs.updateDouble(3, 200);
            rs.insertRow();
            rs.last();
            System.out.println("meto a "+rs.getString(2)+" con id "+rs.getInt(1)+" y salario "+rs.getDouble(3));

            rs.moveToInsertRow();
            rs.updateInt(1,4);
            rs.updateString(2,"fran");
            rs.updateDouble(3, 450);
            rs.insertRow();
            rs.last();
            System.out.println("meto a "+rs.getString(2)+" con id "+rs.getInt(1)+" y salario "+rs.getDouble(3));

            rs.moveToInsertRow();
            rs.updateInt(1,5);
            rs.updateString(2,"diego");
            rs.updateDouble(3, 2048);
            rs.insertRow();
            rs.last();
            System.out.println("meto a "+rs.getString(2)+" con id "+rs.getInt(1)+" y salario "+rs.getDouble(3));

           /* sql="INSERT INTO EMPLEADOS VALUES (3, 'robertito', 200), (4, 'francisco', 450), (5, 'diego', 2048)";
            stmt.executeUpdate(sql);*/

            rs.beforeFirst();
            while(rs.next()){
                System.out.println("Id: " + rs.getInt(1));
                System.out.println("Nombre: " + rs.getString(2));
                System.out.println("Salario: " + rs.getDouble(3));
                System.out.println("");
            }


            //vamos a modificar el segundo empleado y borrar el cuarto
            rs.absolute(2);
            System.out.println("vamos a cambiar a "+rs.getString(2)+":");
            rs.updateInt(1,22);
            rs.updateString(2,"paco pepe");
            rs.updateDouble(3, 1250);
            rs.updateRow();
            System.out.println("Id: " + rs.getInt(1));
            System.out.println("Nombre: " + rs.getString(2));
            System.out.println("Salario: " + rs.getDouble(3));
            System.out.println("");

            //borramos el cuarto
            rs.absolute(4);
            System.out.println("vamos a borrar a "+rs.getString(2)+":");
            rs.deleteRow();

            rs.afterLast();
            while(rs.previous()){
                System.out.println("Id: " + rs.getInt(1));
                System.out.println("Nombre: " + rs.getString(2));
                System.out.println("Salario: " + rs.getDouble(3));
                System.out.println("");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
