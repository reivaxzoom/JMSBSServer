/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.client;

import java.io.Serializable;
import javax.money.NumberValue;

/**
 *
 * @author Xavier
 */
public class ClientRequest implements Serializable{
 private static final long serialVersionUID = 1L;
    private String name;
    private int amount ;
    private NumberValue unityPrice;

    public ClientRequest() {
    }

    
    
    public ClientRequest(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public ClientRequest(String name, int amount, NumberValue unityPrice) {
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
        return "ClientRequest{" + "name=" + name + ", amount=" + amount + '}';
    }

    
    
    
    
}
