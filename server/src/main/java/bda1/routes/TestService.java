package bda1.routes;


import bda1.utils.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class TestService {


    @GET
    @Path("/get")
    @Produces("application/json")
    public Test getTestInJSON() {
        Test test = new Test();
        test.setId(1);
        test.setPrice(20);
        test.setTest("assim");
        return test;
    }

}

/*
    Don't create an instance just inject the DAO
    @Inject
    private FactureDAO FactureDAO;
*/


