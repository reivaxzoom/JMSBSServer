///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package springSample;
//
//import java.util.List;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
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
//		"springStart",
//		 })
//class SpringContext {
//}
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringContext.class)
//public class BussinessTest {
//
//    @Autowired
//    BussinesService service;
//    
//    @Test
//    public void testGetall(){
//        List<Store> allEntries = service.retrieveAllRelated("tesco");
//        System.out.println("========================================="+allEntries.size());
//        allEntries.stream().forEach(System.out::println);
//        Assert.assertNotNull(allEntries);
//    }
////    @Test
////    public void testGetMongo(){
////        List<StoreA> allEntries = service.findAllMongo("tesco");
////        System.out.println("========================================="+allEntries.size());
////        allEntries.stream().forEach(System.out::println);
////        Assert.assertNotNull(allEntries);
////    }
//    
//    
//    
//}
