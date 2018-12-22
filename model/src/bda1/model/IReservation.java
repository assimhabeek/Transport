package bda1.model;

import java.util.Set;

public interface IReservation {

    public void addTransport(Transport t);

    public void removeTransport(Transport t);

    public void addFacture(Facture f);

    public void removeFacture(Facture f);

    public void addVoyageur(Voyageur v);

    public void removeVoyageur(Voyageur v);

    public void addAdresse(Adresse a);

    public void removeAdresse(Adresse a);

    public Set<Voyageur> getrVoyageurs();

	public void setrVoytageurs(Set<Voyageur> vs);

    public Set<Transport> getrTransports();
	
	public void setrTransports(Set<Transport> ts);

	public Facture getrFacture();
	
	public void setrFacture(Facture f);

	public Adresse getrAdresse();
	
	public void setrAdresse(Adresse a);

}