/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.singleBussiness;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xavier
 */
public class StoreOperationsImplTest {
    
    private StoreOperations so;
     private static final Log LOG = LogFactory.getLog(StoreOperationsImplTest.class);
    
    public StoreOperationsImplTest() {
        so = new StoreOperationsImpl();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
//    @AfterClass
//    public static void tearDownClass() throws IOException {
//        File file = new File("target/resources");
//        URL[] urls = {file.toURI().toURL()};
//        ClassLoader loader = new URLClassLoader(urls);
//        ResourceBundle bundle = ResourceBundle.getBundle("message", Locale.getDefault(), loader);
//        System.out.println("qpid properties");
//        System.out.println("topic.requestTopic="+bundle.getString("topic.requestTopic"));
//        System.out.println("queue.responseQueue="+bundle.getString("queue.responseQueue"));
//        System.out.println("connectionfactory.localConnectionFactory="+bundle.getString("connectionfactory.localConnectionFactory"));
//        System.out.println("jms properties");
//        System.out.println("SPORTSTOPICNAME="+bundle.getString("SPORTSELECTION"));
//        System.out.println("SPORTSTOPICNAME="+bundle.getString("SPORTSTOPICNAME"));
//        
//        assertNotNull( bundle.getString("SPORTSTOPICNAME"));
//    }
    
//    private static final String JMSselector = java.util.ResourceBundle.getBundle("JMSselectors").getString("SPORTSELECTION");
//    private static final String topicName = java.util.ResourceBundle.getBundle("JMSselectors").getString("SPORTSTOPICNAME");
    
    void checkJava(){}
    void checkMongoDb(){}
    void checkQpid(){}
    
    
    
    @Before
    public void setUp() {
         StoreOperations instance = new StoreOperationsImpl();
        LOG.info("removing database");
        instance.removeAllListItems();
        LOG.info("populating database");
        instance.createSampleItems();
        assertTrue("Empty elements found in db",instance.findAllItems().size()>0);
        
    }
    
    @After
    public void tearDown() {
    }
    

    /**
     * Test of addStockOne method, of class StoreOperationsImpl.
     */
    @Test
    public void testAddStockOne() {
        LOG.info("addStockOne");
        ItemStore it =so.findItem("backpack");
        so.addStockOne(it);
        ItemStore it1 =so.findItem("backpack");
        assertEquals(it.getAmount()+1, it1.getAmount());
        
    }

    /**
     * Test of reduceOne method, of class StoreOperationsImpl.
     */
    @Test
    public void testReduceOne() {
        LOG.info("reduceOne");
        ItemStore it =so.findItem("backpack");
        so.reduceOne(it);
        ItemStore it1 =so.findItem("backpack");
        assertEquals(it.getAmount()-1, it1.getAmount());
    }

    /**
     * Test of addStock method, of class StoreOperationsImpl.
     */
    @Test
    public void testAddStock() {
        LOG.info("addStock");
        int numAdded=10;
        ItemStore it =so.findItem("backpack");
        so.addStock(it,numAdded);
        ItemStore it1 =so.findItem("backpack");
        assertEquals(it.getAmount()+numAdded, it1.getAmount());
    }

    /**
     * Test of reduce method, of class StoreOperationsImpl.
     */
    @Test
    public void testReduce() {
        LOG.info("reduce");
        LOG.info("addStock");
        int num=10;
        ItemStore it =so.findItem("backpack");
        so.addStock(it,num);
        ItemStore it1 =so.findItem("backpack");
        assertEquals(it.getAmount()+num, it1.getAmount());
    }

    
   
    
    
    //
//    /**
//     * Test of removeItem method, of class StoreOperationsImpl.
//     */
//    @Test
//    public void testRemoveItem() {
//        LOG.info("removeItem");
//        ItemStore it = null;
//        StoreOperationsImpl instance = new StoreOperationsImpl();
//        instance.removeItem(it);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeAllListItems method, of class StoreOperationsImpl.
//     */
//    @Test
//    public void testRemoveAllListItems() {
//        LOG.info("removeAllListItems");
//        StoreOperationsImpl instance = new StoreOperationsImpl();
//        instance.removeAllListItems();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//
//    /**
//     * Test of checkItemStock method, of class StoreOperationsImpl.
//     */
//    @Test
//    public void testCheckItemStock() {
//        LOG.info("checkItemStock");
//        String name = "";
//        int amount = 0;
//        StoreOperationsImpl instance = new StoreOperationsImpl();
//        ItemStore expResult = null;
//        ItemStore result = instance.checkItemStock(name, amount);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkAvailableItems method, of class StoreOperationsImpl.
//     */
//    @Test
//    public void testCheckAvailableItems() {
//        LOG.info("checkAvailableItems");
//        List<ClientRequest> cliReq = null;
//        StoreOperationsImpl instance = new StoreOperationsImpl();
//        List<ItemStore> expResult = null;
//        List<ItemStore> result = instance.checkAvailableItems(cliReq);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
