package bda1.dao;

import bda1.model.Facture;

import java.sql.*;

public class FactureDAO extends BaseDAO<Facture> {


    public FactureDAO(Connection con) {
        super(con, "FACTURE");
    }

    @Override
    public Facture read(ResultSet result) throws SQLException {
        Facture facture = new Facture(result.getInt("id"),
                result.getDate("EMISSION_DATE").toLocalDate(),
                result.getFloat("TOTAL"),
                result.getBoolean("REGLEE"));
        return facture;
    }

    @Override
    public PreparedStatement write(PreparedStatement statement, Facture obj) throws SQLException {
        statement.setDate(1, Date.valueOf(obj.getDateEmission()));
        statement.setFloat(2, obj.getTotal());
        statement.setBoolean(3, obj.isReglee());
        statement.setInt(4, obj.getrReservation().getId());
        return statement;
    }

    @Override
    public String buildInsertQuery() {
        return "INSERT INTO FACTURE (EMISSION_DATE,TOTAL,REGLEE,RESERVATION_ID)";
    }

    @Override
    public String buildUpdateQuery() {
        return "UPDATE FACTURE SET EMISSION_DATE=?,TOTAL=?,REGLEE=?,RESERVATION_ID=? WHERE ID=?";
    }
}
