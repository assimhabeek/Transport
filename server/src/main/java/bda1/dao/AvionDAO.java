package bda1.dao;

import bda1.model.Avion;
import bda1.model.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvionDAO extends BasicDAO<Avion> {

    private TransportDAO transportDAO;

    public AvionDAO(Connection con) {
        super(con, "AVOIN");
        this.transportDAO = new TransportDAO(con);
    }


    @Override
    public Avion read(ResultSet result) throws SQLException {
        Transport parent = transportDAO.find(result.getInt("TRANSPORT_ID"));
        return new Avion(result.getInt("id"),
                parent.getDateDepart(),
                parent.getDateArrivee(),
                parent.getNbrSiegesOccupes(),
                parent.getNbrSiegesTotal(),
                parent.getPrix(),
                result.getString("COMPAGNIE"),
                result.getString("TYPE_APPAREIL"));
    }

    @Override
    public PreparedStatement write(PreparedStatement statement, Avion obj) throws SQLException {
        statement.setString(1, obj.getCompagnie());
        statement.setString(2, obj.getTypeAppareil());
        return statement;
    }

    @Override
    public String buildInsertQuery() {
        return "INSERT INTO AVION (COMPAGNIE,TYPE_APPAREIL) Values (?,?,?)";
    }

    @Override
    public String buildUpdateQuery() {
        return "UPDATE AVION SET COMPAGNIE=?,TYPE_APPAREIL=? WHERE ID=?";
    }

    @Override
    public boolean update(Avion obj) throws Exception {
        return transportDAO.update(obj) && super.update(obj);
    }

    @Override
    public boolean create(Avion obj) throws SQLException {
        if (transportDAO.create(obj)) {
            return super.create(obj);
        }
        return false;
    }

    @Override
    public boolean delete(Avion obj) throws Exception {
        return transportDAO.delete(obj) && super.delete(obj);
    }
}
