///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package springSample;
//
//import edu.elte.store.ItemStore;
//import edu.elte.store.QStore;
//import edu.elte.store.Store;
//import edu.elte.store.StoreRepository;
//import edu.elte.store.StoreRepositoryImpl;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.stream.Collectors;
//import javax.money.CurrencyUnit;
//import javax.money.Monetary;
//import org.javamoney.moneta.Money;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// *
// * @author Xavier
// */
//
//@Configuration
//@ComponentScan(basePackages = {
//		"springSample",
//		"edu.elte.store" })
//class SpringContext {
//}
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringContext.class)
////@EnableAutoConfiguration
//public class ClientQueryImplTest {
//    
//   
//    
//    @Autowired
//    private StoreRepositoryImpl storeRep;
//    
//    static final String  STORENAME="Tesco";
// 
////    
//       @Test
//    public void testFindItem() {
//        System.out.println("findItem");
//        String nameItem = "";
//        Iterable<Store> result= storeRep.findStorewww(nameItem);
//        System.out.println("Print Stores name "+STORENAME);
//        result.forEach((st) -> {
//            System.out.println("Store: "+st.getName()+ " item :"+ st.getItemStore().stream().filter(p -> p.getName().equals(nameItem)).collect(Collectors.toList()));
//        });
//        assertNotNull(result);
//    }
//
//    
////     private void findItem(String nameItem) {
////
////        QStore qStore = new QStore("Busca");
////        Iterable<Store> result =storeRep.findAll(qStore.name.eq("Tesco"));
////        System.out.println("Print Stores name Tesco");
////        result.forEach((st) -> {
////            System.out.println("Store: "+st.getName()+ " item :"+ st.getItemStore().stream().filter(p -> p.getName().equals(nameItem)).collect(Collectors.toList()));
////        });
////    }
////    
////    
////    
////    
////    @Test
////    public void testCheckAvailabilityItem_String_int() {
////        System.out.println("checkAvailabilityItem");
////        String name = "";
////        int amount = 0;
////        ClientQueryImpl instance = new ClientQueryImpl();
////        instance.checkAvailabilityItem(name, amount);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of printStore method, of class ClientQueryImpl.
////     */
////    @Test
////    public void testPrintStore() {
////        System.out.println("printStore");
////        String name = "";
////        ClientQueryImpl instance = new ClientQueryImpl();
////        instance.printStore(name);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of findItem method, of class ClientQueryImpl.
////     */
////    @Test
//
////
////    /**
////     * Test of checkAvailabilityItem method, of class ClientQueryImpl.
////     */
////    @Test
////    public void testCheckAvailabilityItem_List() {
////        System.out.println("checkAvailabilityItem");
////        List<ItemStore> listRequest = null;
////        ClientQueryImpl instance = new ClientQueryImpl();
////        instance.checkAvailabilityItem(listRequest);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//    
//}
