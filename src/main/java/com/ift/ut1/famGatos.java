package com.ift.ut1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class famGatos {
    private ArrayList<gatitos> gats;
    public famGatos(){
        gats=new ArrayList<>();
    }
    public void addGat(gatitos gato)
    {
        gats.add(gato);
    }
    public void muestraGat()
    {
        for(gatitos g: gats)
        {
            System.out.println(g);
        }
    }
}
