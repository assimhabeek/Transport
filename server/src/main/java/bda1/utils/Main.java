package bda1.utils;

import bda1.dao.FactureDAO;
import bda1.model.Facture;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        FactureDAO factureDAO = new FactureDAO(DBConnection.getDBConnection());
        try {
            Set<Facture> factures = factureDAO.findAll();
            Iterator<Facture> itra = factures.iterator();
            while (itra.hasNext()) {
                System.out.println(itra.next().getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
