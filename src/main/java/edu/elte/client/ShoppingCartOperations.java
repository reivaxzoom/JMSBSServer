package edu.elte.client;

import elte.sportStore.model.Item;
import org.javamoney.moneta.FastMoney;

/**
 *
 * @author Xavier
 */
public interface ShoppingCartOperations {

    boolean add(Item item);
    boolean remove(Item item);
    FastMoney getTotal();
    int itemNumber();
    
}
