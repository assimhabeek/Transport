package bda1.dao;

import bda1.model.Avion;
import bda1.model.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvionDAO extends BaseDAO<Avion> {

    private TransportDAO transportDAO;


    public AvionDAO(Connection con) {
        super(con, "AVOIN");
        this.transportDAO = new TransportDAO(con);
    }


    @Override
    public Avion read(ResultSet result) throws SQLException {
        Transport parent = transportDAO.find(result.getInt("ID"));
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
        statement.setLong(1, obj.getId());
        statement.setString(2, obj.getCompagnie());
        statement.setString(3, obj.getTypeAppareil());
        return statement;
    }

    @Override
    public String buildInsertQuery() {
        return "INSERT INTO AVOIN (ID,COMPAGNIE,TYPE_APPAREIL) Values (?,?,?)";
    }

    @Override
    public String buildUpdateQuery() {
        return "UPDATE AVOIN SET ID=?,COMPAGNIE=?,TYPE_APPAREIL=? WHERE ID=?";
    }

    @Override
    public boolean update(Avion obj) throws Exception {
        transportDAO.update(obj);
        return super.update(obj);
    }

    @Override
    public boolean create(Avion obj) throws SQLException {
        transportDAO.create(obj);
        obj.setId(transportDAO.lastInsertedId());
        return super.create(obj);
    }

    @Override
    public int lastInsertedId() throws SQLException {
        return transportDAO.lastInsertedId();
    }

    @Override
    public boolean delete(Avion obj) throws Exception {
        super.delete(obj);
        return transportDAO.delete(obj);
    }
}
