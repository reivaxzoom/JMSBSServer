/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.singleBussiness;

import elte.sportStore.model.Item;
import java.util.List;

/**
 *
 * @author I328441
 */
public interface StoreOperations {

    void checkout();
    void addStockOne( ItemStore item);
    void reduceOne( ItemStore item);
    void addStock(ItemStore item, int num);
    void reduce(ItemStore item, int num);
    void removeItem(ItemStore it);
    void createSampleItems();
    void insertSampleItems(List<ItemStore> listItems);
    void removeAllListItems();
    ItemStore findItem(String name);
    List<ItemStore> findAllItems();
    Item checkAvailableItem(Item cliReq);
    List<Item> checkAvailableItems(List<Item> cliReq);
    
}
