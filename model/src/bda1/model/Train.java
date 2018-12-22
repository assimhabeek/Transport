package bda1.model;

import java.time.LocalDateTime;

public class Train extends Transport {
    private int nbrWagons;
    private boolean voitureCafeteria;

    public Train(int id, LocalDateTime dateDepart, LocalDateTime dateArrivee, int nbrSiegesOccupes, int nbrSiegesTotal, float prix, int nbrWagons, boolean voitureCafeteria) {
        super(id, dateDepart, dateArrivee, nbrSiegesOccupes, nbrSiegesTotal, prix);
        this.nbrWagons = nbrWagons;
        this.voitureCafeteria = voitureCafeteria;
    }

    public int getNbrWagons() {
        return nbrWagons;
    }

    public void setNbrWagons(int nbrWagons) {
        this.nbrWagons = nbrWagons;
    }

    public boolean isVoitureCafeteria() {
        return voitureCafeteria;
    }

    public void setVoitureCafeteria(boolean voitureCafeteria) {
        this.voitureCafeteria = voitureCafeteria;
    }

    @Override
    public int calculerNbrSiegesDispo() {
        return getNbrSiegesTotal() - getNbrSiegesOccupes();
    }
}
