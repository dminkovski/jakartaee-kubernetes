package org.coffeehouse.model;
public class Coffee {
    private long id;
    public String name;
    public Double price;

    @Override
    public String toString(){
        return String.format("%s : %.2f €",name,price);
    }
}
