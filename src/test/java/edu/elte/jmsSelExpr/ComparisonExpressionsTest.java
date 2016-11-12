/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.jmsSelExpr;

import com.querydsl.core.types.Expression;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Xavier
 */
public class ComparisonExpressionsTest extends AbstractExpressionTest {
    
    @Test
    public void gt(){
        Expression gt=rd.budget.gt(100);
        Assert.assertEquals("budget > 100", serialize(gt));
    }
    @Test
    public void lt(){
        Expression lt=rd.budget.lt(50);
        Assert.assertEquals("budget < 50", serialize(lt));
    } 
    
    @Test
    public void add(){
        Expression add=rd.budget.add(5);
        Assert.assertEquals("budget + 5", serialize(add));
    }
    @Test
    public void substract(){
        Expression sus=rd.budget.subtract(8);
        Assert.assertEquals("budget - 8", serialize(sus));
    }
    @Test
    public void multiply(){
        Expression mul=rd.budget.multiply(3);
        Assert.assertEquals("budget * 3", serialize(mul));
    }
    @Test
    public void divide(){
        Expression dv=rd.budget.divide(2);
        Assert.assertEquals("budget / 2", serialize(dv));
    }
    
            

    
}
