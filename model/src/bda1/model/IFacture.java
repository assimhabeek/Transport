package bda1.model;

import java.util.Set;

public interface IFacture {

    public void addReservation(Reservation r);

    public void removeReservation(Reservation r);

    public Reservation getrReservation();
	
	public void setrReservation(Reservation r);
}
