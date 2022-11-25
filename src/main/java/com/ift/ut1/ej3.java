package com.ift.ut1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class ej3
{
    private static Scanner scan= new Scanner(System.in);
    private static int id;
    private static String nom;
    private static double sal;
    private static final long TAM=(4+20+8);


    public static void main(String[]args) {
        int num=0;
        System.out.println("Aplicación de gestión: ");
        while (true) {
            System.out.println("MENU\n1. dar alta.\n2. consulta.\n3. salir de la app\n4. Generar XML\n5. muestra xml\n\n¿que quieres hacer?");
            num = scan.nextInt();
            scan.nextLine();
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
                case 4:
                    try {
                        creaXml();
                    } catch (ParserConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    muestraXml();
                    break;
            }
        }
    }
    public static void alta()
    {
        try (RandomAccessFile raf = new RandomAccessFile("empleados_raf2.dat", "rw"))
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
            /*StringBuffer sb =new StringBuffer(nom);
            sb.setLength(10);
            raf.writeChars(sb.toString());*/
            raf.writeUTF(nom);
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
        try (RandomAccessFile raf = new RandomAccessFile("empleados_raf2.dat", "rw"))
        {
            System.out.println("que ID quieres ver??");
            id = scan.nextInt();

            raf.seek(TAM*(id-1));

            System.out.println(raf.readInt());

            /*String nombre="";
            for(int i=0;i<10;i++)
                nombre += raf.readChar();*/

            System.out.println(raf.readUTF());
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
        public static void creaXml() throws ParserConfigurationException {
            ArrayList<Empleado> ListaEmpleado = new ArrayList<>();
            Empleado aux;
            try(RandomAccessFile raf = new RandomAccessFile("empleados_raf2.dat", "rw")){
                for(int i = 0;((TAM*i)<=raf.length());i++)
                {
                    raf.seek(TAM*i);
                    if((id=raf.readInt())>0)
                    {
                        /*String nombre="";
                        for(int n=0;n<10;n++)
                            nombre += raf.readChar();*/
                        nom=raf.readUTF();
                        sal=raf.readDouble();
                        aux = new Empleado();
                        aux.setId(id);
                        aux.setNom(nom);
                        aux.setSalario(sal);
                        ListaEmpleado.add(aux);
                    }

                }
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            Document doc = dom.createDocument(null, "xml",null);
            Element raiz = doc.createElement("empleados");
            doc.getDocumentElement().appendChild(raiz);
            Element nodoEmpleado = null, nodoDatos = null;
            Text texto=null;

            for(Empleado emp : ListaEmpleado)
            {
                nodoEmpleado = doc.createElement("Empleado");
                raiz.appendChild(nodoEmpleado);

                nodoDatos= doc.createElement("identificador");
                nodoEmpleado.appendChild(nodoDatos);
                texto = doc.createTextNode(String.valueOf(emp.getId()));
                nodoDatos.appendChild(texto);

                nodoDatos= doc.createElement("denominado");
                nodoEmpleado.appendChild(nodoDatos);
                texto = doc.createTextNode(emp.getNom());
                nodoDatos.appendChild(texto);

                nodoDatos= doc.createElement("salario");
                nodoEmpleado.appendChild(nodoDatos);
                texto = doc.createTextNode(String.valueOf(emp.getSalario()));
                nodoDatos.appendChild(texto);

                /*System.out.println(emp);*/
            }
            Source source = new DOMSource(doc);
            Result resultado = new StreamResult(new File("Empleados.xml"));
            Transformer transformer= null;
            try {
                transformer = TransformerFactory.newInstance().newTransformer();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                /*transformerFactory.setAttribute("indent-number", 2);*/
                transformer.setOutputProperty(OutputKeys.INDENT,"yes");
                transformer.transform(source,resultado);

            }
            catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        }
        public static void muestraXml()
        {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse("Empleados.xml");

                NodeList empleados = doc.getElementsByTagName("Empleado");
                for(int i=0;i<empleados.getLength();i++)
                {
                    Node empleado = empleados.item(i);
                    Element elemento=(Element) empleado;
                    /*System.out.println(elemento.getElementsByTagName("identificador").item(0).getChildNodes().item(0).getNodeValue());
                    System.out.println(elemento.getElementsByTagName("denominado").item(0).getChildNodes().item(0).getNodeValue());
                    System.out.println(elemento.getElementsByTagName("salario").item(0).getChildNodes().item(0).getNodeValue()+"\n");*/
                    System.out.println(elemento.getTextContent());
                }
            }
            catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
        }
}

