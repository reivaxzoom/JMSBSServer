package elte.sportStore.model;

import java.io.Serializable;
import javax.money.NumberValue;

/**
 * Representation of the item
 * @author Xavier
 */
public class Item implements Serializable{
 private static final long serialVersionUID = 1L;
    private String name;
    private int amount ;
    private NumberValue unityPrice;

    public Item() {
    }

    public Item(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Item(String name, int amount, NumberValue unityPrice) {
        this.name = name;
        this.amount = amount;
        this.unityPrice = unityPrice;
    }
    
    
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NumberValue getUnityPrice() {
        return unityPrice;
    }

    
    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", amount=" + amount + '}';
    }

    
    
    
    
}
