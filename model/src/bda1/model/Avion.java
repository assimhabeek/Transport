package bda1.model;

import java.time.LocalDateTime;

public class Avion extends Transport {

    private String compagnie;
    private String typeAppareil;

    public Avion(int id, LocalDateTime dateDepart, LocalDateTime dateArrivee, int nbrSiegesOccupes, int nbrSiegesTotal, float prix, String compagnie, String typeAppareil) {
        super(id, dateDepart, dateArrivee, nbrSiegesOccupes, nbrSiegesTotal, prix);
        this.compagnie = compagnie;
        this.typeAppareil = typeAppareil;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public String getTypeAppareil() {
        return typeAppareil;
    }

    public void setTypeAppareil(String typeAppareil) {
        this.typeAppareil = typeAppareil;
    }

    @Override
    public int calculerNbrSiegesDispo() {
        return getNbrSiegesTotal() - getNbrSiegesOccupes();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Avion && getId() == ((Avion) obj).getId();
    }


}
