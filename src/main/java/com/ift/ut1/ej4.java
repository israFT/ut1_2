package com.ift.ut1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class ej4 {
    public static void main(String[] args) {
        famGatos fg= new famGatos();

        gatitos g1= new gatitos(1,"fermin",300);
        gatitos g2= new gatitos(3, "chon", 567);

        fg.addGat(g1);
        fg.addGat(g2);

        try{
            //escribir
            JAXBContext contexto = JAXBContext.newInstance(fg.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(fg, new File("gatitos.xml"));

            //leer
            JAXBContext contexto2 = JAXBContext.newInstance(famGatos.class);
            Unmarshaller unmarshaller = contexto2.createUnmarshaller();
            famGatos fgAux = (famGatos) unmarshaller.unmarshal(new File("gatitos.xml"));
            fgAux.muestraGat();
        }
        catch (JAXBException e)
        {
            throw new RuntimeException(e);
        }
    }
}
