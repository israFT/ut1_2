package com.ift.ut1;

public class gatitos {
    private int id;
    private String nom;
    private double salario;
    public gatitos(){}
    public gatitos(int id, String nom, double salario) {
        this.id = id;
        this.nom = nom;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getSalario() {
        return salario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "gatitos{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", salario=" + salario +
                '}';
    }
}
