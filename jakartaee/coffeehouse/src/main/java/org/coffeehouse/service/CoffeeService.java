package org.coffeehouse.rest;

import java.util.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import org.coffeehouse.model.Coffee;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/coffees/rest")
@RegisterRestClient(configKey="jakartaee-cafe-api")
public interface APIClient {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    List<Coffee> getCoffees();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void orderCoffee(Coffee coffee);
}