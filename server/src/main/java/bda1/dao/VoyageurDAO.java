package bda1.dao;

import bda1.model.Voyageur;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class VoyageurDAO extends BaseDAO<Voyageur> {


    public VoyageurDAO(Connection con) {
        super(con, "VOYAGEUR");
    }


    @Override
    public String buildInsertQuery() {
        return "INSERT INTO VOYAGEUR (NOM,PRENOM,NAISSANCE_DATE,RESERVATION_ID) Values (?,?,?,?)";
    }


    @Override
    public String buildUpdateQuery() {
        return "UPDATE VOYAGEUR SET NOM=?,PRENOM=?,NAISSANCE_DATE=?,RESERVATION_ID=? WHERE ID=?";
    }


    @Override
    public Voyageur read(ResultSet result) throws SQLException {
        Voyageur v = new Voyageur(result.getInt("ID"),
                result.getString("NOM"),
                result.getString("PRENOM"),
                result.getDate("NAISSANCE_DATE").toLocalDate());
        v.setReservationId(result.getInt("RESERVATION_ID"));
        return v;
    }

    @Override
    public PreparedStatement write(PreparedStatement statement, Voyageur voyageur) throws SQLException {
        statement.setString(1, voyageur.getNom());
        statement.setString(2, voyageur.getPrenom());
        statement.setDate(3, Date.valueOf(voyageur.getDateNaissance()));
        statement.setInt(4, voyageur.getReservationId());
        return statement;
    }


    public Set<Voyageur> findByReservation(int reservationId) throws SQLException {
        String sql = "SELECT * FROM VOYAGEUR WHERE RESERVATION_ID=" + reservationId;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();
        Set<Voyageur> records = new HashSet<>();
        while (result.next()) {
            records.add(read(result));
        }
        return records;
    }
}
