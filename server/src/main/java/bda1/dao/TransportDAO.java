package bda1.dao;

import bda1.model.Reservation;
import bda1.model.Transport;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TransportDAO extends BaseDAO<Transport> {

    public TransportDAO(Connection con) {
        super(con, "TRANSPORT");
    }

    @Override
    public Transport read(ResultSet result) throws SQLException {
        return new Transport(result.getInt("id"),
                result.getTimestamp("DEPART_DATE").toLocalDateTime(),
                result.getTimestamp("ARRIVEE_DATE").toLocalDateTime(),
                result.getInt("NOMBRE_SIEGES_OCCUPES"),
                result.getInt("NOMBRE_SIEGES_TOTAL"),
                result.getFloat("PRIX")) {
            @Override
            public int calculerNbrSiegesDispo() {
                return 0;
            }
        };
    }


    @Override
    public PreparedStatement write(PreparedStatement statement, Transport obj) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(obj.getDateDepart()));
        statement.setTimestamp(2, Timestamp.valueOf(obj.getDateArrivee()));
        statement.setInt(3, obj.getNbrSiegesOccupes());
        statement.setInt(4, obj.getNbrSiegesTotal());
        statement.setFloat(5, obj.getPrix());
        return statement;
    }

    @Override
    public String buildInsertQuery() {
        return "INSERT INTO TRANSPORT " +
                "(DEPART_DATE,ARRIVEE_DATE," +
                "NOMBRE_SIEGES_OCCUPES," +
                "NOMBRE_SIEGES_TOTAL,PRIX)" +
                " Values (?,?,?,?,?)";
    }

    @Override
    public String buildUpdateQuery() {
        return "UPDATE TRANSPORT SET " +
                "DEPART_DATE=?,ARRIVEE_DATE=?," +
                "NOMBRE_SIEGES_OCCUPES=?," +
                "NOMBRE_SIEGES_TOTAL=?," +
                "PRIX=? WHERE ID=?";
    }

    public Set<Transport> findByTransport(int id) throws SQLException {
        String sql = "SELECT * FROM TRANSPORT " +
                "INNER JOIN TRANSPORT_RESERVATION TR on TRANSPORT.ID = TR.TRANSPORT_ID " +
                "AND TR.RESERVATION_ID=" + id;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();
        Set<Transport> records = new HashSet<>();
        while (result.next()) {
            records.add(read(result));
        }
        return records;
    }

}
