package com.ift.ut1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ej1
{
    private static Scanner scan= new Scanner(System.in);
    private static int id;
    private static String nom;
    private static double sal;
    private static final long TAM=(4+20+8);


    public static void main(String[]args) {
        int num = 0;
        System.out.println("Aplicación de gestión: ");
        while (true) {
            System.out.println("MENU\n1. dar alta.\n2. consulta.\n3. salir de la app\n\n¿que quieres hacer?");
            num = scan.nextInt();

            switch (num) {
                case 1:
                    alta();
                    break;
                case 2:
                    consulta();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }
    public static void alta()
    {
        try (RandomAccessFile raf = new RandomAccessFile("empleados_raf.dat", "rw"))
        {
            System.out.println("ID");
            id = scan.nextInt();
            System.out.println("nombre");
            scan.nextLine();
            nom = scan.nextLine();
            System.out.println("salario");
            sal = scan.nextDouble();

            raf.seek(TAM*(id-1));

            raf.writeInt(id);
            StringBuffer sb =new StringBuffer(nom);
            sb.setLength(10);
            raf.writeChars(sb.toString());
            raf.writeDouble(sal);
            System.out.println("se ha escrito en la pos: "+ id);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    public static void consulta()
    {
        try (RandomAccessFile raf = new RandomAccessFile("empleados_raf.dat", "rw"))
        {
            System.out.println("que ID quieres ver??");
            id = scan.nextInt();

            raf.seek(TAM*(id-1));

            System.out.println(raf.readInt());
            String nombre="";
            for(int i=0;i<10;i++)
                nombre += raf.readChar();
            System.out.println(nombre);
            System.out.println(raf.readDouble());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
