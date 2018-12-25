package bda1.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Reservation implements IReservation {
    private int id;
    private LocalDate dateReservation;
    private Set<Voyageur> rVoyageurs;
    private Set<Transport> rTransports;
    private Facture rFacture;
    private Adresse rAdresse;

    public Reservation() {
    }

    public Reservation(int id, LocalDate dateReservation) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.rVoyageurs = new HashSet<>();
        this.rTransports = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Set<Voyageur> getrVoyageurs() {
        return rVoyageurs;
    }

    @Override
    public void setrVoytageurs(Set<Voyageur> vs) {

    }

    public void setrVoyageurs(Set<Voyageur> rVoyageurs) {
        this.rVoyageurs = rVoyageurs;
    }

    public Facture getrFacture() {
        return rFacture;
    }

    public void setrFacture(Facture rFacture) {
        this.rFacture = rFacture;
    }

    public Adresse getrAdresse() {
        return rAdresse;
    }

    public void setrAdresse(Adresse rAdresse) {
        this.rAdresse = rAdresse;
    }

    public Set<Transport> getrTransports() {
        return rTransports;
    }

    public void setrTransports(Set<Transport> rTransports) {
        this.rTransports = rTransports;
    }

    @Override
    public void addTransport(Transport transport) {
        transport.getrReservations().add(this);
        rTransports.add(transport);
    }

    @Override
    public void removeTransport(Transport transport) {
        rTransports.remove(transport);
        transport.getrReservations().remove(this);
    }

    @Override
    public void addFacture(Facture facture) {
        if (facture.getrReservation() != null)
            facture.removeReservation(facture.getrReservation());
        if (getrFacture() != null)
            removeFacture(getrFacture());
        setrFacture(facture);
        facture.setrReservation(this);
    }


    @Override
    public void removeFacture(Facture f) {
        if (f != null) getrFacture().setrReservation(null);
        setrFacture(null);
    }

    @Override
    public void addVoyageur(Voyageur voyageur) {
        rVoyageurs.add(voyageur);
    }

    @Override
    public void removeVoyageur(Voyageur voyageur) {
        rVoyageurs.remove(voyageur);
    }


    @Override
    public void addAdresse(Adresse a) {
        setrAdresse(a);
    }

    @Override
    public void removeAdresse(Adresse a) {
        setrAdresse(null);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Reservation && getId() == ((Reservation) obj).getId();
    }

}
