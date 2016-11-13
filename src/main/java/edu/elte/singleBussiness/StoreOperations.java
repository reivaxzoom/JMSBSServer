package edu.elte.singleBussiness;

import elte.sportStore.model.Item;
import java.util.List;

/**
 *
 * @author I328441
 */
public interface StoreOperations {

    void checkout();
    /**
     * Add one unit element to a specific item
     * @param item item to be added
     */
    void addStockOne( ItemStore item);
    /**
     * Reduce one unit to a specific item
     * @param item item to be reduced
     */
    void reduceOne( ItemStore item);
    /**
     * Add num units to a specific item
     * @param item item to be added
     * @param num number of units
     */
    void addStock(ItemStore item, int num);
    /**
     * Reduces num units to a item store
     * @param item item to be reduced
     * @param num  number of units
     */
    void reduce(ItemStore item, int num);
    /**
     * Remove a specific item from the database
     * @param it item to be removed
     */
    void removeItem(ItemStore it);
    /**
     * load a static predefined list of items 
     */
    void createSampleItems();
    /**
     * insert a loaded list to the database
     * @param listItems list to be inserted
     */
    void insertSampleItems(List<ItemStore> listItems);
    /**
     * Clean elements from the database
     */
    void removeAllListItems();
    /**
     * 
     * @param name
     * @return 
     */
    ItemStore findItem(String name);
    List<ItemStore> findAllItems();
    Item checkAvailableItem(Item cliReq);
    List<Item> checkAvailableItems(List<Item> cliReq);
    
}
