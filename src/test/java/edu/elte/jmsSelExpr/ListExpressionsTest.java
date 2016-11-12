/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.jmsSelExpr;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Xavier
 */
public class ListExpressionsTest extends AbstractExpressionTest {
    
    
    @Test 
    public void in(){
        Expression in=rd.country.in(str1);
        Assert.assertEquals(" country IN ('Belgium', 'Bulgaria', 'Croatia', 'Cyprus', 'Czech Republic')",serialize(in));
    }

    @Test 
    public void notIn(){
        Expression nin=rd.country.notIn(str1);
        Assert.assertEquals("country NOT IN ('Belgium', 'Bulgaria', 'Croatia', 'Cyprus', 'Czech Republic')",serialize(nin));
    }
    
    @Test 
    public void like(){
         Expression like=rd.country.like("Belgium");
        Assert.assertEquals("country LIKE Belgium", serialize(like));
    }
    @Test 
    public void notLike(){
        Expression nlike=rd.country.notLike("Croatia");
        Assert.assertEquals("NOT (country LIKE Croatia)", serialize(nlike));

    }
    @Test 
    public void between(){
        Expression bt=rd.budget.between(100,200);
        Assert.assertEquals("(budget  BETWEEN  100 AND 200)", serialize(bt));

    }
    @Test 
    public void notBetween(){
        Expression nbt=rd.budget.notBetween(500,600);
        Assert.assertEquals("NOT ((budget  BETWEEN  500 AND 600))", serialize(nbt));

    }
    
    
    @Test
    public void isNull(){
        Expression nul=rd.comments.isNull();
        Assert.assertEquals("comments IS NULL", serialize(nul));
    }
    
    @Test
    public void starWith(){
        
        Expression stw=rd.category.startsWith("sp");
        Assert.assertEquals("category LIKE sp%", serialize(stw));
    }
    
    @Test
    public void endWith(){
        Expression end=rd.category.endsWith("rt");
        Assert.assertEquals("category LIKE %rt", serialize(end));
    }
}
