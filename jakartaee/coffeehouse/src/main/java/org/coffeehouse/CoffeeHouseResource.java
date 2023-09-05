package org.coffeehouse;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.coffeehouse.tasks.OrderTask;

@Path("/coffeehouse")
public class CoffeeHouseResource {

    @Inject
    OrderTask orderTask;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String startsOrders() {
        return "Coffees ordered: "+orderTask.getCoffesOrdered().toString();
    }

}
