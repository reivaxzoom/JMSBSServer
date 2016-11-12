/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elte.edu.samples;

import com.querydsl.collections.CollQuery;
import static com.querydsl.collections.CollQueryFactory.from;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xavier
 */
public class SampleQueryTest {
    
    public SampleQueryTest() {
    }
    
    NumberPath<Integer> intVar1 = Expressions.numberPath(Integer.class, "var1");
    NumberPath<Integer> intVar2 = Expressions.numberPath(Integer.class, "var2");
    List<Integer> list1 = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
    List<Integer> list2 = Arrays.asList(2, 2, 3, 3, 3, 4, 4, 4, 4, 4);
    
    List<String> europe = Arrays.asList("Belgium","Bulgaria","Croatia","Cyprus","Czech Republic","Denmark","Estonia","Finland","France","Germany","Greece","Hungary","Ireland","Italy","Latvia","Lithuania","Luxembourg","Malta","Netherlands","Poland","Portugal","Romania","Slovakia","Slovenia","Spain","Sweden","United Kingdom");
    StringPath c= Expressions.stringPath("country");
    
    List<String> food = Arrays.asList("milk","bread");
    StringPath f= Expressions.stringPath("food");
    
    List<Integer> quant = Arrays.asList(6, 3, 8, 3, 2, 4, 9, 8, 2, 1);
    NumberPath<Integer> q = Expressions.numberPath(Integer.class, "var2");
    
    List<String> catRep = Arrays.asList("pastery","beverages","beverages","cleaners","fruit","sport","pastery","beverages","cleaners","fruit");
     StringPath cr =Expressions.stringPath("catRep");
     
    
    
    @Test
    public void sampleInline1(){
        assertNotNull(from(c,europe).from(f, food).from(q,quant).select(c,f,q).where(f.eq("milk")).where(c.startsWith("C").or(c.startsWith("H"))).where(q.goe(8)).fetch());
    }
    
    
    @Test
    public void sampleCriteria(){
        CollQuery cq=from(c,europe).from(f, food).from(q,quant);
        cq.select(c,f,q);
        cq.where(f.eq("milk"),c.startsWith("C").or(c.startsWith("H")));
        cq.where(q.goe(8));
        assertNotNull(cq.fetch());
    }
    
    
     @Test
     /**
      * Order matters, only the last one aggregation function executes
      */
    public void sampleMax() {
        CollQuery cq=from(c,europe).from(f, food).from(q,quant);
//        cq.select(q.min());
        cq.select(q.max());
        assertEquals(9, cq.fetchOne());
    }
    
    @Test
    public void sampleAvg() {
        CollQuery cq=from(c,europe).from(f, food).from(q,quant);
        cq.select(q.avg());
        assertEquals(4.6, cq.fetchOne());
    }
    
    
    @Test
    public void sampleBetween() {
        CollQuery cq=from(c,europe).from(f, food).from(q,quant);
        cq.select(c,q.between(1, 3),q);
        assertNotNull(cq.fetch());
    }
    
    
     @Test
    public void collection_predicates() {
        CollQuery cq=from(c,europe).from(f, food).from(q,quant);
         BooleanExpression cond1 = q.between(1,5);
        BooleanExpression cond2 = q.notIn(2,3)   ;
        Predicate[] conds = {cond1,cond2};
        cq.where(conds);
        cq.select(c,f,q);
        assertEquals(108, cq.fetch().size());
    }
    
    
    @Test
    public void count() {
        assertEquals(Long.valueOf(27), from(c,europe).select(c.count()).fetchOne());
    }

    
    @Test
    public void countDistinct() {
        assertEquals(Long.valueOf(5L), from(cr,catRep).select(cr.countDistinct()).fetchOne());
    }

    
    /**
     * Calculate the sum in single list 
     */
    @Test
    public void sum() {
        CollQuery cq=from(q,quant);
        cq.select(q.sum());
        assertEquals(Integer.valueOf(46), cq.fetchOne());
    }
    
    
    /**
     * Remove repeated records in a single list
     */
    @Test
    public void removeRepeat(){
        CollQuery cq = from(cr,catRep).distinct();
        assertEquals(Arrays.asList("pastery","beverages","cleaners","fruit","sport"),cq.fetch());
    }
    
    /**
     * Create a collections of sets made of the combination of all
     * elements of Europe, food and quant therefore it count it
     */
    @Test
    public void countRecords() {
        CollQuery cq=from(c,europe).from(f, food).from(q,quant);
        assertEquals(540, cq.fetchCount());
    }
    
    /**
     * Agrupate all posibilities of between europe and catRep 
     * and check if there is repeated records and remove it
     */
    @Test
    public void countDistintRecords(){
        CollQuery cq=from(c,europe).from(cr, catRep);
        cq.distinct();
        cq.select(c,cr);
        assertEquals(135, cq.fetchCount());
    }
    
//    @Test
//    public void funcTest(){
////        CollQuery cq=from(c,europe).from(f, food).from(q,quant);
////        BooleanExpression cond2 = q.notIn("Hungary","Croatia")   ;
////        CollQueryFunctions.aggregate(europe, cr, null)
//                
//                
//    QueryMetadata metadata = new DefaultQueryMetadata();
//                 DefaultEvaluatorFactory evaluatorFactory = new DefaultEvaluatorFactory(CollQueryTemplates.DEFAULT);
//                 
//    QRequestData rq = QRequestData.requestData;
//    Evaluator projectionEvaluator = evaluatorFactory.create(metadata, Collections.singletonList(rq), rq.itemNumber);
//    EvaluatorFunction transformer = new EvaluatorFunction(projectionEvaluator);
//    assertEquals("Kitty", transformer.apply(c));
//                
//    }
}
