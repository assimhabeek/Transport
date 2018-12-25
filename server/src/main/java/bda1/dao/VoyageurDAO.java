package bda1.dao;

import bda1.model.Voyageur;

import java.sql.*;

public class VoyageurDAO extends BaseDAO<Voyageur> {


    public VoyageurDAO(Connection con) {
        super(con, "VOYAGEUR");
    }


    @Override
    public String buildInsertQuery() {
        return "INSERT INTO VOYAGEUR (NOM,PRENOM,NAISSANCE_DATE) Values (?,?,?)";
    }


    @Override
    public String buildUpdateQuery() {
        return "UPDATE VOYAGEUR SET NOM=?,PRENOM=?,NAISSANCE_DATE=? WHERE ID=?";
    }


    @Override
    public Voyageur read(ResultSet result) throws SQLException {
        return new Voyageur(result.getInt("ID"),
                result.getString("NOM"),
                result.getString("PRENOM"),
                result.getDate("NAISSANCE_DATE").toLocalDate());
    }

    @Override
    public PreparedStatement write(PreparedStatement statement, Voyageur voyageur) throws SQLException {
        statement.setString(1, voyageur.getNom());
        statement.setString(2, voyageur.getPrenom());
        statement.setDate(3, Date.valueOf(voyageur.getDateNaissance()));
        return statement;
    }


}
