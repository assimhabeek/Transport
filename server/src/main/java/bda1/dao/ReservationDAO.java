package bda1.dao;

import bda1.model.Adresse;
import bda1.model.Reservation;

import java.sql.*;

public class ReservationDAO extends BaseDAO<Reservation> {

    public ReservationDAO(Connection con) {
        super(con, "RESERVATION");
    }

    @Override
    public Reservation read(ResultSet result) throws SQLException {
        Reservation reservation = new Reservation(result.getInt("ID"),
                result.getDate("RESERVATION_DATE").toLocalDate());
        reservation.addAdresse(new Adresse(result.getInt("ID"),
                result.getNString("RUE"),
                result.getString("CODE_POSTAL"),
                result.getNString("VILLE")));
        return reservation;
    }

    @Override
    public PreparedStatement write(PreparedStatement statement, Reservation obj) throws SQLException {
        statement.setDate(1, Date.valueOf(obj.getDateReservation()));
        statement.setString(2, obj.getrAdresse() != null ? obj.getrAdresse().getRue() : null);
        statement.setString(3, obj.getrAdresse() != null ? obj.getrAdresse().getCodePostal() : null);
        statement.setString(4, obj.getrAdresse() != null ? obj.getrAdresse().getVille() : null);
        return statement;
    }

    @Override
    public String buildInsertQuery() {
        return "INSERT INTO RESERVATION (RESERVATION_DATE,RUE,CODE_POSTAL,VILLE) VALUES (?,?,?,?)";
    }

    @Override
    public String buildUpdateQuery() {
        return "UPDATE RESERVATION SET RESERVATION_DATE=?,RUE=?,CODE_POSTAL=?,VILLE=? WHERE ID=?";
    }
}
