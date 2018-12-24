package bda1.routes;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Hello {

    class Test {
        int id;
        String test;
        float price;

        public Test(int id, String test, float price) {
            this.id = id;
            this.test = test;
            this.price = price;
        }
    }

    @GET
    @Path("/get2")
    @Produces(MediaType.APPLICATION_JSON)
    public Test sayHello() {
        return new Test(1, "assim", 20);
    }

    @GET
    @Path("/get1")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello2() {
        return "assim";
    }
}

/*
    Don't create an instance just inject the DAO
    @Inject
    private FactureDAO FactureDAO;
*/


