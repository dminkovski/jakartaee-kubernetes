package org.coffeehouse.tasks;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.coffeehouse.model.Coffee;
import org.coffeehouse.service.CoffeeService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.jboss.logging.Logger;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;


@ApplicationScoped
public class OrderTask {
    private static final Logger log = Logger.getLogger(OrderTask.class);


    @RestClient
    CoffeeService coffeeService;

    @Inject
    MeterRegistry registry;

    private List<Coffee> coffeesOrdered =  new ArrayList<Coffee>();
    private AtomicInteger coffeesOrderedCount =  new AtomicInteger();;

    private Random rand = new Random();

    @Scheduled(every="45s")
    void orderCoffeePeriodically() {
        try{
            log.info("Trying to order coffee...");
            List<Coffee> coffees = coffeeService.getCoffees();
            log.info("Received Coffees to choose from: "+coffees.toString());
            int nextCoffeeIndex = rand.nextInt(coffees.size());
            Coffee coffeeToOrder = coffees.get(nextCoffeeIndex);
            coffeesOrdered.add(coffeeToOrder);
            coffeesOrderedCount.incrementAndGet();
            log.info("Coffee ordered: "+coffeeToOrder.name);
            System.out.println("Coffee to order:"+coffeeToOrder.name+" for: "+coffeeToOrder.price);
            registry.counter("coffee_orders_counter", Tags.of("coffee", coffeeToOrder.name)).increment();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public int getCoffeesOrderedCount(){
        return coffeesOrderedCount.get();
    }
    public List<Coffee> getCoffesOrdered(){
        return coffeesOrdered;
    }
}