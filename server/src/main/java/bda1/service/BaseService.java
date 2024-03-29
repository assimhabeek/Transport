package bda1.service;


import bda1.dao.BaseDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.sql.SQLException;
import java.util.Set;


public abstract class BaseService<T> {

    protected BaseDAO<T> dao;

    public BaseService(BaseDAO<T> dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<T> findAll() {
        Set<T> data = null;
        try {
            data = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public T find(@PathParam("id") int id) {
        T obj = null;
        try {
            obj = dao.find(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(T obj) {
        try {
            dao.update(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Status.OK).entity(obj).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(T obj) {
        try {
            dao.create(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Status.OK).entity(obj).build();
    }


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(T obj) {
        try {
            dao.delete(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Status.OK).entity(obj).build();
    }

}



