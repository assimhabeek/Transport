package bda1.model;

import java.time.LocalDate;

public class Voyageur {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    public Voyageur() {
    }

    public Voyageur(int id, String nom, String prenom, LocalDate dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int calculerAge() {
        return getDateNaissance().getYear() - LocalDate.now().getYear();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Voyageur && getId() == ((Voyageur) obj).getId();
    }

}
