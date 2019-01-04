package bda1.model;

import bda1.utils.LocalDateTimeAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Transport implements ITransport {
    public int id;

    protected LocalDateTime dateDepart;
    protected LocalDateTime dateArrivee;

    protected int nbrSiegesOccupes;

    protected int nbrSiegesTotal;


    protected Set<Reservation> rReservation;

    protected float prix;


    public Transport() {
    }

    public Transport(int id, LocalDateTime dateDepart, LocalDateTime dateArrivee, int nbrSiegesOccupes, int nbrSiegesTotal, float prix) {
        this.id = id;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.nbrSiegesOccupes = nbrSiegesOccupes;
        this.nbrSiegesTotal = nbrSiegesTotal;
        this.prix = prix;
        this.rReservation = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDateTime dateDepart) {
        this.dateDepart = dateDepart;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDateTime dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public int getNbrSiegesOccupes() {
        return nbrSiegesOccupes;
    }

    public void setNbrSiegesOccupes(int nbrSiegesOccupes) {
        this.nbrSiegesOccupes = nbrSiegesOccupes;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public Set<Reservation> getrReservations() {
        return rReservation;
    }

    @Override

    public void setrReservations(Set<Reservation> rs) {
        this.rReservation = rs;
    }

    @Override
    public void addReservation(Reservation reservation) {
        rReservation.add(reservation);
        reservation.getrTransports().add(this);
        if (reservation.getrVoyageurs() != null) {
            setNbrSiegesOccupes(getNbrSiegesOccupes() + reservation.getrVoyageurs().size());
        }
    }

    @Override
    public void removeReservation(Reservation reservation) {
        reservation.getrTransports().remove(this);
        rReservation.remove(reservation);
        setNbrSiegesOccupes(getNbrSiegesOccupes() - reservation.getrVoyageurs().size());
    }

    public int getNbrSiegesTotal() {
        return nbrSiegesTotal;
    }

    public void setNbrSiegesTotal(int nbrSiegesTotal) {
        this.nbrSiegesTotal = nbrSiegesTotal;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Train && getId() == ((Train) obj).getId();
    }

}
