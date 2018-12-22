package bda1.model;

import java.util.Set;

public interface ITransport {

    public void addReservation(Reservation r);

    public void removeReservation(Reservation r);

    public Set<Reservation> getrReservations();

	public void setrReservations(Set<Reservation> rs);
	
    public int getNbrSiegesTotal();
	
	public int calculerNbrSiegesDispo();

}