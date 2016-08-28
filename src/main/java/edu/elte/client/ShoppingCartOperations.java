/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.client;

import edu.elte.client.ClientRequest;
import edu.elte.singleBussiness.ItemStore;
import java.util.List;

/**
 *
 * @author Xavier
 */
public interface ShoppingCartOperations {

    void checkItemStock(ClientRequest cliReq);
    
    void checkItemsStock(List<ClientRequest> listClient);

    void removeItem(ClientRequest item);
    
    void addOneItem(ClientRequest item);
    
    void removeItemFromExistent(ClientRequest item,int number);
    
    void addItemsToExistent(ClientRequest item, int number);
    
    
//    List<ItemStore> findAllItemsStock();
     
     
    
}
