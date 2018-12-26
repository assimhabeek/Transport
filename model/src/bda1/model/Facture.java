package bda1.model;

import bda1.utils.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class Facture implements IFacture {
    private int id;

    private LocalDate dateEmission;
    private float total;
    private boolean reglee;

    public Facture() {
    }

    private Reservation rReservation;

    public Facture(int id, LocalDate dateEmission, float total, boolean reglee) {
        this.id = id;
        this.dateEmission = dateEmission;
        this.total = total;
        this.reglee = reglee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isReglee() {
        return reglee;
    }

    public void setReglee(boolean reglee) {
        this.reglee = reglee;
    }

    public Reservation getrReservation() {
        return rReservation;
    }

    public void setrReservation(Reservation rReservation) {
        this.rReservation = rReservation;
    }

    public void imprimerFacture() {

    }

    @Override
    public void addReservation(Reservation r) {
        if (r.getrFacture() != null)
            r.removeFacture(r.getrFacture());
        if (getrReservation() != null)
            removeReservation(getrReservation());
        setrReservation(r);
        r.setrFacture(this);
    }

    @Override
    public void removeReservation(Reservation r) {
        if (getrReservation() != null) getrReservation().setrFacture(null);
        setrReservation(null);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Facture && getId() == ((Facture) obj).getId();
    }

}
