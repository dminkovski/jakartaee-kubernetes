package org.coffeehouse;

import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderTask {

    @Inject
    APIClient apiClient;

    private List<Cafe> cafesOrdered;
    private AtomicInteger cafesOrderedCount;

    public OrderTask(){
        this.cafesOrdered = new ArrayList<Cafe>();
    }

    @Scheduled(every="45s")
    void orderCafePeriodically() {
        Cafe cafe = new Cafe();

        this.cafesOrdered.add(cafe);
        cafesOrderedCount.incrementAndGet();
        apiClient.sendData(cafe);
    }

    public int getCafesOrdered(){
        return cafesOrderedCount.get();
    }
}