/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.client;

import edu.elte.client.ClientRequest;
import java.util.List;


public class ShoppingCartOperationsImpl implements ShoppingCartOperations {
    
//    StoreOperations storeOps;
    private ShoppingCart cart;
    
    public ShoppingCartOperationsImpl() {
        cart= new ShoppingCart();
    };
    
    
    
//     @Override
//    public ClientRequest checkItemsStock(List<ClientRequest> listClient) {
//       
//        listClient.stream().forEach(cliReq -> checkItemStock(cliReq));
//        return null;
//    }
//    
    
//    Stream<Student> students = persons.stream()
//      .filter(p -> p.getAge() > 18)
//      .map(new Function<Person, Student>() {
//                  @Override
//                  public Student apply(Person person) {
//                     return new Student(person);
//                  }
//              });
//    


    @Override
    public void removeItem(ClientRequest item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOneItem(ClientRequest item) {
      cart.add(item);
    }

    @Override
    public void removeItemFromExistent(ClientRequest item, int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addItemsToExistent(ClientRequest item, int number) {
        for (int i = 1; i <= number; i++) 
            addOneItem(item);
    }

    @Override
    public void checkItemStock(ClientRequest cliReq) {
         //CONVERT INTO A MSG AND SEND IT
    }

    @Override
    public void checkItemsStock(List<ClientRequest> listClient) {
         //CONVERT INTO A MSG AND SEND IT
    }

  



    


   
    
}
